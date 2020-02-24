package org.nnc.sequences.fuzzy

case class FuzzyMatch[V](value: V, cost: Int) {
  override def toString: String = s"{$cost:$value}"
}
