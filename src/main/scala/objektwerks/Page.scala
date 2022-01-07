package objektwerks

import com.raquo.laminar.api.L.*

sealed trait Page
case object LoginPage extends Page
case object ItemsPage extends Page

object Page:
  def renderPage(page: Page): HtmlElement =
    page match
      case LoginPage => LoginView()
      case ItemsPage => ItemsView()