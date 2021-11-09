package objektwerks

import com.raquo.laminar.api.L._

import utest._

object AirstreamTest extends TestSuite {
  val tests = Tests {
    test("var") {
      val integerVar = Var(1)
      assert( integerVar.now() == 1 )

      integerVar.set(2)
      assert( integerVar.now() == 2 )

      integerVar.update(i => i + 1)
      assert( integerVar.now() == 3 )

      implicit val owner = new Owner {}
      assert( integerVar.signal.observe.now() == 3 )
    }

    test("eventbus") {
      val intEventBus = new EventBus[Int]()
      implicit val owner = new Owner {}
      intEventBus.events.foreach { i => assert( i == 1 ) }
      intEventBus.emit(1)
    }

    test("eventstream") {
      val (stream, callback) = EventStream.withCallback[Int]
      implicit val owner = new Owner {}
      stream.foreach { i => assert( i == 1 ) }
      callback(1)
    }

    test("signal") {
      val (stream, callback) = EventStream.withCallback[Int]
      val intSignal = stream.startWith(0)
      implicit val owner = new Owner {}
      intSignal.changes.foreach { i => assert( i == 1 ) }
      assert( intSignal.observe.now() == 0 )
      callback(1)
      assert( intSignal.observe.now() == 1 )
    }
  }
  TestRunner.run(tests)
}