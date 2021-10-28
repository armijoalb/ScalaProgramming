package lectures.part3funcprog

object AnonymousFunctions extends App {

  // this is too much!
  val doubler_bad = new Function1[Int,Int] {
    override def apply(v1: Int): Int = v1*2
  }

  // anonumous function or lambda functions!
  val doubler: Int => Int = x => x * 2

  // multiple parameters
  val adder: (Int,Int) => Int = (a,b) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // lambda functions needs to use () for calling them

  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curyly braces with lambdas
  val stringToInt = { (str:String) =>
    str.toInt
  }

  // more syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to X=>X+1
  val niceAdder: (Int,Int) => Int = _ + _ // equivalent to (x,y) => x+y

  // for _ use it's needed to specify the types.
}
