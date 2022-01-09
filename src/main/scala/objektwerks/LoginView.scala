package objektwerks

import com.raquo.laminar.api.L.*

object LoginView:
  def apply(): HtmlElement =
    div(
      h4(cls("w3-light-grey w3-text-indigo"), "Login"),
      label(cls("w3-left-align w3-text-indigo"), "Email Address"),
      input(
        cls("w3-input w3-hover-light-gray w3-text-indigo"),
        typ("email"),
        required(true)
      ),
      label(cls("w3-left-align w3-text-indigo"), "Pin"),
      input(
        cls("w3-input w3-hover-light-gray w3-text-indigo"),
        typ("number"),
        pattern("\\d*"),
        stepAttr("1"),
        required(true)
      )
      button(cls("w3-bar-item w3-button w3-text-indigo"), "Login").amend {
        onClick --> { _ =>
          Router.router.pushState(ItemsPage)
        }
      }
    )