package objektwerks

import com.raquo.laminar.api.L.*

object LoginView:
  def apply: HtmlElement =
    div(
      h4(cls("w3-light-grey w3-text-indigo"), "Login"),
      input(
        cls("w3-input w3-hover-light-gray w3-text-indigo"),
        typ("text"),
        required(true)
      )
    )