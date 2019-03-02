package cappi

import sbt._
import sbt.Keys._
import sbt.Defaults._

/** An sbt interface for the Google caliper microbenchmark tool */
object Plugin extends sbt.Plugin {
  import cappi.Keys._

  def cappiSettings = Seq(
    caliperOptions in cappi := Nil,
    caliperVersion in cappi := Some("0.5-rc1"),
    benchmarks in cappi := {
     val base = (scalaSource in Test).value
     (sources in Test).value.map {
        IO.relativize(base, _).getOrElse("").replace(java.io.File.separator,".").replace(".scala", "")
      }.filter(_.endsWith("Benchmark"))
    },
    libraryDependencies ++=
      (caliperVersion in cappi).value.map(cv => Seq("com.google.caliper" % "caliper" % cv % "test")).getOrElse(Nil),
    benchmark in cappi := benchmarkTaskInitTask.value((benchmarks in cappi).value),
    benchmarkOnly in cappi := benchmarkTaskInitTask.value(
      Def.spaceDelimited("<arg>").parsed
    )
  )

   // straight otta compton https://github.com/sbt/sbt/blob/9ea7da6de61aac90f9b52516e829da612817f459/main/src/main/scala/sbt/Defaults.scala#L405-L410

  private[this] def forkOptions: Def.Initialize[Task[ForkOptions]] =
    (fullClasspath in Test, baseDirectory, javaOptions, outputStrategy, envVars, javaHome, connectInput) map {
      (tcp, base, options, strategy, env, javaHomeDir, connectIn) =>
        // bootJars is empty by default because only jars on the user's classpath should be on the boot classpath
        val cp = "-classpath" :: Path.makeString(tcp.files) :: Nil
        ForkOptions(
          bootJars = Nil,
          javaHome = javaHomeDir,
          connectInput = connectIn,
          outputStrategy = strategy,
          runJVMOptions = options ++ cp,
          workingDirectory = Some(base),
          envVars = env)
    }

  private def benchmarkTaskInitTask: Def.Initialize[Task[Seq[String] => Unit]] = Def.task {
    val cpa = (fullClasspath in Test).value
    val forkOpts = forkOptions.value
    val out = streams.value
    val fr = new ForkRun(forkOpts)
    val opts = (caliperOptions in cappi).value
    ({ benchmarks: Seq[String] =>
      if (benchmarks.isEmpty) println("No benchmarks specified - nothing to run")
      else benchmarks.map( b => sbt.toError(fr.run("com.google.caliper.Runner",
                              Attributed.data(cpa), opts :+ b, out.log)))
    })
  }
}

