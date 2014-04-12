/**
 * 
 */
package com.example

/**
 * @author akilman
 *
 */
object TimerAnonFunctionExample {
  
  def oncePerSecond(callback: () => Unit) {
    while (true) {
      callback()
      Thread sleep 1000
    }
  }

  def main(args: Array[String]): Unit = {  
    oncePerSecond(() =>
      println("time flies like an arrow..."))
  }

}