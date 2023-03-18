package lectures.part2oop

object AbstractdataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String = "Wild"

    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    override def eat: Unit = println("crunch crunch")

  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }


  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    def eat: Unit = println("nomnomnom")

    def eat(animal: Animal): Unit = println(s"I'm croc and I'm eating ${animal.creatureType}")

  }

  val dog = new Dog
  val croc = new Crocodile

  croc.eat(dog)

  // traits vs abstract class



}
