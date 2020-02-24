package org.nnc.sequences

/**
  * Линейное представление дерева ключей (Trie).
  *
  * @param chars  Символы.
  * @param values Значения.
  * @param lens   Индекс - размер поддерева.
  * @tparam E Тип символов.
  * @tparam V Тип значений.
  */
case class TrieIndex[E, V >: Null](chars: Array[E],
                                   values: Array[V],
                                   lens: Array[Int])
