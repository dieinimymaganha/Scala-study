package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2
  println(x)

  println(2 + 3 * 4)
  // + - * / & | ^ << >> >>> (right shift with zero extension)

  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ..... side effects

  // Instructions vs Expressions

  // IF expression
  val aCondition = true

  val aConditionValue = if (aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionValue)
  println(if (aCondition) 5 else 3)
  println(1 + 3)


  var i = 0
  while (i < 10){
    print(i)
    i += 1

    // NEVER WRITE THIS AGAIN

    // Everything in Scala is an Expression

    val aWeirdValue = (aVariable = 3) // Unit === void

    print(aWeirdValue)

    // side effectes:
  }

}
