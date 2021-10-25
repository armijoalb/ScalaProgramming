package lectures.part2oop

object CaseClasses extends App{

  /*
  equals, hashCode, toString
   */

  /*
  Case classes: are good for generic classes.
    1.- Class parameters are transformerd to fields.
    2.- have sensible toString execution
   */

  case class Person(name:String, age:Int)

  val jim = new Person("jim", 24)
  println(jim.name)

  // these two do the same.
  println(jim.toString)
  println(jim)

}
