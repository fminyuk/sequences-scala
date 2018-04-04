package org.nnc.sequences.levenshtein

import scala.collection.mutable.ArrayBuffer

trait State {
  def i: Int

  def positions: Array[Position]

  def move(delta: Int): State

  override def toString: String = s"$i:{${positions.mkString(",")}}"
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

    StateImpl(i, result.toArray)
  }

  private case class StateImpl(i: Int, positions: Array[Position]) extends State {
    override def move(delta: Int): State = StateImpl(i + delta, positions.map(p => Position(p.i + delta, p.e)))
  }

}