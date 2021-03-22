package objektwerks

import com.raquo.laminar.api.L._

import org.scalajs.dom.document

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("App")
object App {
  @JSExport
  def init(): Unit = {
    val items = List( Item(value = "wash car") )
    val varItems = Var( items )
    render(
      container = document.getElementById("app"),
      rootNode = Items( varItems )
    )
    ()
  }
}