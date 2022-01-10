package objektwerks

import com.raquo.laminar.api.L.*

object Store:
  val emailAddress = Var("")
  val pin = Var("")
  val tasks = Var(List.empty[Task])