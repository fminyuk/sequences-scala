package org.nnc.sequences.pattern

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

trait PatternParser extends RegexParsers {
  def pattern: Parser[Pattern] = PatternParser.PATTERN ~> PatternParser.IDENTIFIER ^^ { name => Pattern(name) }
}

object PatternParser {
  val PATTERN = "pattern"

  val IDENTIFIER: Regex = """[a-zA-Z_][a-zA-Z_0-9]*""".r
}