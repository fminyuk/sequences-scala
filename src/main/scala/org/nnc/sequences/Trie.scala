package org.nnc.sequences

import scala.collection.mutable

class Trie[E, V >: Null] {
  val children = mutable.Map[E, Trie[E, V]]()
  var value: V = null
}
