/** 
 * Mon Sep 29 07:50:38 PM KST 2025 
 */
package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class OtherHuffmanSuite extends FunSuite {
  // ----- Test cases from jason9751 -----
trait TestTrait_jason9751 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[jason9751] weight of a larger tree") {
    new TestTrait_jason9751 {
      assert(weight(t1) === 5)
    }
  }

  test("[jason9751] chars of a larger tree") {
    new TestTrait_jason9751 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[jason9751] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[jason9751] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[jason9751] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[jason9751] decode and encode a very short text should be identity") {
    new TestTrait_jason9751 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from junseong -----
trait TestTrait_junseong {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val codeTree = Fork(
      Fork(Leaf('b', 1), Leaf('c', 1), List('b', 'c'), 2),
      Leaf('a', 2),
      List('b', 'c', 'a'),
      4
    )
    val encoded = List(0, 1, 1, 1, 0, 0)
    val decoded = List('c', 'a', 'a', 'b')
  }

  test("[junseong] weight of a larger tree") {
    new TestTrait_junseong {
      assert(weight(t1) === 5)
    }
  }

  test("[junseong] chars of a larger tree") {
    new TestTrait_junseong {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[junseong] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[junseong] times <<EDITED: times function doesn't preserve order>>") {
    val freq = times(List('a', 'a', 'b', 'c'))
    assert(freq.toSet === Set(('a', 2), ('b', 1), ('c', 1)))
  }

  test("[junseong] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(
      List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x',3))
    )
  }

  test("[junseong] singleton") {
    assert(singleton(List(Leaf('t', 2))))
    assert(!singleton(List(Leaf('t', 2), Leaf('x', 3))))
  }

  test("[junseong] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[junseong] combine of empty leaf list") {
    assert(combine(List()) === List())
  }

  test("[junseong] combine of singleton leaf list") {
    val leaflist = List(Leaf('x', 1))
    assert(combine(leaflist) === leaflist)
  }

  test("[junseong] createCodeTree") {
    new TestTrait_junseong {
      val tree = createCodeTree(List('a', 'a', 'b', 'c'))
      assert(tree === codeTree)
    }
  }

  test("[junseong] decode") {
    new TestTrait_junseong {
      assert(decode(codeTree, encoded) === decoded)
    } 
  }

  test("[junseong] encode") {
    new TestTrait_junseong {
      assert(encode(codeTree)(decoded) === encoded)    
    }
  }

  test("[junseong] decode and encode a very short text should be identity") {
    new TestTrait_junseong {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("[junseong] codeBits") {
    val table = List(('a', List(0)))
    assert(codeBits(table)('a') === List(0))
  }

  test("[junseong] convert") {
    new TestTrait_junseong {
      val table = convert(codeTree)
      val lookup = codeBits(table)_
      assert(lookup('a') == List(1))
      assert(lookup('b') == List(0, 0))
      assert(lookup('c') == List(0, 1))
    }
  }

  test("[junseong] quickEncode") {
    new TestTrait_junseong {
      assert(quickEncode(codeTree)(decoded) === encoded)
    }
  }

  test("[junseong] decode and quickEncode a very short text should be identity") {
    new TestTrait_junseong {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from ejongwon7 -----
trait TestTrait_ejongwon7 {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("[ejongwon7] weight of a larger tree") {
    new TestTrait_ejongwon7 {
      assert(weight(t1) === 5)
    }
  }


  test("[ejongwon7] chars of a larger tree") {
    new TestTrait_ejongwon7 {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("[ejongwon7] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("[ejongwon7] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("[ejongwon7] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }


  test("[ejongwon7] decode and encode a very short text should be identity") {
    new TestTrait_ejongwon7 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from dongdc -----
trait TestTrait_dongdc {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[dongdc] weight of a larger tree") {
    new TestTrait_dongdc {
      assert(weight(t1) === 5)
      assert(weight(t2) === 9)
    }
  }

  test("[dongdc] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h','e','l','l','o',',',' ','w','o','r','l','d'))
  }

  test("[dongdc] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[dongdc] combine of some leaf list") {
    val leaflist = List(Leaf('e',1), Leaf('t',2), Leaf('x',4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e','t'),3), Leaf('x',4)))
  }

  test("[dongdc] decode and encode a very short text should be identity") {
    new TestTrait_dongdc {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("[dongdc] until reduces to a single tree") {
    val trees = makeOrderedLeafList(List(('a',2), ('b',3), ('c',1)))
    val result = until(singleton, combine)(trees)
    assert(result.size === 1)
    assert(weight(result.head) === 6)
  }

  test("[dongdc] createCodeTree produces correct total weight") {
    val chars = "aabbbcccc".toList
    val tree = createCodeTree(chars)
    assert(weight(tree) === chars.size)
  }

  test("[dongdc] encode and decode with createCodeTree should be identity") {
    val text = "thequickbrownfox".toList
    val tree = createCodeTree(text)
    val encoded = encode(tree)(text)
    val decoded = decode(tree, encoded)
    assert(decoded === text)
  }

  test("[dongdc] createCodeTree with single character string") {
    val chars = "aaaa".toList
    val tree = createCodeTree(chars)
    assert(weight(tree) === 4)
    val encoded = encode(tree)(chars)
    val decoded = decode(tree, encoded)
    assert(decoded === chars)
  }

  // ----- Test cases from cmhong -----
trait TestTrait_cmhong {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[cmhong] weight of a larger tree") {
    new TestTrait_cmhong {
      assert(weight(t1) === 5)
    }
  }

  test("[cmhong] chars of a larger tree") {
    new TestTrait_cmhong {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[cmhong] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[cmhong] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[cmhong] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[cmhong] decode and encode a very short text should be identity") {
    new TestTrait_cmhong {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from phs1104 -----
trait TestTrait_phs1104 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[phs1104] weight of a larger tree") {
    new TestTrait_phs1104 {
      assert(weight(t1) === 5)
    }
  }

  test("[phs1104] chars of a larger tree") {
    new TestTrait_phs1104 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[phs1104] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[phs1104] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[phs1104] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[phs1104] decode and encode a very short text should be identity") {
    new TestTrait_phs1104 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from pbs7818 -----
trait TestTrait_pbs7818 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[pbs7818] weight of a larger tree") {
    new TestTrait_pbs7818 {
      assert(weight(t1) === 5)
    }
  }

  test("[pbs7818] chars of a larger tree") {
    new TestTrait_pbs7818 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[pbs7818] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[pbs7818] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[pbs7818] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[pbs7818] decode and encode a very short text should be identity") {
    new TestTrait_pbs7818 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from vancover2010 -----
trait TestTrait_vancover2010 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[vancover2010] weight of a larger tree") {
    new TestTrait_vancover2010 {
      assert(weight(t1) === 5)
    }
  }

  test("[vancover2010] chars of a larger tree") {
    new TestTrait_vancover2010 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[vancover2010] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[vancover2010] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[vancover2010] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[vancover2010] decode and encode a very short text should be identity") {
    new TestTrait_vancover2010 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from seojin -----
trait TestTrait_seojin {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[seojin] weight of a larger tree") {
    new TestTrait_seojin {
      assert(weight(t1) === 5)
    }
  }

  test("[seojin] chars of a larger tree") {
    new TestTrait_seojin {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[seojin] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[seojin] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[seojin] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[seojin] decode and encode a very short text should be identity") {
    new TestTrait_seojin {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from sys030610 -----
trait TestTrait_sys030610 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[sys030610] weight of a larger tree") {
    new TestTrait_sys030610 {
      assert(weight(t1) === 5)
    }
  }

  test("[sys030610] chars of a larger tree") {
    new TestTrait_sys030610 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[sys030610] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[sys030610] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[sys030610] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[sys030610] decode and encode a very short text should be identity") {
    new TestTrait_sys030610 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from leejm21 -----
trait TestTrait_leejm21 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[leejm21] weight of a larger tree") {
    new TestTrait_leejm21 {
      assert(weight(t1) === 5)
    }
  }

  test("[leejm21] chars of a larger tree") {
    new TestTrait_leejm21 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[leejm21] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[leejm21] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[leejm21] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[leejm21] decode and encode a very short text should be identity") {
    new TestTrait_leejm21 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from vanilla -----
trait TestTrait_vanilla {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[vanilla] weight of a larger tree") {
    new TestTrait_vanilla {
      assert(weight(t1) === 5)
    }
  }

  test("[vanilla] chars of a larger tree") {
    new TestTrait_vanilla {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[vanilla] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[vanilla] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[vanilla] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[vanilla] decode and encode a very short text should be identity") {
    new TestTrait_vanilla {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from danakharaz -----
trait TestTrait_danakharaz {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[danakharaz] weight of a larger tree") {
    new TestTrait_danakharaz {
      assert(weight(t1) === 5)
    }
  }

  test("[danakharaz] chars of a larger tree") {
    new TestTrait_danakharaz {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[danakharaz] chars of a single leaf tree") {
    new TestTrait_danakharaz {
      assert(chars(Leaf('a',1)) === List('a'))
    }
  }

  test("[danakharaz] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[danakharaz] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[danakharaz] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[danakharaz] decode and encode a very short text should be identity") {
    new TestTrait_danakharaz {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("[danakharaz] decode and quickEncode a very short text should be identity") {
    new TestTrait_danakharaz {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from hyeonuk20719 -----
trait TestTrait_hyeonuk20719 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[hyeonuk20719] weight of a larger tree") {
    new TestTrait_hyeonuk20719 {
      assert(weight(t1) === 5)
    }
  }

  test("[hyeonuk20719] chars of a larger tree") {
    new TestTrait_hyeonuk20719 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[hyeonuk20719] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[hyeonuk20719] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[hyeonuk20719] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[hyeonuk20719] decode and encode a very short text should be identity") {
    new TestTrait_hyeonuk20719 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("[hyeonuk20719] decode and encode \"This is The long long TexT\"") {
    val text = "This is The long long Text"
    val charList = string2Chars(text)
    val codeTree = createCodeTree(charList)
    val encodedBits = encode(codeTree)(charList)
    val decodedText = decode(codeTree, encodedBits)
    assert(charList === decodedText)
  }

  test("[hyeonuk20719] quickEncode is equal to encode") {
    val text = "vfdoijioFIONFJIOfDMIOmklvfdsmiojfvdklmzvvmilAIJVDSMILrsdjiodsvmlkVSDSVNIOmklfvzs"
    val charList = string2Chars(text)
    val codeTree = createCodeTree(charList)
    val encodedBits = encode(codeTree)(charList)
    val quickEncodedBits = quickEncode(codeTree)(charList)

    assert(encodedBits === quickEncodedBits)
  }

  test("[hyeonuk20719] get secret message") {
    println(s"""Secret Message is "${decodedSecret.mkString}"!!""")
  }

  // ----- Test cases from kmw14641 -----
trait TestTrait_kmw14641 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[kmw14641] weight of a larger tree") {
    new TestTrait_kmw14641 {
      assert(weight(t1) === 5)
    }
  }

  test("[kmw14641] chars of a larger tree") {
    new TestTrait_kmw14641 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[kmw14641] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[kmw14641] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[kmw14641] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[kmw14641] decode and encode a very short text should be identity") {
    new TestTrait_kmw14641 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("[kmw14641] decode and encode roughly created string") {
    val originalText = "aldshfsdalifuhaslfiuhsadlfiuhasdihfuahdusahsudfasuidlfhlasduhflisahufd"
    val codeTree = createCodeTree(string2Chars(originalText))
    assert(decode(codeTree, encode(codeTree)(originalText.toList)) === originalText.toList)
  }

  test("[kmw14641] decode and quickEncode roughly created string") {
    val originalText = "aldshfsdalifuhaslfiuhsadlfiuhasdihfuahdusahsudfasuidlfhlasduhflisahufd"
    val codeTree = createCodeTree(string2Chars(originalText))
    assert(decode(codeTree, quickEncode(codeTree)(originalText.toList)) === originalText.toList)
  }

  // ----- Test cases from junyup4830 -----
trait TestTrait_junyup4830 {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("[junyup4830] weight of a larger tree") {
    new TestTrait_junyup4830 {
      assert(weight(t1) === 5)
    }
  }


  test("[junyup4830] chars of a larger tree") {
    new TestTrait_junyup4830 {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("[junyup4830] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("[junyup4830] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("[junyup4830] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }


  test("[junyup4830] decode and encode a very short text should be identity") {
    new TestTrait_junyup4830 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from tedoh7 -----
trait TestTrait_tedoh7 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[tedoh7] weight of a larger tree") {
    new TestTrait_tedoh7 {
      assert(weight(t1) === 5)
    }
  }

  test("[tedoh7] chars of a larger tree") {
    new TestTrait_tedoh7 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[tedoh7] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[tedoh7] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[tedoh7] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[tedoh7] decode and encode a very short text should be identity") {
    new TestTrait_tedoh7 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  test("[tedoh7] I really wonder what the secret code is") {
    decodedSecret
  }

  // ----- Test cases from dannyssy05 -----
trait TestTrait_dannyssy05 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[dannyssy05] weight of a larger tree") {
    new TestTrait_dannyssy05 {
      assert(weight(t1) === 5)
    }
  }

  test("[dannyssy05] chars of a larger tree") {
    new TestTrait_dannyssy05 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[dannyssy05] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[dannyssy05] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[dannyssy05] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[dannyssy05] decode and encode a very short text should be identity") {
    new TestTrait_dannyssy05 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from yeon903 -----
trait TestTrait_yeon903 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[yeon903] weight of a larger tree") {
    new TestTrait_yeon903 {
      assert(weight(t1) === 5)
    }
  }

  test("[yeon903] chars of a larger tree") {
    new TestTrait_yeon903 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[yeon903] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[yeon903] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[yeon903] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[yeon903] decode and encode a very short text should be identity") {
    new TestTrait_yeon903 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from cotmdgus -----
trait TestTrait_cotmdgus {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[cotmdgus] weight of a larger tree") {
    new TestTrait_cotmdgus {
      assert(weight(t1) === 5)
    }
  }

  test("[cotmdgus] chars of a larger tree") {
    new TestTrait_cotmdgus {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[cotmdgus] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[cotmdgus] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[cotmdgus] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[cotmdgus] decode and encode a very short text should be identity") {
    new TestTrait_cotmdgus {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from Jih00nLim -----
trait TestTrait_Jih00nLim {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("[Jih00nLim] weight of a larger tree") {
    new TestTrait_Jih00nLim {
      assert(weight(t1) === 5)
    }
  }


  test("[Jih00nLim] chars of a larger tree") {
    new TestTrait_Jih00nLim {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("[Jih00nLim] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("[Jih00nLim] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("[Jih00nLim] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }


  test("[Jih00nLim] decode and encode a very short text should be identity") {
    new TestTrait_Jih00nLim {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from wow332 -----
trait TestTrait_wow332 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[wow332] weight of a larger tree") {
    new TestTrait_wow332 {
      assert(weight(t1) === 5)
    }
  }

  test("[wow332] chars of a larger tree") {
    new TestTrait_wow332 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[wow332] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[wow332] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[wow332] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[wow332] decode and encode a very short text should be identity") {
    new TestTrait_wow332 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from hyunsoo13 -----
trait TestTrait_hyunsoo13 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[hyunsoo13] weight of a larger tree") {
    new TestTrait_hyunsoo13 {
      assert(weight(t1) === 5)
    }
  }

  test("[hyunsoo13] chars of a larger tree") {
    new TestTrait_hyunsoo13 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[hyunsoo13] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[hyunsoo13] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[hyunsoo13] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[hyunsoo13] decode and encode a very short text should be identity") {
    new TestTrait_hyunsoo13 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from tesniere -----
trait TestTrait_tesniere {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[tesniere] weight of a larger tree") {
    new TestTrait_tesniere {
      assert(weight(t1) === 5)
    }
  }

  test("[tesniere] chars of a larger tree") {
    new TestTrait_tesniere {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[tesniere] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[tesniere] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[tesniere] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[tesniere] decode and encode a very short text should be identity") {
    new TestTrait_tesniere {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  // ----- Test cases from matthew2839 -----
trait TestTrait_matthew2839 {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("[matthew2839] weight of a larger tree") {
    new TestTrait_matthew2839 {
      assert(weight(t1) === 5)
    }
  }

  test("[matthew2839] chars of a larger tree") {
    new TestTrait_matthew2839 {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("[matthew2839] string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("[matthew2839] makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("[matthew2839] combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("[matthew2839] decode and encode a very short text should be identity") {
    new TestTrait_matthew2839 {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  test("[matthew2839] I really wonder what the secret code is") {
    decodedSecret
  }
}
