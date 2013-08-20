package cappi

import sbt.{ InputKey, TaskKey }

object Keys {
  val benchmark = TaskKey[Unit]("benchmark", "Executes all benchmarks.")
  val benchmarkOnly = InputKey[Unit]("benchmark-only", "Executes specified benchmarks.")
}
