package org.nnc.sequences.levenshtein

import org.scalatest.FunSuite

class PositionTest extends FunSuite {
  test("toString") {
    assert(Position(1, 2).toString() == "1#2")
    assert(Position(3, 7).toString() == "3#7")
    assert(Position(0, 5).toString() == "0#5")
  }

  test("==") {
    assert(Position(1, 2) == Position(1, 2))
    assert(Position(3, 7) == Position(3, 7))
    assert(Position(3, 7) != Position(1, 7))
    assert(Position(3, 7) != Position(3, 6))
  }
}
