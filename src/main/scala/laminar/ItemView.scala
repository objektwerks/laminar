package laminar

import com.raquo.laminar.api.L._

class ItemView private(val element: HtmlElement)

object ItemView {
  def apply(items: List[Item], header: String): ItemView = {
    val itemsStream: EventStream[List[Item]] = EventStream.fromValue(items, true)
    val lisStream: EventStream[List[Li]] = itemsStream.map(items => items.map(renderItem))
    val div: HtmlElement = renderItems(lisStream, header)
    new ItemView(div)
  }

  def renderItem(item: Item): Li = li(item.value)

  def renderItems(elements: EventStream[List[Li]], header: String): Div = {
    div(
      cls := "w3-container",
      p(header),
      ul(
        cls := "w3-ul w3-hoverable",
        children <-- elements
      ),
      input(typ := "text")
    )
  }
}