package objektwerks

import com.raquo.laminar.api.L.*

import org.scalajs.dom.document
import org.scalajs.dom.console.log

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("App")
object App:
  @JSExport
  def init(): Unit =
    render(
      container = document.getElementById("container"),
      rootNode = div( child <-- Router.splitter.$view )
    )
    log("App.init()")
    ()