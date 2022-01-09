package objektwerks

import com.raquo.laminar.api.L.*

import org.scalajs.dom.document
import org.scalajs.dom.console.log

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("App")
object App:
  @JSExport
  def init(): Unit =
    val container = document.getElementById("container")
    val baseNode = div( child <-- Router.splitter.$view )
    val rootNode = render(container, baseNode)
    log(s"App.init() -> root node: $rootNode")
    ()