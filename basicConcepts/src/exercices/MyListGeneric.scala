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

  // higher order functions
  def map[B](transformation:A => B ):MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
  def flatMap[B](transformer:A => MyList[B]):MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head:Nothing = throw new NoSuchElementException
  def tail:MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element:B):MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""

  override def map[B](transformation:Nothing=>B):MyList[B] = Empty
  override def flatMap[B](transformer:Nothing => MyList[B]):MyList[B] = Empty
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
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

  override def map[B](transformation:A => B): MyList[B] = {
    new Cons(transformation(h), t.map(transformation))
  }

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  override def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

}
// We dont need them. Deleted
/*trait MyPredicate[-T] { // T => Boolean
  def test(element:T):Boolean
}

trait MyTransformer[-A,B] { // A => B
  def transform(element:A):B
}*/

//class EvenPredicate[T] extends MyPredicate[T]
//class StringToIntTransformer[A,B] extends MyTransformer[A,B]

object ListTests extends App {
  /*
  val listOfInts: MyList[Int] = Empty
  val listOfInts: MyList[String] = Empty
  This should be possible, for that we need Empty to extend from List of "Nothing"
   */

  val listOfInts: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val clonelistOfInts: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfInts2: MyList[Int] = new Cons(4, new Cons(5,Empty))

  val listOfStrings: MyList[String] = new Cons("1", new Cons("2", Empty))
  println(listOfInts.toString)
  println(listOfStrings.toString)

  println(listOfInts.map(elem => elem * 2).toString)

  println(listOfInts.filter(elem => elem%2 == 0))

  println(listOfInts ++ listOfInts2)
  println(listOfInts.flatMap(elem => new Cons(elem, new Cons(elem+1, Empty))))

  println(listOfInts == clonelistOfInts)
}
