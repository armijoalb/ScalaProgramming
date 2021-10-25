package lectures.part2oop

object Inheritance extends App{

  // single class inheritance
  sealed class Animal {
    def eat: Unit = println("ñam ñam")
    val creatureType: String = "Wild"
  }

  // only can inherit non private methods, protected and public are inherited
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  // cannot access protected methods here --> cat.eat

  cat.crunch

  /*
  class Person(name:String, age:Int)
  class Adult(name:String, age:Int, idCard:String) extends Person

  this does not compile, because it need to call the Person constructor with correct parameters.
  */
  // This compiles because it can call the default constructor of Person, which need name and age.
  class Person(name:String, age:Int)
  class Adult(name:String, age:Int, idCard:String) extends Person(name, age)

  // Overriding
  class Dog(override val creatureType: String) extends Animal{
    override def eat: Unit = {
      super.eat // this calls the Animals eat's method.
      println("DOG crunch crunch")
    }
    // This is possible, but you can override it at "constructor level" too.
    // override val creatureType: String = "Domestic"
  }

  val dog = new Dog("domestic")
  dog.eat

  // type substitution.
  // polymorphism, to do this, eat in Animal must be public.
  val unknownAnimal:Animal = new Dog("k9")
  unknownAnimal.eat

  // preventing overriding
  // final class members: same as methods
  // final methods: methods that cannot be override
  // final class: classes that you cannot "extends"
  // seal the class: extension is valid only in the same file.
  // sealed class Animal permits Cat and Dog inherit, but you cannot extends Animal in other files.
}
