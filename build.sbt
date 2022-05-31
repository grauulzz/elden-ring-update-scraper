import Dependencies._

ThisBuild / organization := "org.grauulzz"
ThisBuild / scalaVersion := "2.12.15"
ThisBuild / crossScalaVersions := Seq("2.12.15", "2.13.8")

ThisBuild / scalacOptions ++=
  Seq(
    "-deprecation",
    "-feature",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-unchecked",
    "-Xfatal-warnings",
    "-Ykind-projector"
  ) ++ Seq("-rewrite", "-indent") ++ Seq("-source", "future")

lazy val `elden-ring-update-scraper` =
  project
    .in(file("."))
    .settings(name := "elden-ring-update-scraper")
    .settings(commonSettings)
    .settings(dependencies)

lazy val commonSettings = commonScalacOptions ++ Seq(
  update / evictionWarningOptions := EvictionWarningOptions.empty
)

lazy val commonScalacOptions = Seq(
  Compile / console / scalacOptions --= Seq(
    "-Wunused:_",
    "-Xfatal-warnings"
  ),
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value
)

lazy val dependencies = Seq(

  // src dependencies
  // check otu io.micrometer.core.aysnc
  libraryDependencies ++= Seq(
    "net.ruippeixotog" %% "scala-scraper" % "2.2.1",
    "com.google.code.gson" % "gson" % "2.9.0",
    "io.micronaut.build" % "micronaut-maven-plugin" % "3.3.0",

    // https://mavenlibs.com/maven/dependency/info.picocli/picocli-codegen
    "info.picocli" % "picocli-codegen" % "4.6.3",
    // https://mavenlibs.com/maven/dependency/io.micronaut/micronaut-http-validation
    "io.micronaut" % "micronaut-http-validation" % "3.4.3",
    // https://mavenlibs.com/maven/dependency/io.micronaut.picocli/micronaut-picocli
    "io.micronaut.picocli" % "micronaut-picocli" % "4.1.0",
    // https://mavenlibs.com/maven/dependency/io.micronaut/micronaut-http-client
    "io.micronaut" % "micronaut-http-client" % "3.4.3",
    // https://mavenlibs.com/maven/dependency/io.micronaut/micronaut-jackson-databind
    "io.micronaut" % "micronaut-jackson-databind" % "3.4.3",
    // https://mavenlibs.com/maven/dependency/io.micronaut/runtime
    "io.micronaut" % "runtime" % "1.0.0.M4",
    // https://mavenlibs.com/maven/dependency/io.micronaut.configuration/micronaut-picocli
    "io.micronaut.configuration" % "micronaut-picocli" % "1.2.1",
    // https://mavenlibs.com/maven/dependency/jakarta.annotation/jakarta.annotation-api
    "jakarta.annotation" % "jakarta.annotation-api" % "2.1.0",
    // https://mavenlibs.com/maven/dependency/ch.qos.logback/logback-classic
    "ch.qos.logback" % "logback-classic" % "1.2.11",
    // https://mavenlibs.com/maven/dependency/org.graalvm.nativeimage/svm
    "org.graalvm.nativeimage" % "svm" % "22.1.0.1",
    // https://mavenlibs.com/maven/dependency/io.micronaut/micronaut-inject
    // "io.micronaut" % "micronaut-inject" % "3.4.3",
  ),

  // test dependencies
  libraryDependencies ++= Seq(
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-15`,
    "com.github.sbt" % "junit-interface" % "0.13.3",
    "org.scalatest" %% "scalatest" % "3.2.12"
  ).map(_ % Test)
)
