package logcluster.preproc

import logcluster.alg.LogEntry

object GenericSensitivePreprocessor extends Preprocessor {

  val regex = "[ ;:]ERROR[ ;:]".r
  def apply(app: String, line: String) = regex.findFirstMatchIn(line).map(m => LogEntry(owner = app, original = line, msg = line))

}