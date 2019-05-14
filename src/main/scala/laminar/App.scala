package laminar

import com.raquo.laminar.api.L._
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("App")
object App {
  @JSExport
  def init(): Unit = {
    val app = document.getElementById("app")
    val items = List[Item](Item(value = "wash car"), Item(value = "mow yard"), Item(value = "clean pool"))
    val itemsVar = Var(items)
    val itemView = ItemsView(itemsVar).htmlElement
    render(app, itemView)
    ()
  }
}