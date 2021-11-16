package lectures.part3funcprog

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("super failure"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod() : String = throw  new RuntimeException("no string for you buster!")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  val anotherPotentialFailure = Try{
    // code that might fail
  }
  // utilities
  println(potentialFailure.isSuccess)
  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()) orElse Try(backupMethod())
  println(fallbackTry)

  // if you design the API
  def betterUnsafeMethod():Try[String] = Failure(new RuntimeException)
  def betterBackupMethod():Try[String] = Success("a valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // Try also have map, flatMap and filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap( x => Success(x*10)))
  println(aSuccess.filter(_ > 3))

  // => for comprehensions
  val hostname = "localhost"
  val port  = "8080"
  def renderHtml(page:String): Unit = println(page)

  class Connection {
    def get(url:String) :String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url:String):Try[String] = Try(get(url))
  }



  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host:String, port:String): Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("some else took the port")
    }

    def getSafeConnection(host:String, port:String):Try[Connection] = Try(getConnection(host, port))
  }



  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHtml)

    // shorthand version
  HttpService.getSafeConnection(hostname, port)
  .flatMap(connection => connection.getSafe("/home"))
  .foreach(renderHtml)

  // for comprehension version
  for {
    connection <- HttpService.getSafeConnection(hostname, port)
    html <- connection.getSafe("/home")
  } renderHtml(html)
}
