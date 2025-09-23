package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("times") {
    val aba = "aba".toList
    assert(times(aba) === List(('a', 2), ('b', 1))
          || times(aba) === List(('b', 1), ('a', 2)))
    assert(times("aaaaa".toList) === List(('a', 5)))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and encode a very long text should be identity - 1") {
    new TestTrees {
      val text = "abhguijhuosajfljkasdh flahs fiulqhwuefashd flashdlajkshdflkqjwh nldfkjas dfkljashklf j shadlkfj halskd fjhqikewfhlaakjshdflasudfy9oq4984yh43hbkqbhkajhualuifhluihlfaskjhdfiqlwuthilqk3hksafhas,jkdfnsjkadvhklzsjhflkjhwe,kthq3l4iuthlo3q48ythoqfyhelaskjhjafskajdfnak,jhfakufh24938yo934ui8qyhtwhrkfnas,jkdfbb"
      val t = createCodeTree(text.toList)
      // Length are measured from here: https://huffman-coding-online.vercel.app/
      assert(encode(t)(text.toList).size === 1308)
      assert(decode(t, encode(t)(text.toList)) === text.toList)
    }
  }

    test("decode and encode a very long text should be identity - 2") {
    new TestTrees {
      val text = "ghfgjfkfgdsrfgjftjkftdgkljk;l;'iiftuffht cdxdf gmnihuybgvtfrceg6bhnyj8u9iu yt 0i9u8y6590"
      val t = createCodeTree(text.toList)
      // Length are measured from here: https://huffman-coding-online.vercel.app/
      assert(encode(t)(text.toList).size === 398)
      assert(decode(t, encode(t)(text.toList)) === text.toList)
    }
  }

  test("decode and quickEncode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and quickEncode a very long text should be identity - 1") {
    new TestTrees {
      val text = "abhguijhuosajfljkasdh flahs fiulqhwuefashd flashdlajkshdflkqjwh nldfkjas dfkljashklf j shadlkfj halskd fjhqikewfhlaakjshdflasudfy9oq4984yh43hbkqbhkajhualuifhluihlfaskjhdfiqlwuthilqk3hksafhas,jkdfnsjkadvhklzsjhflkjhwe,kthq3l4iuthlo3q48ythoqfyhelaskjhjafskajdfnak,jhfakufh24938yo934ui8qyhtwhrkfnas,jkdfbb"
      val t = createCodeTree(text.toList)
      // Length are measured from here: https://huffman-coding-online.vercel.app/
      assert(quickEncode(t)(text.toList).size === 1308)
      assert(decode(t, quickEncode(t)(text.toList)) === text.toList)
    }
  }

    test("decode and quickEncode a very long text should be identity - 2") {
    new TestTrees {
      val text = "ghfgjfkfgdsrfgjftjkftdgkljk;l;'iiftuffht cdxdf gmnihuybgvtfrceg6bhnyj8u9iu yt 0i9u8y6590"
      val t = createCodeTree(text.toList)
      // Length are measured from here: https://huffman-coding-online.vercel.app/
      assert(quickEncode(t)(text.toList).size === 398)
      assert(decode(t, quickEncode(t)(text.toList)) === text.toList)
    }
  }

  test("convert a small CodeTree") {
    new TestTrees {
      val ct: CodeTable = convert(t2)
      assert(ct.sortBy(_._1) == List(('a', List(0, 0)), ('b', List(0, 1)), ('d', List(1))))
    }
  }

  test("convert doesn't miss any") {
    new TestTrees {
      val text = "abcdefghijklmnopqrstuvz".toList
      val codetree = createCodeTree(text)
      val codetable = convert(codetree)
      assert(codetable.forall(x => text.find(_ == x._1).isDefined))
    }
  }

  test("DecodeSecret") {
    println(decodedSecret.mkString) // huffmanestcool
  }
}
