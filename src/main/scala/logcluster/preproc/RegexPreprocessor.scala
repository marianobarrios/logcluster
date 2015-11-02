package logcluster.preproc

import scala.util.matching.Regex
import logcluster.alg.LogEntry

trait RegexPreprocessor extends Preprocessor {

  val regex: Regex
  def apply(app: String, line: String) = regex.findFirstMatchIn(line).map(m => LogEntry(owner = app, line, m.group("msg")))

}