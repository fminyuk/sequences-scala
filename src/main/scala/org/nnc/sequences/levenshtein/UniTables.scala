package org.nnc.sequences.levenshtein

/**
  * Таблицы для детерминированного автомата Левенштейна.
  *
  * @param n           Максимально допустимое редакционное расстояние.
  * @param start       Индекс стартового состояния.
  * @param stop        Индекс состояния остановки.
  * @param transitions Таблица стоимости состояний.
  * @param cost        Таблица переходов.
  */
class UniTables(val n: Int, val start: Int, val stop: Int, val transitions: Array[Array[Int]], val cost: Array[Array[Int]])
