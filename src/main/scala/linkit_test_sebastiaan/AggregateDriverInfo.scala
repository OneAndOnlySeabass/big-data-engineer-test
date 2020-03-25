// Scala object to aggregate driver registered miles and hours
package linkit_test_sebastiaan

import org.apache.spark.sql.SparkSession

object AggregateDriverInfo extends App {
  override def main(args: Array[String]): Unit = {
    // Set up Spark session, set logging level to WARN
    val spark = SparkSession.builder()
      .master("local")
      .appName("blob-to-hive")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    // Load the CSV files from local as I did not get HDFS online
    val drivers = spark.read
      .options(Map(
        "inferSchema" -> "true",
        "header" -> "true"
      ))
      .csv("data-spark/drivers.csv")
      .selectExpr("driverId AS DRIVER_ID", "name AS NAME")
    val timesheet = spark.read
      .options(Map(
        "inferSchema" -> "true",
        "header" -> "true"
      ))
      .csv("data-spark/timesheet.csv")

    // Aggregate hours and miles per driver
    val timesheet_agg = timesheet.groupBy("driverId")
      .agg(Map(
        "hours-logged" -> "sum",
        "miles-logged" -> "sum"
      ))
      .withColumnRenamed("sum(hours-logged)", "HOURS_LOGGED")
      .withColumnRenamed("sum(miles-logged)", "MILES_LOGGED")
      .withColumnRenamed("driverId", "DRIVER_ID")

    // Join data and output the resulting data frame to console
    val joined = drivers.join(timesheet_agg, "DRIVER_ID")
        .sort("NAME")
    joined.show()
  }
}
