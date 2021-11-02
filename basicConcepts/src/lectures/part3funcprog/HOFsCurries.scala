package lectures.part3funcprog

object HOFsCurries extends App{

  val superFunction : (Int, (String, (Int => Boolean)) => Int) => (Int =>Int) = null
  // higher order functions = HOF
  // map, flatMap, filter in MyList

  // function that applies a function n times over a value x
  // nTime(f, 3, x) = f(f(f(x)))
  // nTime(f,n,x) = f(f(...f(x)) = nTimes(f, n-1, f(x))

  def nTimes(f:Int=>Int, n:Int, x:Int):Int = {
    if (n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  val plusOne = (x:Int)=> x+1
  println(nTimes(plusOne, 10, 1))

}
