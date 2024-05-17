package objektwerks

import com.raquo.airstream.web.AjaxStream
import com.raquo.airstream.web.AjaxStream.AjaxStreamError
import com.raquo.laminar.api.L.*

import scala.concurrent.ExecutionContext.Implicits.global

import utest.*

object AirstreamAsyncTest extends TestSuite {
  def parse(json: String): String = ujson.read(json)("value").str

  val tests = Tests {
    test("ajax") {
      val stream = AjaxStream
        .get("https://api.chucknorris.io/jokes/random")
        .map { response =>
          val joke = parse(response.responseText)
          println(s"*** $joke")
          assert(joke.nonEmpty)
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