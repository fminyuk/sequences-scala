package org.nnc.sequences.pattern

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

trait PatternParser extends RegexParsers {
  def pattern: Parser[Pattern] = PatternParser.PATTERN ~> unitList ^^ {
    units => Pattern(units)
  }

  def unit: Parser[Unit] = unitOption | unitRequired | unitRepeat

  def unitOption: Parser[Unit] = "[" ~> content <~ "]" ^^ {
    content => Unit(Unit.Option(), content)
  }

  def unitRequired: Parser[Unit] = "(" ~> content <~ ")" ^^ {
    content => Unit(Unit.Required(), content)
  }

  def unitRepeat: Parser[Unit] = "{" ~> content ~ opt("|" ~> slice) <~ "}" ^^ {
    case content ~ None => Unit(Unit.Repeat(0, Int.MaxValue), content)
    case content ~ Some((min, max)) => Unit(Unit.Repeat(min, max), content)
  }

  def unitList: Parser[Seq[Unit]] = rep1(unit)

  def content: Parser[Content] = contentBlock | contentUnits

  def contentBlock: Parser[Content] = PatternParser.IDENTIFIER ^^ {
    name => ContentBlock(name)
  }

  def contentUnits: Parser[Content] = unitList ^^ {
    units => ContentUnits(units)
  }

  def slice: Parser[(Int, Int)] = opt(PatternParser.INT) ~ ":" ~ opt(PatternParser.INT) ^^ {
    case None ~ _ ~ None => (0, Int.MaxValue)
    case None ~ _ ~ Some(max) => (0, max.toInt)
    case Some(min) ~ _ ~ None => (min.toInt, Int.MaxValue)
    case Some(min) ~ _ ~ Some(max) => (min.toInt, max.toInt)
  }
}

object PatternParser {
  val PATTERN = "pattern"

  val IDENTIFIER: Regex = """[a-zA-Z_][a-zA-Z_0-9]*""".r
  val INT: Regex = """\d+""".r
}