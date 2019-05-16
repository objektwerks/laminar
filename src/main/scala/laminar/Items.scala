package laminar

import java.util.concurrent.atomic.AtomicInteger

import com.raquo.laminar.api.L._
import org.scalajs.dom.console._
import org.scalajs.dom.ext.KeyCode

case class Item(id: String = Item.newId(), value: String)

object Item {
  private val autoinc = new AtomicInteger()
  val newId = () => autoinc.incrementAndGet.toString
  val emptyItem = Item("", "")
}

object Items {
  def apply(itemsVar: Var[List[Item]]): Items = new Items(itemsVar)
  val onEnterPress = onKeyPress.filter(_.keyCode == KeyCode.Enter)
}

class Items private(itemsVar: Var[List[Item]]) {
  import UnsafeInnerHtmlModifier._
  import Items.onEnterPress

  private val itemEventBus = new EventBus[Item]()
  private val itemsSignal: Signal[List[Li]] = itemsVar.signal.split(_.id)(renderItem)
  private val addItemElement: HtmlElement = renderAddItem
  private val updateItemElement: HtmlElement = renderUpdateItem
  private val itemsElement: HtmlElement = renderItems(itemsSignal)
  private val rootElement = renderRoot(addItemElement, updateItemElement, itemsElement)

  log("items", itemsVar.now.toString)

  def render: HtmlElement = rootElement

  private def renderRoot(addItemElement: HtmlElement, updateItemElement: HtmlElement, itemsElement: HtmlElement): HtmlElement =
    div(cls("w3-container"),
      div(cls("w3-container"),
        header(cls("w3-container w3-indigo"), h4("Item")),
        section(cls("w3-container"), addItemElement),
        section(cls("w3-container"), updateItemElement)
      ),
      div(cls("w3-container"),
        header(cls("w3-container w3-indigo"), h4("Items")),
        section(cls("w3-container"), itemsElement)
      )
    )

  private def renderItem(itemId: String, item: Item, itemSignal: Signal[Item]): Li =
    li(id(itemId), cls("w3-display-container"),
      item.id + ". ",
      child.text <-- itemSignal.map(_.value),
      inContext { li =>
        span(cls("w3-button w3-display-right"),
          onClick --> { _ =>
            itemsVar.update(_.filterNot(_.id == li.ref.id))
            display.none(li)
            log("removed item", itemsVar.now.toString)
          },
          unsafeInnerHtml := "&times;"
        )
      },
      inContext { li =>
        onClick --> { _ =>
          log("selected item", itemsVar.now.find(_.id == li.ref.id).toString)
          itemEventBus.writer.onNext(itemsVar.now.find(_.id == li.ref.id).getOrElse(Item.emptyItem))
        }
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
          inContext { input =>
            onEnterPress.mapTo(input.ref.value).filter(_.nonEmpty) --> { _ =>
              itemsVar.update(_ :+ Item(value = input.ref.value))
              input.ref.value = ""
              log("added item", itemsVar.now.toString)
            }
          }
        ))
      )
    )

  private def renderUpdateItem: HtmlElement =
    div(cls("w3-container"), paddingTop("10px"), paddingBottom("10px"),
      div(cls("w3-row"),
        div(cls("w3-col"), width("15%"), label("Update:")),
        div(cls("w3-col"), width("85%"),
          input(cls("w3-input w3-hover-light-gray"), typ("text"),
            id <-- itemEventBus.events.map(_.id),
            value <-- itemEventBus.events.map(_.value),
            inContext { input =>
              onEnterPress.mapTo(input.ref.value).filter(_.nonEmpty) --> { _ =>
                itemsVar.update(_.map(item => if (item.id == input.ref.id) item.copy(value = input.ref.value) else item))
                input.ref.value = ""
                log("updated item", itemsVar.now.find(_.id == input.ref.id).toString)
              }
            }
          )
        )
      )
    )
}