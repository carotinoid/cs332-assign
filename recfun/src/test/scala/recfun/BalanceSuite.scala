package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BalanceSuite extends FunSuite {
  import Main.balance

  test("balance: '(if (zero? x) max (/ 1 x))' is balanced") {
    assert(balance("(if (zero? x) max (/ 1 x))".toList))
  }

  test("balance: 'I told him ...' is balanced") {
    assert(balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))
  }

  test("balance: ':-)' is unbalanced") {
    assert(!balance(":-)".toList))
  }

  test("balance: counting is not enough") {
    assert(!balance("())(".toList))
  }

  test("balance: )a ab)a - unbalanced") {
    assert(!balance(")a ab)a".toList))
  }

  test("balance: ((())) - balanced") {
    assert(balance("((()))".toList))
  } 

  test("balance: () - balanced") {
    assert(balance("()".toList))
  }

  test("balance: (()) - balanced") {
    assert(balance("(())".toList))
  }

  test("balance: (((()((b)((b ()(  (a( ()))(b)) )a()b)(()())) )() ()a - unbalanced") {
    assert(!balance("(((()((b)((b ()(  (a( ()))(b)) )a()b)(()())) )() ()a".toList))
  }

  test("balance: (() - unbalanced") {
    assert(!balance("(()".toList))
  }

  test("balance: )(a) - unbalanced") {
    assert(!balance(")(a)".toList))
  }

  test("balance: none - balanced") {
    assert(balance("".toList))
  }
}
