package lectures.part3funcprog

import scala.annotation.tailrec

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

  /*
    1.- What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900?
    2.- Overly simplified social entwork based on maps.
      Person = String
        - add a person to the network
        - remove
        - friend
        - unfriend

        Get:
        - number of friends of a person
        - person with most friends
        - if theres is a social connection between two people (direct or not)
   */

  // Exercice 1
  val phonebook2 = Map("Jim"->555, "JIM"->666).withDefaultValue(-1)
  // this will generate a Map with the value of "jim" updated to the last value that was encountered
  // in the original Map.
  println(phonebook2.map(pair => pair._1.toLowerCase() -> pair._2))

  // Exercice 2
  def add(network:Map[String, Set[String]], person:String):Map[String, Set[String]] = {
    network + (person -> Set())
  }

  def friend(network:Map[String, Set[String]], personA: String, personB: String):Map[String, Set[String]] = {
    val friendListA = network(personA)
    val friendListB = network(personB)

    // update network with friendListA adding personB and otherwise for personB with personA
    network + (personA -> (friendListA + personB)) + (personB -> (friendListB + personA))
  }

  def unfriend(network:Map[String, Set[String]], personA: String, personB: String):Map[String, Set[String]] = {
    val friendListA = network(personA)
    val friendListB = network(personB)

    // update network with friendListA remove personB and otherwise for personB with personA
    network + (personA -> (friendListA - personB)) + (personB -> (friendListB - personA))
  }

  def remove(network:Map[String, Set[String]], person:String):Map[String, Set[String]] = {
    @tailrec
    def removeFromFriendLists(friendList:Set[String], networkAux:Map[String, Set[String]]):Map[String, Set[String]] = {
      // if it's emtpy, there is no more work to do.
      if (friendList.isEmpty) networkAux
      // for every person friend, remove friends for that person and the current person
      else removeFromFriendLists(friendList.tail, unfriend(networkAux, person, friendList.head))
    }

    val unfriended = removeFromFriendLists(network(person), network)
    unfriended - person
  }

  val emtpy: Map[String, Set[String]] = Map()
  val network = add(add(emtpy, "bob"), "mary")

  println(network)
  println(friend(network, "bob", "mary"))
  println(unfriend(friend(network, "bob", "mary"), "bob", "mary"))
  println(remove(friend(network, "bob", "mary"), "bob"))

  val people = add(add(add(emtpy, "bob"), "mary"), "jim")
  val jimBob = friend(people, "bob", "jim")
  val testNet = friend(jimBob, "bob", "mary")

  println(testNet)

  def nFriends(network:Map[String, Set[String]], person:String):Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  def personWithMostFriends(network:Map[String, Set[String]]) : String = {
    network.maxBy(pair => pair._2.size)._1
  }

  def peopleWithNoFriends(network:Map[String, Set[String]]):Int = {
    network.count(pair => pair._2.isEmpty)
  }

  println(nFriends(testNet, "mary"))
  println(personWithMostFriends(testNet))
  println(peopleWithNoFriends(testNet))

  def socialConnection(network:Map[String, Set[String]], a:String, b:String):Boolean = {
    @tailrec
    def bfs(target:String, consideredPeople:Set[String], discoveredPeople:Set[String]):Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "mary", "jim"))

}
