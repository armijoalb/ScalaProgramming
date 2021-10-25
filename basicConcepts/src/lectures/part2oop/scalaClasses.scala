package lectures.part2oop

/*
  When you declare inside a constructor a parameter with the keyword *val*,
  you're making this parameter public and you can access its value from everywhere
  you use it.

  Class parameters are not FIELDS, that's why you cannot access them out of the class definition.
  Fields on the other hand allows you to do that, you need to write the keyword *val* before the field definition.
 */
class Writer(firstName: String, surname:String, val yearOfBirth:Int) {
  def fullname():String = {
    s"hello my name is $firstName $surname, and I was born in $yearOfBirth"
  }
}

class Novel(name:String, yearOfRelease:Int, author:Writer) {
  def getAuthorAge:Int = yearOfRelease - this.author.yearOfBirth
  def isWrittenBy(author:Writer):Boolean = author == this.author
  def copy(newYear:Int):Novel = new Novel(name=this.name, author=this.author, yearOfRelease=newYear)
}

class Counter(initVal:Int){
  // if you want to access initVal instead of making a getter method, you can use val inside the param definition.
  def count:Int = this.initVal
  def incrementValue:Counter = new Counter(initVal = this.initVal + 1) // immutability
  def decrementValue:Counter = new Counter(initVal = this.initVal - 1)
  def incrementValue(amount:Int) = new Counter(initVal= this.initVal + amount)
  def decrementValue(amount:Int) = new Counter(initVal = this.initVal - amount)
}

/*
 if you want, for example, to log some info and you need to put a println inside the function and you're doing
 overloading, you need to use recursion for that.
 */
class Counter2(val count:Int = 0) {
  def inc:Counter2 = {
    println("incrementing")
    new Counter2(count+1)
  }

  def dec:Counter2 = {
    println("decrementing")
    new Counter2(count-1)
  }

  def inc(n:Int):Counter2 = {
    if (n <= 0) this
      // this way you call the method inc and you allow to do some printing with the console, logging, etc...
    else inc.inc(n-1)
  }

  def dec(n:Int):Counter2 = {
    if (n <= 0 ) this
    else dec.dec(n-1)
  }

  def getOuput = println(s"actual count : ${this.count}")
}

object scalaClasses extends App{

  val myWriterObj = new Writer(firstName = "alberto", surname="armijo", yearOfBirth = 1996)
  println(myWriterObj.fullname())

  val myNovelObj = new Novel(name="i dont know scala", yearOfRelease = 2021, author = myWriterObj)
  println(myNovelObj.getAuthorAge)

  /*
  This should return false by default, because although the object values are the same
  the object aren't , so the comparison between this two object has to be done differently
  if we want this method to return true for this case.
   */
  val impostor = new Writer(firstName = "alberto", surname = "armijo", yearOfBirth = 1996)
  println(myNovelObj.isWrittenBy(impostor))

  val counter = new Counter2
  counter.inc(4).dec.getOuput

}
