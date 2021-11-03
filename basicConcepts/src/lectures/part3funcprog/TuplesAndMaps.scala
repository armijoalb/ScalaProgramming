package lectures.part3funcprog

object TuplesAndMaps extends App{

  // tuples = finite ordered lists
  val aTuple = (2, "hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1)
  println(aTuple.copy(_2="goodbey java"))
  println(aTuple.swap)

  // maps = keys:values
  val aMap:Map[String, Int] = Map()
  val phonebook = Map(("Jim", 555), "daniel" -> 345).withDefaultValue(-1)
  println(phonebook.contains("jim"))
  println(phonebook("mary"))

  val aParing = "mary"->999
  val newphonebook = phonebook+aParing

  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))
  println(phonebook.filterKeys(x=> x.startsWith("J")))
  println(phonebook.mapValues(number=>number*10))

  println(phonebook.toList)
  println(List("daniel"->444).toMap)

  val names =  List("BOB", "james", "angela", "mary", "daniel", "jim")
  println(names.groupBy(names => names.charAt(0)))
}
