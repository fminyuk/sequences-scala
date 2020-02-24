package org.nnc.sequences

import scala.collection.mutable
import scala.reflect.ClassTag

object TrieIndexFactory {
  def create[E: ClassTag, V >: Null : ClassTag](sequences: Seq[Sequence[E, V]])(implicit ord: Ordering[E]): TrieIndex[E, V] = {
    import math.Ordering.Implicits.seqOrdering

    val sorted = sequences.sortBy(_.chars)

    createFromSorted(sorted)
  }

  private def createFromSorted[E: ClassTag, V >: Null : ClassTag](sorted: Seq[Sequence[E, V]])
                                                                 (implicit ord: Ordering[E]): TrieIndex[E, V] = {
    val chars = new mutable.ArrayBuffer[E]
    val values = new mutable.ArrayBuffer[V]
    val lens = new mutable.ArrayBuffer[Int]

    chars += null.asInstanceOf[E]
    values += null
    lens += 0

    val stack = mutable.Stack[Int]()
    for (cur <- sorted) {
      while (stack.size > cur.chars.size) {
        val pos = stack.pop();
        lens(pos) = chars.size - pos - 1
      }

      while (stack.nonEmpty && chars(stack.top) != cur.chars(stack.size - 1)) {
        val pos = stack.pop()
        lens(pos) = chars.size - pos - 1
      }

      for (c <- cur.chars.drop(stack.size)) {
        stack.push(chars.size)
        chars += c
        values += null
        lens += 0
      }

      values(values.size - 1) = cur.value
    }

    lens(0) = lens.size - 1
    while (stack.nonEmpty) {
      val pos = stack.pop()
      lens(pos) = chars.size - pos - 1
    }

    TrieIndex(chars.toArray, values.toArray, lens.toArray)
  }
}
