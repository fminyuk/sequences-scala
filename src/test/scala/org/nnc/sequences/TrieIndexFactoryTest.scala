package org.nnc.sequences

import org.scalatest.FunSuite

class TrieIndexFactoryTest extends FunSuite {
  test("some") {
    val seqs = SeqUtils.createSequences(Seq(
      "bde",
      "abc",
      "aabc",
      "abd"
    ))

    val res1 = TrieIndexFactory.create(seqs)
    val res2 = TrieConverter.convert(TrieFactory.create(seqs))

    assert(res1.chars sameElements res2.chars)
    assert(res1.values sameElements res2.values)
    assert(res1.lens sameElements res2.lens)
  }
}
