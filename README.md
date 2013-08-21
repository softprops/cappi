# cappi

> The [sweetest](http://www.urbandictionary.com/define.php?term=cappi) [sbt](http://www.scala-sbt.org/) plugin your [microbenchmarks](https://code.google.com/p/caliper/wiki/JavaMicrobenchmarks) will ever meet.

Cappi runs [Caliper](https://code.google.com/p/caliper/) benchmarks

## Install

This project targets sbt version [0.13](http://www.scala-sbt.org/0.13.0/docs/home.html). If you should want 0.12 support [drop me an issue](https://github.com/softprops/cappi/issues/new?title=add%20support%20for%20sbt%200.12)

To install Cappi in your sbt project, add the following to your project's plugin configuration, typically in a `project/plugins.sbt` file.

```scala
resolvers += Resolver.url(
  "bintray-sbt-plugin-releases",
    url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(
      Resolver.ivyStylePatterns)

addSbtPlugin("me.lessis" % "cappi" % "0.1.0")
```

Then mix `cappiSettings` into your build defintion, typically in a `build.sbt` file.

```scala
seq(cappiSettings:_*)
```

## Usage

Cappi will run your benchmarks, but it needs to know _what_ they are first. By default, Cappi defines two tasks to run your bench marks `benchmark` and `benchmarkOnly` both scoped to the `cappi` task. 

### Resolving Benchmarks

In order to run `benchmarks` Cappi makes the assumption that you've named your benchmark in a source file with a name ending with "Benchmark.scala".
Cappi will look for this under your `src/test/scala` directory. It makes the assumption that you are packaging your benchmarks in folders that mirror
the scala package they are under.

For example, the class `foo.BarBenchmark`

```scala
package foo
class BarBenchmark extends com.google.caliper.SimpleBenchmark {
 // ...
}
```

would be expected to be located in a file named `src/test/scala/foo/BarBenchmark.scala`

You can see the full list of resolved benchmark class names by running the following the the sbt REPL

```scala
show cappi::benchmarks
```

Alternatively, you can override the `benchmarks` setting in your build definition

```scala
(cappi.Keys.benchmarks in cappi.Keys.cappi) := Seq("foo.BazBenchmark")
```

### Running Benchmarks

To run your benchmarks, simply run the following in the sbt REPL. This will run all benchmarks.

```scala
cappi::benchmark
```

Alternatively you can run just a target benchmark with `benchmarkOnly` providing a fully qualified Benchmark class name.

```scala
cappi::benchmarkOnly com.you.YourBenchmark
```

### Caliper on your classpath

To run benchmarks, you need to first write benchmarks. To write benchmarks you need caliper on your classpath.
To remove the need for you doing this yourself, Cappi will add caliper to your _test_ classpath. This make getting started
a much smoother process.

To override the calpier version in use, "0.5-rc1" by default, override the `caliperVersion` setting.

```scala
caliperVersion in cappi := Some("custom-version")
```

If you do not which to have Cappi add this to your test classpath for some reason, say your benchmarks are not actually under
your test class path. Set the `caliperVersion` setting to `None`

```scala
caliperVersion in cappi := None
```

## props

This plugin continues on the path that [this plugin](https://github.com/alno/sbt-caliper) started.

Doug Tangren (softprops) 2013
