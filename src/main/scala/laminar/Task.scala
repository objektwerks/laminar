package laminar

import java.util.concurrent.atomic.AtomicInteger

import com.raquo.laminar.api.L._

class Task private(val id: Int, val task: String, val element: HtmlElement)

object Task {
  val id = new AtomicInteger()

  def newId:Int = id.incrementAndGet

  def apply(id: Int = newId, task: String, element: HtmlElement): String = {
    val taskStream: EventStream[List[Task]] =

    def renderTask(id: String, task: String, taskStream: EventStream[Task]): Div = {
      div(
        p("task: ", child.text <-- taskStream.map(_.task))
      )
    }
    val taskElements: EventStream[List[Div]] = taskStream.split(_.id)(renderTask)
    div("Tasks: ", children <-- taskElements)
  }
}