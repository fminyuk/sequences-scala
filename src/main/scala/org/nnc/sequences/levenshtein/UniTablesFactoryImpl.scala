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

    UniTables(n, start, stop, transitions, cost)
  }

  private def calcTransitions(calc: StateCalc): mutable.Map[State, Array[State]] = {
    val transitions = mutable.HashMap[State, Array[State]]()
    val states = mutable.Queue[State]()
    states.enqueue(calc.start)
    while (states.nonEmpty) {
      val state = states.dequeue
      val next = (0 until (1 << (2 * calc.n + 1))).map(calc.next(state, _).copyTo(0)).toArray
      transitions(state) = next
      states ++= next.filter(!transitions.contains(_))
    }

    transitions
  }

  private def calcState2Index(states: Iterable[State]): Map[State, Int] =
    states.zipWithIndex.toMap

  private def calcStopState(transitions: Array[Array[Int]]): Int =
    transitions.zipWithIndex.find(s => s._1.forall(_ == s._2)).get._2
}
