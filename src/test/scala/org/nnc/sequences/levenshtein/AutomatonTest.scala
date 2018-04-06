package org.nnc.sequences.levenshtein

import org.scalatest.FunSuite

class AutomatonTest extends FunSuite {
  test("empty n=0") {
    val automaton = create("", 0)
    // Совпадение
    assert(cost(automaton, "") == 0)
    // Один лишний символ
    assert(cost(automaton, "a") == -1)
  }

  test("empty n=1") {
    val automaton = create("", 1)
    assert(cost(automaton, "") == 0)
    assert(cost(automaton, "a") == 1)
    // Два лишних символа
    assert(cost(automaton, "aa") == -1)
    assert(cost(automaton, "ab") == -1)
  }

  test("empty n=2") {
    val automaton = create("", 2)
    assert(cost(automaton, "") == 0)
    assert(cost(automaton, "a") == 1)
    assert(cost(automaton, "aa") == 2)
    assert(cost(automaton, "ab") == 2)
    // Три лишних символа
    assert(cost(automaton, "aaa") == -1)
    assert(cost(automaton, "abc") == -1)
  }

  test("strings") {
    val automaton = create("abcdefg", 2)
    assert(cost(automaton, "abcdefg") == 0)
    // Удаление символа
    assert(cost(automaton, "bcdefg") == 1)
    assert(cost(automaton, "abcefg") == 1)
    assert(cost(automaton, "abcdef") == 1)
    // Добавление символа
    assert(cost(automaton, "zabcdefg") == 1)
    assert(cost(automaton, "abczdefg") == 1)
    assert(cost(automaton, "abcdefgz") == 1)
    // Перестановка
    assert(cost(automaton, "bacdefg") == 2)
    assert(cost(automaton, "abcedfg") == 2)
    assert(cost(automaton, "abcdegf") == 2)
    // Ошибочные строки
    assert(cost(automaton, "abfg") == -1)
    assert(cost(automaton, "zzzz") == -1)
    assert(cost(automaton, "abczzzefg") == -1)
  }

  private def create(string: String, n: Int) = new Automaton(string.toCharArray, n, new UniTablesFactoryImpl)

  private def cost(automaton: Automaton[Char], string: String) = {
    val items = string.toCharArray
    var pointer = automaton.start
    for (item <- items) {
      pointer = pointer.next(item)
    }
    pointer.cost
  }
}
