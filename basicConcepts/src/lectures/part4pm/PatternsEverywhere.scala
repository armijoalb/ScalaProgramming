package lectures.part4pm

object PatternsEverywhere extends App{
  // big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "null pointer"
    case _ => "something else"
  }

  // catches are actually matches!


  // big idea #2
  val list = List(1,2,3,4)
  val evenOnes =  for {
    x <- list if x % 2 == 0 // generators are also based on pattern matching
  } yield 10 * x

  val tuples =  List((1,2), (3,4))
  val filterTuples =  for{
    (first,second) <- tuples
  } yield first*second
  // case classes, :: operators, ...

  // big idea #3
  val tuple = (1,2,3)
  val (a,b,c) = tuple
  // multiple value definition base on pattern matching
  println(b)

  val head::tail = list
  println(head)
  println(tail)

  // big idea #4 - NEW
  // partial function -> are based on pattern matching
  val mappedList = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }
  println(mappedList)


}
