package objektwerks

import com.raquo.laminar.api.L.*

import org.scalajs.dom.console.log

import Validator.*

object LoginView:
  def apply(emailAddress: Var[String], pin: Var[String]): HtmlElement =
    val emailAddressError = new EventBus[String]
    val pinError = new EventBus[String]
    div(cls("w3-container"),
      h5(cls("w3-light-grey w3-text-indigo"), "Login"),
      label(cls("w3-left-align w3-text-indigo"), "Email"),
      input(
        cls("w3-input w3-hover-light-gray w3-text-indigo"),
        typ("email"),
        minLength(3),
        required(true),
        placeholder("youraddress@email.com"),
        value <-- emailAddress,
        onInput.mapToValue.filter(_.nonEmpty).setAsValue --> emailAddress,
        onKeyUp.mapToValue --> { value =>
          if value.isEmailAddress() then emailAddressError.emit("")
          else emailAddressError.emit("Please, enter a valid email address.")
        }
      ),
      div(cls("w3-border-white w3-text-red"), child.text <-- emailAddressError.events),
      label(cls("w3-left-align w3-text-indigo"), "Pin"),
      input(
        cls("w3-input w3-hover-light-gray w3-text-indigo"),
        typ("text"),
        minLength(6),
        maxLength(6),
        required(true),
        placeholder("abc123"),
        value <-- pin,
        onInput.mapToValue.filter(_.nonEmpty).setAsValue --> pin,
        onKeyUp.mapToValue --> { value =>
          if value.isPin() then pinError.emit("")
          else pinError.emit("Please, enter a valid 6 character pin.")
        }      
      ),
      div(cls("w3-border-white w3-text-red"), child.text <-- pinError.events),
      div(cls("w3-bar w3-margin-top w3-center"),
        button(cls("w3-button w3-round-xxlarge w3-light-gray w3-text-indigo"),
          "Login",
          disabled <-- emailAddress.signal.map(_.isEmailAddress())
                       .combineWith(pin.signal.map(_.isPin()))
                       .map( (a, b) => !(a && b) )
        ).amend {
          onClick --> { _ =>
            log(s"email address: ${emailAddress.now()} pin: ${pin.now()}")
            if emailAddress.now().isEmailAddress() && pin.now().isPin() then
              log("Email address and pin is valid!")
              Router.router.pushState(TasksPage)
            else
              log("Email address and/or pin is invalid!")
              Router.router.$currentPage
          }
        }
      )
    )