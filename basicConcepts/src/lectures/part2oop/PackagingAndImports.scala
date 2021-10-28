package lectures.part2oop

import playground.{PrinceCharming, Cinderella => princesa}
import java.sql.{Date => SQLDate}

// you can rename a class name with => operator.

// import "imports" a package, you could also use the fully qualified naem specifying all the path.
// packages are in hierarchy
// matching folder structures.

// classes in the same package are not necessary to import in Scala.


object PackagingAndImports extends App {
  val princess = new princesa

  val contador = new Counter(initVal = 1)


  // package object
  // you can access the method, vals in all the package.
  sayHello

  // imports
  val prince = new PrinceCharming

  val mySQLDate= new SQLDate(2018, 5,4)

  // there are some predefined imports, like println and some java imports.
}
