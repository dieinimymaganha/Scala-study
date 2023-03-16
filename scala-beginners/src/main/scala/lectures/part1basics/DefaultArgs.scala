package lectures.part1basics

object DefaultArgs extends App {

  def trFactorial(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFactorial(n - 1, n * acc)
  }

  val fact10 = trFactorial(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = print("saving picture")

  savePicture(format = "png")


}
