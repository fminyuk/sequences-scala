package org.nnc.sequences

import org.scalatest.FunSuite

class TrieIndexFactoryTest extends FunSuite {
  test("some") {
    val seqs = createSequences(Array(
      "bde",
      "abc",
      "aabc",
      "abd"
    ))

    val res1 = TrieIndexFactory.create(seqs)

    println(res1.chars.map(_.toString).reduce(_ + " " + _))
    println(res1.lens.map(_.toString).reduce(_ + " " + _))

    val res2 = TrieConverter.convert(TrieFactory.create(seqs))

    println(res2.chars.map(_.toString).reduce(_ + " " + _))
    println(res2.lens.map(_.toString).reduce(_ + " " + _))

    //    println(res.map(_.value).reduce(_ + " " + _))
  }

  private def createSequences(data: Array[String]): Array[Sequence[Char, String]] = {
    data.map(s => Sequence(s, s))
  }
}
