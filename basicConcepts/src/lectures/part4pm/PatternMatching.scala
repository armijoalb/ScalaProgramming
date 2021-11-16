package lectures.part4pm

import scala.util.Random
import scala.util.matching.Regex.Match

object PatternMatching extends App {

  val random = new Random()
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "thrd time is the charm"
    case _ => "something else" // _ is a wildcard, means every other case

  }

  println(x)
  println(description)

  // 1.- Decompose values
  // 2.- what if no cases match => MatchError
  // 3.- return type?? try to unify the types that are in the match
  // 4.- PM works really well with case classes*
  case class Person(name:String, age:Int)
  val bob = Person("bob", 22)

  val greeting = bob match {
    case Person(n,a) if a < 21 => s"Hi, my name is $n and I cant drink in the US"
    case Person(n,a) => s"Hi, my name is $n and my age is $a years old"
    case _ => "i don't know who i am"
  }

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed:String) extends Animal
  case class Parrot(greeting:String) extends Animal

  val animal: Animal = Dog("terra nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
    case _ => println("i dont know what animal it is")
  }

  // DO NOT match everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  // this is BAD!

  /*
  Exercices
  simple funcion uses PM
  takes an Expr => human readable form

  sum(NUMBER(2), NUMBER(3)) => 2+3
  sum(number(2), number(3), number(4)) => 2+3+4
  prod(sum(number(2), number(1), number(3)) => (2+1)*3
   */

  trait Expr
  case class Number(n:Int) extends Expr
  case class Sum(e1:Expr, e2:Expr) extends Expr
  case class Prod(e1: Expr, e2:Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1)+"+"+show(e2)
    case Prod(e1,e2) => {
      def maybeShowParenthesis(expr: Expr) = expr match {
        case Prod(_,_) => show(expr)
        case Number(_) => show(expr)
        case _ => "(" + show(expr) + ")"
      }
      maybeShowParenthesis(e1) + "*" + maybeShowParenthesis(e2)
    }
  }

  println(show(Sum(Number(1), Number(2))))
  println(show(Sum(Sum(Number(1), Number(2)),Number(3))))
  println(show(Sum(Prod(Number(2), Number(3)), Number(5))))
  println(show(Prod(Sum(Number(2), Number(3)), Number(5))))

}
