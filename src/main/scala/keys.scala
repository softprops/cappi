package cappi

import sbt.{ InputKey, SettingKey, TaskKey }

object Keys {
  val cappi = TaskKey[String]("cappi", "cappi is a front end for the caliper microbenchmarking toolkit")
  val caliperVersion = SettingKey[String]("caliper-version", "Version of caliper to depend on in test. Default is (0.5-rc1)")
  val benchmark = TaskKey[Unit]("benchmark", "Executes all benchmarks.")
  val benchmarkOnly = InputKey[Unit]("benchmark-only", "Executes specified benchmarks.")
}
