package org.nnc.sequences

import org.scalatest.FunSuite

class TrieFactoryTest extends FunSuite {
  test("empty set") {
    val trie = TrieFactory.create(List())

    assert(trie.value == null)
    assert(trie.children.isEmpty)
  }

  test("empty key") {
    val trie = TrieFactory.create(List(seq("")))

    assert(trie.value == "")
    assert(trie.children.isEmpty)
  }

  //noinspection SameParameterValue
  private def seq(key: String): Sequence[Char, String] = {
    Sequence(key, key)
  }
}
