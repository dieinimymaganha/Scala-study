package com.sundogsoftware.spark

import org.apache.log4j._
import org.apache.spark.sql._
import org.apache.spark.sql.functions.{col, udf}
import org.apache.spark.sql.types.{IntegerType, LongType, StructType}

import scala.io.{Codec, Source}

/** Find the movies with the most ratings */
object PopularMoviesDataset {

  // Case class so we can get a column name for our movie ID
  case class Movies(userID: Int, movieID: Int, rating: Int, timesTamp: Long)


  def loadMovieNames(): Map[Int, String] = {
    // Handle character enconding issues
    implicit val codec: Codec = Codec("ISO-8859-1")

    var movieNames: Map[Int, String] = Map()
    val lines = Source.fromFile("data/ml-100k/u.item")
    for (line <- lines.getLines()) {
      val fields = line.split('|')
      if (fields.length > 1) {
        movieNames += (fields(0).toInt -> fields(1))
      }
    }
    lines.close()
    movieNames
  }

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print erros
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Use new SparkSession interface in Spark 2.0
    val spark = SparkSession
      .builder
      .appName("PopularMovies")
      .master("local[*]")
      .getOrCreate()

    // Create schema when reading u.data
    val moviesSchema = new StructType()
      .add("userID", IntegerType, nullable = true)
      .add("movieID", IntegerType, nullable = true)
      .add("rating", IntegerType, nullable = true)
      .add("timestamp", LongType, nullable = true)


    val nameDict = spark.sparkContext.broadcast(loadMovieNames())

    import spark.implicits._

    // Load up movie data as dataset
    val moviesDS = spark.read
      .option("sep", "\t")
      .schema(moviesSchema)
      .csv("data/ml-100k/u.data")
      .as[Movies]

    // Some SQL-style magic to sort all movies by popularity in one line!
    val moviesCounts = moviesDS.groupBy("movieID").count()

    // Create a user-defined function to look up movie names from our
    // shared Map variable

    // We start by declaring an "anonymous function" in Scala
    val lookupName: Int => String = (movieID: Int) => {
      nameDict.value(movieID)
    }

    // Then wrap it with a udf
    val lookupNameUDF = udf(lookupName)

    // Add a movieTitle column using our new udf
    val moviesWithNames = moviesCounts.withColumn("movieTitle", lookupNameUDF(col("movieId")))


    // Sort the results

    val sortedMoviesWithNames = moviesWithNames.sort("count")

    // Show the results without truncating it
    sortedMoviesWithNames.show(sortedMoviesWithNames.count.toInt, truncate = false)

    spark.stop()


  }

}
