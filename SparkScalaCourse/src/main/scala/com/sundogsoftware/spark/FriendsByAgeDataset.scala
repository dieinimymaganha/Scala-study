package com.sundogsoftware.spark

import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object FriendsByAgeDataset {

  case class FakeFriends(id: Int, name: String, age: Int, friends: Long)

  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession
      .builder
      .appName("FriendsByAge")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    val ds = spark
      .read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[FakeFriends]

    // Select only age and numFriends columns
    val friendsByAge = ds.select("age", "friends")

    // From friendsByAge we group by "age" and then compute average
    friendsByAge.groupBy("age").avg("friends").show()

    // sorted:
    friendsByAge.groupBy("age").avg("friends").sort("age").show()

    // Formatted more nicely:
    friendsByAge.groupBy("age").agg(round(avg("friends"), 2))
      .sort("age").show()


    // With a custom column name:
    friendsByAge.groupBy("age").agg(round(avg("friends"), 2))
      .alias("friends_avg").sort("age").show()


  }

}
