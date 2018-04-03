package org.nnc.sequences

class TrieFactory[E, V >: Null] {
  def create(sequences: Seq[Sequence[E, V]]) = {
    val root = new Trie[E, V]

    for (sequence <- sequences) {
      var node = root
      for (char <- sequence.chars) {
        node = node.children.getOrElseUpdate(char, new Trie[E, V])
      }

      node.value = sequence.value
    }

    root
  }
}
