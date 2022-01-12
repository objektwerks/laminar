package objektwerks

import com.raquo.laminar.api.L.*

import org.scalajs.dom.console.log

object IndexView:
    def apply(): HtmlElement =
      div(
        div(cls("w3-bar w3-text-indigo"),
          button(cls("w3-button w3-round-xxlarge w3-light-grey w3-text-indigo"), "Login").amend {
            onClick --> { _ =>
              log("Login onClick")
              Router.router.pushState(LoginPage)
            }
          }
        ),
        div(
          ul(cls("w3-ul"),
            li(h5(cls("w3-light-grey w3-text-indigo"), "Features:")),
            li(cls("w3-text-indigo"), "Add Task"),
            li(cls("w3-text-indigo"), "Edit Task"),
            li(cls("w3-text-indigo"), "List Tasks")
         )
        )
      )