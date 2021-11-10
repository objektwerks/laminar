package objektwerks

import com.raquo.airstream.web.AjaxEventStream
import com.raquo.airstream.web.AjaxEventStream.AjaxStreamError
import com.raquo.laminar.api.L._

import scala.concurrent.ExecutionContext.Implicits.global

import utest._

object AirstreamAsyncTest extends TestSuite {
  def parse(joke: String): String = {
    val json = ujson.read(joke)
    json("value").obj("joke").str
  }

  val tests = Tests {
    test("ajax") {
      val stream = AjaxEventStream
        .get("http://api.icndb.com/jokes/random/")
        .map { response =>
          val joke = parse(response.responseText)
          println(s"*** $joke")
          joke
        }
        .recover { case error: AjaxStreamError => Some(error.getMessage) }
      val signal = stream.toSignal("").observe(new Owner {})
      signal.now()
    }
  }

  TestRunner.runAsync(tests).map { asyncResults =>
    val results = asyncResults.leaves.toSeq
    assert(results.head.value.isSuccess)
    assert(results.tail.head.value.isSuccess)
  }
}