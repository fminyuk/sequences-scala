package org.nnc.sequences

import org.scalatest.FunSuite

class FunTest extends FunSuite {
  test("some test") {
    val stack = List(1, 2)
    val result = stack.size
    assert(result === 2)
  }

  test("sequence") {
    val sequence = new Sequence("aaa".toCharArray, "aaa")
    print(sequence.chars(0))
  }

  test("trie") {
    val trie = new Trie[Char, String]
    trie.value = null

    print(trie.value)
  }
}
