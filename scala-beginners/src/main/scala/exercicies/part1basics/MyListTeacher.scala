package exercicies.part1basics

import scala.NoSuchElementException

abstract class MyListTeacher[+A] {

  def head: A

  def tail: MyListTeacher[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyListTeacher[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"
}


object Empty extends MyListTeacher[Nothing] {

  def head: Nothing = throw new NoSuchElementException

  def tail: MyListTeacher[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyListTeacher[B] = new Cons(element, Empty)

  def printElements: String = ""
}

class Cons[+A](h: A, t: MyListTeacher[A]) extends MyListTeacher[A] {
  def head: A = h

  def tail: MyListTeacher[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyListTeacher[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

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
  val listOfString: MyListTeacher[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfString.toString)

}