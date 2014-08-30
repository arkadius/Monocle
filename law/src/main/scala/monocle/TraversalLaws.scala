package monocle

import org.scalacheck.Prop._
import org.scalacheck.{Arbitrary, Properties}

import scalaz.{Equal, Reader}
import scalaz.std.list._
import scalaz.syntax.equal._

object TraversalLaws {

  def apply[S: Arbitrary: Equal, A: Arbitrary: Equal](traversal: SimpleTraversal[S, A]) = new Properties("Traversal") {
    include(SetterLaws(traversal.asSetter))

    property("modifyK . id == id") = forAll { s: S =>
      traversal.modifyK(Reader.apply(identity)).run(s) === s
    }

    property("set - get all") = forAll { (s: S, a: A) =>
      traversal.getAll(traversal.set(a)(s)) === traversal.getAll(s).map(_ => a)
    }
  }

}
