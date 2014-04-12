object Match {

  def main(args: Array[String]) {
    for (arg <- args)
      arg match {
        case "Java" => println("Java")
        case "Scala" => println("Scala")
        case "Ruby" => println("Ruby")
        case _ => println("wtf")
      }

  }

}