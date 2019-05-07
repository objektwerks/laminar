package laminar

import com.raquo.laminar.api.L._

class InputBox private(val element: HtmlElement, val eventSource: Input)

object InputBox {
  def apply(label: String, typeof: String): InputBox = {
    val eventSource = input(typ := typeof)
    val element = div(label, eventSource)
    new InputBox(element, eventSource)
  }
}