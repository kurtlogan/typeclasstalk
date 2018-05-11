package example

import scala.annotation.{implicitAmbiguous, implicitNotFound}

@implicitNotFound("Show typeclass for ${A} cannot be found")
@implicitAmbiguous("Mutliple instance for show found")
trait Show[A] {
  def show(a: A): String
}

trait Even[A] {
  def isEven(a: A): Boolean
}

object Show {
  implicit val stringShow: Show[String] = (a: String) => s"string: $a"
  implicit val intShow: Show[Int] = (i: Int) => s"int: $i"

  implicit def listShow[A: Show]: Show[List[A]] = (xs: List[A]) =>
    xs.map(Show[A].show).mkString("list(,", ", ", ")")

  def apply[A: Show] = implicitly[Show[A]]

  object ops {
    implicit class ShowOps[A: Show](a: A) {
      def show: String = Show[A].show(a)
    }
  }
}

object Even {

  implicit val intShow: Even[Int] = (i: Int) => i % 2 == 0

  implicit def listShow[A: Even]: Even[List[A]] = (xs: List[A]) =>
    xs.forall(implicitly[Even[A]].isEven)

  def apply[A: Even] = implicitly[Even[A]]

  object ops {
    implicit class EvenOps[A: Even](a: A) {
      def isEven: Boolean = Even[A].isEven(a)
    }
  }
}

object Typeclass extends App {

  import Show.ops._

  def fromImplicitScope[A](implicit a: A): A = a

  def log[A: Show](a: A) = {
    println(a.show)
  }

  log("hello")
  log(42)

  log(List(1, 2, 3, 4))

  def logIsEven[A: Even](a: A) = println(implicitly[Even[A]].isEven(a))

  logIsEven(2)
  logIsEven(1)
  logIsEven(List(1, 2, 3, 4))
  logIsEven(List(2, 4))
}
