package objektwerks

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.*
import com.raquo.waypoint.*

import upickle.default.*

import org.scalajs.dom

import Serializer.given

object Router:
  val routes = List(
    Route.static(IndexPage, root / endOfSegments),
    Route.static(LoginPage, root / "login" / endOfSegments),
    Route.static(TasksPage, root / "tasks" / endOfSegments)
  )

  val router = new com.raquo.waypoint.Router[Page](
    routes = routes,
    serializePage = page => write(page)(pageRW),
    deserializePage = pageAsString => read(pageAsString)(pageRW),
    getPageTitle = _.title,
  )(
    $popStateEvent = L.windowEvents.onPopState,
    owner = L.unsafeWindowOwner
  )

  val splitter = SplitRender[Page, HtmlElement](router.$currentPage)
    .collectStatic(IndexPage) { IndexView() }
    .collectStatic(LoginPage) { LoginView(Store.emailAddress, Store.pin) }
    .collectStatic(TasksPage) { TasksView(Store.tasks) }