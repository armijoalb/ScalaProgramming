package exercices

// Lets make it covariant
abstract class MyList[+A] {
  /*
  head = first element of the list
  tail = remainder of the list
  isEmpty = boolean is this emtpy.
  add(int) = new List with the element added.
  override toSTring = a string representation of the list.
   */

  def head:A
  def tail:MyList[A]
  def isEmpty:Boolean
  def add[B >: A](element:B):MyList[B] // this is for covariance
  def printElements:String
  override def toString: String = "{" + printElements + "}"
  // When we call this method, it will use, the classes/object printElements overrided method.

  def map[B](transformation:MyTransformer[A,B]):MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
  def flatMap[B](transformer:MyTransformer[A,MyList[B]]):MyList[B]
}

object Empty extends MyList[Nothing] {
  def head:Nothing = throw new NoSuchElementException
  def tail:MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element:B):MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""

  override def map[B](transformation:MyTransformer[Nothing,B]):MyList[B] = Empty
  override def flatMap[B](transformer:MyTransformer[Nothing,MyList[B]]):MyList[B] = Empty
  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class Cons[+A](h:A, t:MyList[A]) extends MyList[A] {
  def head:A = h
  def tail:MyList[A] = t
  def isEmpty:Boolean = false
  def add[B >: A](element:B):MyList[B] = new Cons(element, this)

  override def printElements: String = {
    if (t.isEmpty) h.toString
    else h + " " + t.printElements
  }

  override def map[B](transformation:MyTransformer[A,B]): MyList[B] = {
    new Cons(transformation.transform(h), t.map(transformation))
  }

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }

  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

}

trait MyPredicate[-T] {
  def test(element:T):Boolean
}

trait MyTransformer[-A,B] {
  def transform(element:A):B
}

//class EvenPredicate[T] extends MyPredicate[T]
//class StringToIntTransformer[A,B] extends MyTransformer[A,B]

object ListTests extends App {
  /*
  val listOfInts: MyList[Int] = Empty
  val listOfInts: MyList[String] = Empty
  This should be possible, for that we need Empty to extend from List of "Nothing"
   */

  val listOfInts: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfInts2: MyList[Int] = new Cons(4, new Cons(5,Empty))

  val listOfStrings: MyList[String] = new Cons("1", new Cons("2", Empty))
  println(listOfInts.toString)
  println(listOfStrings.toString)

  println(listOfInts.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }).toString)

  println(listOfInts.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }))

  println(listOfInts ++ listOfInts2)
  println(listOfInts.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(element: Int): MyList[Int] = new Cons(element, new Cons(element+1, Empty))
  }))
}
