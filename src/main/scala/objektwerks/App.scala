package objektwerks

import com.raquo.laminar.api.L.*

import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("App")
object App:
  @JSExport
  def init(): Unit =
    val app = div(
      child <-- Router.splitter.$view
    )
    render(
      container = document.getElementById("content"),
      rootNode = app
    )
    ()