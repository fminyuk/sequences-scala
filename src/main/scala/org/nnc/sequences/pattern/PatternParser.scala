package org.nnc.sequences.pattern

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

trait PatternParser extends RegexParsers {
  def pattern: Parser[Pattern] = PatternParser.PATTERN ~> PatternParser.IDENTIFIER ~ "=" ~ unitVars ^^ {
    case name ~ _ ~ unit => Pattern(name, unit)
  }

  def unitVars: Parser[Unit] = rep1sep(unitSeq, "|") ^^ {
    case head +: Seq() => head
    case units => UnitVars(units)
  }

  def unitSeq: Parser[Unit] = rep1(unitSimple) ^^ {
    case head +: Seq() => head
    case units => UnitSeq(units)
  }

  def unitSimple: Parser[Unit] = unitOption | unitRequired | unitRepeat

  def unitRequired: Parser[Unit] = "(" ~> unitContent <~ ")" ^^ {
    content => UnitRequired(content)
  }

  def unitOption: Parser[Unit] = "[" ~> unitContent <~ "]" ^^ {
    content => UnitOption(content)
  }

  def unitRepeat: Parser[Unit] = "{" ~> unitContent ~ opt(":" ~> slice) <~ "}" ^^ {
    case content ~ None => UnitRepeat(content, 0, Int.MaxValue)
    case content ~ Some((min, max)) => UnitRepeat(content, min, max)
  }

  def unitContent: Parser[Unit] = unitBlock | unitVars

  def unitBlock: Parser[Unit] = PatternParser.IDENTIFIER ^^ {
    name => UnitBlock(name)
  }

  def slice: Parser[(Int, Int)] = opt(PatternParser.INT) ~ "," ~ opt(PatternParser.INT) ^^ {
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