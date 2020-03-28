package org.nnc.sequences

package object pattern {

  /**
    * Шаблон.
    *
    * @param unit Элемент.
    */
  final case class Pattern(unit: Unit)

  /**
    * Элемент шаблона.
    */
  sealed trait Unit

  /**
    * Варианты элементов.
    *
    * @param units Элементы.
    */
  final case class UnitVars(units: Seq[Unit]) extends Unit

  /**
    * Последовательность элементов.
    *
    * @param units Элементы.
    */
  final case class UnitSeq(units: Seq[Unit]) extends Unit

  /**
    * Элемент основанный на блоке.
    *
    * @param block Имя блока.
    */
  final case class UnitBlock(block: String) extends Unit

  /**
    * Обязательный элемент.
    *
    * @param unit Элемент.
    */
  final case class UnitRequired(unit: Unit) extends Unit

  /**
    * Необязательный элемент.
    *
    * @param unit Элемент.
    */
  final case class UnitOption(unit: Unit) extends Unit

  /**
    * Повторяющийся элемент.
    *
    * @param unit Элемент.
    * @param min Минимальное число повторов.
    * @param max Максимальное число повторов.
    */
  final case class UnitRepeat(unit: Unit, min: Int, max: Int) extends Unit

}
