package exercicies.part1basics

import scala.NoSuchElementException

abstract class MyListTeacher[+A] {

  def head: A

  def tail: MyListTeacher[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyListTeacher[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  // higher-order functions
  def map[B](transformer: A => B): MyListTeacher[B]

  def flatMap[B](transformer: A => MyListTeacher[B]): MyListTeacher[B]

  def filter(predicate: A => Boolean): MyListTeacher[A]

  def ++[B >: A](list: MyListTeacher[B]): MyListTeacher[B]

  // hofs
  def foreEach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyListTeacher[A]


  def zipWith[B, C](list: MyListTeacher[B], zip: (A, B) => C): MyListTeacher[C]

  def fold[B](start: B)(operator: (B, A) => B): B
}


case object Empty extends MyListTeacher[Nothing] {

  def head: Nothing = throw new NoSuchElementException

  def tail: MyListTeacher[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyListTeacher[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyListTeacher[B] = Empty

  def flatMap[B](transformer: Nothing => MyListTeacher[B]): MyListTeacher[B] = Empty

  def filter(predicate: Nothing => Boolean): MyListTeacher[Nothing] = Empty

  def ++[B >: Nothing](list: MyListTeacher[B]): MyListTeacher[B] = list

  // hofs
  def foreEach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int) = Empty

  def zipWith[B, C](list: MyListTeacher[B], zip: (Nothing, B) => C): MyListTeacher[C] =
    if (!list.isEmpty) throw new RuntimeException("List do not have the same lenght")
    else Empty

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyListTeacher[A]) extends MyListTeacher[A] {
  def head: A = h

  def tail: MyListTeacher[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyListTeacher[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  /*
   [1, 2, 3].filter(n % 2 ==0) =
      [2, 3].filter(n % 2 == 0) =
      = new Cons(2, [3].filter(n % 2 ==0))
      = new Cons(2, Empty.filter(n % 2 ==0))
      = new Cons(2, Empty)
  */
  def filter(predicate: A => Boolean): MyListTeacher[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  /*
  [1,2,3].map(n * 2)
   = new Cons(2, [2,3].map(n * 2))
   = new Cons(2, new Cons(4, [3].map(n * 2 )))
   = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2 )))))
   = new Cons(2, new Cons(4, new Cons(6, Empty))))
  */
  def map[B](transformer: A => B): MyListTeacher[B] =
    new Cons(transformer(h), t.map(transformer))

  /*
    [1, 2] ++ [3, 4, 5]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(3, new Cons(5)))))
  * */
  def ++[B >: A](list: MyListTeacher[B]): MyListTeacher[B] = new Cons(h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n =< [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
  */
  def flatMap[B](transformer: A => MyListTeacher[B]): MyListTeacher[B] =
    transformer(h) ++ t.flatMap(transformer)

  // hofs

  def foreEach(f: A => Unit): Unit = {
    f(h)
    t.foreEach(f)
  }

  def sort(compare: (A, A) => Int): MyListTeacher[A] = {

    def insert(x: A, sortedList: MyListTeacher[A]): MyListTeacher[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)

  }

  def zipWith[B, C](list: MyListTeacher[B], zip: (A, B) => C): MyListTeacher[C] =
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)


  }


}

//object ListTest extends App {
//  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  println(list.tail.head)
//  println(list.add(4).head)
//  println(list.add(4).head)
//  println(list.toString)
//
//}
//


object ListTest extends App {
  val listOfIntegers: MyListTeacher[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers: MyListTeacher[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyListTeacher[Int] = new Cons(4, new Cons(5, Empty))
  val listOfString: MyListTeacher[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfString.toString)


  println(listOfIntegers.map(_ * 2).toString)

  println(listOfIntegers.filter(_ % 2 == 0).toString)


  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))).toString)

  println(cloneListOfIntegers == listOfIntegers)

  listOfIntegers.foreEach(println)

  println(listOfIntegers.sort((x, y) => y - x))

  println(anotherListOfIntegers.zipWith[String, String](listOfString, _ + "-" + _))

  println(listOfIntegers.fold(0)(_ + _))


}