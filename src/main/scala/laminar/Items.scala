package laminar

import java.util.concurrent.atomic.AtomicInteger

import com.raquo.laminar.api.L._
import org.scalajs.dom.console._
import org.scalajs.dom.ext.KeyCode

case class Item(id: String = Item.newId(), value: String)

object Item {
  private val autoinc = new AtomicInteger()
  val newId = () => autoinc.incrementAndGet.toString
  val empty = Item("", "")
}

object Items {
  private val onEnterPress = onKeyPress.filter(_.keyCode == KeyCode.Enter)

  def apply(itemsVar: Var[List[Item]]): HtmlElement = {
    log("items", itemsVar.now.toString)
    new Items(itemsVar).rootElement
  }

  private def onSelectItem(itemsVar: Var[List[Item]], id: String): Item = {
    val item = itemsVar.now.find(_.id == id).getOrElse(Item.empty)
    log("selected item", item.toString)
    item
  }

  private def onAddItem(itemsVar: Var[List[Item]], value: String): Unit = {
    itemsVar.update(_ :+ Item(value = value))
    log("added item", itemsVar.now.toString)
  }

  private def onEditItem(itemsVar: Var[List[Item]], id: String, value: String): Unit = {
    itemsVar.update(_.map(item => if (item.id == id) item.copy(value = value) else item))
    log("edited item", onSelectItem(itemsVar, id).toString)
  }

  private def onRemoveItem(itemsVar: Var[List[Item]], id: String): Unit = {
    itemsVar.update(_.filterNot(_.id == id))
    log("removed item", itemsVar.now.toString)
  }
}

class Items private(itemsVar: Var[List[Item]]) {
  import InnerHtmlModifier._
  import Items._

  private val itemEventBus = new EventBus[Item]()
  private val itemsSignal: Signal[List[Li]] = itemsVar.signal.split(_.id)(renderItem)
  private val addItemElement: HtmlElement = renderAddItem
  private val editItemElement: HtmlElement = renderEditItem
  private val itemsElement: HtmlElement = renderItems(itemsSignal)
  private val rootElement: HtmlElement = renderRoot(addItemElement, editItemElement, itemsElement)

  private def renderRoot(addItemElement: HtmlElement, updateItemElement: HtmlElement, itemsElement: HtmlElement): HtmlElement =
    div(cls("w3-container"),
      div(
        div(cls("w3-light-grey"), h4(cls("w3-text-indigo"), "Item")),
        div(addItemElement),
        div(updateItemElement)
      ),
      div(
        div(cls("w3-light-grey"), h4(cls("w3-text-indigo"), "Items")),
        div(itemsElement)
      )
    )

  private def renderItem(itemId: String, item: Item, itemSignal: Signal[Item]): Li =
    li(id(itemId), cls("w3-text-indigo w3-display-container"),
      child.text <-- itemSignal.map(item.id + ". " + _.value),
      inContext { li =>
        log("rendered item", item.toString)
        span(cls("w3-button w3-display-right w3-text-indigo"),
          onClick --> { _ =>
            onRemoveItem(itemsVar, li.ref.id)
            display.none(li)
          },
          unsafeInnerHtml := "&times;"
        )
      },
      inContext { li =>
        onClick --> { _ => itemEventBus.writer.onNext(onSelectItem(itemsVar, li.ref.id)) }
      }
    )

  private def renderItems(itemsSignal: Signal[List[Li]]): Div =
    div(cls("w3-container"),
      ul(cls("w3-ul w3-hoverable"), children <-- itemsSignal)
    )

  private def renderAddItem: HtmlElement =
    div(cls("w3-container"), paddingTop("3px"), paddingBottom("3px"),
      div(cls("w3-row"),
        div(cls("w3-col"), width("15%"), label(cls("w3-left-align w3-text-indigo"), "Add:")),
        div(cls("w3-col"), width("85%"),
          input(cls("w3-input w3-hover-light-gray w3-text-indigo"), typ("text"),
            inContext { input =>
              onEnterPress.mapTo(input.ref.value).filter(_.nonEmpty) --> { _ =>
                onAddItem(itemsVar, input.ref.value)
                input.ref.value = ""
              }
            }
          )
        )
      )
    )

  private def renderEditItem: HtmlElement =
    div(cls("w3-container"), paddingTop("3px"), paddingBottom("3px"),
      div(cls("w3-row"),
        div(cls("w3-col"), width("15%"), label(cls("w3-left-align w3-text-indigo"), "Edit:")),
        div(cls("w3-col"), width("85%"),
          input(cls("w3-input w3-hover-light-gray w3-text-indigo"), typ("text"), readOnly(true),
            id <-- itemEventBus.events.map(_.id),
            value <-- itemEventBus.events.map(_.value),
            readOnly <-- itemEventBus.events.map(_.id.isEmpty),
            inContext { input =>
              onEnterPress.mapTo(input.ref.value).filter(_.nonEmpty) --> { _ =>
                onEditItem(itemsVar, input.ref.id, input.ref.value)
                input.ref.id = ""
                input.ref.value = ""
                readOnly(true)(input)
              }
            }
          )
        )
      )
    )
}