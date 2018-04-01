package org.nnc.sequences

import org.scalatest.FunSuite

class FunTest extends FunSuite {
  test("some test") {
    val stack = List(1, 2)
    val result = stack.size
    assert(result === 2)
  }
}
