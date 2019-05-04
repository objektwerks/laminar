package laminar

import java.util.concurrent.atomic.AtomicInteger

import com.raquo.laminar.api.L._

class Task private(val id: Int, val item: String, val element: HtmlElement)

object Task {
  val id = new AtomicInteger()

  def newId:Int = id.incrementAndGet

  def apply(id: Int = newId, task: String, element: HtmlElement): Task = {
    val taskStream: EventStream[List[Task]] = EventStream(List.empty[Task])

    def render(id: Int, task: String, taskStream: EventStream[Task]): Div = {
      div(
        p("task: ", child.text <-- taskStream.map(_.item))
      )
    }
    val element = div(children <-- taskStream.split(_.id)(render(_, task, taskStream)))
    new Task(id, task, element)
  }
}