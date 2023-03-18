package lectures.part2oop

object InheritanceAndTraits extends App {

  // single class inheritance
  class Animal {
    val creatureType = "wild"
    protected def eat = println("Nomnon")
  }

  class Cat extends Animal {

    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat

  cat.crunch

  // constructors

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)

  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding

  class Dog extends Animal {
    override val creatureType = "domestic"

    override def eat = {
      super.eat
      println("crunch crunch")
    }
  }

  val dog = new Dog
  dog.eat
  println(cat.creatureType)
  println(dog.creatureType)

  class Bird(override val creatureType: String) extends Animal {
    override def eat = println("crunch crunch")
  }

  val bird = new Bird(creatureType = "Fly")

  println(bird.creatureType)

  // type substituition(broad: polymorphism)
  val unknowAnimal: Animal = new Bird("K9")

  //  unknowAnimal.eat

  // overRIDING vc overLOADING

  // super

  // preventing overrides
  // 1 - use final
  // 2 - use final on the entire class
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other files



}
