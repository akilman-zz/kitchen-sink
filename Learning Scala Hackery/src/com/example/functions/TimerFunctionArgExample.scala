package com.example

/**
 * Basic version of passing a function as an argument
 * 
 * @author akilman
 */
object TimerFunctionArgExample {
  
  def oncePerSecond(callback: () => Unit) {
    while (true) {
      callback()
      Thread sleep 1000
    }
  }
  
  def timeFlies() {
    println("time files like an arrow...")
  }

  def main(args: Array[String]): Unit = {  
    oncePerSecond(timeFlies)
  }

}