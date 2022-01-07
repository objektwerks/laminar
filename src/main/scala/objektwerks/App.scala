package objektwerks

import com.raquo.laminar.api.L.*

import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("App")
object App extends Router:
  @JSExport
  def init(): Unit =
    val app = div(
      child <-- router.$currentPage.map(Page.render)
    )
    render(
      container = document.getElementById("content"),
      rootNode = app
    )
    ()