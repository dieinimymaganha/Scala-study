// Flow control

// if / else
if(1 > 3) println("Impossible") else println("The world makes sense.")

if( 1 > 3){
  println("Impossible")
  println("Really?")
} else{
  println("The world makes sens.")
  println("still")
}

// Matching
val number = 3
number match {
  case 1 => print("One")
  case 2 => print("Two")
  case 3 => print("Three")
  case _ => print("Something else")
}

for (x <-1 to 4){
  val squared = x * x
  println(squared)
}

var x = 10

while (x > 10) {
  println(x)
  x -= 1
}

x = 0
do {println(x); x+= 1} while (x <= 10)

// Expressions
{val x = 10; x + 20}
println({val x = 10; x + 20})