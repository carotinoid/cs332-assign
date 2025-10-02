package forcomp

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Anagrams._

@RunWith(classOf[JUnitRunner])
class AnagramsSuite extends FunSuite {

  test("wordOccurrences: abcd") {
    assert(wordOccurrences("abcd") === List(('a', 1), ('b', 1), ('c', 1), ('d', 1)))
  }

  test("wordOccurrences: Robert") {
    assert(wordOccurrences("Robert") === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
  }



  test("sentenceOccurrences: abcd e") {
    assert(sentenceOccurrences(List("abcd", "e")) === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
  }

  test("sentenceOccurrences: abcd abxy") {
    assert(sentenceOccurrences(List("abcd", "abxy")) === List(('a', 2), ('b', 2), ('c', 1), ('d', 1), ('x', 1), ('y', 1)))
  }


  test("dictionaryByOccurrences.get: eat") {
    assert(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet) === Some(Set("ate", "eat", "tea")))
  }



  test("word anagrams: married") {
    assert(wordAnagrams("married").toSet === Set("married", "admirer"))
  }

  test("word anagrams: player") {
    assert(wordAnagrams("player").toSet === Set("parley", "pearly", "player", "replay"))
  }



  test("subtract: lard - r") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    assert(subtract(lard, r) === lad)
  }



  test("subtract: apple - p") {
    val apple = List(('a', 1), ('e', 1), ('l', 1), ('p', 2))
    val p = List(('p', 1))
    val aple = List(('a', 1), ('e', 1), ('l', 1), ('p', 1))
    assert(subtract(apple, p) === aple)
  }



  test("combinations: []") {
    assert(combinations(Nil) === List(Nil))
  }

  test("combinations: abba") {
    val abba = List(('a', 2), ('b', 2))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2))
    )
    assert(combinations(abba).toSet === abbacomb.toSet)
  }



  test("sentence anagrams: []") {
    val sentence = List()
    assert(sentenceAnagrams(sentence) === List(Nil))
  }

  test("sentence anagrams: Linux rulez") {
    val sentence = List("Linux", "rulez")
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }  

  test("sentence anagrams: Yes man") {
    val sentence = List("Yes", "man")
    val anas = List(
      List("en", "as", "my"),
      List("en", "my", "as"),
      List("man", "yes"),
      List("men", "say"),
      List("as", "en", "my"),
      List("as", "my", "en"),
      List("sane", "my"),
      List("Sean", "my"),
      List("my", "en", "as"),
      List("my", "as", "en"),
      List("my", "sane"),
      List("my", "Sean"),
      List("say", "men"),
      List("yes", "man")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }

  test("memoized sentence anagrams: []") {
    val sentence = List()
    assert(sentenceAnagramsMemo(sentence) === List(Nil))
  }

  test("memoized sentence anagrams: Linux rulez") {
    val sentence = List("Linux", "rulez")
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )
    assert(sentenceAnagramsMemo(sentence).toSet === anas.toSet)
  }  

  test("memoized sentence anagrams: Yes man") {
    val sentence = List("Yes", "man")
    val anas = List(
      List("en", "as", "my"),
      List("en", "my", "as"),
      List("man", "yes"),
      List("men", "say"),
      List("as", "en", "my"),
      List("as", "my", "en"),
      List("sane", "my"),
      List("Sean", "my"),
      List("my", "en", "as"),
      List("my", "as", "en"),
      List("my", "sane"),
      List("my", "Sean"),
      List("say", "men"),
      List("yes", "man")
    )
    assert(sentenceAnagramsMemo(sentence).toSet === anas.toSet)
  }

  // 두 함수의 성능을 비교하기 위한 테스트
  test("sentenceAnagrams performance comparison with and without memoization") {
    val sentence = List("I", "love", "Scala")

    println("\nSentence: \"I love Scala\"")

    // Case 1
    val startTime1 = System.nanoTime()
    val result1 = sentenceAnagrams(sentence)
    val duration1 = (System.nanoTime() - startTime1) / 1e6d

    println(f"Baseline (sentenceAnagrams)\t\t:$duration1%.2f ms")

    // Case 2-1
    memo.clear()
    val startTime2 = System.nanoTime()
    val result2 = sentenceAnagramsMemo(sentence)
    val duration2 = (System.nanoTime() - startTime2) / 1e6d

    println(f"Memoized (first run, cold cache)\t:$duration2%.2f ms")

    // Case 2-2
    val startTime3 = System.nanoTime()
    val result3 = sentenceAnagramsMemo(sentence)
    val duration3 = (System.nanoTime() - startTime3) / 1e6d
    
    println(f"Memoized (second run, warm cache)\t:$duration3%.2f ms")

    assert(result1.toSet == result2.toSet, "The results of both functions should be the same")
    assert(result2.toSet == result3.toSet, "The results of memoized function should be consistent")
    assert(duration3 < duration1, "Cached run should be faster than the baseline")
    assert(duration3 < duration2, "Cached run should be faster than the first memoized run")


    println("\nUsing cache of \"I love scala\", calculate \"Hello World\"")

    val sentence2 = List("Hello", "World")

    val startTime4 = System.nanoTime()
    val result4 = sentenceAnagrams(sentence2)
    val duration4 = (System.nanoTime() - startTime4) / 1e6d

    println(f"Baseline (sentenceAnagrams)\t\t:$duration4%.2f ms")

    val startTime5 = System.nanoTime()
    val result5 = sentenceAnagramsMemo(sentence2)
    val duration5 = (System.nanoTime() - startTime5) / 1e6d

    println(f"Memoized (sentenceAnagramsMemo)\t\t:$duration5%.2f ms")

    assert(result4.toSet == result5.toSet, "The results of both functions should be the same")
    assert(duration5 < duration4, "Memoized run should be faster than the baseline")

    println("\nUsing caches, calculate \"Today is a good day\"")

    val sentence3 = List("Today", "is", "a", "good", "day")

    val startTime6 = System.nanoTime()
    val result6 = sentenceAnagrams(sentence3)
    val duration6 = (System.nanoTime() - startTime6) / 1e6d

    println(f"Baseline (sentenceAnagrams)\t\t:$duration6%.2f ms")

    val startTime7 = System.nanoTime()
    val result7 = sentenceAnagramsMemo(sentence3)
    val duration7 = (System.nanoTime() - startTime7) / 1e6d

    println(f"Memoized (sentenceAnagramsMemo)\t\t:$duration7%.2f ms")

    assert(result6.toSet == result7.toSet, "The results of both functions should be the same")
    assert(duration7 < duration6, "Memoized run should be faster than the baseline")

    println("\nThe memoized version is significantly faster on the second run.")
  }

}
