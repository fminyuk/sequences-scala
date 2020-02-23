package org.nnc.sequences

import scala.collection.mutable

/**
  * Дерево ключей.
  *
  * @tparam E Тип символов.
  * @tparam V Тип значений.
  */
class Trie[E, V >: Null] {
  val children: mutable.Map[E, Trie[E, V]] = mutable.Map[E, Trie[E, V]]()
  var value: V = _
}
