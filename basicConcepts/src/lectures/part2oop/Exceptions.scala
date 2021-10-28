package lectures.part2oop

object Exceptions extends App{
  val x: String  = null
  /*
  println(x.length)// this throws null exception
  */

  // throw exceptions
  // --> throw new NullPointerException
//  val aWeirdValue = throw new NullPointerException

  // throwable classes extends the Throwable class.
  // Exception and Error are the major Throwable subtypes.
  // Exceptions --> something wrong with the program
  // Error --> something wrong with the JVm, system...

  // catch exceptions
  def getInt(withExceptions:Boolean):Int = {
    if (withExceptions) throw new RuntimeException("no int for you!")
    else 42
  }

  try {
    // code that might fail
    getInt(true)
  }catch {
    // manage exceptions
    case e: RuntimeException => println("caught a runtime extension")
  } finally {
    // code that will run NO MATTER WHAT
    println("finally")
  }

  // the finally block is optional and does not influence the return type of this expression.
  // use finally only for side efects.

  class MyException extends Exception

  val exception:MyException = new MyException
  throw exception


  /*
  exercices
   */

  // this would crash the JVM. OOM
  // val array = Array.ofDim(Int.MaxValue)

  // this would make a stack overflow.
//  def infinite:Int = 1 + infinite
//  val noLimit = infinite

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculatorException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x:Int, y:Int):Int = {
      val result = x +y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y <0 && result > 0) throw new UnderflowException
      else result
    }
    def subtract(x:Int, y:Int):Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def multiply(x:Int, y:Int):Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def divide(x:Int, y:Int):Int = {
      if (y==0) throw new MathCalculatorException
      else x/y
    }
  }

  println(PocketCalculator.divide(2,0))
}
