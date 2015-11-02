package logcluster.alg

import java.io.File
import java.io.PrintStream
import java.io.FileOutputStream
import logcluster.util.createDirOrCheckEmpty
import java.io.IOException
import scala.collection.mutable
import com.typesafe.scalalogging.StrictLogging

trait Reporter {
  def addToCluster(clusterId: String, entry: LogEntry)
  def finish() {}
}

class FileReporter(val title: String, val dir: File, append: Boolean = false) extends Reporter with StrictLogging {

  if (append)
    FileReporter.createDirIfNecessary(dir)
  else
    createDirOrCheckEmpty(dir)

  logger.info(s"Saving clusters in directory $dir")

  private val writers = mutable.HashMap[String, PrintStream]()

  override def addToCluster(clusterId: String, entry: LogEntry) {
    val writer = writers.get(clusterId) match {
      case Some(existing) => existing
      case None =>
        val newWriter = new PrintStream(new FileOutputStream(new File(dir, clusterId)), append)
        writers += clusterId -> newWriter
        newWriter
    }
    logger.debug(s"Writing line ${entry.original.mkString(",")}")
    writer.println(entry.original)
    writer.flush()
  }

  override def finish() { writers.values.foreach(_.close) }

}

object FileReporter {

  private def createDirIfNecessary(dir: File) {
    dir.mkdirs()
    val list = dir.list
    if (list == null)
      throw new IOException(s"Cannot create or access directory $dir");
  }

}