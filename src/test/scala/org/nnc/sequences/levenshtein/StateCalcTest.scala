package org.nnc.sequences.levenshtein

import org.scalatest.FunSuite

class StateCalcTest extends FunSuite {
  test("start") {
    val calc = new StateCalc(0)
    assert(calc.start == State(0, Position(0, 0)))
  }

  test("cost n=0") {
    val calc = new StateCalc(0)
    val s1 = calc.start
    assert(calc.cost(s1, -1) == 1)
    assert(calc.cost(s1, 0) == 0)
    assert(calc.cost(s1, 1) == 1)
    val s2 = s1.copyTo(1)
    assert(calc.cost(s2, 0) == 1)
    assert(calc.cost(s2, 1) == 0)
    assert(calc.cost(s2, 2) == 1)
  }

  test("cost n=1") {
    val calc = new StateCalc(1)
    val s1 = State(0, Position(0, 0))
    assert(calc.cost(s1, 0) == 0)
    assert(calc.cost(s1, 1) == 1)
    assert(calc.cost(s1, 2) == 2)
    val s2 = State(0, Position(0, 1), Position(1, 1))
    assert(calc.cost(s2, 0) == 1)
    assert(calc.cost(s2, 1) == 1)
    assert(calc.cost(s2, 2) == 2)
    val s3 = State(1, Position(0, 1), Position(2, 1))
    assert(calc.cost(s3, 0) == 1)
    assert(calc.cost(s3, 1) == 2)
    assert(calc.cost(s3, 2) == 1)
  }

  test("cost n=2") {
    val calc = new StateCalc(2)
    val s1 = State(0, Position(0, 1), Position(1, 1))
    assert(calc.cost(s1, 0) == 1)
    assert(calc.cost(s1, 1) == 1)
    assert(calc.cost(s1, 2) == 2)
    val s2 = State(2, Position(0, 2), Position(2, 1))
    assert(calc.cost(s2, 0) == 2)
    assert(calc.cost(s2, 1) == 2)
    assert(calc.cost(s2, 2) == 1)
    assert(calc.cost(s2, 3) == 2)
    assert(calc.cost(s2, 4) == 3)
  }

  test("next n=0") {
    val calc = new StateCalc(0)
    val s = calc.start
    val s0 = calc.next(s, bin("0"))
    val s1 = calc.next(s, bin("1"))
    assert(s0.positions.lengthCompare(0) == 0)
    assert(s1.positions.lengthCompare(1) == 0)
    assert(s1.positions(0) == Position(1, 0))
  }

  test("next n=1") {
    val calc = new StateCalc(1)
    val s = calc.start
    val s000 = calc.next(s, bin("000")).copyTo(0)
    val s001 = calc.next(s, bin("001")).copyTo(0)
    val s010 = calc.next(s, bin("010")).copyTo(0)
    val s011 = calc.next(s, bin("011")).copyTo(0)
    val s100 = calc.next(s, bin("100")).copyTo(0)
    val s101 = calc.next(s, bin("101")).copyTo(0)
    val s110 = calc.next(s, bin("110")).copyTo(0)
    val s111 = calc.next(s, bin("111")).copyTo(0)
    assert(s010.positions.lengthCompare(1) == 0)
    assert(s000.positions.lengthCompare(2) == 0)
    assert(s001.positions.lengthCompare(3) == 0)
    assert(s000 == s100)
    assert(s001 == s101)
    assert(s010 == s011)
    assert(s010 == s110)
    assert(s010 == s111)
    assert(s010 == s)
    val s000n000 = calc.next(s000, bin("000")).copyTo(0)
    val s000n001 = calc.next(s000, bin("001")).copyTo(0)
    val s000n010 = calc.next(s000, bin("010")).copyTo(0)
    val s000n011 = calc.next(s000, bin("011")).copyTo(0)
    val s000n100 = calc.next(s000, bin("100")).copyTo(0)
    val s000n101 = calc.next(s000, bin("101")).copyTo(0)
    val s000n110 = calc.next(s000, bin("110")).copyTo(0)
    val s000n111 = calc.next(s000, bin("111")).copyTo(0)
    assert(s000n000.positions.lengthCompare(0) == 0)
    assert(s000n001.positions.lengthCompare(0) == 0)
    assert(s000n010.positions.lengthCompare(1) == 0)
    assert(s000n011.positions.lengthCompare(1) == 0)
    assert(s000n100.positions.lengthCompare(1) == 0)
    assert(s000n101.positions.lengthCompare(1) == 0)
    assert(s000n110.positions.lengthCompare(2) == 0)
    assert(s000n111.positions.lengthCompare(2) == 0)
    val s001n000 = calc.next(s001, bin("000")).copyTo(0)
    val s001n001 = calc.next(s001, bin("001")).copyTo(0)
    val s001n010 = calc.next(s001, bin("010")).copyTo(0)
    val s001n011 = calc.next(s001, bin("011")).copyTo(0)
    val s001n100 = calc.next(s001, bin("100")).copyTo(0)
    val s001n101 = calc.next(s001, bin("101")).copyTo(0)
    val s001n110 = calc.next(s001, bin("110")).copyTo(0)
    val s001n111 = calc.next(s001, bin("111")).copyTo(0)
    assert(s001n000.positions.lengthCompare(0) == 0)
    assert(s001n001.positions.lengthCompare(1) == 0)
    assert(s001n010.positions.lengthCompare(1) == 0)
    assert(s001n011.positions.lengthCompare(2) == 0)
    assert(s001n100.positions.lengthCompare(1) == 0)
    assert(s001n101.positions.lengthCompare(2) == 0)
    assert(s001n110.positions.lengthCompare(2) == 0)
    assert(s001n111.positions.lengthCompare(3) == 0)
    val s100n000 = calc.next(s100, bin("000")).copyTo(0)
    val s100n001 = calc.next(s100, bin("001")).copyTo(0)
    val s100n010 = calc.next(s100, bin("010")).copyTo(0)
    val s100n011 = calc.next(s100, bin("011")).copyTo(0)
    val s100n100 = calc.next(s100, bin("100")).copyTo(0)
    val s100n101 = calc.next(s100, bin("101")).copyTo(0)
    val s100n110 = calc.next(s100, bin("110")).copyTo(0)
    val s100n111 = calc.next(s100, bin("111")).copyTo(0)
    assert(s100n000.positions.lengthCompare(0) == 0)
    assert(s100n001.positions.lengthCompare(0) == 0)
    assert(s100n010.positions.lengthCompare(1) == 0)
    assert(s100n011.positions.lengthCompare(1) == 0)
    assert(s100n100.positions.lengthCompare(1) == 0)
    assert(s100n101.positions.lengthCompare(1) == 0)
    assert(s100n110.positions.lengthCompare(2) == 0)
    assert(s100n111.positions.lengthCompare(2) == 0)
  }

  test("next n=2") {
    val calc = new StateCalc(2)
    val s = calc.start
    val s00000 = calc.next(s, bin("00000")).copyTo(0)
    val s00001 = calc.next(s, bin("00001")).copyTo(0)
    val s00010 = calc.next(s, bin("00010")).copyTo(0)
    val s00011 = calc.next(s, bin("00011")).copyTo(0)
    val s00100 = calc.next(s, bin("00100")).copyTo(0)
    assert(s00000.positions.lengthCompare(2) == 0)
    assert(s00000.positions(0) == Position(-1, 1))
    assert(s00000.positions(1) == Position(0, 1))
    assert(s00001.positions.lengthCompare(3) == 0)
    assert(s00001.positions(0) == Position(-1, 1))
    assert(s00001.positions(1) == Position(0, 1))
    assert(s00001.positions(2) == Position(2, 2))
    assert(s00010.positions.lengthCompare(3) == 0)
    assert(s00010.positions(0) == Position(-1, 1))
    assert(s00010.positions(1) == Position(0, 1))
    assert(s00010.positions(2) == Position(1, 1))
    assert(s00011 == s00010)
    assert(s00100 == s)
  }

  def bin(string: String) = Integer.parseInt(string, 2)
}
