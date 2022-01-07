package objektwerks

import com.raquo.laminar.api.L.*
import com.raquo.airstream.ownership.Owner

import org.scalajs.dom.console.log
import org.scalajs.dom.ext.KeyCode

import InnerHtmlModifier.*

final case class Item(id: String = Item.nextId, value: String)

object Item:
  private var id = 0
  val empty = Item("", "")

  private def nextId: String =
    id = id + 1
    id.toString

object ItemView:
  def apply(itemsVar: Var[List[Item]]): HtmlElement = Renderer( Model(itemsVar) ).render

  private final class Model(val itemsVar: Var[List[Item]]):
    given owner: Owner = new Owner {}
    itemsVar.signal.foreach(items => log(s"items change event -> ${items.toString}"))

    val selectedItemVar: Var[Option[Item]] = Var(None)

    def selectedItem: Item = selectedItemVar.now().getOrElse(Item.empty)

    def onSelectItem(id: String): Unit =
      val item = itemsVar.now().find(_.id == id)
      selectedItemVar.set(item)

    def onAddItem(value: String): Unit = itemsVar.update(_ :+ Item(value = value))

    def onEditItem(id: String, value: String): Unit =
      itemsVar.update(_.map(item => if (item.id == id) item.copy(value = value) else item))
      selectedItemVar.set(None)

    def onRemoveItem(id: String): Unit = itemsVar.update(_.filterNot(_.id == id))

  private final class Renderer(model: Model):
    val onEnterPress = onKeyPress.filter(_.keyCode == KeyCode.Enter)

    def render: HtmlElement =
      renderRoot(
        model
          .itemsVar
          .signal
          .split(_.id)( (_, item, itemSignal) => renderItem(item, itemSignal) )
      )

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

    def renderItems(itemsSignal: Signal[List[Li]]): Div =
      div(cls("w3-container"),
        ul(cls("w3-ul w3-hoverable"), children <-- itemsSignal)
      )

    def renderItem(item: Item, itemSignal: Signal[Item]): Li =
      li(cls("w3-text-indigo w3-display-container"),
        child.text <-- itemSignal.map(item.id + ". " + _.value),
        span(cls("w3-button w3-display-right w3-text-indigo"),
          onClick.mapTo(item.id) --> { id => model.onRemoveItem(id) },
          unsafeInnerHtml := "&times;"
        ),
        onClick.mapTo(item.id) --> { id => model.onSelectItem(id) }
      )

    def renderAddItem: Div =
      div(cls("w3-container"), paddingTop("3px"), paddingBottom("3px"),
        div(cls("w3-row"),
          div(cls("w3-col"), width("15%"), label(cls("w3-left-align w3-text-indigo"), "Add:")),
          div(cls("w3-col"), width("85%"),
            input(cls("w3-input w3-hover-light-gray w3-text-indigo"), typ("text"),
              inContext { input =>
                onEnterPress.mapTo(input.ref.value).filter(_.nonEmpty) --> { value =>
                  model.onAddItem(value)
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
                onEnterPress.mapTo(input.ref.value).filter(_.nonEmpty) --> { value =>
                  model.onEditItem(model.selectedItem.id, value)
                }
              }
            )
          )
        )
      )