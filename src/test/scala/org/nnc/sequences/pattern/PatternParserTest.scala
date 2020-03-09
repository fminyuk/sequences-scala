package org.nnc.sequences.pattern

import org.scalatest.FunSuite

class PatternParserTest extends FunSuite {
  private val parser = new PatternParser {}

  test("pattern") {
    val src = "pattern some"

    val res = parser.parseAll(parser.pattern, src)

    assert(res.successful)
    assert(res.get == Pattern("some"))
  }
}
