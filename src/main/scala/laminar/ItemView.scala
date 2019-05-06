package laminar

import com.raquo.laminar.api.L._

class ItemView private(val element: HtmlElement)

object ItemView {
  def apply(items: List[Item]): ItemView = {
    val itemsStream: EventStream[List[Item]] = EventStream.fromValue(items, true)
    val divsStream: EventStream[List[Div]] = itemsStream.map(items => items.map(renderItem))
    val div: HtmlElement = renderItems(divsStream)
    new ItemView(div)
  }

  def renderItem(item: Item): Div = {
    div(
      p(item.value)
    )
  }

  def renderItems(elements: EventStream[List[Div]]): Div = {
    div(
      children <-- elements
    )
  }
}