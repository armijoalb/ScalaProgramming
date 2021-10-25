package lectures.part2oop

object AbstractDataTypes extends App{

  // abstract --> this classes cannot be instanciated, needs to be extended.
  abstract class Animal {
    // abstract classes can have both abstract and non-abstract members
    val creatureType:String
    def eat:Unit = println("crunch eat")

  }

  class Dog extends Animal {
    override val creatureType: String = "canine"
    override def eat:Unit = println("crunch crunch") //you doesn't need to write override for subclasses of abstract classes.
  }

  // traits
  // 1.- traits does not have constructor parameters.
  // 2.- multiple traits may be inherited by the same class. This is how scala does multi-inheriting
  // 3.- trais = behaviour, abstract class = things
  trait Carnivore {
    def eat(animal: Animal):Unit
    val preferredMeal:String = "fresh meat"
  }
  // This class inherits methods from Animal and Carnivore
  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"
    override def eat: Unit = println("nomnomnom")
    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)
}
