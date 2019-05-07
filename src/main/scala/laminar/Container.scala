package laminar

import com.raquo.laminar.api.L._
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Container")
object Container {
  @JSExport
  def init(): Unit = {
    val container = document.getElementById("container")
    val counter = Counter(label = "Count:").element
    val items = List(Item(value = "one"), Item(value = "two"), Item(value = "three"))
    val itemView = ItemView(items, "Items:").element
    render(container, div(counter, itemView))
    ()
  }
}