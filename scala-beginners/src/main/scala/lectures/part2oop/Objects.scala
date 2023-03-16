package lectures.part2oop

object Objects extends App {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")

  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2

    def canFly: Boolean = false
  }


  class Person {
    // instance-level functionality
  }

  println(Person.N_EYES)
//  println(Person.canFly)


  // Scala object = SINGLETON INSTANCE
  val mary = Person
  val john = Person

  println(s"isEqual? ${mary == john}")

}
