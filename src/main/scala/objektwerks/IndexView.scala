package objektwerks

import com.raquo.laminar.api.L.*

import org.scalajs.dom.console.log

object IndexView:
    def apply(): HtmlElement =
      div(cls("w3-container"),
        div(cls("w3-bar w3-white w3-text-indigo"),
          a(href("#"), cls("w3-bar-item w3-button"), "Login").amend {
            onClick --> { _ =>
              log("Login onClick")
              Router.router.pushState(LoginPage)
            }
          }
        ),
        div(cls("w3-container"),
          h4(cls("w3-light-grey w3-text-indigo"), "Features"),
          ul(cls("w3-ul w3-hoverable"),
            li(cls("w3-text-indigo w3-display-container"), "Add Task"),
            li(cls("w3-text-indigo w3-display-container"), "Edit Task"),
            li(cls("w3-text-indigo w3-display-container"), "List Tasks")
         )
        )
      )