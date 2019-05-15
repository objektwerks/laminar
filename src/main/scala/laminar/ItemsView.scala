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
  private val itemsSignal: Signal[List[Li]] = itemsVar.signal.split(_.id)(renderItem)
  private val addItemElement: HtmlElement = renderAddItem
  private val itemsElement: HtmlElement = renderItems(itemsSignal)
  private val element = render(addItemElement, itemsElement)
  log("items", itemsVar.now.toString)

  def htmlElement: HtmlElement = element

  private def render(addItemElement: HtmlElement, itemsElement: HtmlElement): HtmlElement =
    div(cls("w3-container"),
      div(cls("w3-container"),
        header(cls("w3-container w3-indigo"), h4("Item")),
        section(cls("w3-container"), addItemElement)
      ),
      div(cls("w3-container"),
        header(cls("w3-container w3-indigo"), h4("Items")),
        section(cls("w3-container"), itemsElement)
      )
    )

  private def renderItem(itemId: String, item: Item, itemSignal: Signal[Item]): Li =
    li(id(itemId), cls("w3-display-container"),
      item.id + ". ", child.text <-- itemSignal.map(_.value),
      inContext { liElement =>
        span(cls("w3-button w3-display-right"),
          onClick --> { _ =>
            itemsVar.update(_.filterNot(_.id == liElement.ref.id))
            display.none(liElement)
            log("removed item", itemsVar.now.toString)
          },
          unsafeInnerHtml := "&times;"
        )
      }
    )

  private def renderItems(itemsSignal: Signal[List[Li]]): Div =
    div(cls("w3-container"),
      ul(cls("w3-ul w3-hoverable"), children <-- itemsSignal)
    )

  private def renderAddItem: HtmlElement =
    div(cls("w3-container"), paddingTop("10px"), paddingBottom("10px"),
      div(cls("w3-row"),
        div(cls("w3-col"), width("15%"), label("Add:")),
        div(cls("w3-col"), width("85%"), input(cls("w3-input w3-hover-light-gray"), typ("text"),
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
}