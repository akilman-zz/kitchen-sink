name := "snippets"

version := "0.1"

scalaVersion := "2.10.4"

// Output compiler warnings by default
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies += "junit" % "junit" % "4.11"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.1.2"

libraryDependencies += "org.scala-tools.testing" % "specs_2.10" % "1.6.9"
