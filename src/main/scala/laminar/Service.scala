package laminar

import org.scalajs.dom.console._

class Service {
  def send(prefix: String, statement: String): Unit = log(prefix, statement)
}

object Service {
  def apply(): Service = new Service()
}