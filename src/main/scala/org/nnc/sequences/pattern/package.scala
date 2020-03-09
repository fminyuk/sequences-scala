package org.nnc.sequences

package object pattern {

  /**
    * Шаблон.
    *
    * @param name Имя шаблона.
    */
  case class Pattern(name: String)

  /**
    * Блок.
    */
  sealed trait Block {
    /**
      * Тип блока.
      */
    val blockType: BlockType
  }

  /**
    * Элементарный блок.
    *
    * @param blockType Тип блока.
    * @param name      Имя блока.
    */
  final case class BlockElem(blockType: BlockType, name: String) extends Block

  /**
    * Составной блок.
    *
    * @param blockType Тип блока.
    * @param blocks    Блоки.
    */
  final case class BlockCompose(blockType: BlockType, blocks: Seq[Block]) extends Block

  /**
    * Тип блока.
    */
  sealed trait BlockType

  /**
    * Обязательный блок.
    */
  final case class RequiredBlock() extends BlockType

  /**
    * Необязательный блок.
    */
  final case class OptionBlock() extends BlockType

  /**
    * Повторяющийся блок.
    *
    * @param min Минимальное число повторов.
    * @param max Максимальное число повторов.
    */
  final case class RepeatBlock(min: Option[Int], max: Option[Int]) extends BlockType

}
