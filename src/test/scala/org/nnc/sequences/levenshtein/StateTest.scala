package org.nnc.sequences.levenshtein

import org.scalatest.FunSuite

class StateTest extends FunSuite {
  test("toString") {
    val s = State(3, Position(1, 3), Position(3, 2), Position(4, 2))
    assert(s.toString == "3:{3#2,4#2,1#3}")
  }

  test("==") {
    val s1 = State(3, Position(1, 3), Position(3, 2), Position(4, 2))
    val s2 = State(3, Position(4, 2), Position(1, 3), Position(3, 2))
    assert(s1 == s2)
  }

  test("reduce") {
    val s1 = State(0, Position(0, 0), Position(1, 2), Position(-1, 1), Position(-2, 5), Position(2, 1))
    assert(s1 == State(0, Position(0, 0)))

    val s2 = State(1, Position(1, 2), Position(2, 3), Position(3, 3))
    assert(s2 == State(1, Position(1, 2), Position(3, 3)))
  }

  test("copy to") {
    val s1 = State(3, Position(1, 3), Position(3, 2), Position(4, 2))
    val s2 = s1.copyTo(5)
    val s3 = s2.copyTo(2).copyTo(3)
    assert(s1 != s2)
    assert(s2 != s3)
    assert(s1 == s3)

    println((if (true) Some(5) else None) ++: Seq(1, 2))
  }
}
