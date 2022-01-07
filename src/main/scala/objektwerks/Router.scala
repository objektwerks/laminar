package objektwerks

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L.*
import com.raquo.waypoint.*

import upickle.default.*

import org.scalajs.dom

import Serializer.given

trait Router:
  val loginRoute = Route.static(LoginPage, root / "login" / endOfSegments)
  val itemsRoute = Route.static(ItemsPage, root / "items" / endOfSegments)

  val router = new com.raquo.waypoint.Router[Page](
    routes = List(loginRoute, itemsRoute),
    serializePage = page => write(page)(pageRW),
    deserializePage = pageAsString => read(pageAsString)(pageRW),
    getPageTitle = _.toString,
  )(
    $popStateEvent = L.windowEvents.onPopState,
    owner = L.unsafeWindowOwner
  )