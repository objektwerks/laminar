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
        )
      )