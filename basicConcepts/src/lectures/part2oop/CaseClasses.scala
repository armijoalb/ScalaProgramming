package lectures.part2oop

object CaseClasses extends App{

  /*
  equals, hashCode, toString
   */

  /*
  Case classes: are good for generic classes.
    1.- Class parameters are transformerd to fields.
    2.- have sensible toString execution
    3.- equals and hasCode implemented OOTB (out of the box)
    4.- CCs have handy copy methods
    5.- CC have companion objects
    6.- CC are serializable.
    7.- CCs have extractor patterns = CCs can be used in Pattern Matching!
   */

  case class Person(name:String, age:Int)

  val jim = new Person("jim", 24)
  println(jim.name)

  // these two do the same.
  println(jim.toString)
  println(jim)

  val jim2 = new Person("jim", 24)
  println(jim == jim2)

  val jim3 = jim.copy(age=45)
  println(jim3)

  // this creates automatically a companion object for person
  val thePerson = Person
  // companion object also have some default object.
  // this generates a new person. This example generates apply method that uses the constructor.
  val mary = Person("Mary", 23)

  /*
  Case objects have the same properties as CCs, but no companion objects.
   */
  case object UnitedKingdom {
    def name: String = "The Uk of Gb and NI"
  }


}
