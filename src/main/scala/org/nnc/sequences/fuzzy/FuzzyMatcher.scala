package org.nnc.sequences
package fuzzy

import org.nnc.sequences.levenshtein.{Automaton, AutomatonState}

import scala.collection.mutable.ArrayBuffer

class FuzzyMatcher[E, V >: Null](trie: TrieIndex[E, V]) {
  def search(pattern: Automaton[E]): Seq[FuzzyMatch[V]] = {
    val matches = ArrayBuffer[FuzzyMatch[V]]()
    search(0, pattern.start, matches)
    matches.toSeq
  }

  private def search(pos: Int, pointer: AutomatonState[E], matches: ArrayBuffer[FuzzyMatch[V]]): Int = {
    val nodeValue = trie.values(pos)
    if (nodeValue != null) {
      val cost = pointer.cost
      if (cost >= 0) {
        matches += new FuzzyMatch(nodeValue, cost)
      }
    }
    val size = 1 + trie.lens(pos)
    if (pointer.nonStop) {
      var offset = 1
      while (offset < size) {
        val next = pointer.next(trie.chars(pos + offset))
        offset += search(pos + offset, next, matches)
      }
    }
    size
  }
}
