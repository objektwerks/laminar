package laminar

import com.raquo.laminar.api.L._
import laminar.UnsafeInnerHtmlModifier._
import org.scalajs.dom.console._
import org.scalajs.dom.ext.KeyCode

object ItemsView {
  def apply(itemsVar: Var[List[Item]]): ItemsView = new ItemsView(itemsVar)
}

class ItemsView private(itemsVar: Var[List[Item]]) {
  private val onEnterPress = onKeyPress.filter(_.keyCode == KeyCode.Enter)
  private val liSignal: Signal[List[Li]] = itemsVar.signal.split(_.id)(renderItem)
  private val element: HtmlElement = renderItems(liSignal)
  log("items", itemsVar.now.toString)

  def htmlElement: HtmlElement = element

  private def renderItem(itemId: String, item: Item, itemSignal: Signal[Item]): Li = li(
    id := itemId,
    cls := "w3-display-container",
    div(
      p(itemId + ". ", child.text <-- itemSignal.map(_.value)),
      p("edited: ", child.text <-- itemSignal.map(_ != item).map(_.toString))
    ),
    inContext { liElement =>
      span(
        cls := "w3-button w3-display-right",
        onClick --> { _ =>
          itemsVar.update(_.filterNot(_.id == liElement.ref.id))
          display.none(liElement)
          log("removed item", itemsVar.now.toString)
        },
        unsafeInnerHtml := "&times;"
      )
    }
  )

  private def renderItems(liSignal: Signal[List[Li]]): Div = {
    div(
      cls := "w3-container",
      h4(cls := "w3-indigo", "Items"),
      ul(
        cls := "w3-ul w3-hoverable",
        children <-- liSignal
      ),
      hr(),
      div(
        cls := "w3-container",
        div(
          cls := "w3-row",
          div(cls := "w3-col", width := "15%", label("Add:")),
          div(cls := "w3-col", width := "85%", input(cls := "w3-input w3-hover-light-gray", typ := "text",
            inContext { itemInput =>
              onEnterPress.mapTo(itemInput.ref.value).filter(_.nonEmpty) --> { _ =>
                itemsVar.update(_ :+ Item(value = itemInput.ref.value))
                itemInput.ref.value = ""
                log("added item", itemsVar.now.toString)
              }
            }
          ))
        )
      )
    )
  }
}