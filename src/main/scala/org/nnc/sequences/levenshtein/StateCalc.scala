package org.nnc.sequences.levenshtein

class StateCalc(val n: Int) {
  def start: State = State(0, Position(0, 0))

  def next(state: State, z: Int): State = {
    val positions = state.positions.flatMap(p => getElementary(p, z >> (state.i - p.i + p.e)))
    State(state.i + 1, positions)
  }

  def cost(state: State, i: Int): Int = ((n + 1) +: state.positions.map(p => p.e + (i - p.i).abs)).min

  private def getElementary(position: Position, z: Int): Seq[Position] = getJ(z, n - position.e) match {
    case 0 => Seq(Position(position.i + 1, position.e))
    case j => {
      if (j > 0) Some(Position(position.i + j + 1, position.e + j)) else None
    } ++: {
      if (position.e < n) {
        Seq(Position(position.i, position.e + 1), Position(position.i + 1, position.e + 1))
      } else Seq()
    }
  }

  private def getJ(z: Int, k: Int): Int = (0 to k).find(i => (z & (1 << (k - i))) != 0).getOrElse(-1)
}
