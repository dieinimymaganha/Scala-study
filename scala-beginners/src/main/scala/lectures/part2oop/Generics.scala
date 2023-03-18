package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // use the type A

    def add[B >: A](element: B): MyList[B] = ???

  }


  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]

  val listOfStrings = new MyList[String]

  // generic methods

  object MyList {
    def empty[A]: MyList[A] = ???

  }

  val emptyListOfIntegers = MyList.empty[Int]


  // variance problem

  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // 1. Yes List[Cat] extends List[Animal]  = COVARIANCE

  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION => We return a list of Animals


  // 2. No = INVARIANCE
  class InvariantList[A]

  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Cat]

  // Bounded types
  class Cage[A <: Animal](animal: A)

  val cage = new Cage(new Dog)


}
