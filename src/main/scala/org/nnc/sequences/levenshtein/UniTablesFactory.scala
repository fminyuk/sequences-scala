package org.nnc.sequences.levenshtein

/**
  * Фабрика создания таблиц детерминированного автомата Левенштейна.
  */
trait UniTablesFactory {
  /**
    * Возвращает таблицы для детерминированного автомата Левенштейна.
    *
    * @param n Максимально допустимое редакционного расстояние.
    * @return Таблицы для детерминированного автомата Левенштейна для заданного n.
    */
  def create(n: Int): UniTables
}
