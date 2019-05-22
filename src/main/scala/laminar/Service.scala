package laminar

import org.scalajs.dom.console._

class Service {
  def onChange(message: String, change: String): Unit = log(message, change)
}

object Service {
  def apply(): Service = new Service()
}