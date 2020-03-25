/**
 * Scala object to upload CSV files to Azure Blob storage (was part of my initial solution).
 * For the script to work, the following environment variables need to be set:
 * - HDI_STORAGE_ENDPOINT: Endpoint url to the Azure storage account coupled to the HDInsight clusters
 * - HDI_STORAGE_SAS_TOKEN: SAS token generated for the storage account, generated according to documentation here:
 *   https://github.com/Azure/azure-sdk-for-java/blob/master/sdk/storage/azure-storage-blob/README.md#sas-token
 * - HDI_BLOB_CONTAINER: Destination container inside Azure storage account.
 *
 */
package linkit_test_sebastiaan

import com.azure.storage.blob._

object UploadToBlobStorage extends App {
  // Main function to upload
  override def main(args: Array[String]): Unit = {
    // Define file paths to upload
    val sparkFiles = List("drivers.csv", "timesheet.csv", "truck_event_text_partition.csv")
    val hBaseFiles = List("dangerous-driver.csv", "extra-driver.csv")

    // Set up Blob clients
    val blobContainerClient: BlobContainerClient = new BlobContainerClientBuilder()
      .endpoint(sys.env("HDI_STORAGE_ENDPOINT"))
      .sasToken(sys.env("HDI_STORAGE_SAS_TOKEN"))
      .containerName(sys.env("HDI_BLOB_CONTAINER"))
      .buildClient()
    println("Uploading Spark files to Blob storage...")
    sparkFiles.foreach { file => uploadFileToBlob(s"data-spark/$file", blobContainerClient) }
    println("Uploading HBase files to Blob storage...")
    hBaseFiles.foreach { file => uploadFileToBlob(s"data-hbase/$file", blobContainerClient) }
    println("Uploads successful.")
  }

  /**
   * Helper function to upload a single file to Blob storage
   * @param path: Local path to file. Destination path in the Blob container will be equal to local path.
   * @param containerClient: BlobClient object pointing to desired Blob Container (set from BlobContainerClient)
   */
  def uploadFileToBlob(path: String, containerClient: BlobContainerClient): Unit = {
      val client = containerClient.getBlobClient(path)
      client.uploadFromFile(path, true)
  }
}