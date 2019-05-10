package laminar

import com.raquo.laminar.api.L._

class ItemView private(val element: HtmlElement)

object ItemView {
  import UnsafeInnerHtmlModifier._

  def apply(items: List[Item]): ItemView = {
    val itemsStream: EventStream[List[Item]] = EventStream.fromValue(items, true)
    val lisStream: EventStream[List[Li]] = itemsStream.map(items => items.map(renderItem))
    val div: HtmlElement = renderItems(lisStream)
    new ItemView(div)
  }

  def renderItem(item: Item): Li = li(
    id := item.idToString,
    cls := "w3-display-container",
    item.render,
    inContext { liElement =>
      span(
        cls := "w3-button w3-display-right",
        onClick --> { _ => display.none(liElement) },
        unsafeInnerHtml := "&times;"
      )
    }
  )

  def renderItems(elements: EventStream[List[Li]]): Div = {
    div(
      cls := "w3-container",
      h4(cls := "w3-indigo", "Items"),
      ol(
        cls := "w3-ul w3-hoverable",
        children <-- elements
      ),
      hr(),
      div(
        cls := "w3-container",
        div(
          cls := "w3-row",
          div(cls := "w3-col", width := "10%", label("Add:")),
          div(cls := "w3-col", width := "90%", input(cls := "w3-input", typ := "text"))
        )
      )
    )
  }
}