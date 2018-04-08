package org.nnc.sequences

/**
  * Линейное представление дерева ключей (Trie).
  *
  * @param chars  Символы.
  * @param values Значения.
  * @param lens   Индекс -размер поддерева.
  * @tparam E Тип символов.
  * @tparam V Тип значений.
  */
class TrieIndex[E, V >: Null](val chars: Array[E], val values: Array[V], val lens: Array[Int])
