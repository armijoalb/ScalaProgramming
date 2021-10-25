package lectures.part2oop

import scala.annotation.tailrec

object ScalaFunctions extends App{

  def greeting_func(name:String, age:Int): String ={
    "Hello my name is " + name + " and my age is " + age
  }

  // This implementation is fucking bad. this is make an stack overflow
  def factorial_func(n:Int):Int = {
    if ( n <= 0 ) 1
    else n * factorial_func(n-1)
  }

  /*
   * Better implemententation.
   * When you need to use "loops", you have to use tail recursion.
   * https://es.stackoverflow.com/questions/121965/que-es-tail-recursion
   * Para asegurarse que la función es tail recursive, se puede añadir el decorador @tailrec
   * Si no es tail recursive, el compilador dará un error ya que este decorador le obliga a escala a
   * utilizar esta optimización.
   */

  def optimized_factorial(n:Int):BigInt = {
    @tailrec
    def aux_factorial(x: Int, acc:BigInt):BigInt = {
      if (x <= 1) acc
      else aux_factorial(x-1, x*acc)
    }

    aux_factorial(n, 1)
  }

  def fibonacci_func(n:Int):Int ={
    if (n <= 2) 1
    else fibonacci_func(n-1)+fibonacci_func(n-2)
  }

  def optimized_fibonacci(n:Int):BigInt = {
    @tailrec
    def aux_fibo(t:Int, fib_1: BigInt, fib_2: BigInt ):BigInt = {
      println("calculating fibo " + t + " before values : " + fib_1 + " , " + fib_2)
      if (t >= n) fib_1
      else aux_fibo(t+1, fib_1+fib_2, fib_1)
    }

    if (n == 0) 0
    else if (n <= 2) 1
    else aux_fibo(2, 1, 1)
  }

  def is_prime(n:Int):Boolean = {
    def is_prime_until(t:Int):Boolean = {
      if (t <= 1) true
      else is_prime_until(t - 1) && (n % t != 0)
    }

    is_prime_until(n/2)
  }

  def optimized_is_prime(n:Int):Boolean = {
    @tailrec
    def is_prime_tailrec(t:Int, isStillPrime:Boolean):Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) true
      else is_prime_tailrec(t-1, (n % t != 0 && isStillPrime))
    }

    is_prime_tailrec(n/2, true)
  }

  def concat_str_tr(s:String, n:Int):String = {
    @tailrec
    def aux_concat(acc_str:String, aux_n:Int):String = {
      if (aux_n == 0) acc_str
      else aux_concat(acc_str+s, aux_n-1)
    }

    aux_concat("", n)
  }




  println(greeting_func("alberto", 25))
  println(factorial_func(5))
  println(fibonacci_func(5))
  println(is_prime(37))
  println(is_prime(16))

  // tail rec
  println(optimized_factorial(700))
  println(concat_str_tr("alberto", 5000))
  println(optimized_fibonacci(5))
}
