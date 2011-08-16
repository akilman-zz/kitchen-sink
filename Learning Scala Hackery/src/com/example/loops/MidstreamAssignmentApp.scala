package com.example.loops

object MidstreamAssignmentApp {

  def main(args: Array[String]) =
    {
      // Note the array-initialization syntax; the type (Array[String])
      // is inferred from the initialized elements
      val names = Array(
        "Ted Neward", "Neal Ford", "Scott Davis", "Venkat Subramaniam", "David Geary")

      for {
        name <- names
        firstName = name.substring(0, name.indexOf(' '))
      } println("Found " + firstName)
    }
}
