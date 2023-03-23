// Data structures

// Tuples
// Immutable lists

val captainStuff = ("Picard", "Enterprise-D", "NCC-1701-D")
println(captainStuff)

//Refer to the individual fields with a ONE-BASED index
println(captainStuff._1)
println(captainStuff._2)
println(captainStuff._3)

val picardShio = "Picard" -> "Enterprise-D"
println(picardShio._2)

val aBunchOfStuuff = ("Kirk", 1964, true)

// Lists
// Like a tuple, but more funcionality
// Must be of same type

val shipList = List("Enterprise", "Defiant", "Voyager", "Deep Space Nine")

println(shipList(0))
// zero-based

println(shipList.head)
println(shipList.tail)

for (ship <- shipList) {
  println(ship)
}

val backwarkdShips = shipList.map((ship: String) => {
  ship.reverse
})

for (ship <- backwarkdShips) {
  println(ship)
}


// reduce() to combine together all the items in collection using some functions
val numberList = List(1, 2, 3, 4, 5)
val sum = numberList.reduce((x: Int, y: Int) => x + y)
println(sum)

val iHateFives = numberList.filter((x: Int) => x != 5)
println(iHateFives)

val iHateThrees = numberList.filter(_ != 3)
println(iHateThrees)

// Concatenate lists
val moreNumbers = List(6,7,8)
val lostsOfNumbers = numberList ++ moreNumbers

val resersed = numberList.reverse
val sorted = resersed.sorted