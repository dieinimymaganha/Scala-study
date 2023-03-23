// Functions

// format def <functiona name> (parameter name: type...): return type = {}


def squareIt(x: Int): Int = {
  x * x
}

def cubeIt(x: Int): Int = {
  x * x * x
}

println(squareIt(2))
println(cubeIt(3))

def convertAndDouble(x: String): Int = {
  x.toInt * 2
}

def transformInt(x: Int, f: Int => Int): Int = {
  f(x)
}

def testTransform(x: String, f: String => Int): Int = {
  f(x)
}

val resultTest = testTransform("8", convertAndDouble)

println(resultTest)

transformInt(3, x => x * x * x)


transformInt(10, x => x / 2)

transformInt(2, x => {
  val y = x * 2; y * y
})