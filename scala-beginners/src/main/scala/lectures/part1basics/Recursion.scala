package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {


  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST expression
    }

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))


}
