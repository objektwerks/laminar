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
    val itemView = ItemView(Item.items, "Items:").element
    render(container, div(counter, itemView))
    ()
  }
}