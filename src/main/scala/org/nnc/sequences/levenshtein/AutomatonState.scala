package org.nnc.sequences.levenshtein

trait AutomatonState[E] {
  def next(char: E): AutomatonState[E]

  def cost: Int

  def isStop: Boolean
}
