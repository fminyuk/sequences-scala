package org.nnc.sequences.levenshtein

/**
  * Таблицы для детерминированного автомата Левенштейна.
  *
  * @param n Максимально допустимое редакционное расстояние.
  * @param start Индекс стартового состояния.
  * @param stop Индекс состояния остановки.
  * @param transitions Таблица стоимости состояний.
  * @param cost Таблица переходов.
  */
class UniTables(n: Int, start: Int, stop: Int, transitions: Array[Array[Int]], cost: Array[Array[Int]])
