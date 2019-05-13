package laminar

import com.raquo.laminar.api.L._
import org.scalajs.dom.ext.KeyCode

class ItemView private(val element: HtmlElement)

object ItemView {
  import UnsafeInnerHtmlModifier._

  private val itemsVar: Var[List[Item]] = Var[List[Item]](List.empty[Item])
  private val onEnterPress = onKeyPress.filter(_.keyCode == KeyCode.Enter)

  def apply(items: List[Item]): ItemView = {
    itemsVar.set(items)
    val liSignal: Signal[List[Li]] = itemsVar.signal.split(_.id)(renderItem)
    val element: HtmlElement = renderItems(liSignal)
    new ItemView(element)
  }

  def renderItem(itemId: String, item: Item, itemSignal: Signal[Item]): Li = li(
    id := itemId,
    cls := "w3-display-container",
    div(
      p(itemId + ". ", child.text <-- itemSignal.map(_.value)),
      p("edited: ", child.text <-- itemSignal.map(_ != item ).map(_.toString))
    ),
    inContext { liElement =>
      span(
        cls := "w3-button w3-display-right",
        onClick --> { _ =>
          display.none(liElement)
          itemsVar.update(_.filterNot(_.id == liElement.ref.id))
        },
        unsafeInnerHtml := "&times;"
      )
    }
  )

  def renderItems(liSignal: Signal[List[Li]]): Div = {
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
          div(cls := "w3-col", width := "85%", input(cls := "w3-input", typ := "text", inContext { itemInput =>
            onEnterPress.mapTo(itemInput.ref.value).filter(_.nonEmpty) --> { _ =>
              itemsVar.update(_ :+ Item(value = itemInput.ref.value))
              itemInput.ref.value = ""
            }
          }))
        )
      )
    )
  }
}