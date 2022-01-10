package objektwerks

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.*
import com.raquo.waypoint.*

import upickle.default.*

import org.scalajs.dom

import Serializer.given

object Router:
  private val indexPageRoute = Route.static(IndexPage, root / endOfSegments)
  private val loginPageRoute = Route.static(LoginPage, root / "login" / endOfSegments)
  private val itemsPageRoute = Route.static(ItemsPage, root / "items" / endOfSegments)

  val router = new com.raquo.waypoint.Router[Page](
    routes = List(indexPageRoute, loginPageRoute, itemsPageRoute),
    serializePage = page => write(page)(pageRW),
    deserializePage = pageAsString => read(pageAsString)(pageRW),
    getPageTitle = _.toString,
  )(
    $popStateEvent = L.windowEvents.onPopState,
    owner = L.unsafeWindowOwner
  )

  val splitter = SplitRender[Page, HtmlElement](router.$currentPage)
    .collectStatic(IndexPage) { LoginView() }
    .collectStatic(LoginPage) { LoginView() }
    .collectStatic(ItemsPage) { ItemsView() }