package org.nnc.sequences

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

object TrieConverter {
  def convert[E: ClassTag, V >: Null : ClassTag](trie: Trie[E, V]): TrieIndex[E, V] = {
    val chars = new ArrayBuffer[E]
    val values = new ArrayBuffer[V]
    val lens = new ArrayBuffer[Int]
    fill(chars, values, lens, null.asInstanceOf[E], trie)
    TrieIndex(chars.toArray, values.toArray, lens.toArray)
  }

  private def fill[E, V >: Null](chars: ArrayBuffer[E],
                                 values: ArrayBuffer[V],
                                 lens: ArrayBuffer[Int],
                                 char: E,
                                 trie: Trie[E, V]): Int = {
    val cur = lens.size
    chars += char
    values += trie.value
    lens += 0
    var len = 0
    for (child <- trie.children) {
      len += fill(chars, values, lens, child._1, child._2)
    }
    lens(cur) = len
    len + 1
  }
}
