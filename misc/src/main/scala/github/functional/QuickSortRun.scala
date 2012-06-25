package com.example.functional

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
    /*
     * TODO: How can I assign array values in one shot?
     */
    val toSort = new Array[Int](3)
    toSort(0) = 2 // 2, 1, 3
    toSort(1) = 1
    toSort(2) = 3
    val sorted = sort(toSort)

    for (i <- sorted) { 	
      println(i)
    }
    
  }
}