package lectures.part4pm

import exercices.{Cons, Empty, MyList}


object AllThePatterns extends App {
/*  // 1.- constants
  val x:Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "the scala"
    case true => "the truth"
    case AllThePatterns => "a singleton object"
  }

  // 2.- match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 2.3 Tuples
  val aTuple = 1->2
  val matchATuple = aTuple match {
    case (1,2) =>
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1,(2,3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2,v)) =>
  }

  // PM can be NESTED!

  // 4.- case classes - constructor pattern
  val aList: MyList[Int] = new Cons(1, new Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
  }

  val aStandardList = List(1,2,3,42)
  val aStandardlistMatching = aStandardList match {
    case List(1,_,_,_) => // extractor - advanced
    case List(1,_*) => // list of arbitrary length - advanced
    case 1 :: List() => // infix pattern
    case List(1,2,3) :+ 42 => // more infix patterns.
  }

  // 6.- type specifiers
  val unknown: Any = 2
  val unknownMatching = unknown match {
    case list:List[Int] => // explicit type specifier
    case _ =>
  }

  // 7.- name biding
  val nameBidingMatch = aStandardList match {
    case nonEmptyList @ Cons(_,_) => // name binding => use the name later(here)
    case Cons(1, rest @ Cons(2,_)) => // name binding inside nested patterns.
  }

  // 8.- multi-patterns
  val multipattern = aList match {
    case Empty | Cons(0,_) => // compound pattern (multipattern)
  }

  // 9.- if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialNumber, _)) if specialNumber % 2 == 0 =>
  }*/

  // Question
  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings:List[String] => "a list of strings"
    case listOfNumbers:List[String] => "a list of ints"
    case _ => ""
  }

  println(numbersMatch)
  // JVM trick question

}
