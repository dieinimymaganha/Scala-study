package lectures.part2oop

object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    def openDocument(): Unit =
      if (this == READ) println("opening document ...")
      else println("reading not allowed")
  }

  val somePermissions: Permissions = Permissions.READ

  //constructor args

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }


  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = // wharever
      PermissionsWithBits.NONE
  }

  // standard API

  val somePermissionsOrdinal = somePermissions.ordinal

  val allPermissions = PermissionsWithBits.values // array of all possible values of the enu,

  val readPermission: Permissions = Permissions.valueOf("READ")


  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
  }


}
