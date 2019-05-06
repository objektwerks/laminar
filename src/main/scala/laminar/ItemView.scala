package laminar

import com.raquo.laminar.api.L._

class ItemView private(val element: HtmlElement)

object ItemView {
  def apply(items: List[Item]): ItemView = {
    val itemsStream: EventStream[List[Item]] = EventStream.fromValue(items, true)
    val lisStream: EventStream[List[Li]] = itemsStream.map(items => items.map(renderItem))
    val div: HtmlElement = renderItems(lisStream)
    new ItemView(div)
  }

  def renderItem(item: Item): Li = li(item.value)

  def renderItems(elements: EventStream[List[Li]]): Div = {
    div(
      cls := "w3-container",
      p("Items:"),
      ul(
        cls := "w3-ul w3-hoverable",
        children <-- elements
      )
    )
  }
}