package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val doubler: Int => Int =   (x: Int) => x * 2
  println(doubler(2))

  // multiple params in a lambda
  val add: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no paramns

  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curly braces with lambdas
  val stringToInt = {(str: String) => str.toInt}

  // MOAR syntactic sugar
  val niceIncrement: Int => Int =  _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  val superAdder = (x: Int) => (y: Int) => x + y

  println(superAdder(3)(4))



}
