package org.nnc.sequences

package object pattern {

  /**
    * Шаблон.
    *
    * @param name  Имя шаблона.
    * @param units Элементы.
    */
  final case class Pattern(name: String, units: Seq[Unit])

  /**
    * Элемент шаблона.
    *
    * @param unitType Тип элемента.
    * @param content  Содержимое элемиента.
    */
  final case class Unit(unitType: Unit.Type, content: Content)

  final object Unit {

    /**
      * Тип элемента.
      */
    sealed trait Type

    /**
      * Обязательный блок.
      */
    final case class Required() extends Type

    /**
      * Необязательный блок.
      */
    final case class Option() extends Type

    /**
      * Повторяющийся блок.
      *
      * @param min Минимальное число повторов.
      * @param max Максимальное число повторов.
      */
    final case class Repeat(min: Int, max: Int) extends Type

  }

  /**
    * Содержимое элемента.
    */
  sealed trait Content

  /**
    * Элемент основанный на блоке.
    *
    * @param name Имя блока.
    */
  final case class ContentBlock(name: String) extends Content

  /**
    * Составной элемент.
    *
    * @param units Элементы.
    */
  final case class ContentUnits(units: Seq[Unit]) extends Content

}
