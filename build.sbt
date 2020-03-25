libraryDependencies ++= Seq(
  "com.azure" % "azure-storage-blob" % "12.5.0",
  "org.apache.spark" %% "spark-core" % "2.4.3",
  "org.apache.spark" %% "spark-sql" % "2.4.3",
  "org.apache.spark" %% "spark-streaming" % "2.4.3"
)
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.7"