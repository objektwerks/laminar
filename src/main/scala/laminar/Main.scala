package laminar

@JSExportTopLevel("Main")
object Main {
  @JSExport
  def init(): Unit = {
    val modelView = ModelView()
    modelView.init()
  }
}