package lectures.part3funcprog

import java.awt.desktop.SystemSleepEvent
import scala.util.Random

object Sequences extends App{

  // Seq
  val aSequence = Seq(1,2,3,5,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))
  print(aSequence.sorted)

  // Ranges
  val aRange:Seq[Int] = 1 to 10
  aRange.foreach(println)

  // lists
  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 89
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  // arrays
  val numbers = Array(1,2,3,4)
  val treeElements = Array.ofDim[Int](3)
  treeElements.foreach(println)

  // mutation
  numbers(2) = 0
  println(numbers.mkString(" "))

  val numberSeq: Seq[Int] = numbers
  println(numberSeq)

  // vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists
  val MaxRuns = 1000
  val MaxCapacity = 1000000
  def getWriteTime(collection:Seq[Int]):Double = {
    val r = new Random

    val times = for{
      it <- 1 to MaxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(MaxCapacity), r.nextInt())
      System.nanoTime()-currentTime
    }

    times.sum * 1.0 / MaxRuns
  }

  val numbersList = (1 to MaxCapacity).toList
  val numbersVector = (1 to MaxCapacity).toVector

  // keeps reference to tail
  // updating a element in the middle takes long
  println(getWriteTime(numbersList))
  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
}


