package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  //  println(aFunction(a = "Hello", b = 5))

  def aRepetedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepetedFunction(aString, n - 1)
  }

  //  println(aRepetedFunction(aString = "Hello", n = 3))


  def concatedString(name: String, age: Int): String = {
    s"Hi may name is $name, and I am $age years old"
  }

  println(concatedString(name = "Dieinimy", age = 32))


  def factorial(n: Int): Int = {
    if (n == 0) 1
    else n * factorial(n - 1)
  }

  println(factorial(n = 5))


  def fibonacci(n: Int): Int = {
    if(n <= 2 ) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  println(fibonacci(8))
  println(fibonacci(9))
  println(fibonacci(9))




}
