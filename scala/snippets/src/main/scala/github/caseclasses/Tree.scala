package com.example.caseclasses

abstract class Tree {
  
  case class Sum(l: Tree, r: Tree) extends Tree
  case class Var(n: String) extends Tree
  case class Const(v: Int) extends Tree
  
  type Environment = String => Int
  
  def eval(t: Tree, env: Environment): Int = t match {
    case Sum(l, r) 	=> eval(l, env) + eval(r, env)
    case Var(n)		=> env(n)
    case Const(v)	=> v
  }
  
  def main(args: Array[String]): Unit = {
    def tree = Sum(Var("x"),Const(1))
    
    eval(tree, {case "x" => 5})
  }
  
}