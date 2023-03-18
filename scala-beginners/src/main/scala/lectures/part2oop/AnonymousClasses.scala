package lectures.part2oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }


  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahhahahaha")
  }

  /*
    equivalent with

    class AnonymousClasses$$anan$1 extends Animal {
      override def eta: Unit = println("hahahahahhaha")
    }
    val funnyAnimal = new AnonymousClasses$$anan$1
  */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")


  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I help?")
  }

}
