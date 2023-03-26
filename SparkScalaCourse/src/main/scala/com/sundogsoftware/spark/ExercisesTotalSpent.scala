package com.sundogsoftware.spark

import org.apache.log4j._
import org.apache.spark._

object ExercisesTotalSpent {

  def parseLine(line: String): (Int, Double) = {
    val fields = line.split(",")
    val customer = fields(0).toInt
    val spent = fields(2).toDouble
    (customer, spent)
  }

  def main(args: Array[String]) {

    // Set the log level  to only print errors.
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine, named RatingCounter
    val sc = new SparkContext("local[*]", "RatingsCounter")


    // Load up each line of the ratings data into an RDD
    val lines = sc.textFile("data/customer-orders.csv")


    val values = lines.map(parseLine)


    val sumByKey = values.groupBy(_._1).mapValues(_.map(_._2).sum)

    for (result <- sumByKey) {
      val customer = result._1
      val value = result._2
      val formattedValue = f"$value%.2f"
      println(s"Customer: $customer | Value: $formattedValue")
    }


  }
}
