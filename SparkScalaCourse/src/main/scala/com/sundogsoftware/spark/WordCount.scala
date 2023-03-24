package com.sundogsoftware.spark

import org.apache.log4j._
import org.apache.spark._

object WordCount {

  def main(args: Array[String]) {
    // Set the log level  to only print errors.
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine, named RatingCounter
    val sc = new SparkContext("local[*]", "WordCount")

    // Read each line of my book int an RDD
    val input = sc.textFile("data/book.txt")

    // Split into words separated by a space character
    val words = input.flatMap(x => x.split(" "))

    // Count up the occurrences of each word
    val wordCounts = words.countByValue()

    // Print the results
    wordCounts.foreach(println)


  }

}
