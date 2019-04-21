package laminar

import com.raquo.laminar.api.L._
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Client")
object Container {
  @JSExport
  def init(): Unit = {
    val container = document.getElementById("container")
    val element = Counter("counter").element
    render(container, element)
    ()
  }
}