package objektwerks

import com.raquo.laminar.api.L.*

import org.scalajs.dom.console.log

object LoginView:
  def apply(): HtmlElement =
    div(cls("w3-container"),
      h4(cls("w3-light-grey w3-text-indigo"), "Login"),
      label(cls("w3-left-align w3-text-indigo"), "Email Address"),
      input(
        cls("w3-input w3-hover-light-gray w3-text-indigo"),
        typ("email"),
        required(true),
        value <-- Store.emailAddress,
        onInput.mapToValue.filter(_.nonEmpty) --> Store.emailAddress
      ),
      label(cls("w3-left-align w3-text-indigo"), "Pin"),
      input(
        cls("w3-input w3-hover-light-gray w3-text-indigo"),
        typ("number"),
        pattern("\\d*"),
        stepAttr("1"),
        minLength(6),
        maxLength(6),
        required(true),
        value <-- Store.pin,
        onInput.mapToValue.filter(_.nonEmpty) --> Store.pin
      ),
      button(cls("w3-bar-item w3-button w3-text-indigo"), "Login").amend {
        onClick --> { _ =>
          log(s"email address: ${Store.emailAddress.now()} pin: ${Store.pin.now()}")
          Router.router.pushState(ItemsPage)
        }
      }
    )