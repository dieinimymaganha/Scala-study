package exercicies.part1basics

import scala.NoSuchElementException

abstract class MyListTeacher[+A] {

  def head: A

  def tail: MyListTeacher[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyListTeacher[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyListTeacher[B]

  def flatMap[B](transformer: MyTransformer[A, MyListTeacher[B]]): MyListTeacher[B]

  def filter(predicate: MyPredicate[A]): MyListTeacher[A]

  def ++[B >: A](list: MyListTeacher[B]): MyListTeacher[B]

}


object Empty extends MyListTeacher[Nothing] {

  def head: Nothing = throw new NoSuchElementException

  def tail: MyListTeacher[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyListTeacher[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyListTeacher[B] = Empty

  def flatMap[B](transformer: MyTransformer[Nothing, MyListTeacher[B]]): MyListTeacher[B] = Empty

  def filter(predicate: MyPredicate[Nothing]): MyListTeacher[Nothing] = Empty

  def ++[B >: Nothing](list: MyListTeacher[B]): MyListTeacher[B] = list
}

class Cons[+A](h: A, t: MyListTeacher[A]) extends MyListTeacher[A] {
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
  def filter(predicate: MyPredicate[A]): MyListTeacher[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  /*
  [1,2,3].map(n * 2)
   = new Cons(2, [2,3].map(n * 2))
   = new Cons(2, new Cons(4, [3].map(n * 2 )))
   = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2 )))))
   = new Cons(2, new Cons(4, new Cons(6, Empty))))
  */
  def map[B](transformer: MyTransformer[A, B]): MyListTeacher[B] =
    new Cons(transformer.transform(h), t.map(transformer))

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
  def flatMap[B](transformer: MyTransformer[A, MyListTeacher[B]]): MyListTeacher[B] =
    transformer.transform(h) ++ t.flatMap(transformer)



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


trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}


object ListTest extends App {
  val listOfIntegers: MyListTeacher[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyListTeacher[Int] = new Cons(4, new Cons(5, Empty))
  val listOfString: MyListTeacher[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfString.toString)


  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)


  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(new MyTransformer[Int, MyListTeacher[Int]]{
    override def transform(elem: Int): MyListTeacher[Int] = new Cons(elem, new Cons(elem + 1 , Empty))
  }).toString)


}