# cappi

The [sweetest](http://www.urbandictionary.com/define.php?term=cappi) [sbt](http://www.scala-sbt.org/) plugin your [microbenchmarks](https://code.google.com/p/caliper/wiki/JavaMicrobenchmarks) will ever [meet](https://code.google.com/p/caliper/).


## usage

```
cappi::benchmark
```

```
cappi::benchmarkOnly com.you.MyBenchmark
```

To override the calpier version in use (0.5-rc1). Override the `caliperVersion in cappi` task

```scala
caliperVersion in cappi := Def.task { "custom-version" }
```

## props

This plugin continues on the path that [this plugin](https://github.com/alno/sbt-caliper) started.

Doug Tangren (softprops) 2013
