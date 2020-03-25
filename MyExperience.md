#My experience and further notes
For the assignments, I have tried two different approaches. The first was with Azure HDInsight, which was unsuccessful on my small trial subscription.
The second attempt was to get the tools working locally, which was also largely unsuccessful due to complications installing Hadoop on Windows.

## 1. HDInsight attempt
My idea was to use Azure HDInsight, using small cluster setups to get the job done on trial credit.
I had two main reasons for doing this:
1. HDInsight has all the tools for the assignment (Spark, Hive, HBase and Kafka) in one place
2. I mainly use the Azure cloud platform at my current job, so I am more familiar with it compared to on-premise or other cloud platforms.

For storage of the CSV files, I used an Azure storage account. The Scala object UploadToBlobStorage
uploads the CSV files to Azure Blob storage.

My Azure HDInsight environment would consist components, all on the same virtual network to enable data transfer between the clusters:
* Spark cluster
* Interactive query cluster (Hive LLAP)
* HBase cluster

However, on my attempt to set this up, I struggled with the large cluster size needed, and I could not get the Spark and Hive LLAP clusters
running simultaneously due to exceeding the limit or cores on my trial subscription, even in the smallest possible setup (40 cores). I truly under-estimated how many compute
would be needed. This lead me to abandon my attempt at running this through HDInsight.

## 2. Local installation
On my local device, only Spark was installed before the assignment started. The rest was not there yet.
Complicating things further, this was a Windows machine. I considered making my device dual-boot, but figured that would
cost me more time than the extra Windows hassle, as time was quickly running out by then. However, the complications from installing
Hadoop on Windows lead to me running out of time eventually.

However, as Spark was already installed on my local machine, mostly for unit testing at my current job, I could still get this done
with the time I had left on Wednesday evening. The result is in the Scala object AggregateDriverInfo

## My experience with the assignment
On general note on my experience is that the assignment was very challenging for me. This was for the following reasons:
1. As my main language for general programming is Python/PySpark and only having used Scala in a handful of situations,
    getting my Scala scripts to work was a challenge sometimes.
2. While I have used many tools in the Apache Stack indirectly, most of these were on a managed platform, 
    mainly on Databricks, and Azure Event Hubs as a Kafka-like platform. Setting up and managing all components separately
    added an extra challenge for me.
3. The only tool I ever installed locally was Spark itself.

Especially because of the small datasets and the large clusters I had to set up, it felt a bit like eating a salad with a pitchfork.
It's possible, but a normal fork would be more suitable for the small datasets. This would for me be using 
Python's pandas (instead of Spark), a small SQL database (instead of Hive) and another NoSQL tool suitable for small data.

This assignment definitely taught me some lessons on the complexity of a big data environment, and how many things applications like Databricks
actually take out of your hands. I regret that I only got the Spark part to work in the end.
But I am also happy to take that lesson to heart, and hope to get better on the infrastructure side of big data as well.
