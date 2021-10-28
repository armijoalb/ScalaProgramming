package lectures.part3funcprog

object WhatsAFunction extends App {
  // dream: use functions as first class elements
  // problem: we come from a oop

  // doubler can be called now like a function.
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  // scala have function types. Function[A,B]

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string:String):Int = string.toInt
  }
  println(stringToIntConverter("3")+4)

  // syntatic sugar for FunctionN
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a:Int, b:Int):Int = a+b
  }

  // Function types = Function2[A,B,R] === (A,B) => R

  val concatenator: (String, String) => String = new Function2[String,String,String] {
    override def apply(v1: String, v2:String): String = v1+v2
  }

  // Function1[Int,Function1[Int,Int]]
  val superAdder: Function1[Int, Function1[Int,Int]] = new Function1[Int, Function1[Int,Int]]  {
    override def apply(x: Int): Function1[Int,Int] = new Function[Int,Int] {
      override def apply(y: Int): Int = x+y
    }
  }
  val adder3 =superAdder(3)
  println(adder3(4))
  // this does the same
  println(superAdder(3)(4)) // curried functions

  // this does the same as superAdder!
  val superAdd = (x:Int) => (y:Int) => x+y
}

// Function Traits for up to 22 parameters.
trait MyFunction[A,B] {
  def apply(element:A):B
}