package org.nnc.sequences.fuzzy

class FuzzyMatch[V](val value: V, val cost: Int) {
  override def toString: String = s"{$cost:$value}"
}
