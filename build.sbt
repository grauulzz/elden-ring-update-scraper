import Dependencies._

ThisBuild / organization := "org.grauulzz"
ThisBuild / scalaVersion := "3.1.0"
//ThisBuild / crossScalaVersions := Seq("2.12.15", "2.13.8")

ThisBuild / scalacOptions ++=
  Seq(
    "-deprecation",
    "-feature",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-unchecked",
    "-Xfatal-warnings",
    "-Yexplicit-nulls",
    "-Ykind-projector",
    "-Ysafe-init"
  ) ++ Seq("-rewrite", "-noindent") ++ Seq("-source", "future")

lazy val commonScalacOptions = Seq(
  Compile / console / scalacOptions --= Seq(
    "-Wunused:_",
    "-Xfatal-warnings"
  ),
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value
)

lazy val commonSettings = commonScalacOptions ++ Seq(
  update / evictionWarningOptions := EvictionWarningOptions.empty
)

lazy val `elden-ring-update-scraper` =
  project
    .in(file("."))
    .settings(name := "elden-ring-update-scraper")
    .settings(commonSettings)
    .settings(dependencies)

lazy val dependencies = Seq(
  // src dependencies
  // check otu io.micrometer.core.aysnc and // "io.micronaut" % "micronaut-inject" % "3.4.3",
  libraryDependencies ++= Seq(
    ("net.ruippeixotog" %% "scala-scraper" % "2.2.1")
      .cross(CrossVersion.for3Use2_13),
    "com.google.code.gson" % "gson" % "2.9.0",
    "io.micronaut.build" % "micronaut-maven-plugin" % "3.3.0",
    "info.picocli" % "picocli-codegen" % "4.6.3",
    "io.micronaut" % "micronaut-http-validation" % "3.4.3",
    "io.micronaut.picocli" % "micronaut-picocli" % "4.1.0",
    "io.micronaut" % "micronaut-http-client" % "3.4.3",
    "io.micronaut" % "micronaut-jackson-databind" % "3.4.3",
    "io.micronaut" % "runtime" % "1.0.0.M4",
    "io.micronaut.configuration" % "micronaut-picocli" % "1.2.1",
    "jakarta.annotation" % "jakarta.annotation-api" % "2.1.0",
    "ch.qos.logback" % "logback-classic" % "1.2.11",
    "org.graalvm.nativeimage" % "svm" % "22.1.0.1"
  ),
  // test dependencies
  libraryDependencies ++= Seq(
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-15`
  ).map(_ % Test)
)
