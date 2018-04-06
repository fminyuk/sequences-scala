package org.nnc.sequences
package fuzzy

import java.io.IOException

import org.nnc.sequences.levenshtein.{Automaton, UniTablesFactoryImpl}
import org.scalatest.FunSuite

import scala.io.Source

class FuzzyMatcherTest extends FunSuite {

  ignore("wiki dictionary") {
    val words = readWords
    val pattern = createPattern("профессор", 2)
    val trie = createTrie(words)
    val linear = new LinearTrieFactory[Char, String]().create(trie)
    val matcher = new FuzzyMatcher(linear)
    var matches: Seq[FuzzyMatch[String]] = null
    val t0 = System.nanoTime()
    for (i <- 0 until 25)
      matches = matcher.search(pattern)
    val t1 = System.nanoTime()
    println(s"Time: ${(t1 - t0) / 25} ns")
    println(s"Matches (${matches.length}):\n${matches.mkString("\n")}")
  }

  @throws[IOException]
  private def readWords = {
    val filename = classOf[FuzzyMatcherTest].getClassLoader.getResource("words.txt").getFile
    Source.fromFile(filename, "utf-8").getLines.toArray
  }

  private def createTrie(keys: Iterable[String]) =
    new TrieFactory[Char, String]().create(keys.map(k => new Sequence(k.toCharArray, k)))

  private def createPattern(string: String, n: Int) =
    new Automaton(string.toCharArray, n, new UniTablesFactoryImpl)
}
