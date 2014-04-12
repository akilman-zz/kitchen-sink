package com.example.exceptions

object Application {

  /**
   * Dummy code which always throws an exception
   */
  def generateException() {
	  println("Generating exception...")
	  throw new Exception("Generated exception")
  }
  
  def main(args: Array[String]) {
    tryWithLogging {
      generateException
    }
    println("Exiting main...")
   
  }
  
  /**
   * Drop-in for a try keyword. The thinking is it's comparable to AspectJ
   */
  def tryWithLogging(s: => Unit) {
    try {
      s
    } catch {
      case ex: Exception =>
      /*
       * Logging to console for now 
       */
        ex.printStackTrace
    }
  }
  
}