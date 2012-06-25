package com.example.loops

object Application {
  
  def basicForLoop() {
    for (i <- 6 to 10) 
      println("Counting " + i)    
  }
  
  /*
   * XXX: According to DeveloperWorks series this is possible; compiler says otherwise...
   */
//  def filteredForLoop() {
//    for (i <- 1 to 10; i % 2 == 0)
//      System.out.println("Counting " + i)
//  }
//
  
  def main(args: Array[String]) {
    
	  basicForLoop()
    
  }

}