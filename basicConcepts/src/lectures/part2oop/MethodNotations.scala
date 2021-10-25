package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favMovie: String) {
    def likes(mov: String): Boolean = favMovie == mov

    def hangOutWith(person: Person) = s"${this.name} is hanging out with ${person.name}"

    def +(person: Person) = s"${this.name} likes ${person.name}"

    def -(person: Person) = s"${this.name} doesn't like ${person.name}"

    def unary_! : String = s"${this.name} hates ${this.favMovie}"

    def favoriteMovie: String = favMovie

    def apply(): String = s"Hi my name is ${this.name} and my fav movie is ${this.favMovie}"

    // Overloading
    // Infix notation
    def +(nickname: String): Person = new Person(name = s"$name ($nickname)", favMovie = this.favMovie)

    // Apply
    def apply(x: Int): String = s"${this.name} watched ${this.favMovie} $x times"

    // Postfix notation
    def learns(x: String): String = s"${this.name} learns $x"

    def learnsScala: String = this learns "Scala"
  }

  class PersonWithAge(val name: String, favMovie: String, val age: Int = 0) {
    def unary_+ : PersonWithAge = new PersonWithAge(name, favMovie, age + 1)
  }

  val Mary = new Person("Mary", "Inception")
  println(Mary.likes("Inception"))
  println(Mary likes "Inception") // Both are valid.

  // YOU CAN OVERLOAD ANY OF THIS!
  /*
  This is called infix notation or operator notation (syntactic sugar)
  you can use it with method that only use one parameter.
  object.mehthod(parameter) can be written like object method parameter
   */

  val tom = new Person("tom", "fight club")
  println(tom hangOutWith Mary)

  /*
  "operators" in Scala
  Inside a class, you can define operators and use it like normal operators in scala with infix notation.

  In Scala, ALL OPERATORS are methods defined in classes.
  you can do 1.+(2) and it will work, because inside the class Int of Scala there's is a implementation of the
  method "+".
  */

  println(tom + Mary)
  println(Mary - tom)

  /*
  Prefix Notation.
  Is used too unary operators, that are also methods in scala.
  This next example does the same
  unary_ only works with + - ~ !
   */
  val x = -1
  val y = 1.unary_-

  println(!Mary)
  println(Mary.unary_!)

  /*
  Postfix notation
  Functions that doesn't receive any parameters can use Postfix notation.
  postfix notation works like this.
  object.method is the same as object method
  This is not really useful because you only change a "." for a " "
   */

  println(Mary.favoriteMovie)
  println(Mary favoriteMovie)

  /*
  Apply.
  if you define apply method inside a class, you can use the following notation because the compiler will
  look for that method.
   */
  println(Mary.apply())
  println(Mary())

  // exercices
  println((Mary + "the rockstar").apply())
  println(tom.apply(2))

  val chris = new PersonWithAge(name = "chris", favMovie = "snatch", age = 24)
  println((+chris).age)

  println(Mary learnsScala)
}
