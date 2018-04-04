package org.nnc.sequences.levenshtein

import org.scalatest.FunSuite

class StateTest extends FunSuite {
  test("toString") {
    val s = State(3, Seq(Position(1, 3), Position(3, 2), Position(4, 2)))
    assert(s.toString == "3:{3#2,4#2,1#3}")
  }

  test("==") {
    val s1 = State(3, Seq(Position(1, 3), Position(3, 2), Position(4, 2)))
    val s2 = State(3, Seq(Position(4, 2), Position(1, 3), Position(3, 2)))
    assert(s1 == s2)
  }

  test("reduce") {
    val s1 = State(0, Seq(Position(0, 0), Position(1, 2), Position(-1, 1), Position(-2, 5), Position(2, 1)))
    assert(s1 == State(0, Seq(Position(0, 0))))

    val s2 = State(1, Seq(Position(1, 2), Position(2, 3), Position(3, 3)))
    assert(s2 == State(1, Seq(Position(1, 2), Position(3, 3))))
  }

  test("move") {
    val s1 = State(3, Seq(Position(1, 3), Position(3, 2), Position(4, 2)))
    val s2 = s1.move(10)
    val s3 = s2.move(2).move(-12)
    assert(s1 != s2)
    assert(s2 != s3)
    assert(s1 == s3)
  }
}
