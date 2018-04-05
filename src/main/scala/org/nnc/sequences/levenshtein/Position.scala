package org.nnc.sequences.levenshtein

import java.lang.Math.abs

/**
  * Позиция - состояние недетерминированного автомата Левенштейна.
  *
  * @param i Индекс.
  * @param e Ошибка.
  */
final case class Position(i: Int, e: Int) {
  override def toString: String = s"$i#$e";

  def isSubsumes(p: Position) = abs(i - p.i) <= p.e - e
}
