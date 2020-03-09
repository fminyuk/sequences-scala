package org.nnc.sequences

object SeqUtils {
  /**
    * Создать последовательности из строк.
    *
    * @param data Строки.
    * @return Последовательности.
    */
  def createSequences(data: Seq[String]): Seq[Sequence[Char, String]] = {
    data.map(s => Sequence(s, s))
  }
}
