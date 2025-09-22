package objsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class OtherTweetSetSuite extends FunSuite {
  // ----- Test cases from junseong -----
trait TestSets_junseong {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  test("filter: on empty set -- by junseong") {
    new TestSets_junseong {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: on singleton set -- by junseong") {
    new TestSets_junseong {
      assert(size(set2.filter(tw => tw.user == "a")) == 1)
    }
  }

  test("filter: a on set5 -- by junseong") {
    new TestSets_junseong {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by junseong") {
    new TestSets_junseong {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by junseong") {
    new TestSets_junseong {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by junseong") {
    new TestSets_junseong {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by junseong") {
    new TestSets_junseong {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("mostRetweeted: with empty set -- by junseong") {
    new TestSets_junseong {
      intercept[java.util.NoSuchElementException] {
        set1.mostRetweeted
      }
    }
  }

  test("mostRetweeted: set5 -- by junseong") {
    new TestSets_junseong {
      val tweet = set5.mostRetweeted
      assert(tweet.retweets === 20)
    }
  }

  // I didn't implement trends(n)
  // test("descending: set5 -- by junseong") {
  //   new TestSets_junseong {
  //     val trends = set5.descendingByRetweet
  //     assert(!trends.isEmpty)
  //     assert(trends.head.user === "a" || trends.head.user === "b")
  //     assert(trends(1).get.user === "a" || trends(1).get.user === "b")
  //     assert(trends(2).get.user === "d")
  //     assert(trends(3).get.user === "c")
  //   }
  // }

  // ----- Test cases from ejongwon7 -----
trait TestSets_ejongwon7 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by ejongwon7") {
    new TestSets_ejongwon7 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by ejongwon7") {
    new TestSets_ejongwon7 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by ejongwon7") {
    new TestSets_ejongwon7 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by ejongwon7") {
    new TestSets_ejongwon7 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by ejongwon7") {
    new TestSets_ejongwon7 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by ejongwon7") {
    new TestSets_ejongwon7 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by ejongwon7") {
    new TestSets_ejongwon7 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from dongdc -----
trait TestSets_dongdc {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by dongdc") {
    new TestSets_dongdc {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by dongdc") {
    new TestSets_dongdc {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by dongdc") {
    new TestSets_dongdc {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by dongdc") {
    new TestSets_dongdc {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by dongdc") {
    new TestSets_dongdc {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by dongdc") {
    new TestSets_dongdc {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by dongdc") {
    new TestSets_dongdc {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from cmhong -----
trait TestSets_cmhong {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by cmhong") {
    new TestSets_cmhong {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by cmhong") {
    new TestSets_cmhong {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by cmhong") {
    new TestSets_cmhong {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by cmhong") {
    new TestSets_cmhong {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by cmhong") {
    new TestSets_cmhong {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by cmhong") {
    new TestSets_cmhong {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by cmhong") {
    new TestSets_cmhong {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from pbs7818 -----
trait TestSets_pbs7818 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by pbs7818") {
    new TestSets_pbs7818 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by pbs7818") {
    new TestSets_pbs7818 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by pbs7818") {
    new TestSets_pbs7818 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by pbs7818") {
    new TestSets_pbs7818 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by pbs7818") {
    new TestSets_pbs7818 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by pbs7818") {
    new TestSets_pbs7818 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by pbs7818") {
    new TestSets_pbs7818 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from vancover2010 -----
trait TestSets_vancover2010 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by vancover2010") {
    new TestSets_vancover2010 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by vancover2010") {
    new TestSets_vancover2010 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by vancover2010") {
    new TestSets_vancover2010 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by vancover2010") {
    new TestSets_vancover2010 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by vancover2010") {
    new TestSets_vancover2010 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by vancover2010") {
    new TestSets_vancover2010 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by vancover2010") {
    new TestSets_vancover2010 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from seojin -----
trait TestSets_seojin {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by seojin") {
    new TestSets_seojin {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by seojin") {
    new TestSets_seojin {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by seojin") {
    new TestSets_seojin {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by seojin") {
    new TestSets_seojin {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by seojin") {
    new TestSets_seojin {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by seojin") {
    new TestSets_seojin {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by seojin") {
    new TestSets_seojin {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from sys030610 -----
trait TestSets_sys030610 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by sys030610") {
    new TestSets_sys030610 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by sys030610") {
    new TestSets_sys030610 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by sys030610") {
    new TestSets_sys030610 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by sys030610") {
    new TestSets_sys030610 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by sys030610") {
    new TestSets_sys030610 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by sys030610") {
    new TestSets_sys030610 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by sys030610") {
    new TestSets_sys030610 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from leejm21 -----
trait TestSets_leejm21 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by leejm21") {
    new TestSets_leejm21 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by leejm21") {
    new TestSets_leejm21 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by leejm21") {
    new TestSets_leejm21 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by leejm21") {
    new TestSets_leejm21 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by leejm21") {
    new TestSets_leejm21 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by leejm21") {
    new TestSets_leejm21 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by leejm21") {
    new TestSets_leejm21 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from vanilla -----
trait TestSets_vanilla {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by vanilla") {
    new TestSets_vanilla {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by vanilla") {
    new TestSets_vanilla {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by vanilla") {
    new TestSets_vanilla {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by vanilla") {
    new TestSets_vanilla {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by vanilla") {
    new TestSets_vanilla {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by vanilla") {
    new TestSets_vanilla {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by vanilla") {
    new TestSets_vanilla {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from hyeonuk20719 -----
trait TestSets_hyeonuk20719 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by hyeonuk20719") {
    new TestSets_hyeonuk20719 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from kmw14641 -----
trait TestSets_kmw14641 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by kmw14641") {
    new TestSets_kmw14641 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by kmw14641") {
    new TestSets_kmw14641 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by kmw14641") {
    new TestSets_kmw14641 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by kmw14641") {
    new TestSets_kmw14641 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by kmw14641") {
    new TestSets_kmw14641 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by kmw14641") {
    new TestSets_kmw14641 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by kmw14641") {
    new TestSets_kmw14641 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from dannyssy05 -----
trait TestSets_dannyssy05 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by dannyssy05") {
    new TestSets_dannyssy05 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by dannyssy05") {
    new TestSets_dannyssy05 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by dannyssy05") {
    new TestSets_dannyssy05 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by dannyssy05") {
    new TestSets_dannyssy05 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by dannyssy05") {
    new TestSets_dannyssy05 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by dannyssy05") {
    new TestSets_dannyssy05 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by dannyssy05") {
    new TestSets_dannyssy05 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from psm1017 -----
trait TestSets_psm1017 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by psm1017") {
    new TestSets_psm1017 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by psm1017") {
    new TestSets_psm1017 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by psm1017") {
    new TestSets_psm1017 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by psm1017") {
    new TestSets_psm1017 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by psm1017") {
    new TestSets_psm1017 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by psm1017") {
    new TestSets_psm1017 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by psm1017") {
    new TestSets_psm1017 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from yeon903 -----
trait TestSets_yeon903 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by yeon903") {
    new TestSets_yeon903 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by yeon903") {
    new TestSets_yeon903 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by yeon903") {
    new TestSets_yeon903 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by yeon903") {
    new TestSets_yeon903 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by yeon903") {
    new TestSets_yeon903 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by yeon903") {
    new TestSets_yeon903 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by yeon903") {
    new TestSets_yeon903 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  test("descending: empty set -- by yeon903") {
    new TestSets_yeon903 {
      val trends = set1.descendingByRetweet
      assert(trends.isEmpty)
    }
  }

  test("descending: single element -- by yeon903") {
    new TestSets_yeon903 {
      val trends = set2.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a")
      assert(trends.head.retweets == 20)
      assert(trends.tail.isEmpty)
    }
  }

  test("descending: set5 order verification -- by yeon903") {
    new TestSets_yeon903 {
      val trends = set5.descendingByRetweet

      assert(trends.head.retweets == 20)
      assert {
        trends.tail.head.retweets >= trends.tail.tail.head.retweets
      }
      assert(trends.tail.tail.head.retweets >= trends.tail.tail.tail.head.retweets)
      assert(trends.tail.tail.tail.tail.isEmpty)
    }
  }

  test("descending: verify all elements present -- by yeon903") {
    new TestSets_yeon903 {
      val trends = set5.descendingByRetweet
      val trendSet = Set(trends.head, trends.tail.head, trends.tail.tail.head, trends.tail.tail.tail.head)
      val originalSet = asSet(set5)
      assert(trendSet == originalSet)
    }
  }

  test("descending: retweet counts in correct order -- by yeon903") {
    new TestSets_yeon903 {
      val trends = set5.descendingByRetweet
      val retweetCounts = List(
        trends.head.retweets,
        trends.tail.head.retweets,
        trends.tail.tail.head.retweets,
        trends.tail.tail.tail.head.retweets
      )

      assert(retweetCounts(0) >= retweetCounts(1))
      assert(retweetCounts(1) >= retweetCounts(2))
      assert(retweetCounts(2) >= retweetCounts(3))
      assert(retweetCounts.contains(20))
      assert(retweetCounts.contains(9))
      assert(retweetCounts.contains(7))
    }
  }

  test("descending: with duplicate retweet counts -- by yeon903") {
    new TestSets_yeon903 {
      val e = new Tweet("e", "e body", 20)
      val set6 = set5.incl(e)
      val trends = set6.descendingByRetweet

      assert(trends.head.retweets == 20)
      assert(trends.tail.head.retweets == 20)
      assert(trends.tail.tail.head.retweets == 20)

      var count = 0
      var current = trends
      while (!current.isEmpty) {
        count += 1
        current = current.tail
      }
      assert(count == 5)
    }
  }

  // ----- Test cases from cotmdgus -----
trait TestSets_cotmdgus {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by cotmdgus") {
    new TestSets_cotmdgus {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by cotmdgus") {
    new TestSets_cotmdgus {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by cotmdgus") {
    new TestSets_cotmdgus {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by cotmdgus") {
    new TestSets_cotmdgus {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by cotmdgus") {
    new TestSets_cotmdgus {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by cotmdgus") {
    new TestSets_cotmdgus {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by cotmdgus") {
    new TestSets_cotmdgus {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from chj0530 -----
trait TestSets_chj0530 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)

    //additional test case
    val e = new Tweet("A", "A body", 7)
    val set6 = set5.incl(e)
  }



  test("filter: on empty set -- by chj0530") {
    new TestSets_chj0530 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by chj0530") {
    new TestSets_chj0530 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by chj0530") {
    new TestSets_chj0530 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("filter: 7 on set6 for left node -- by chj0530") {
    new TestSets_chj0530 {
      assert(size(set6.filter(tw => tw.retweets == 7)) === 2)
    }
  }

  test("union: set4c and set4d -- by chj0530") {
    new TestSets_chj0530 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by chj0530") {
    new TestSets_chj0530 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by chj0530") {
    new TestSets_chj0530 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("test for mostRetweeted -- by chj0530") {
    new TestSets_chj0530 {
      assert(set6.mostRetweeted.retweets == 20)
    }
  }

  //below test code are generated by AI
  test("mostRetweeted: empty set throws -- by chj0530") {
  val empty = new Empty
  intercept[java.util.NoSuchElementException] {
    empty.mostRetweeted
  }
}

  test("mostRetweeted: single element -- by chj0530") {
    val t = new Tweet("u", "m", 42)
    val s = new Empty().incl(t)
    assert(s.mostRetweeted eq t)          // 같은 인스턴스여야 함
    assert(s.mostRetweeted.retweets == 42)
  }

  test("mostRetweeted: max is in LEFT subtree -- by chj0530") {
    // 텍스트 순서: "a" < "m" < "z"  → b는 left, c는 right 로 들어감
    val a = new Tweet("a", "m", 1)
    val b = new Tweet("b", "a", 50)  // left
    val c = new Tweet("c", "z", 10)  // right
    val s = new Empty().incl(a).incl(b).incl(c)
    assert(s.mostRetweeted eq b)
    assert(s.mostRetweeted.retweets == 50)
  }

  test("mostRetweeted: max is in RIGHT subtree -- by chj0530") {
    val a = new Tweet("a", "m", 1)
    val b = new Tweet("b", "a", 10)  // left
    val c = new Tweet("c", "z", 77)  // right (최대)
    val s = new Empty().incl(a).incl(b).incl(c)
    assert(s.mostRetweeted eq c)
    assert(s.mostRetweeted.retweets == 77)
  }

  test("mostRetweeted: ties allowed (either of the max) -- by chj0530") {
    val a = new Tweet("a", "a body", 20)
    val b = new Tweet("b", "b body", 20)
    val s = new Empty().incl(a).incl(b)
    val top = s.mostRetweeted
    assert(top.retweets == 20)
    assert((top eq a) || (top eq b))      // 동률일 때 둘 중 하나면 OK
  }


  test("descending: set5 -- by chj0530") {
    new TestSets_chj0530 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  //below test code are generated by AI
  test("descendingByRetweet: result is sorted non-increasing -- by chj0530") {
    new TestSets_chj0530 {
      val trends = set5.descendingByRetweet
      var prev = Int.MaxValue
      var ok = true
      trends.foreach { t =>
        if (t.retweets > prev) ok = false
        prev = t.retweets
      }
      assert(ok, "The list is not sorted in descending order")
    }
  }

  test("descendingByRetweet: length matches set size -- by chj0530") {
    new TestSets_chj0530 {
      val trends = set5.descendingByRetweet
      var count = 0
      trends.foreach(_ => count += 1)
      assert(count == size(set5), s"Expected ${size(set5)} but got $count")
    }
  }

  test("descendingByRetweet: contains all elements from set -- by chj0530") {
    new TestSets_chj0530 {
      val trends = set5.descendingByRetweet
      val elemsFromList = scala.collection.mutable.Set[Tweet]()
      trends.foreach(elemsFromList += _)

      assert(elemsFromList.toSet == asSet(set5))
    }
  }

  test("descendingByRetweet: single element set -- by chj0530") {
    val single = new Empty().incl(new Tweet("x", "only tweet", 42))
    val list = single.descendingByRetweet
    assert(!list.isEmpty)
    assert(list.head.retweets == 42)
    assert(list.tail.isEmpty)
  }

  test("descendingByRetweet: empty set -- by chj0530") {
    val empty = new Empty
    val list = empty.descendingByRetweet
    assert(list.isEmpty)
  }

  // ----- Test cases from Jih00nLim -----
trait TestSets_Jih00nLim {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by Jih00nLim") {
    new TestSets_Jih00nLim {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by Jih00nLim") {
    new TestSets_Jih00nLim {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by Jih00nLim") {
    new TestSets_Jih00nLim {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by Jih00nLim") {
    new TestSets_Jih00nLim {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by Jih00nLim") {
    new TestSets_Jih00nLim {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by Jih00nLim") {
    new TestSets_Jih00nLim {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by Jih00nLim") {
    new TestSets_Jih00nLim {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // ----- Test cases from hyunsoo13 -----
trait TestSets_hyunsoo13 {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }



  test("filter: on empty set -- by hyunsoo13") {
    new TestSets_hyunsoo13 {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5 -- by hyunsoo13") {
    new TestSets_hyunsoo13 {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5 -- by hyunsoo13") {
    new TestSets_hyunsoo13 {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d -- by hyunsoo13") {
    new TestSets_hyunsoo13 {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1) -- by hyunsoo13") {
    new TestSets_hyunsoo13 {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2) -- by hyunsoo13") {
    new TestSets_hyunsoo13 {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5 -- by hyunsoo13") {
    new TestSets_hyunsoo13 {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }
}
