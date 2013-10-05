package cappi

object Opts {
  def params(kvs: (String, String)*) = "-D" :: kvs.map { case (k,v) => "%s=%s".format(k,v) }.mkString(",") :: Nil
  def jvmOptions(kvs: (String, String)*) = "-J" :: kvs.map { case (k,v) => "%s=%s".format(k,v) }.mkString(",") :: Nil
  def warmupMillis(time: Long) = "--warmupMillis" :: time.toString :: Nil
  def runMillis(time: Long) = "--runMillis" :: time.toString :: Nil
  def captureVmLog = "--captureVmLog" :: Nil
  def measureMemory = "--measureMemory" :: Nil
  def vm(m: String) = "--vm" :: m :: Nil
  def timeUnit(unit: String) = "--timeUnit" :: unit :: Nil
  def instanceUnit(unit: String) = "--instanceUnit" :: unit :: Nil
  def memoryUnit(unit: String) = "--memoryUnit" :: unit :: Nil
  def saveResults(to: String) = "--saveResults" :: to :: Nil
  def printScore = "--printScore" :: Nil
  def uploadResults = "--uploadResults" :: Nil
  def debug = "--debug" :: Nil
  def debugReps = "--debug-reps" :: Nil
  def measurementType(typ: String) = "--measurementType" :: typ :: Nil
  def primaryMeasurementType(typ: String) = "--primaryMeasurementType" :: typ :: Nil
}
