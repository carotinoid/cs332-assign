/** 
 * Sat Sep 27 04:55:33 PM KST 2025 
 */
package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class OtherHuffmanSuite extends FunSuite {
  // ----- Test cases from junseong -----
  // MISIMPLEMENTATION DETECTED, SENT FIX REQUEST
// trait TestTrait_junseong {
//     val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
//     val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
//     val codeTree = Fork(
//       Leaf('c', 1),
//       Fork(Leaf('a', 2), Leaf('b', 1), List('a', 'b'), 3),
//       List('c', 'a', 'b'),
//       4
//     )
//     val encoded = List(0, 1, 0, 1, 0, 1, 1)
//     val decoded = List('c', 'a', 'a', 'b')
//   }

//   test("[junseong] weight of a larger tree") {
//     new TestTrait_junseong {
//       assert(weight(t1) === 5)
//     }
//   }

//   test("[junseong] chars of a larger tree") {
//     new TestTrait_junseong {
//       assert(chars(t2) === List('a','b','d'))
//     }
//   }

//   test("[junseong] string2chars(\"hello, world\")") {
//     assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
//   }

//   test("[junseong] times [edited - Ordering is not important]") {
//     val freq = times(List('a', 'a', 'b', 'c'))
//     assert(freq.toSet === Set(('a', 2), ('b', 1), ('c', 1)))
//   }

//   test("[junseong] makeOrderedLeafList for some frequency table") {
//     assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('t', 2), Leaf('e',1), Leaf('x',3)))
//   }

//   test("[junseong] singleton") {
//     assert(singleton(List(Leaf('t', 2))))
//     assert(!singleton(List(Leaf('t', 2), Leaf('x', 3))))
//   }

//   test("[junseong] combine of some leaf list") {
//     val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
//     assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
//   }

//   test("[junseong] combine of empty leaf list") {
//     assert(combine(List()) === List())
//   }

//   test("[junseong] combine of singleton leaf list") {
//     val leaflist = List(Leaf('x', 1))
//     assert(combine(leaflist) === leaflist)
//   }

//   test("[junseong] createCodeTree") {
//     new TestTrait_junseong {
//       val tree = createCodeTree(List('a', 'a', 'b', 'c'))
//       assert(tree === codeTree)
//     }
//   }

//   test("[junseong] decode") {
//     new TestTrait_junseong {
//       assert(decode(codeTree, encoded) === decoded)
//     } 
//   }

//   test("[junseong] encode") {
//     new TestTrait_junseong {
//       assert(encode(codeTree)(decoded) === encoded)    
//     }
//   }

//   test("[junseong] decode and encode a very short text should be identity") {
//     new TestTrait_junseong {
//       assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
//     }
//   }

//   test("[junseong] codeBits") {
//     val table = List(('a', List(0)))
//     assert(codeBits(table)('a') === List(0))
//   }

//   test("[junseong] convert") {
//     new TestTrait_junseong {
//       val table = convert(codeTree)
//       val lookup = codeBits(table)_
//       assert(lookup('a') == List(1, 0))
//       assert(lookup('b') == List(1, 1))
//       assert(lookup('c') == List(0))
//     }
//   }

//   test("[junseong] quickEncode") {
//     new TestTrait_junseong {
//       assert(quickEncode(codeTree)(decoded) === encoded)
//     }
//   }

//   test("[junseong] decode and quickEncode a very short text should be identity") {
//     new TestTrait_junseong {
//       assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
//     }
//   }

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
}
