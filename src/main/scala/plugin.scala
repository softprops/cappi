package cappi

import sbt._
import sbt.Keys._
import sbt.Defaults._

// my attempt at getting https://github.com/alno/sbt-caliper working
// see also https://github.com/alno/sbt-caliper/issues/1

object Plugin extends sbt.Plugin {
  import cappi.Keys._

  val benchmarkTasks = Seq(
    benchmark := benchmarkTaskInitTask.value(
      (sources in Test).value map { _.base } filter { _.endsWith("Benchmark") }
    ),
    benchmarkOnly := benchmarkTaskInitTask.value(
      Def.spaceDelimited("<arg>").parsed
    )
  )

   // straight otta compton https://github.com/sbt/sbt/blob/9ea7da6de61aac90f9b52516e829da612817f459/main/src/main/scala/sbt/Defaults.scala#L405-L410
  private[this] def forkOptions: Def.Initialize[Task[ForkOptions]] =
    (baseDirectory, javaOptions, outputStrategy, envVars, javaHome, connectInput) map {
      (base, options, strategy, env, javaHomeDir, connectIn) =>
        // bootJars is empty by default because only jars on the user's classpath should be on the boot classpath
        ForkOptions(bootJars = Nil, javaHome = javaHomeDir, connectInput = connectIn, outputStrategy = strategy, runJVMOptions = options, workingDirectory = Some(base), envVars = env)
    }

  private def benchmarkTaskInitTask: Def.Initialize[Task[Seq[String] => Unit]] = Def.task {
    val cpa = (fullClasspath in Test).value
    val forkOpts = forkOptions.value
    val out = streams.value
    val fr = new ForkRun(forkOpts)
    ({ args: Seq[String] =>
      if (args.isEmpty) println("No benchmarks specified - nothing to run")
      else sbt.toError(fr.run("com.google.caliper.Runner", Build.data(cpa), args, out.log))
    })
  }
}

