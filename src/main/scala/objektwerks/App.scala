package objektwerks

import com.raquo.laminar.api.L._
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("App")
object App {
  @JSExport
  def init(): Unit = {
    val app = document.getElementById("app")
    val items = createItems()
    render(app, items)
    ()
  }

  private def createItems(): HtmlElement = {
    val itemsVar = Var(List[Item](Item(value = "wash car"), Item(value = "mow yard"), Item(value = "clean pool")))
    Items(itemsVar)
  }
}