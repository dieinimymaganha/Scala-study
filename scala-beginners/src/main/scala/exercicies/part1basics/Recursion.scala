package exercicies.part1basics

import scala.annotation.tailrec

object Recursion extends App {


  @tailrec
  def concatenateTailrec(aString: String, n: Int, accumulator: String): String = {
    if (n <= 0) accumulator
    else concatenateTailrec(aString, n - 1, aString + accumulator)
  }

  println(concatenateTailrec("Hello", 5, ""))


  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailRec(t - 1, n % t != 0 && isStillPrime)
    }

    isPrimeTailRec(n / 2, true)
  }

  println(isPrime(1))
  println(isPrime(2))
  println(isPrime(3))
  println(isPrime(4))


  def fibonacci(n: Int): Int = {
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if(i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last )

    if (n <= 2 ) 1
    else fiboTailrec(2, 1, 1 )

  }

  println(fibonacci(8))


}
