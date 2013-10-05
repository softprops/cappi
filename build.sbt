organization := "me.lessis"

name := "cappi"

version := "0.1.2-SNAPSHOT"

description := "a sweetest benchmarking plugin you will ever meet"

scalacOptions ++= Seq(Opts.compile.deprecation, "-feature")

sbtPlugin := true

licenses := Seq(
  ("MIT", url("https://github.com/softprops/%s/blob/%s/LICENSE"
              .format(name.value, version.value))))

publishArtifact in Test := false

seq(bintraySettings:_*)

bintray.Keys.packageLabels in bintray.Keys.bintray := Seq("microbenchmark", "benchmark", "caliper")

seq(lsSettings:_*)

(LsKeys.tags in LsKeys.lsync) := Seq("microbenchmark", "benchmark", "caliper")

(externalResolvers in LsKeys.lsync) := (resolvers in bintray.Keys.bintray).value
