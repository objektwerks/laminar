package objektwerks

import com.raquo.laminar.api.L.*

object Store:
  val email = Var("")
  val pin = Var("")
  val items = Var(List.empty[Item])