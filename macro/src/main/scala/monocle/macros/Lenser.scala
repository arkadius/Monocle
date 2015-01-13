package monocle.macros

import monocle.Lens
import monocle.macros.internal.MacroImpl

class Lenser[A] {
  def apply[B](field: A => B): Lens[A, B] = macro MacroImpl.lenser_impl[A, B]
}

object Lenser {
  def apply[A] = new Lenser[A]
}


