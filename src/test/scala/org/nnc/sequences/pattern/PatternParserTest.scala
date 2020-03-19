package org.nnc.sequences.pattern

import org.scalatest.FunSuite

class PatternParserTest extends FunSuite {
  private val parser = new PatternParser {}

  test("pattern: required") {
    val src = "pattern (abc)"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(Seq(Unit(Unit.Required(), ContentBlock("abc")))))
  }

  test("pattern: option") {
    val src = "pattern [abc]"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(Seq(Unit(Unit.Option(), ContentBlock("abc")))))
  }

  test("pattern: repeat") {
    val src = "pattern {abc}"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(Seq(Unit(Unit.Repeat(0, Int.MaxValue), ContentBlock("abc")))))
  }

  test("pattern: repeat{1,2}") {
    val src = "pattern {abc:1,2}"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern(Seq(Unit(Unit.Repeat(1, 2), ContentBlock("abc")))))
  }
}
