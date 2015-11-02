package logcluster.preproc
import logcluster.alg.LogEntry

trait Preprocessor {
  def apply(app: String, line: String): Option[LogEntry]
}