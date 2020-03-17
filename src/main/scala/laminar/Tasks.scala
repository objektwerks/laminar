package laminar

import org.scalajs.dom.console._

case class Task(id: String = Task.id.increment(),
                value: String,
                opened: String = Dates.nowAsString(),
                closed: String = Dates.nowAsString())

object Task {
  val id = Id()
  val empty = Task("", "", "", "")
}

// TODO: Refactor UI!
object Tasks {
  import com.raquo.laminar.api.L._

  def apply(tasksVar: Var[List[Task]]): HtmlElement = new View(new Model(tasksVar)).render

  private class Logger(tasksVar: Var[List[Task]]) {
    import com.raquo.airstream.ownership.Owner

    implicit val owner = new Owner {}
    tasksVar.signal.foreach(tasks => log(s"tasks change event -> ${tasks.toString}"))
  }

  private class Model(val tasksVar: Var[List[Task]]) {
    val _ = new Logger(tasksVar)
    val selectedTaskVar: Var[Option[Task]] = Var(None)

    def selectedTask: Task = selectedTaskVar.now.getOrElse(Task.empty)

    def onSelectedTask(id: String): Unit = {
      val task = tasksVar.now.find(_.id == id)
      selectedTaskVar.set(task)
    }

    def onAddTask(value: String): Unit = tasksVar.update(_ :+ Task(value = value))

    def onEditTask(id: String, value: String): Unit = {
      tasksVar.update(_.map(task => if (task.id == id) task.copy(value = value) else task))
      selectedTaskVar.set(None)
    }

    def onRemoveTask(id: String): Unit = tasksVar.update(_.filterNot(_.id == id))
  }

  private class View(model: Model) {
    import InnerHtmlModifier._
    import org.scalajs.dom.ext.KeyCode

    val onEnterPress = onKeyPress.filter(_.keyCode == KeyCode.Enter)

    def render: HtmlElement = renderRoot(model.tasksVar.signal.split(_.id)((_, task, taskSignal) => renderTask(task, taskSignal)))

    def renderRoot(tasksSignal: Signal[List[Li]]): HtmlElement =
      div(cls("w3-container"),
        div(
          h4(cls("w3-light-grey w3-text-indigo"), "Task"),
          renderAddTask,
          renderEditTask
        ),
        div(
          h4(cls("w3-light-grey w3-text-indigo"), "Tasks"),
          renderTasks(tasksSignal)
        )
      )

    def renderTask(task: Task, taskSignal: Signal[Task]): Li =
      li(cls("w3-text-indigo w3-display-container"),
        child.text <-- taskSignal.map(task.id + ". " + _.value),
        span(cls("w3-button w3-display-right w3-text-indigo"),
          onClick.mapTo(task.id) --> { id => model.onRemoveTask(id) },
          unsafeInnerHtml := "&times;"
        ),
        onClick.mapTo(task.id) --> { id => model.onSelectedTask(id) }
      )

    def renderTasks(tasksSignal: Signal[List[Li]]): Div =
      div(cls("w3-container"),
        ul(cls("w3-ul w3-hoverable"), children <-- tasksSignal)
      )

    def renderAddTask: Div =
      div(cls("w3-container"), paddingTop("3px"), paddingBottom("3px"),
        div(cls("w3-row"),
          div(cls("w3-col"), width("15%"), label(cls("w3-left-align w3-text-indigo"), "Add:")),
          div(cls("w3-col"), width("85%"),
            input(cls("w3-input w3-hover-light-gray w3-text-indigo"), typ("text"),
              inContext { input =>
                onEnterPress.mapTo(input.ref.value).filter(_.nonEmpty) --> { value =>
                  model.onAddTask(value)
                  input.ref.value = ""
                }
              }
            )
          )
        )
      )

    def renderEditTask: Div =
      div(cls("w3-container"), paddingTop("3px"), paddingBottom("3px"),
        div(cls("w3-row"),
          div(cls("w3-col"), width("15%"), label(cls("w3-left-align w3-text-indigo"), "Edit:")),
          div(cls("w3-col"), width("85%"),
            input(cls("w3-input w3-hover-light-gray w3-text-indigo"), typ("text"), readOnly(true),
              value <-- model.selectedTaskVar.signal.map(_.getOrElse(Task.empty).value),
              readOnly <-- model.selectedTaskVar.signal.map(_.isEmpty),
              inContext { input =>
                onEnterPress.mapTo(input.ref.value).filter(_.nonEmpty) --> { value =>
                  model.onEditTask(model.selectedTask.id, value)
                }
              }
            )
          )
        )
      )
  }

}