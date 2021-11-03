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

  // hofs and curries
  def forEach(func: A=>Unit):Unit
  def sort(compare:(A,A)=>Int):MyList[A]
  def zipWith[B,C](list:MyList[B], func:(A,B)=>C):MyList[C]
  def fold[B](i:B)(func:(B,A) => B):B
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

  override def forEach(func: Nothing => Unit): Unit = ()
  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  override def zipWith[B, C](list: MyList[B], func: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("List do not have the same length")
    else Empty
  }

  override def fold[B](i: B)(func: (B, Nothing) => B): B = i
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

  override def forEach(func: A => Unit): Unit = {
    func(h)
    t.forEach(func)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x:A, sortedList:MyList[A]):MyList[A] = {
      if (sortedList.isEmpty) new Cons(x,Empty)
      else if (compare(x,sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }
    val sortedTail =  t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], func: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("List do not have the same length")
    else new Cons(func(h, list.head), t.zipWith(list.tail, func))
  }

  override def fold[B](i: B)(func: (B, A) => B): B = {
    t.fold(func(i, h))(func)
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

  listOfInts.forEach(println)
  println(listOfInts.sort((x,y)=> y-x))
  println(listOfInts2.zipWith[String, String](listOfStrings, _ + "-" +_))
  println(listOfInts.fold(0)(_+_))

  // test for comprehesions.
  val combinations = for {
    n <- listOfInts
    str <- listOfStrings
  } yield n + "-" + str
  println(combinations)
}
