package laminar

import com.raquo.laminar.api.L._
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Container")
object Container {
  @JSExport
  def init(): Unit = {
    val container = document.getElementById("container")
    val items = List(Item(value = "wash car"), Item(value = "mow yard"), Item(value = "clean pool"))
    val itemView = ItemView(items).element
    render(container, itemView)
    ()
  }
}