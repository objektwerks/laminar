package laminar

import com.raquo.laminar.api.L._
import org.scalajs.dom.MouseEvent

class Counter private(val countSignal: Signal[Int], val element: HtmlElement)

object Counter {
  def apply(label: String): Counter = {
    val incrementClickBus = new EventBus[MouseEvent]
    val decrementClickBus = new EventBus[MouseEvent]
    val countSignal = EventStream
      .merge(incrementClickBus.events.mapTo(1), decrementClickBus.events.mapTo(-1))
      .fold(initial = 0)(_ + _)
    val element = div(
      cls := "w3-container",
      child <-- countSignal.map(count => span(s"$label $count  ")),
      button(cls := "w3-btn w3-circle", onClick --> decrementClickBus, "-"),
      button(cls := "w3-btn w3-circle", onClick --> incrementClickBus, "+")
    )
    new Counter(countSignal, element)
  }
}