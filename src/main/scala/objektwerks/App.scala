package objektwerks

import com.raquo.laminar.api.L._
import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("App")
object App {
  @JSExport
  def init(): Unit = {
    render(
      container = document.getElementById("app"),
      rootNode = Items( Var(List[Item](Item(value = "wash car"), Item(value = "mow yard"), Item(value = "clean pool"))) )
    )
    ()
  }
}