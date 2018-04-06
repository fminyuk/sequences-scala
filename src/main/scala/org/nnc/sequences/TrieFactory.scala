package org.nnc.sequences

class TrieFactory[E, V >: Null] {
  def create(sequences: Iterable[Sequence[E, V]]): Trie[E, V] = {
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
