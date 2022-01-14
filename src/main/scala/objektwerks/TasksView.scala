package objektwerks

import com.raquo.laminar.api.L.*
import com.raquo.airstream.ownership.Owner

import org.scalajs.dom.console.log
import org.scalajs.dom.KeyCode

import InnerHtmlModifier.*

final case class Task(id: String = Task.nextId, value: String)

object Task:
  private var id = 0
  val empty = Task("", "")

  private def nextId: String =
    id = id + 1
    id.toString

object TasksView:
  def apply(tasks: Var[List[Task]]): HtmlElement = Renderer( Model(tasks) ).render

  private final class Model(val tasksVar: Var[List[Task]]):
    given owner: Owner = new Owner {}
    tasksVar.signal.foreach(tasks => log(s"tasks change event -> ${tasks.toString}"))

    val selectedTaskVar: Var[Option[Task]] = Var(None)

    def selectedTask: Task = selectedTaskVar.now().getOrElse(Task.empty)

    def onSelectTask(id: String): Unit =
      val task = tasksVar.now().find(_.id == id)
      selectedTaskVar.set(task)

    def onAddTask(value: String): Unit = tasksVar.update(_ :+ Task(value = value))

    def onEditTask(id: String, value: String): Unit =
      tasksVar.update(_.map(task => if (task.id == id) task.copy(value = value) else task))
      selectedTaskVar.set(None)

    def onRemoveTask(id: String): Unit = tasksVar.update(_.filterNot(_.id == id))

  private final class Renderer(model: Model):
    val onEnterPress = onKeyPress.filter(_.keyCode == KeyCode.Enter)

    def render: HtmlElement =
      renderRoot(
        model
          .tasksVar
          .signal
          .split(_.id)( (_, task, taskSignal) => renderTask(task, taskSignal) )
      )

    def renderRoot(tasksSignal: Signal[List[Li]]): HtmlElement =
      div(cls("w3-container"),
        div(
          h5(cls("w3-light-grey w3-text-indigo"), "Task"),
          renderAddTask,
          renderEditTask
        ),
        div(
          h5(cls("w3-light-grey w3-text-indigo"), "Tasks"),
          renderTasks(tasksSignal)
        )
      )

    def renderTasks(tasksSignal: Signal[List[Li]]): Div =
      div(cls("w3-container"),
        ul(cls("w3-ul w3-hoverable"), children <-- tasksSignal)
      )

    def renderTask(task: Task, taskSignal: Signal[Task]): Li =
      li(cls("w3-text-indigo w3-display-container"),
        child.text <-- taskSignal.map(task.id + ". " + _.value),
        span(cls("w3-button w3-display-right w3-text-indigo"),
          onClick.mapTo(task.id) --> { id => model.onRemoveTask(id) },
          unsafeInnerHtml := "&times;"
        ),
        onClick.mapTo(task.id) --> { id => model.onSelectTask(id) }
      )

    def renderAddTask: Div =
      div(cls("w3-container"), paddingTop("3px"), paddingBottom("3px"),
        label(cls("w3-left-align w3-text-indigo"), "Add"),
        input(
          cls("w3-input w3-hover-light-gray w3-text-indigo"),
          typ("text"),
          placeholder("Enter task, press enter."),
          inContext { input =>
            onEnterPress.mapToValue.filter(_.nonEmpty) --> { value =>
              model.onAddTask(value)
              input.ref.value = ""
            }
          }
        )
      )

    def renderEditTask: Div =
      div(cls("w3-container"), paddingTop("3px"), paddingBottom("3px"),
        label(cls("w3-left-align w3-text-indigo"), "Edit"),
        input(
          cls("w3-input w3-hover-light-gray w3-text-indigo"),
          typ("text"),
          readOnly(true),
          placeholder("Select and edit task, press enter."),
          value <-- model.selectedTaskVar.signal.map(_.getOrElse(Task.empty).value),
          readOnly <-- model.selectedTaskVar.signal.map(_.isEmpty),
          onEnterPress.mapToValue.filter(_.nonEmpty) --> { value =>
            model.onEditTask(model.selectedTask.id, value)
          }
        )
      )