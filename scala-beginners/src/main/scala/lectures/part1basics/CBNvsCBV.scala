package lectures.part1basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println(s"by value ${x}")
    println(s"by value ${x}")

  }

  def calledByName(x: => Long): Unit = {
    println(s"by name ${x}")
    println(s"by name ${x}")
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
}
