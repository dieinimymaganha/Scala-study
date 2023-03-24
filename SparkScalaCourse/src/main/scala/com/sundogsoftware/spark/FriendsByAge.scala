package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.log4j._

/** Compute the average number of friends by age in a social network. */
object FriendsByAge {

  /** A Function that splits a line of input into (age, numFriends) tuples. */
  def parseLine(line: String): (Int, Int) = {
    // Split by commas
    val fields = line.split(",")
    val age = fields(2).toInt

    val numFriends = fields(3).toInt
    (age, numFriends)
  }


  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print erros
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "FriendsByAge")

    // Load each line of the source data into an RDD
    val lines = sc.textFile("data/fakefriends-noheader.csv")

    // User or parselines function to convert to (age, numFriends) tuples
    val rdd = lines.map(parseLine)


    // adding together all the numFriends values and 1's respectively.
    val totalsByAge = rdd.mapValues(x => (x, 1)).reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))

    // So now we have tuples of (age, (totalFriends, totalInstances))
    // To compute the average we divide totalFriends / totalInstances for each age.
    val averageByAge = totalsByAge.mapValues(x => x._1 / x._2)


    // Collect the result from the RDD (This kicks off computing the Dag and actually executes job)
    val results = averageByAge.collect()

    results.sorted.foreach(println)


  }
}
