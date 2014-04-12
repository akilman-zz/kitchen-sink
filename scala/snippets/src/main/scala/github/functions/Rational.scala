package com.example.functions

class Rational(n:Int, d:Int)
{
  private def gcd(x:Int, y:Int): Int =
  {
    if (x==0) y
    else if (x<0) gcd(-x, y)
    else if (y<0) -gcd(x, -y)
    else gcd(y%x, x)
  }
  private val g = gcd(n,d)

  val numer:Int = n/g
  val denom:Int = d/g

  def +(that:Rational) =
    new Rational(numer*that.denom + that.numer*denom, denom * that.denom)
  def -(that:Rational) =
    new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
  def *(that:Rational) =
    new Rational(numer * that.numer, denom * that.denom)
  def /(that:Rational) =
    new Rational(numer * that.denom, denom * that.numer)

  override def toString() =
    "Rational: [" + numer + " / " + denom + "]"
}

object RunRational extends App
{
  val r1 = new Rational(1, 3)
  val r2 = new Rational(2, 5)
  val r3 = r1 - r2
  val r4 = r1 + r2
  Console.println("r1 = " + r1)
  Console.println("r2 = " + r2)
  Console.println("r3 = r1 - r2 = " + r3)
  Console.println("r4 = r1 + r2 = " + r4)
}