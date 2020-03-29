package org.nnc.sequences.pattern

import org.scalatest.FunSuite

class PatternParserTest extends FunSuite {
  private val parser = new PatternParser {}

  test("pattern: required") {
    val src = "pattern x = (abc)"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern("x", UnitRequired(UnitBlock("abc"))))
  }

  test("pattern: option") {
    val src = "pattern x = [abc]"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern("x", UnitOption(UnitBlock("abc"))))
  }

  test("pattern: repeat") {
    val src = "pattern x = {abc}"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern("x", UnitRepeat(UnitBlock("abc"), 0, Int.MaxValue)))
  }

  test("pattern: repeat{1,2}") {
    val src = "pattern x = {abc:1,2}"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern("x", UnitRepeat(UnitBlock("abc"), 1, 2)))
  }

  test("pattern: seq") {
    val src = "pattern x = (a) [b]"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern("x", UnitSeq(Seq(UnitRequired(UnitBlock("a")), UnitOption(UnitBlock("b"))))))
  }

  test("pattern: vars") {
    val src = "pattern x = (a) | (b)"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern("x", UnitVars(Seq(UnitRequired(UnitBlock("a")), UnitRequired(UnitBlock("b"))))))
  }

  test("pattern: complex") {
    val src = "pattern x = ((a) [b]) | (c)"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern("x", UnitVars(Seq(
      UnitRequired(UnitSeq(Seq(UnitRequired(UnitBlock("a")), UnitOption(UnitBlock("b"))))),
      UnitRequired(UnitBlock("c")))
    )))
  }
}
