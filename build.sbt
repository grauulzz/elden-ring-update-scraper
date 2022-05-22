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
    "-unchecked"
  )

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
    "-Xfatal-warnings",
  ),
  Test / console / scalacOptions :=
    (Compile / console / scalacOptions).value,
)

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    "net.ruippeixotog" %% "scala-scraper" % "2.2.1",
    "org.scalatest" %% "scalatest" % "2.12.15" % "test"
  ),
  libraryDependencies ++= Seq(
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-15`,
  ).map(_ % Test),
)
