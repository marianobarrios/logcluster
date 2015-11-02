package logcluster.preproc
import logcluster.alg.LogEntry

object GenericPreprocessor extends Preprocessor {

  val regex = "[ ;:]E(RROR|rror)[ ;:]".r
  def apply(app: String, line: String) = regex.findFirstMatchIn(line).map(m => LogEntry(owner = app, original = line, msg = line))

}
