package org.nnc.sequences.pattern

import org.scalatest.FunSuite

class PatternParserTest extends FunSuite {
  private val parser = new PatternParser {}

  test("pattern: required") {
    val src = "pattern (abc)"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(UnitRequired(UnitBlock("abc"))))
  }

  test("pattern: option") {
    val src = "pattern [abc]"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(UnitOption(UnitBlock("abc"))))
  }

  test("pattern: repeat") {
    val src = "pattern {abc}"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(UnitRepeat(UnitBlock("abc"), 0, Int.MaxValue)))
  }

  test("pattern: repeat{1,2}") {
    val src = "pattern {abc:1,2}"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(UnitRepeat(UnitBlock("abc"), 1, 2)))
  }

  test("pattern: seq") {
    val src = "pattern (a) [b]"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(UnitSeq(Seq(UnitRequired(UnitBlock("a")), UnitOption(UnitBlock("b"))))))
  }

  test("pattern: vars") {
    val src = "pattern (a) | (b)"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(UnitVars(Seq(UnitRequired(UnitBlock("a")), UnitRequired(UnitBlock("b"))))))
  }

  test("pattern: complex") {
    val src = "pattern ((a) [b]) | (c)"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(UnitVars(Seq(
      UnitRequired(UnitSeq(Seq(UnitRequired(UnitBlock("a")), UnitOption(UnitBlock("b"))))),
      UnitRequired(UnitBlock("c")))
    )))
  }
}
