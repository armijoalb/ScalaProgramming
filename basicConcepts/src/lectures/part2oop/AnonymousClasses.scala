package lectures.part2oop

object AnonymousClasses extends App{
  abstract class Animal {
    def eat:Unit
  }
  // Anonymous Class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahaha")
  }

  /*
  the above is equivalent to ...
  class AnonymousClass extends Animal {
    override def eat:Unit = println("hhahahahaha")
  }

  val funnyAnimal: Animal = new AnonymousClass$anon$1
   */
  funnyAnimal.eat

  class Person(val name:String){
    def sayHi:Unit = println(s"Hi, my name is $name, how can I help?")
  }
  // in order to create a anonymous class you need to provide name, just as when we do a extends
  val jim = new Person(name="jim") {
    override val name:String = "jimbo"
    override def sayHi: Unit = println(s"Hi, my name is $name and i like to play videogames")
  }
  jim.sayHi

}
