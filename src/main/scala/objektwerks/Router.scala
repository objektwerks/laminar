package objektwerks

import com.raquo.laminar.api.L
import com.raquo.waypoint.*

import upickle.default.*

import org.scalajs.dom

import Serializer.given

sealed trait Router:
  val loginRoute = Route.static(LoginPage, root / "login" / endOfSegments)
  
  val itemsRoute = Route[ItemsPage, List[Item]](
    encode = itemsPage => itemsPage.items,
    decode = items => ItemsPage(items),
    pattern = root / "items" / segment[List[Item]] / endOfSegments
  )

  val router = new com.raquo.waypoint.Router[Page](
    routes = List(loginRoute, itemsRoute),
    getPageTitle = _.toString,
    serializePage = page => write(page)(pageRW),
    deserializePage = pageStr => read(pageStr)(pageRW)
  )(
    $popStateEvent = L.windowEvents.onPopState,
    owner = L.unsafeWindowOwner
  )