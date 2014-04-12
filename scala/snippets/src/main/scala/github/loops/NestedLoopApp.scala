package com.example.loops

object NestedLoopApp {
  
  def grep(pattern: String, dir: java.io.File) =
    {
      val filesHere = dir.listFiles
      for (
        file <- filesHere;
        if (file.getName.endsWith(".scala") || file.getName.endsWith(".java"));
        line <- scala.io.Source.fromFile(file).getLines;
        if line.trim.matches(pattern)
      ) println(line)
    }

  def main(args: Array[String]) =
    {
      val pattern = ".*object.*"

      grep(pattern, new java.io.File("."))
    }

}