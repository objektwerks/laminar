package laminar

import com.raquo.laminar.api.L._

object UnsafeInnerHtmlReceiver {
  val unsafeInnerHtml: UnsafeInnerHtmlReceiver.type = UnsafeInnerHtmlReceiver

  def :=[El <: Element](innerHtml: String): Modifier[El] = {
    new Modifier[El] {
      override def apply(element: El): Unit = element.ref.innerHTML = innerHtml
    }
  }
}