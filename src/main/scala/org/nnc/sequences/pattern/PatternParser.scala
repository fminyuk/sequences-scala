package org.nnc.sequences.pattern

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

trait PatternParser extends RegexParsers {
  def pattern: Parser[Pattern] = PatternParser.PATTERN ~> PatternParser.IDENTIFIER ~ ("=" ~> rep1(unit)) ^^ {
    case name ~ units => Pattern(name, units)
  }

  def unit: Parser[Unit] = unitOption | unitRequired | unitRepeat

  def unitOption: Parser[Unit] = "[" ~> content <~ "]" ^^ {
    content => Unit(Unit.Option(), content)
  }

  def unitRequired: Parser[Unit] = "(" ~> content <~ ")" ^^ {
    content => Unit(Unit.Required(), content)
  }

  def unitRepeat: Parser[Unit] = "{" ~> content <~ "}" ^^ {
    content => Unit(Unit.Repeat(0, Int.MaxValue), content)
  }

  def content: Parser[Content] = contentBlock | contentUnits

  def contentBlock: Parser[Content] = PatternParser.IDENTIFIER ^^ {
    name => ContentBlock(name)
  }

  def contentUnits: Parser[Content] = rep1(unit) ^^ {
    units => ContentUnits(units)
  }
}

object PatternParser {
  val PATTERN = "pattern"

  val IDENTIFIER: Regex = """[a-zA-Z_][a-zA-Z_0-9]*""".r
  val INT: Regex = """\d+""".r
}