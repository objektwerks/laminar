package objektwerks

import com.raquo.laminar.api.L.*

sealed trait Page
case object LoginPage extends Page
final case class ItemsPage(items: List[Item]) extends Page