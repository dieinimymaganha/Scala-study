package exercicies.part1basics

abstract class MyListTeacher {

  def head: Int

  def tail: MyListTeacher

  def isEmpty: Boolean

  def add(element: Int): MyListTeacher
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}


object Empty extends MyListTeacher {
  def head: Int = throw new NoSuchElementException

  def tail: MyListTeacher = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add(element: Int): MyListTeacher = new Cons(element, Empty)
  def printElements: String = ""
}

class Cons(h: Int, t: MyListTeacher) extends MyListTeacher {
  def head: Int = h

  def tail: MyListTeacher = t

  def isEmpty: Boolean = false

  def add(element: Int): MyListTeacher = {
    new Cons(element, this)
  }

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements


}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.add(4).head)
  println(list.toString)

}

