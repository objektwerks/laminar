package objektwerks

import com.raquo.laminar.api.L.*

import org.scalajs.dom.console.log

object LoginView:
  def apply(emailAddress: Var[String], pin: Var[String]): HtmlElement =
    div(cls("w3-container"),
      h5(cls("w3-light-grey w3-text-indigo"), "Login"),
      label(cls("w3-left-align w3-text-indigo"), "Email Address"),
      input(
        cls("w3-input w3-hover-light-gray w3-text-indigo"),
        typ("email"),
        required(true),
        value <-- emailAddress,
        onInput.mapToValue.filter(_.nonEmpty) --> emailAddress
      ),
      label(cls("w3-left-align w3-text-indigo"), "Pin"),
      input(
        cls("w3-input w3-hover-light-gray w3-text-indigo"),
        typ("text"),
        pattern("\\d*"),
        stepAttr("1"),
        minLength(6),
        maxLength(6),
        required(true),
        value <-- pin,
        onInput.mapToValue.filter(_.nonEmpty) --> pin
      ),
      button(cls("w3-bar-item w3-button w3-text-indigo"), "Submit").amend {
        onClick --> { _ =>
          log(s"email address: ${emailAddress.now()} pin: ${pin.now()}")
          Router.router.pushState(TasksPage)
        }
      }
    )