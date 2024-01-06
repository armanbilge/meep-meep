package meep

import scala.scalajs.js

trait ->[-A, +B] extends js.Function1[A, B] {
  def apply(a: A): B
}
