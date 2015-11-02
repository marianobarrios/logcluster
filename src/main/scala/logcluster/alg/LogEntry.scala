package logcluster.alg

import java.util.regex.Pattern

case class LogEntry(owner: String, original: String, msg: String, tokens: IndexedSeq[String])

object LogEntry {

  val pattern = Pattern.compile("""[ ,/\\=\[\]():{};"'?&]+""")

  def apply(owner: String, original: String, msg: String) =
    new LogEntry(owner, original, msg, pattern.split(msg).filterNot(_.isEmpty).map(_.intern))

}