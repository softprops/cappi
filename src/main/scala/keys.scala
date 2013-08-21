package cappi

import sbt.{ InputKey, SettingKey, TaskKey }

object Keys {
  val cappi = TaskKey[String]("cappi", "cappi is a front end for the caliper microbenchmarking toolkit")
  val caliperVersion = SettingKey[Option[String]]("caliper-version", "Version of caliper to depend on in test. Default is Some(0.5-rc1)")
  val benchmark = TaskKey[Unit]("benchmark", "Executes all benchmarks.")
  val benchmarkOnly = InputKey[Unit]("benchmark-only", "Executes specified benchmarks.")
  val benchmarks = TaskKey[Seq[String]](
    "benchmarks", "Seq of class names to benchmark. By default all class names are based on items in the Test classpath with names that end with 'Benchmark'")
}
