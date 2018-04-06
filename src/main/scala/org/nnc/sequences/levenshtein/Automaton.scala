package org.nnc.sequences.levenshtein

class Automaton[E](sequence: Array[E], n: Int, uniTablesFactory: UniTablesFactory) {
  private val uni = uniTablesFactory.create(n)

  def start: AutomatonState[E] = new AutomatonStateImpl(uni.start, 0)

  private def calcZ(char: E, position: Int) = {
    var z = 0
    for (i <- (position - n).max(0) to (position + n).min(sequence.length - 1)) {
      if (char == sequence(i)) {
        z |= 1 << (n - (i - position))
      }
    }
    z
  }

  private class AutomatonStateImpl(state: Int, position: Int) extends AutomatonState[E] {
    override def next(char: E): AutomatonState[E] =
      new AutomatonStateImpl(uni.transitions(state)(calcZ(char, position)), position + 1)

    override def cost: Int = {
      val diff = sequence.length - position
      val cost = if (diff >= -n && diff <= n) uni.cost(state)(diff + n) else n + 1
      if (cost == n + 1) -1 else cost
    }

    override def nonStop: Boolean = state != uni.stop
  }
}
