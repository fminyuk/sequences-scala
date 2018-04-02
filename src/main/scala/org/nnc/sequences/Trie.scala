package org.nnc.sequences

import scala.collection.mutable

class Trie[E, V >: Null] {
  val children: Seq[(E, Trie[E, V])] => mutable.HashMap[E, Trie[E, V]] = mutable.HashMap[E, Trie[E, V]]
  var value: V = null
}
