package lectures.part2oop

/*
 scala does not have class-level functionality.
 scala does not have static values inside classes, to define them, you an define
 objects.

 objects in Scala can have multiple parameters and be defined as constants, like static final values are.
 */

object scalaObjects extends App {

  object Person {
    /*
     Scala objects are singleton instances. --> type + its only instance.
     two variables or values referencing an object are the same.
    */
    // Class level functionality.
    val N_EYES: Int = 2

    // factory methods
    def from(mother:Person, father:Person) = new Person("bob")
    // this factory methods can be called with the apply!
    def apply(mother:Person, father:Person) = new Person("bob")
  }
  class Person (val name:String) {
    // Instance level functionality.
  }
  /*
   You can have an object and a class with the same name to have class level and instance level functionality.
   This is called COMPANIONS in Scala.
   With this, both reside in the same scope, and everything we access from this will be accessed from and unique
   instance (singleton) or from a normal instance (class instance)
   */

  println(Person.N_EYES)
  val mary = Person
  val tom = Person
  println(mary == tom)

  val m = new Person("m")
  val charles = new Person("charles")

  val son = Person.from(m, charles)
  println(son.name)

  val son2 = Person(m, charles)
  println(son2.name)

  // Scala Applications. --> Scala object with
  // def main(args:Array[String]):Unit --> this is transformed to Java when compiled.
  // inside an object you should write a main method, or extends App in the definition.
}

