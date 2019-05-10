package laminar

import com.raquo.laminar.api.L._

class ItemView private(val element: HtmlElement)

object ItemView {
  import UnsafeInnerHtmlModifier._

  def apply(items: List[Item]): ItemView = {
    val itemStream: EventStream[List[Item]] = EventStream.fromValue(items, emitOnce = false)
    val liStream: EventStream[List[Li]] = itemStream.split(_.id)(renderItem)
    val element: HtmlElement = renderItems(liStream)
    new ItemView(element)
  }

  def renderItem(id: String, item: Item, itemStream: EventStream[Item]): Li = li(
    cls := "w3-display-container",
    div(
      p(id + ". ", child.text <-- itemStream.map(_.value)),
      p("edited: ", child.text <-- itemStream.map(_ != item ).map(_.toString))
    ),
    inContext { liElement =>
      span(
        cls := "w3-button w3-display-right",
        onClick --> { _ =>
          display.none(liElement) // TODO Remove li element!
        },
        unsafeInnerHtml := "&times;"
      )
    }
  )

  def renderItems(liElements: EventStream[List[Li]]): Div = {
    div(
      cls := "w3-container",
      h4(cls := "w3-indigo", "Items"),
      ul(
        cls := "w3-ul w3-hoverable",
        children <-- liElements
      ),
      hr(),
      div(
        cls := "w3-container",
        div(
          cls := "w3-row",
          div(cls := "w3-col", width := "15%", label("Add:")),
          div(cls := "w3-col", width := "85%", input(cls := "w3-input", typ := "text")) // TODO Add item to list!
        )
      )
    )
  }
}