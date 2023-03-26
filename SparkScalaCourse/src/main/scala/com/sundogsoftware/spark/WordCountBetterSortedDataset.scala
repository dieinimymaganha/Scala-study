package com.sundogsoftware.spark

import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{explode, lower, split, to_date}

object WordCountBetterSortedDataset {

  case class Book(value: String)

  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession
      .builder
      .appName("WordCount")
      .master("local[*]")
      .getOrCreate()

    // Read each line of my book into an Dataset
    import spark.implicits._
    val input = spark.read.text("data/book.txt").as[Book]

    // Split using a regular expression that extracts words
    val words = input.select(explode(split($"value", "\\W+")).alias("word")).filter(
      $"word" =!= "")

    // Normalize everything to lowercase
    val lowercaseWords = words.select(lower($"word").alias("word"))

    // Count up the occurrences of each word
    val wordCounts = lowercaseWords.groupBy("word").count()

    // Sort by counts
    val wordCountsSorted = wordCounts.sort("count")

    // Show the results
    wordCountsSorted.show(wordCountsSorted.count.toInt)

    
  }

}
