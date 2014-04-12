package com.example.functional

import scala.language.postfixOps

object QuickSortRun {

  def sort(xs: Array[Int]): Array[Int] =

    if (xs.length <= 1) {
      xs
    } else {
      val pivot = xs(xs.length / 2)
      Array.concat(
        sort(xs filter (pivot >)),
        xs filter (pivot ==),
        sort(xs filter (pivot <)))
    }

  def main(args: Array[String]) {

    val toSort = Array(2,1,3)
    val sorted = sort(toSort)

    for (i <- sorted) {
      println(i)
    }

  }
}