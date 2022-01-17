package objektwerks

import com.raquo.laminar.api.L.*

sealed trait Page:
  val title = "Tasks"
case object IndexPage extends Page
case object RegisterPage extends Page
case object LoginPage extends Page
case object TasksPage extends Page