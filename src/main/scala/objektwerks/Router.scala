package objektwerks

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.*
import com.raquo.waypoint.*
import com.github.plokhotnyuk.jsoniter_scala.core.*

import Serializer.given

object Router:
  val routes = List(
    Route.static(IndexPage, root / endOfSegments),
    Route.static(RegisterPage, root / "register" / endOfSegments),
    Route.static(LoginPage, root / "login" / endOfSegments),
    Route.static(TasksPage, root / "app" / "tasks" / endOfSegments)
  )

  val router = new com.raquo.waypoint.Router[Page](
    routes = routes,
    serializePage = page => writeToString(page),
    deserializePage = pageAsString => readFromString(pageAsString),
    getPageTitle = _.title,
  )(
    popStateEvents = L.windowEvents(_.onPopState),
    owner = L.unsafeWindowOwner
  )

  val splitter = SplitRender[Page, HtmlElement](router.currentPageSignal)
    .collectStatic(IndexPage) { IndexView() }
    .collectStatic(RegisterPage) { RegisterView(Store.emailAddress) }
    .collectStatic(LoginPage) { LoginView(Store.emailAddress, Store.pin) }
    .collectStatic(TasksPage) { TasksView(Store.tasks) }