# cappi

> The [sweetest](http://www.urbandictionary.com/define.php?term=cappi) [sbt](http://www.scala-sbt.org/) plugin your [microbenchmarks](https://code.google.com/p/caliper/wiki/JavaMicrobenchmarks) will ever [meet](https://code.google.com/p/caliper/).

Cappi runs Caliper benchmarks

## usage

Cappi will run your benchmarks but it needs to know what they are first. By default Cappi defines two tasks to run your bench marks `benchmark` and `benchmarkOnly` both scoped to the `cappi` task. 

### Resolving Benchmarks

In order to run `benchmarks` Cappi will take make the assumption that you've named your benchmark in a source file with a name ending with "Benchmark".
Cappi will look for this under your `src/main/test` directory. It makes the assumption that you are packaging your benchmarks in folders that mirror
the scala package they are under


For example the class `foo.BarBenchmark`

```scala
package foo
class BarBenchmark extends com.google.caliper.SimpleBenchmark {
...
}
```

would be located in a file named `src/test/scala/foo/Bar.scala`


You can see the full list of resolved benchmarks by running the following the the sbt REPL

```scala
cappi::benchmarks
```

Alternatively you can override the `benchmarks` setting in your build definition.

```
(cappi.Keys.benchmarks in cappi.Keys.cappi) := Seq("foo.BazBenchmark")
```

### Running Benchmarks

To run your benchmarks, simply run the following in the sbt REPL

```
cappi::benchmark
```

Alternatively you can run just a target benchmark with `benchmarkOnly` providing a fully qualified Benchmark name.

```
cappi::benchmarkOnly com.you.YourBenchmark
```

### Caliper on your classpath

To remove the need for you doing this yourself, Cappi will add caliper to your classpath.

To override the calpier version in use, 0.5-rc1, override the `caliperVersion in cappi` task

```scala
caliperVersion in cappi := "custom-version"
```

## props

This plugin continues on the path that [this plugin](https://github.com/alno/sbt-caliper) started.

Doug Tangren (softprops) 2013
