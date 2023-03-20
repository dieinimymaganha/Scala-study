package lectures.part2oop

object Exceptions extends App {

  //  val x : String = null

  //  println(x.length)
  // This ^^ will crash with a NPE

  // throwing and catching exceptions

  //  val aWeirdValue: String = throw new NullPointerException

  // Throwable classes extend the Throwable class.
  // Exception and Error are the Major Throwable subtypes


  def getInt(wihExceptions: Boolean): Int =
    if (wihExceptions) throw new RuntimeException("No int for you")
    else 42


  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code thata will get executed no Matter What
    // optional
    // does not influence the return type this expression
    // use finally only for side effects
    println("finally")
  }
  println(potentialFail)

  // 3. how to define your own exceptions

  //  class MyException extends Exception
  //
  //  val exception = new MyException
  //
  //  throw exception


  // OOM
  //  val array = Array.ofDim(Int.MaxValue)

  // SO
  //  def infinte: Int = 1 + infinte
  //  val noLimit = infinte


  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException

  object PocketCalculator {

    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
  }

  println(PocketCalculator.add(Int.MaxValue, 10))


}
