package org.nnc.sequences.levenshtein

import scala.collection.mutable

class UniTablesFactoryImpl extends UniTablesFactory {
  override def create(n: Int): UniTables = {
    val calc = new StateCalc(n)

    val transitionsStates = calcTransitions(calc)
    val state2index = calcState2Index(transitionsStates.keys)

    // Start
    val start = state2index(calc.start)

    // Переходы
    val transitions = Array.ofDim[Int](transitionsStates.size, 1 << (2 * n + 1))
    for (entry <- transitionsStates) {
      val stateTransitions = transitions(state2index(entry._1))
      for (z <- 0 until (1 << (2 * n + 1))) {
        stateTransitions(z) = state2index(entry._2(z))
      }
    }

    // Стоимость
    val cost = Array.ofDim[Int](transitionsStates.size, 2 * n + 1)
    for (entry <- state2index) {
      val stateCost = cost(entry._2)
      for (i <- -n to n) {
        stateCost(i + n) = calc.cost(entry._1, i)
      }
    }

    // Состояние остановки.
    val stop = calcStopState(transitions)

    new UniTables(n, start, stop, transitions, cost)
  }


  private def calcTransitions(calc: StateCalc) = {
    val transitions = mutable.HashMap[State, Array[State]]()
    val states = mutable.Queue[State]()
    states.enqueue(calc.start)
    while (states.nonEmpty) {
      val state = states.dequeue
      transitions(state) = new Array[State](1 << (2 * calc.n + 1))
      for(z <- 0 until (1 << (2 * calc.n + 1))){
        val next = calc.next(state, z).copyTo(0)
        transitions(state)(z) = next
      }
    }

    transitions
  }

  private def calcState2Index(states: Iterable[State]) =
    states.zipWithIndex.toMap

  private def calcStopState(transitions: Array[Array[Int]]) =
    transitions.zipWithIndex.find(s => s._1.forall(i => i == s._2)).get._2
}
