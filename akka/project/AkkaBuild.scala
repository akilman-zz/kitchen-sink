import sbt._
import sbt.Keys._

object AkkaBuild extends Build {

  lazy val akka = Project(
    id = "akka",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "akka",
      organization := "akka",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.4",

      libraryDependencies += "com.typesafe.akka" % "akka-actor_2.10" % "2.3.3"
  )
  )
}
