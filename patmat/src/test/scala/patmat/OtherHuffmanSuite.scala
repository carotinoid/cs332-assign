package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class OtherHuffmanSuite extends FunSuite {
  // ----- Test cases from dongdc -----
trait TestTrait_dongdc {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree -- by dongdc") {
    new TestTrait_dongdc {
      assert(weight(t1) === 5)
      assert(weight(t2) === 9)
    }
  }

  test("string2chars(\"hello, world\") -- by dongdc") {
    assert(string2Chars("hello, world") === List('h','e','l','l','o',',',' ','w','o','r','l','d'))
  }

  test("makeOrderedLeafList for some frequency table -- by dongdc") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list -- by dongdc") {
    val leaflist = List(Leaf('e',1), Leaf('t',2), Leaf('x',4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e','t'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity -- by dongdc") {
    new TestTrait_dongdc {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("until reduces to a single tree -- by dongdc") {
    val trees = makeOrderedLeafList(List(('a',2), ('b',3), ('c',1)))
    val result = until(singleton, combine)(trees)
    assert(result.size === 1)
    assert(weight(result.head) === 6)
  }

  test("createCodeTree produces correct total weight -- by dongdc") {
    val chars = "aabbbcccc".toList
    val tree = createCodeTree(chars)
    assert(weight(tree) === chars.size)
  }

  test("encode and decode with createCodeTree should be identity -- by dongdc") {
    val text = "thequickbrownfox".toList
    val tree = createCodeTree(text)
    val encoded = encode(tree)(text)
    val decoded = decode(tree, encoded)
    assert(decoded === text)
  }

  test("createCodeTree with single character string -- by dongdc") {
    val chars = "aaaa".toList
    val tree = createCodeTree(chars)
    assert(weight(tree) === 4)
    val encoded = encode(tree)(chars)
    val decoded = decode(tree, encoded)
    assert(decoded === chars)
  }

  // ----- Test cases from pbs7818 -----
trait TestTrait_pbs7818 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree -- by pbs7818") {
    new TestTrait_pbs7818 {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree -- by pbs7818") {
    new TestTrait_pbs7818 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\") -- by pbs7818") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table -- by pbs7818") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list -- by pbs7818") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity -- by pbs7818") {
    new TestTrait_pbs7818 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from seojin -----
trait TestTrait_seojin {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree -- by seojin") {
    new TestTrait_seojin {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree -- by seojin") {
    new TestTrait_seojin {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\") -- by seojin") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table -- by seojin") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list -- by seojin") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity -- by seojin") {
    new TestTrait_seojin {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from hyeonuk20719 -----
trait TestTrait_hyeonuk20719 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree -- by hyeonuk20719") {
    new TestTrait_hyeonuk20719 {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree -- by hyeonuk20719") {
    new TestTrait_hyeonuk20719 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\") -- by hyeonuk20719") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table -- by hyeonuk20719") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list -- by hyeonuk20719") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity -- by hyeonuk20719") {
    new TestTrait_hyeonuk20719 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from cotmdgus -----
trait TestTrait_cotmdgus {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree -- by cotmdgus") {
    new TestTrait_cotmdgus {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree -- by cotmdgus") {
    new TestTrait_cotmdgus {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\") -- by cotmdgus") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table -- by cotmdgus") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list -- by cotmdgus") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity -- by cotmdgus") {
    new TestTrait_cotmdgus {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from Jih00nLim -----
trait TestTrait_Jih00nLim {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree -- by Jih00nLim") {
    new TestTrait_Jih00nLim {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree -- by Jih00nLim") {
    new TestTrait_Jih00nLim {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\") -- by Jih00nLim") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table -- by Jih00nLim") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list -- by Jih00nLim") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }


  test("decode and encode a very short text should be identity -- by Jih00nLim") {
    new TestTrait_Jih00nLim {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
}
