package objektwerks

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.*
import com.raquo.waypoint.*

import upickle.default.*

import org.scalajs.dom

import Serializer.given

object Router:
  private val basePath = "target/scala-3.1.0/classes/"
  private val loginPageRoute = Route.static(LoginPage, root / "login" / endOfSegments, basePath)
  private val itemsPageRoute = Route.static(ItemsPage, root / "items" / endOfSegments, basePath)

  private val router = new com.raquo.waypoint.Router[Page](
    routes = List(loginPageRoute, itemsPageRoute),
    serializePage = page => write(page)(pageRW),
    deserializePage = pageAsString => read(pageAsString)(pageRW),
    getPageTitle = _.toString,
  )(
    $popStateEvent = L.windowEvents.onPopState,
    owner = L.unsafeWindowOwner
  )

  val splitter = SplitRender[Page, HtmlElement](router.$currentPage)
    .collectStatic(LoginPage) { LoginView() }
    .collectStatic(ItemsPage) { ItemsView() }