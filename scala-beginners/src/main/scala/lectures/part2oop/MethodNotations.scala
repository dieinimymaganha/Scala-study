package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {

    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def unary_! : String = s"$name, what the heck?"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }

  val mary = new Person(name = "Mary", favoriteMovie = "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception")

  // infix notation = operator notation

  // "operator" in Scala

  val tom = new Person("Tom", "Fight Club")

  println(mary hangOutWith tom)
  println(mary.hangOutWith(tom))
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS.

  // Akkaa actiors have ! ?

  // prefix notation

  val x = -1 // equivalent with unary_-
  val y = 1.unary_-

  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)

  // println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent


}
