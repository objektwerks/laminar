package laminar

import com.raquo.laminar.api.L._
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Container")
object Container {
  @JSExport
  def init(): Unit = {
    val container = document.getElementById("container")
    val items = List(StringItem(value = "one"), StringItem(value = "two"), StringItem(value = "three"))
    val itemView = ItemView(items, "Items:").element
    render(container, itemView)
    ()
  }
}