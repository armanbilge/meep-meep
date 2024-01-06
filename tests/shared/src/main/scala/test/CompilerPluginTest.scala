package test

import meep.->

class CompilerPluginTest {
  def capture[A, B](f: A -> B): Unit = ()

  // this should fail to compile
  // TODO need a way to test this
  // def f(n: Int) = capture[Int, Int](_ + n)
}
