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
    val words = input.flatMap(x => x.split("\\W+"))

    // Normalize everything to lowercase
    val lowerCasesWords = words.map(x => x.toLowerCase())

    // Count of the occurrences of each word
    val wordCounts = lowerCasesWords.map(x => (x, 1)).reduceByKey((x, y) => x + y)


    // Flib (word, count) tuples to (count, word) and then sort by key (the counts)
    val wordCountsSorted = wordCounts.map(x => (x._2, x._1)).sortByKey()

    // Print the results
    //    wordCountsSorted.foreach(println)


    // Print the results
    for (result <- wordCountsSorted) {
      val count = result._1
      val word = result._2
      println(s"$word: $count")
    }

  }

}
