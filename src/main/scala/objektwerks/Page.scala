package objektwerks

import com.raquo.laminar.api.L.*

sealed trait Page
case object LoginPage extends Page
final case class ItemsPage(itemsVar: Var[List[Item]]) extends Page