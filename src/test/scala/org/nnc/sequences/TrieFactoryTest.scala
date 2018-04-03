package org.nnc.sequences

import org.scalatest.FunSuite

class TrieFactoryTest extends FunSuite {
  private val factory = new TrieFactory[Char, String]

  test("empty set") {
    val trie = factory.create(List())

    assert(trie.value == null)
    assert(trie.children.isEmpty)
  }

  test("empty key") {
    val trie = factory.create(List(seq("")))

    assert(trie.value == "")
    assert(trie.children.isEmpty)
  }

  private def seq(key: String) = new Sequence(key.toCharArray, key)
}
