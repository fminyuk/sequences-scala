package org.nnc.sequences
package fuzzy

import java.io.IOException

import org.nnc.sequences.levenshtein.{Automaton, UniTablesFactoryImpl}
import org.scalatest.FunSuite

import scala.io.Source

class FuzzyMatcherTest extends FunSuite {

  test("wiki dictionary") {
    val words = readWords
    val pattern = createPattern("профессор", 2)
    val sequences = createSequences(words)
//    val trie = TrieConverter.convert(TrieFactory.create(sequences))
    val trie = TrieIndexFactory.create(sequences)
    val matcher = new FuzzyMatcher(trie)
    var matches: Seq[FuzzyMatch[String]] = null
    val t0 = System.nanoTime()
    for (_ <- 0 until 25) {
      matches = matcher.search(pattern)
    }
    val t1 = System.nanoTime()
    println(s"Time: ${(t1 - t0) / 25} ns")
    println(s"Matches (${matches.length}):\n${matches.sortBy(_.cost).mkString("\n")}")
  }

  @throws[IOException]
  private def readWords: Array[String] = {
    val filename = classOf[FuzzyMatcherTest].getClassLoader.getResource("words.txt").getFile
    val source = Source.fromFile(filename, "utf-8")
    try {
      source.getLines.toArray
    } finally {
      source.close()
    }
  }

  private def createSequences(data: Seq[String]): Seq[Sequence[Char, String]] = {
    data.map(s => Sequence(s, s))
  }

  //noinspection SameParameterValue
  private def createPattern(str: String, n: Int) =
    new Automaton(str.toCharArray, n, new UniTablesFactoryImpl)
}
