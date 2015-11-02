package logcluster.alg

import java.io.File
import java.io.PrintStream
import java.io.FileOutputStream
import logcluster.util.createDirOrCheckEmpty
import java.io.IOException
import scala.collection.mutable
import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime
import logcluster.persistence.ErrorPersister._

case class CassReporter(application: String) extends Reporter with StrictLogging {

  override def addToCluster(clusterId: String, entry: LogEntry) {
	  persist(entry.owner, clusterId, entry.original)
  }

}
