package org.nnc.sequences.levenshtein

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Seq

/**
  * Состояние детерминированного автомата Левенштейна, суперпозиция состояний недетерминированного автомата.
  *
  * @param i         Индекс.
  * @param positions Позиции - состояния недетерминированного автомата.
  */
final class State private(val i: Int, val positions: Seq[Position]) {
  def copyTo(i: Int) = new State(i, positions.map(p => Position(p.i + i - this.i, p.e)))

  override def toString: String = s"$i:{${positions.mkString(",")}}"

  override def hashCode(): Int = {
    val state = Seq(positions, i)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def equals(other: Any): Boolean = other match {
    case that: State => positions == that.positions && i == that.i
    case _ => false
  }
}

object State {
  def apply(i: Int, positions: Iterable[Position]): State = {
    val base = Position(i, 0)

    val filtered = positions.filter(p => base.isSubsumes(p))
    val ordered = filtered.toSeq.sortBy(p => (p.e, p.i))

    val result = ArrayBuffer[Position]()
    for (position <- ordered) {
      if (result.forall(p => !p.isSubsumes(position))) {
        result.append(position)
      }
    }

    new State(i, result)
  }

  def apply(i: Int, positions: Position*): State = apply(i, positions.toIterable)
}