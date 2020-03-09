package org.nnc.sequences

import org.scalatest.FunSuite

class TrieFactoryTest extends FunSuite {
  test("empty set") {
    val trie = TrieFactory.create(SeqUtils.createSequences(Seq()))

    assert(trie.value == null)
    assert(trie.children.isEmpty)
  }

  test("empty key") {
    val trie = TrieFactory.create(SeqUtils.createSequences(Seq("")))

    assert(trie.value == "")
    assert(trie.children.isEmpty)
  }
}
