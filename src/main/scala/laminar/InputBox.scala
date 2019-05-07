package laminar

import com.raquo.laminar.api.L._

class InputBox private(val element: HtmlElement, val box: Input)

object InputBox {
  def apply(label: String, typeof: String): InputBox = {
    val box = input(typ := typeof)
    val element = div(label, box)
    new InputBox(element, box)
  }
}