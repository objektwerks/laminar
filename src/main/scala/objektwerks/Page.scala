package objektwerks

import com.raquo.laminar.api.L.*

sealed trait Page
case object LoginPage extends Page
case object ItemsPage extends Page