package laminar

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Client")
object Client {
  @JSExport
  def init(): Unit = ModelView().init()
}