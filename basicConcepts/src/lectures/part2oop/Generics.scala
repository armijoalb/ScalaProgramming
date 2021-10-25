package lectures.part2oop

object Generics extends App{

  // Generic class
  // Type A is a generic type
  class MyList[+A] {
    // you can use type A for any type
    // with +A is covariant.

    def add[B >: A](element:B):MyList[B] = ???
    /*
      A = Cat
      B = Animal
    */
  }

  val listOfInts = new MyList[Int]
  val listOfStrings = new MyList[String]

  class MyMap[KeyType,ValueType]

  // can also be used for traits.
  trait MyList2[A]

  // generic methods.
  object MyList {
    def emtpy[A]:MyList[A] = ???
  }

  val emptyListOfInts = MyList.emtpy[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1.- if yes List[Cat] extends List[Animal] => Covariance
  // it permits polymorphism for types used in this class.
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // what happends if animalList.add(animal:Dog) ??? it you can do that, it's really a list of generic Animals!
  // so we need to return a list of animals.

  // 2.- if no List[Cat] does not extends List[Animal] => Invariance
  // every type must have its own val, you cannot use polymorphism for this.
  class InvariantList[A]
  // this cannot be done with invariant val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]

  // 3.- no way List[Cat] does not extends List[Animal] => Contravariance
  // Contravariance --> opposite to covariance
  // it permits to use the bigger, super class with a subclass type declared, but no the other way
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]


  // bounded types
  // this means that this class only admits subclases of animal
  class Cage[A <: Animal](animal: A)
  // this is not permited => val cage = new Cage[Animal]
  class Car
  // this is not permited also => val cage = new Cage[Car]

  // this means that this class only admits super types of animal
  class Cage2[A >: Animal](animal:A)


}
