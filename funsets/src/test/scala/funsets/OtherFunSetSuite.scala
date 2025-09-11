/**
 *  Thu Sep 11 03:48:44 PM KST 2025 
 */

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
class OtherFunSetSuite extends FunSuite {
  // ----- Test cases from junseong -----
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
  test("string take -- by junseong") {
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
  test("adding ints -- by junseong") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented -- by junseong") {
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

  trait TestSets_junseong {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s12 = union(s1, s2)
    val s13 = union(s1, s3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1 -- by junseong") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets_junseong {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements -- by junseong") {
    new TestSets_junseong {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains overlapping elements -- by junseong") {
    new TestSets_junseong {
      val s = intersect(s12, s13) // {1}
      assert(contains(s, 1), "intersection should contain 1")
      assert(!contains(s, 2), "intersection should not contain 2")
      assert(!contains(s, 3), "intersection should not contain 3")
    }
  }

  test("diff contains unique element to lhs -- by junseong") {
    new TestSets_junseong {
      val s = diff(s12, s13) // {2}
      assert(!contains(s, 1), "diff should not contain 1")
      assert(contains(s, 2), "diff should contain 2")
      assert(!contains(s, 3), "intersection should not contain 3")
    }
  }

  test("filter contains element that satisfies predicate -- by junseong") {
    new TestSets_junseong {
      val p = (x: Int) => x == 1
      val s = filter(s12, p) // {1}
      assert(contains(s, 1), "filter should contain 1")
      assert(!contains(s, 2), "filter should not contain 2")
      assert(!contains(s, 3), "filter should not contain 3")
    }
  }

  test("forall checks whether all elements satisfy given predicate -- by junseong") {
    new TestSets_junseong {
      assert(!forall(s12, (x: Int) => x < 2), "s12 should not satisfy x < 2")
      assert(forall(s12, (x: Int) => x <= 2), "s12 should satisfy x <= 2")
    }    
  }

  test("exists checks whether at least one element satisfy given predicate -- by junseong") {
    new TestSets_junseong {
      assert(exists(s12, (x: Int) => x < 2), "s12 should have element that satisfy x < 2")
      assert(!exists(s12, (x: Int) => x > 2), "s12 should not have element that satisfy x > 2")
    }
  }

  test("map should transform each element of set -- by junseong") {
    new TestSets_junseong {
      val s = map(s1, _ + 1)
      assert(!contains(s, 1), "transformed set should not contain 1")
      assert(contains(s, 2), "transformed set should contain 2")
    }
  }

  // ----- Test cases from dongdc -----
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
  test("string take -- by dongdc") {
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
  test("adding ints -- by dongdc") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented -- by dongdc") {
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

  trait TestSets_dongdc {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet contains elem -- by dongdc") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets_dongdc {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s2, 1), "Singleton")
      assert(contains(s2, 2), "Singleton")
      assert(!contains(s3, 2), "Singleton")
      assert(contains(s3, 3), "Singleton")
    }
  }

  test("union contains all elements -- by dongdc") {
    new TestSets_dongdc {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("forall with complex sets -- by dongdc") {
    new TestSets_dongdc {
      val setForAll = union(union(s1, s2), union(s3, s4))
      val evens = intersect(setForAll, union(s2, s4))

      assert(forall(setForAll, x => x > 0), "All elements positive")
      assert(forall(evens, x => x % 2 == 0), "All elements of evens are even")
      assert(!forall(setForAll, x => x % 2 == 0), "Not all elements are even")
    }
  }

  test("exists with complex sets -- by dongdc") {
    new TestSets_dongdc {
      val setExists = union(union(s1, s2), union(s3, union(s4, s5)))

      assert(exists(setExists, x => x % 2 == 1), "Odd exist")
      assert(!exists(setExists, x => x > 10), "element > 10 not exists")
      assert(exists(setExists, x => x == 3 || x == 4), "3 or 4 exists")
      assert(!exists(setExists, x => x == 3 && x == 4), "3 also 4 not exists")
    }
  }

  test("map with complex sets -- by dongdc") {
    new TestSets_dongdc {
      val setMap = union(union(s1, s2), s3)
      val squares = map(setMap, x => x * x)
      assert(contains(squares, 1), "Squares contains 1")
      assert(contains(squares, 4), "Squares contains 4")
      assert(contains(squares, 9), "Squares contains 9")
      assert(!contains(squares, 2), "Squares not contains 2")

      val shifted = map(setMap, x => x + 10)
      assert(contains(shifted, 11), "Shifted contains 11")
      assert(contains(shifted, 12), "Shifted contains 12")
      assert(contains(shifted, 13), "Shifted contains 13")

      val setMap2 = union(s4, s5)
      val mod2 = map(setMap2, x => x % 2)
      assert(contains(mod2, 0), "Mapped contains 0")
      assert(contains(mod2, 1), "Mapped contains 1")
    }
  }

  // ----- Test cases from phs1104 -----
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
  test("string take -- by phs1104") {
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
  test("adding ints -- by phs1104") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented -- by phs1104") {
    assert(contains(x => x % 2 == 0, 100))
    assert(contains(x => x % 2 == 1, 101))
    assert(contains(x => x % 2 == 0, 102))
    assert(contains(x => x % 2 == 1, 103))
    assert(contains(x => x % 2 == 0, 104))
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

  trait TestSets_phs1104 {
    val value = 1
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s6 = singletonSet(6)
    val s12 = union(s1, s2)
    val s23 = union(s2, s3)
    val s34 = union(s3, s4)
    val s45 = union(s4, s5)
    val s123 = union(s12, s3)
    val s234 = union(s23, s4)
    val s345 = union(s34, s5)
    val s1234 = union(s123, s4)
    val s12345 = union(s1234, s5)

    val even: Set = (x : Int) => x % 2 == 0
    val odd: Set = (x : Int) => x % 2 == 1
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1 -- by phs1104") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets_phs1104 {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements -- by phs1104") {
    new TestSets_phs1104 {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersection test -- by phs1104") {
    val T = new TestSets_phs1104{
        override val value = 2
        assert(contains(intersect(s12, s23), 2), "intersect 1")
        assert(contains(intersect(s1234, s4), 4), "intersect 4")
    }
    assert(T.value == 2)
  }

  test("difference test -- by phs1104") {
    new TestSets_phs1104{
        assert(contains(diff(s1234, s345), 2), "diff 1")
        assert(contains(diff(s1234, s345), 1), "diff 1")
    }
  }

  test("for all test -- by phs1104"){
    new TestSets_phs1104{
        assert(forall(s12345, (x: Int) => x < 100), "forall 1")
    }
  }

    test("exists test -- by phs1104"){
      new TestSets_phs1104{
          assert(exists(s12345, (x: Int) => x % 2 == 0), "exists 1")
          assert(exists(even, x => x % 3 == 0), "exists 2")
      }
  }

  // ----- Test cases from seojin -----
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
  test("string take -- by seojin") {
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
  test("adding ints -- by seojin") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented -- by seojin") {
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

  trait TestSets_seojin {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val A = union(union(s1, s2),s3)
  }
  

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1 -- by seojin") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets_seojin {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }


  test("union contains all elements -- by seojin") {
    new TestSets_seojin {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }


  test("intersect contains all elements -- by seojin") {
    new TestSets_seojin {
      val s = intersect(union(s1, s2), union(s2, s3))
      assert(!contains(s, 1), "intersect 1")
      assert(contains(s, 2), "intersect 2")
      assert(!contains(s, 3), "intersect 3")
    }
  }


  test("diff contains all elements -- by seojin") {
    new TestSets_seojin {
      val s = diff(union(s1, s2), union(s2, s3))
      assert(contains(s, 1), "diff 1")
      assert(!contains(s, 2), "diff 2")
      assert(!contains(s, 3), "diff 3")
    }
  }


  test("filter works well -- by seojin") {
    new TestSets_seojin {
      val s = filter(A, x => x>=2)
      assert(!contains(s, 1), "filter 1")
      assert(contains(s, 2), "filter 2")
      assert(contains(s, 3), "filter 3")
    }
  }

  test("for all works well -- by seojin") {
    new TestSets_seojin {
      assert(forall(A, (x: Int) => x>=0), "for all element of {1,2,3}, x >= 0")
      assert(!forall(A, (x: Int) => x==0), "for all element of {1,2,3}, x >= 0")
    }
  }

  test("exists works well -- by seojin") {
    new TestSets_seojin {
      val p = (x: Int) => x==2
      assert(exists(A, p), "There exists 2 in {1,2,3}")
      assert(!exists(A, (x: Int) => x==0), "There exists 2 in {1,2,3}")
    }
  }

  test("map works well -- by seojin") {
    new TestSets_seojin {
      val f = (x: Int) => 2*x
      assert(contains(map(A,f),2), "map contains 2")
      assert(contains(map(A,f),4), "map contains 4")
      assert(contains(map(A,f),6), "map contains 6")
      assert(!contains(map(A,f),-2), "map not contains -2")
      assert(!contains(map(A,f),1), "map not contains 1")
    }
  }

  // ----- Test cases from hyeonuk20719 -----
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
  test("string take -- by hyeonuk20719") {
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
  test("adding ints -- by hyeonuk20719") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented -- by hyeonuk20719") {
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

  trait TestSets_hyeonuk20719 {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1 -- by hyeonuk20719") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets_hyeonuk20719 {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      val s = union(s1, s2)
      print("Union of {1}, {2} : ")
      printSet(s)

      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect test -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      val s = intersect(s1, s2)
      print("Intersect of {1}, {2} : ")
      printSet(s)
      val ss = intersect(union(s1, s2), s1)
      print("Intersect of {1, 2}, {1} : ")
      printSet(ss)

      assert(!contains(s, 1))
      assert(!contains(s, 2))
      assert(contains(ss, 1))
      assert(!contains(ss, 2))
    }
  }

  test("diff test -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      val s = diff(s1, s2)
      print("Diff of {1}, {2} : ")
      printSet(s)

      assert(contains(s, 1))
      assert(!contains(s, 2))

      val ss = diff(s2, union(s1, s2))
      print("Diff of {2}, {1, 2} : ")
      printSet(ss)

      assert(!contains(ss, 1))
      assert(!contains(ss, 2))

      val sss = diff(s1, s1)
      print("Diff of {1}, {1} : ")
      printSet(sss)

      assert(!contains(sss, 1))
      assert(!contains(sss, 2))
    }
  }

  test("filter test -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      val s = filter(union(s1, union(s2, s3)), x => x <= 2)
      print("Filter {1, 2, 3}, with x => x <= 2 : ")
      printSet(s)

      assert(contains(s, 1))
      assert(contains(s, 2))
      assert(!contains(s, 3))
    }
  }

  test("forall test -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      val s = union(s1, union(s2, s3))

      assert(forall(s, x => x <= 3))
      assert(!forall(s, x => x <= 2))
    }
  }

  test("exists test -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      val s = union(s1, union(s2, s3))

      assert(exists(s, x => x == 1))
      assert(exists(s, x => x >= 3))
      assert(!exists(s, x => x < 1))
    }
  }

  test("square map test -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      val squareSet = map(union(union(s1, s2), s3), x => x * x)
      print("Map x => x * x to {1, 2, 3} : ")
      printSet(squareSet)

      assert(contains(squareSet, 1))
      assert(!contains(squareSet, 2))
      assert(contains(squareSet, 4))
      assert(contains(squareSet, 9))
    }
  }

  // ----- Test cases from kmw14641 -----
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
  test("string take -- by kmw14641") {
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
  test("adding ints -- by kmw14641") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented -- by kmw14641") {
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

  trait TestSets_kmw14641 {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4: Set = x => x >= 0 && x <= 100
    val s5: Set = x => x >= 50 && x <= 150
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1 -- by kmw14641") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets_kmw14641 {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 2), "Singleton")
    }
  }

  test("union contains all elements -- by kmw14641") {
    new TestSets_kmw14641 {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains only intersected elements -- by kmw14641") {
    new TestSets_kmw14641 {
      val s = intersect(s4, s5)
      assert(contains(s, 50), "Intersect")
      assert(contains(s, 100), "Intersect")
      assert(!contains(s, 25), "Intersect")
      assert(!contains(s, 125), "Intersect")
    }
  }

  test("diff contains elements in a, not in b -- by kmw14641") {
    new TestSets_kmw14641 {
      val s = diff(s4, s5)
      assert(contains(s, 0), "Diff")
      assert(!contains(s, 50), "Diff")
      assert(!contains(s, 150), "Diff")
    }
  }

  test("filter contains elements satisfies filter only -- by kmw14641") {
    new TestSets_kmw14641 {
      val s = filter(s4, x => x <= 50)
      assert(contains(s, 50), "Filter")
      assert(!contains(s, 75), "Filter")
      assert(!contains(s, -1), "Filter")
    }
  }

  test("forall - s4 is non-negative integer -- by kmw14641") {
    new TestSets_kmw14641 {
      assert(forall(s4, x => x >= 0))
    }
  }

  test("forall - s4 is not positive integer -- by kmw14641") {
    new TestSets_kmw14641 {
      assert(!forall(s4, x => x > 0))
    }
  }

  test("exists - s4 has 0 -- by kmw14641") {
    new TestSets_kmw14641 {
      assert(exists(s4, x => x == 0))
    }
  }

  test("exists - s4 doesn't have -1 -- by kmw14641") {
    new TestSets_kmw14641 {
      assert(!exists(s4, x => x == -1))
    }
  }

  test("map - doubled s4 has only even -- by kmw14641") {
    new TestSets_kmw14641 {
      val s = map(s4, x => x * 2)
      assert(forall(s, x => x % 2 == 0))
      assert(!exists(s, x => x == 1))
    }
  }

  // ----- Test cases from dannyssy05 -----
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
  test("string take -- by dannyssy05") {
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
  test("adding ints -- by dannyssy05") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented -- by dannyssy05") {
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

  trait TestSets_dannyssy05 {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  ignore("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets_dannyssy05 {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  ignore("union contains all elements") {
    new TestSets_dannyssy05 {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  // ----- Test cases from chj0530 -----
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
  test("string take -- by chj0530") {
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
  test("adding ints -- by chj0530") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented -- by chj0530") {
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

  trait TestSets_chj0530 {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val s4 = union(s1, s2)
    val s5 = union(s2, s3)

    val s6 = union(s4, s3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1 -- by chj0530") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets_chj0530 {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements -- by chj0530") {
    new TestSets_chj0530 {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains overlapped elements -- by chj0530") {
    new TestSets_chj0530 {
      val s = intersect(s4, s5)
      assert(!contains(s, 1), "Intersect 1")
      assert(contains(s, 2), "Intersect 2")
      assert(!contains(s, 3), "Intersect 3")
    }
  }

  test("diff contains first set element without second set element -- by chj0530") {
    new TestSets_chj0530 {
      val s = diff(s4, s5)
      assert(contains(s, 1), "diff 1")
      assert(!contains(s, 2), "diff 2")
      assert(!contains(s, 3), "diff 3")
    }
  }

  test("test for filter -- by chj0530") {
    new TestSets_chj0530 {
      val s = filter(s6, (x: Int) => x % 2 == 1)
      assert(contains(s, 1), "filt 1")
      assert(!contains(s, 2), "filt 2")
      assert(contains(s, 3), "filt 3")
    }
  }

  test("test for forall -ver.1 -- by chj0530") {
    new TestSets_chj0530 {
      assert(forall(s6, (x: Int) => x < 4), "forall 1")
    }
  }

// This testcase might be wrong.
//   test("test for forall -ver.2 -- by chj0530") {
//     new TestSets_chj0530 {
//       assert(!forall(s6, (x: Int) => 4 % x == 0), "forall 2")
//     }
//   }

  test("test for exists -ver.1 -- by chj0530") {
    new TestSets_chj0530 {
      assert(exists(s6, (x: Int) => x % 3 == 0), "exists 1")
    }
  }

  test("test for exists -ver.2 -- by chj0530") {
    new TestSets_chj0530 {
      assert(!exists(s6, (x: Int) => x < 1), "exists 2")
    }
  }

  test("test for map -- by chj0530") {
    new TestSets_chj0530 {
      val s = map(s6, (_ * 10))
      assert(contains(s, 10), "map 1")
      assert(contains(s, 20), "map 2")
      assert(contains(s, 30), "map 3")
    }
  }
}
