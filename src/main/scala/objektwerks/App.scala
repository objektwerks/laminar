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
    val tasks = createTasks()
    render(app, div(items, tasks))
    ()
  }

  private def createItems(): HtmlElement = {
    val itemsVar = Var(List[Item](Item(value = "wash car"), Item(value = "mow yard"), Item(value = "clean pool")))
    Items(itemsVar)
  }

  private def createTasks(): HtmlElement = {
    val tasksVar = Var(List[Task](Task(value = "clean kitchen"), Task(value = "polish silver"), Task(value = "clean floors")))
    Tasks(tasksVar)
  }
}