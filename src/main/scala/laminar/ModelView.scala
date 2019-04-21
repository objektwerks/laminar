package laminar

import com.raquo.laminar.api.L._
import org.scalajs.dom.{console, document}

class ModelView() {
  def init(): Unit = {
    console.log("ModelView initializing...")
    val container = document.getElementById("container")
    render(container, Counter("counter").element)
    console.log("ModelView initialized!")
  }
}

object ModelView {
  def apply(): ModelView = new ModelView()
}