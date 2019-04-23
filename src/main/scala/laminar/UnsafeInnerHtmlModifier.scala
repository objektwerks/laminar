package laminar

import com.raquo.laminar.api.L._

object UnsafeInnerHtmlModifier {
  val unsafeInnerHtml: UnsafeInnerHtmlModifier.type = UnsafeInnerHtmlModifier

  def :=[El <: Element](innerHtml: String): Modifier[El] = {
    new Modifier[El] {
      override def apply(element: El): Unit = element.ref.innerHTML = innerHtml
    }
  }
}