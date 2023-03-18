package exercicies.part1basics

abstract class MyList {
  /*
  head = first element of the list
  tail = remainder of the list
  isEmpty = is this list empty
  add(int) => new list with this element added

  toString => a string representation of the list
  */

  val list_n = List(4, 2, 5, 6, 54, 564, 2)

  def head = println(list_n(0))

  def tail = println(list_n.slice(1, list_n.length))

  def add(value: Int) = list_n :+ value

  def convertString = list_n.mkString(",")

}

object Execute extends App {

  val teste = new MyList {}

  println(teste.list_n)

  teste.head
  teste.tail

  println(teste.add(454444))
  println(teste.convertString)

}


