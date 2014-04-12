object TypeMatch {
  
  def main(args: Array[String]) {
    
    def describe(x: Any) = x match {
      case 5 => 
        "five"
      case true => 
        "truth"
      case "hello" => 
        "hi!"
      case Nil => 
        "the empty list"
      case _ => 
        "Something else"	
    }
    
    println(describe(5))
    println(describe("hello"))
  }
}