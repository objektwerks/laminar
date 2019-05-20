package laminar

import com.raquo.laminar.api.L._

case class Item(id: String = Item.newId(), value: String)

object Item {
  import java.util.concurrent.atomic.AtomicInteger

  private val autoinc = new AtomicInteger()
  val newId = () => autoinc.incrementAndGet.toString
  val empty = Item("", "")
}

object Items {
  def apply(itemsVar: Var[List[Item]]): HtmlElement = new View(new Model(itemsVar)).render

  private class Model(val itemsVar: Var[List[Item]]) {
    import org.scalajs.dom.console._

    log("items", itemsVar.now.toString)

    val selectedItemVar: Var[Option[Item]] = Var(None)

    def selectedItem: Item = selectedItemVar.now.getOrElse(Item.empty)

    def onSelectItem(id: String): Item = {
      val item = itemsVar.now.find(_.id == id).getOrElse(Item.empty)
      log("selected item", item.toString)
      item
    }

    def onAddItem(value: String): Unit = {
      itemsVar.update(_ :+ Item(value = value))
      log("added item", itemsVar.now.toString)
    }

    def onEditItem(id: String, value: String): Unit = {
      itemsVar.update(_.map(item => if (item.id == id) item.copy(value = value) else item))
      log("edited item", onSelectItem(id).toString)
    }

    def onRemoveItem(id: String): Unit = {
      itemsVar.update(_.filterNot(_.id == id))
      log("removed item", itemsVar.now.toString)
    }
  }

  private class View(model: Model) {
    import org.scalajs.dom.ext.KeyCode
    import InnerHtmlModifier._

    val onEnterPress = onKeyPress.filter(_.keyCode == KeyCode.Enter)

    def render: HtmlElement = renderRoot(model.itemsVar.signal.split(_.id)(renderItem))

    def renderRoot(itemsSignal: Signal[List[Li]]): HtmlElement =
      div(cls("w3-container"),
        div(
          h4(cls("w3-light-grey w3-text-indigo"), "Item"),
          renderAddItem,
          renderEditItem
        ),
        div(
          h4(cls("w3-light-grey w3-text-indigo"), "Items"),
          renderItems(itemsSignal)
        )
      )

    def renderItem(itemId: String, item: Item, itemSignal: Signal[Item]): Li =
      li(cls("w3-text-indigo w3-display-container"),
        child.text <-- itemSignal.map(item.id + ". " + _.value),
        inContext { li =>
          span(cls("w3-button w3-display-right w3-text-indigo"),
            onClick --> { _ =>
              model.onRemoveItem(itemId)
              display.none(li)
            },
            unsafeInnerHtml := "&times;"
          )
        },
        inContext { _ =>
          onClick --> { _ => model.selectedItemVar.set(Some(model.onSelectItem(itemId))) }
        }
      )

    def renderItems(itemsSignal: Signal[List[Li]]): Div =
      div(cls("w3-container"),
        ul(cls("w3-ul w3-hoverable"), children <-- itemsSignal)
      )

    def renderAddItem: Div =
      div(cls("w3-container"), paddingTop("3px"), paddingBottom("3px"),
        div(cls("w3-row"),
          div(cls("w3-col"), width("15%"), label(cls("w3-left-align w3-text-indigo"), "Add:")),
          div(cls("w3-col"), width("85%"),
            input(cls("w3-input w3-hover-light-gray w3-text-indigo"), typ("text"),
              inContext { input =>
                onEnterPress.mapTo(input.ref.value).filter(_.nonEmpty) --> { _ =>
                  model.onAddItem(input.ref.value)
                  input.ref.value = ""
                }
              }
            )
          )
        )
      )

    def renderEditItem: Div =
      div(cls("w3-container"), paddingTop("3px"), paddingBottom("3px"),
        div(cls("w3-row"),
          div(cls("w3-col"), width("15%"), label(cls("w3-left-align w3-text-indigo"), "Edit:")),
          div(cls("w3-col"), width("85%"),
            input(cls("w3-input w3-hover-light-gray w3-text-indigo"), typ("text"), readOnly(true),
              value <-- model.selectedItemVar.signal.map(_.getOrElse(Item.empty).value),
              readOnly <-- model.selectedItemVar.signal.map(_.isEmpty),
              inContext { input =>
                onEnterPress.mapTo(input.ref.value).filter(_.nonEmpty) --> { _ =>
                  model.onEditItem(model.selectedItem.id, input.ref.value)
                  input.ref.id = ""
                  input.ref.value = ""
                  model.selectedItemVar.set(None)
                }
              }
            )
          )
        )
      )
  }
}