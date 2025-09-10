package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    def isOdd(x: Int): Boolean = (x % 2 == 1) || (x % 2 == -1)
    def isEven(x: Int): Boolean = (x % 2 == 0)
    def isDividedBy(n: Int)(x: Int): Boolean = (x % n == 0)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 2), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }

    new TestSets {
      val s12 = union(s1, s2)
      val s23 = union(s2, s3)
      val s123 = union(s12, s23)

      assert(contains(s123, 1), "Union 1")
      assert(contains(s123, 2), "Union 2")
      assert(contains(s123, 3), "Union 3")
      assert(!contains(s123, 4), "Union 4")
    }

    new TestSets {
      val setEven = (x: Int) => (x % 2 == 0)
      val setOdd = (x: Int) => (x % 2 == 1 || x % 2 == -1)
      val setInteger = union(setEven, setOdd)
      assert(contains(setInteger, -999), "Union 5")
      assert(contains(setInteger, -2), "Union 6")
      assert(contains(setInteger, 0), "Union 7")
      assert(contains(setInteger, 284), "Union 8")
    }
  }

  test("intersect") {
    new TestSets {
      val sx = intersect(s1, s2)
      assert(!contains(sx, 1), "Intersect 1")
      assert(!contains(sx, 2), "Intersect 2")
    }
    new TestSets {
      val s12 = union(s1, s2)
      val s23 = union(s2, s3)
      val s2x = intersect(s12, s23)
      assert(!contains(s2x, 1), "Intersect 1")
      assert(contains(s2x, 2), "Intersect 2")
      assert(!contains(s2x, 3), "Intersect 3")
    }
  }

  test("diff") {
    new TestSets {
      val sd = diff(s1, s2)
      assert(contains(sd, 1), "Diff 1-1")
      assert(!contains(sd, 2), "Diff 1-2")
    }
    new TestSets {
      val s12 = union(s1, s2)
      val s23 = union(s2, s3)
      val s1d = diff(s12, s23)
      assert(contains(s1d, 1), "Diff 2-1")
      assert(!contains(s1d, 2), "Diff 2-2")
      assert(!contains(s1d, 3), "Diff 2-3")
    }
    new TestSets {
      val setEven = (x: Int) => (x % 2 == 0)
      val setDivBy4 = (x: Int) => (x % 4 == 0)
      val ss = diff(setEven, setDivBy4)
      assert(contains(ss, 2), "Diff 3-1")
      assert(contains(ss, 6), "Diff 3-2")
      assert(!contains(ss, 4), "Diff 3-3")
      assert(!contains(ss, 8), "Diff 3-4")
      assert(!contains(ss, 0), "Diff 3-5")
    }
    new TestSets {
      val sd = diff(s1, s2)
      assert(contains(sd, 1), "Diff 4-1")
      assert(!contains(sd, 2), "Diff 4-2")
    }
    new TestSets {
      val s12 = union(s1, s2)
      val sd = diff(s12, s2)
      assert(contains(sd, 1), "Diff 5-1")
      assert(!contains(sd, 2), "Diff 5-2")
    }
  }

  test("filter") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)
      val odd = filter(s123, isOdd)
      val even = filter(s123, isEven)
      assert(contains(odd, 1), "Odd 1")
      assert(!contains(odd, 2), "Odd 2")
      assert(contains(odd, 3), "Odd 3")
      assert(!contains(even, 1), "Even 1")
      assert(contains(even, 2), "Even 2")
      assert(!contains(even, 3), "Even 3")
    }
  }

  test("forall") {
    new TestSets {
      val s13 = union(s1, s3)
      assert(forall(s13, isOdd), "Forall 1")
      assert(!forall(union(s13, s2), isOdd), "Forall 2")
    }
    new TestSets {
      val setEven = (x: Int) => (x % 2 == 0)
      assert(forall(setEven, isEven), "Forall 3")
      assert(!forall(union(setEven, s1), isEven), "Forall 4")
    }

  }

  test("exists") {
    new TestSets {
      val setOdd = (x: Int) => (x % 2 == 1) || (x % 2 == -1)
      assert(!exists(setOdd, isEven), "Exists 1")
      assert(!exists(union(setOdd, s1), isEven), "Exists 2")
      assert(exists(union(setOdd, s2), isEven), "Exists 3")
    }
    new TestSets {
      val s12 = union(s1, s2);
      assert(exists(s12, isEven), "Exists 4")
      assert(exists(s12, isOdd), "Exists 5")
    }
  }

  test("map") {
    new TestSets {
      val setEven = (x: Int) => (x % 2 == 0)
      val setDoubledEven = map(setEven, (x: Int) => (x * 2))
      assert(forall(setEven, isEven), "Map 1")
      assert(forall(setDoubledEven, isDividedBy(4)_), "Map 2")
      assert(!exists(setDoubledEven, isOdd), "Map 3")
      assert(exists(setDoubledEven, isEven), "Map 4")
    }
    new TestSets {
      val setNatural = (x: Int) => (x > 0)
      val setInteger = (x: Int) => true
      val setDoubledNatural = map(setNatural, (x: Int) => (x * 2))
      val setDoubledInteger = map(setInteger, (x: Int) => (x * 2))
      assert(forall(setDoubledNatural, isEven), "Map 5")
      assert(forall(setDoubledInteger, isEven), "Map 6")
      assert(!exists(setDoubledInteger, isOdd), "Map 7")
      assert(forall(setDoubledNatural, (x: Int) => (x > 0)), "Map 8")
      val setDoubledIntegerPlusOne = map(setDoubledInteger, (x: Int) => (x + 1))
      assert(!forall(setDoubledIntegerPlusOne, isEven), "Map 9")
      assert(exists(setDoubledIntegerPlusOne, isOdd), "Map 10")
    }
  }
}
