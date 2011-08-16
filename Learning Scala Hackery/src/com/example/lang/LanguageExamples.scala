package com.example.lang

object LanguageExamples {

  /**
   * Interesting example of defining a while keyword 
   */
  def While(p: => Boolean)(s: => Unit) {
    if (p) { 
      s; 
      While(p)(s) 
      }
  }

  def main(args: Array[String]) {

    var i = 0
    While(i < 5) {
      println("Hello")
      i = i + 1
    }

    var j = 0
    while (j < 5) {
      println("Hi")
      j = j + 1
    }
  }

}