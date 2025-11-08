package nodescala

import scala.language.postfixOps
import scala.util.{Try, Success, Failure}
import scala.collection._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.async.Async.{async, await}
import org.scalatest._
import NodeScala._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NodeScalaSuite extends FunSuite {

  test("A Future should always be completed") {
    val always = Future.always(517)

    assert(Await.result(always, 0 nanos) == 517)
  }
  test("A Future should never be completed") {
    val never = Future.never[Int]

    try {
      Await.result(never, 1 second)
      assert(false)
    } catch {
      case t: TimeoutException => // ok!
    }
  }
  test("Future.all All futures should be completed") {
    val f1 = Future.always(1)
    val f2 = Future.always(2)
    val f3 = Future.always(3)
    try {
      val all = Future.all(List(f1, f2, f3))
      assert(Await.result(all, 1.second) == List(1, 2, 3))
    } catch {
      case _: TimeoutException => assert(false)
    }
  }
  test("Future.all All futures should be not completed if one fails") {
    val f1 = Future.always(1)
    val f2 = Future.always(2)
    val f3 = Future.never[Int]

    try {
      val all = Future.all(List(f1, f2, f3))
      assert(Await.result(all, 0 nanos) == List(1, 2, 3))
    } catch {
      case _: TimeoutException => // ok!
    }
  }
  test("Future.all should complete with a list of results when all futures succeed") {
    val f1 = Future { Thread.sleep(50); 1 }
      val f2 = Future { Thread.sleep(10); 2 }
      val f3 = Future { Thread.sleep(30); 3 }
    val futures = List(f1, f2, f3)
    val allFutures = Future.all(futures)
    val result = Await.result(allFutures, 1.second)
    assert(result == List(1, 2, 3))
  }
  test("Future.all should fail immediately if any future fails") {
    val ex = new RuntimeException("Test failure")
    val slowSuccess = Future { Thread.sleep(500); "Success" }
    val fastFailure = Future { Thread.sleep(50); throw ex }
    val futures = List(slowSuccess, fastFailure)
    val allFutures = Future.all(futures)

    try {
      Await.result(allFutures, 1.second)
      assert(false)
    } catch {
      case e: RuntimeException =>
        assert(e == ex)
      case _: TimeoutException =>
        assert(false)
    }
  }
  test("Future.all should return an empty list for an empty list input") {
    val emptyList = List[Future[Int]]()
    val allFutures = Future.all(emptyList)

    val result = Await.result(allFutures, 0.nanos)
    assert(result == List())
  }
  test("Future.any should return first one: case success") {
    val f1 = Future { Thread.sleep(30); 1 }
    val f2 = Future { Thread.sleep(10); 2 }
    val f3 = Future { Thread.sleep(20); 3 }
    val anyFutures = Future.any(List(f1, f2, f3))
    val result = Await.result(anyFutures, 1.second)
    assert(result == 2)
  }
  test("Future.any should return first one: case failure") {
    val f1 = Future { Thread.sleep(20); 1 }
    val f2 = Future { Thread.sleep(30); throw new Exception("Failed") }
    val f3 = Future { Thread.sleep(10); throw new RuntimeException("This!") }
    try {
      val anyFutures = Future.any(List(f1, f2, f3))
      val result = Await.result(anyFutures, 1.second)
    } catch {
      case e: RuntimeException =>
        assert(e.getMessage == "This!")
      case _: Exception =>
        assert(false)
    }
  }
  test("Future.any with an empty list should never complete") {
    val emptyList = List[Future[Int]]()
    val anyFuture = Future.any(emptyList)
    try {
      Await.result(anyFuture, 100.millis)
      assert(false)
    } catch {
      case t: TimeoutException => // ok! 
    }
  }
  test("Future.delay should NOT complete before the specified duration") {
    val delayDuration = 200.millis
    val f = Future.delay(delayDuration)

    try {
      Await.result(f, 150.millis)
      assert(false, "Future.delay completed too early")
    } catch {
      case _: TimeoutException => // ok!
    }
  }
  test("Future.delay should complete after the specified duration") {
    try {
      val f = Future.delay(100.millis)
      val result = Await.result(f, 150.millis)
    } catch {
      case _: TimeoutException =>
        assert(false, "Future.delay did not complete after the specified time")
    }
  }

  test("Future.delay(0) should complete almost immediately") {
    try {
      val f = Future.delay(0.nanos)
      val result = Await.result(f, 30.millis)
    } catch {
      case _: TimeoutException =>
        assert(false, "Future.delay(0.nanos) did not complete immediately")
    }
  }
  test("Future.delay(negative) should make exception") {    
    try {
      val f = Future.delay(-10.millis) 
      val result = Await.result(f, 30.millis)
      assert(false, "Future.delay(-10.millis) completed successfully")
    } catch {
      case _: TimeoutException =>
        assert(false, "Future.delay(-10.millis) should not make Timeout")
      case _: IllegalArgumentException => // ok!
    }
  }
  test("now: should return the value if the future is already successfully completed") {
    val f = Future { "Success" }
    val result = Await.result(f, 1.second) // f는 이제 완료 상태
    
    try {
      assert(f.now == "Success")
    } catch {
      case _: Throwable => 
        assert(false, "f.now threw an exception when it should have returned a value")
    }
  }

  test("now: should throw NoSuchElementException if the future is not yet completed") {
    val f = Future { Thread.sleep(500); "Done" }

    try {
      f.now 
      assert(false, "f.now did not throw an exception when the future was incomplete")
    } catch {
      case _: NoSuchElementException => // ok
      case t: Throwable =>
        assert(false, s"f.now threw the wrong exception: $t")
    }
  }

  test("now: should re-throw the original exception if the future is already failed") {
    val ex = new RuntimeException("Test Failure")
    val f = Future[Int] { throw ex }
    try {
      Await.ready(f, 1.second) 
    } catch {
      case _: Throwable => 
    }

    try {
      f.now
      assert(false, "f.now did not re-throw the exception when the future had failed")
    } catch {
      case e: RuntimeException =>
        assert(e == ex, "f.now threw a different exception")
      case _: NoSuchElementException =>
        assert(false, "f.now threw NoSuchElementException instead of the original failure")
    }
  }
  test("continueWith: should run continuation after a successful future") {
    val f = Future.always(10)
    Await.ready(f, 1.second) 
    val cont = f.continueWith { future: Future[Int] =>
      future.now + 5 
    }
    val result = Await.result(cont, 1.second)
    assert(result == 15)
  }

  test("continueWith: should run continuation after a failed future") {
    val ex = new RuntimeException("Original Failure")
    val f = Future[Int] { throw ex }
    Await.ready(f, 1.second)
    val cont = f.continueWith { future: Future[Int] =>
      future.value match {
        case Some(Failure(e)) if e == ex => "Continuation handled failure"
        case _ => "Continuation saw something else"
      }
    }
    val result = Await.result(cont, 1.second)
    assert(result == "Continuation handled failure")
  }

  test("continueWith: should fail if the continuation itself throws an exception") {
    val contEx = new ArithmeticException("Continuation Failed")
    val f = Future.always(10)

    val cont = f.continueWith { future: Future[Int] =>
      throw contEx
    }

    try {
      Await.result(cont, 1.second)
      assert(false, "Continuation did not fail as expected")
    } catch {
      case e: ArithmeticException =>
        assert(e == contEx)
    }
  }
  test("continue: should run continuation with Success") {
    val f = Future.always(10)
    val cont = f.continue { result: Try[Int] =>
      result match {
        case Success(value) => value + 5
        case Failure(_) => -1
      }
    }
    val result = Await.result(cont, 1.second)
    assert(result == 15)
  }

  test("continue: should run continuation with Failure") {
    val ex = new RuntimeException("Original Failure")
    val f = Future[Int] { throw ex }
    val cont = f.continue { result: Try[Int] =>
      result match {
        case Success(_) => "Continuation saw success"
        case Failure(e) if e == ex => "Continuation handled failure"
      }
    }
    val result = Await.result(cont, 1.second)
    assert(result == "Continuation handled failure")
  }
  test("continue: should fail if the continuation itself throws an exception") {
    val contEx = new ArithmeticException("Continuation Failed")
    val f = Future.always(10)
    val cont = f.continue { result: Try[Int] =>
      throw contEx
    }

    try {
      Await.result(cont, 1.second)
      assert(false, "Continuation did not fail as expected")
    } catch {
      case e: ArithmeticException =>
        assert(e == contEx)
    }
  }
import java.util.concurrent.atomic.AtomicBoolean
  test("Future.run should start a cancellable computation") {
    val isRunning = new AtomicBoolean(false)
    val computation = (ct: CancellationToken) => Future {
      isRunning.set(true)
      try {
        while (ct.nonCancelled) {
          Thread.sleep(5)
        }
      } finally {
        isRunning.set(false) 
      }
    }

    val sub: Subscription = Future.run()(computation)

    Thread.sleep(50)
    assert(isRunning.get() == true, "Computation did not start")

    sub.unsubscribe()

    Thread.sleep(50)
    assert(isRunning.get() == false, "Computation did not stop after unsubscribing")
  }

  class DummyExchange(val request: Request) extends Exchange {
    @volatile var response = ""
    val loaded = Promise[String]()
    def write(s: String): Unit = {
      response += s
    }
    def close(): Unit = {
      loaded.success(response)
    }
  }

  class DummyListener(val port: Int, val relativePath: String) extends NodeScala.Listener {
    self =>

    @volatile private var started = false
    var handler: Exchange => Unit = null

    def createContext(h: Exchange => Unit) = this.synchronized {
      assert(started, "is server started?")
      handler = h
    }

    def removeContext() = this.synchronized {
      assert(started, "is server started?")
      handler = null
    }

    def start() = self.synchronized {
      started = true
      new Subscription {
        def unsubscribe() = self.synchronized {
          started = false
        }
      }
    }

    def emit(req: Request) = {
      val exchange = new DummyExchange(req)
      if (handler != null) handler(exchange)
      exchange
    }
  }

  class DummyServer(val port: Int) extends NodeScala {
    self =>
    val listeners = mutable.Map[String, DummyListener]()

    def createListener(relativePath: String) = {
      val l = new DummyListener(port, relativePath)
      listeners(relativePath) = l
      l
    }

    def emit(relativePath: String, req: Request) = this.synchronized {
      val l = listeners(relativePath)
      l.emit(req)
    }
  }
  test("Server should serve requests") {
    val dummy = new DummyServer(8191)
    val dummySubscription = dummy.start("/testDir") {
      request => for (kv <- request.iterator) yield (kv + "\n").toString
    }

    // wait until server is really installed
    Thread.sleep(500)

    def test(req: Request): Unit = {
      val webpage = dummy.emit("/testDir", req)
      val content = Await.result(webpage.loaded.future, 1 second)
      val expected = (for (kv <- req.iterator) yield (kv + "\n").toString).mkString
      assert(content == expected, s"'$content' vs. '$expected'")
    }

    test(immutable.Map("StrangeRequest" -> List("Does it work?")))
    test(immutable.Map("StrangeRequest" -> List("It works!")))
    test(immutable.Map("WorksForThree" -> List("Always works. Trust me.")))

    dummySubscription.unsubscribe()
  }

}




