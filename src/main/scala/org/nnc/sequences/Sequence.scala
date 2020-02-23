package org.nnc.sequences

/**
  * Последовательность со значением.
  *
  * @param chars Последовательность символов.
  * @param value Значение.
  * @tparam E Тип символа.
  * @tparam V Тип значения.
  */
case class Sequence[E, V](chars: Array[E],
                          value: V)

