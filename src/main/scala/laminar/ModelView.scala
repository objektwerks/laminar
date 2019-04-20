package laminar

class ModelView() {
  def init(): Unit = println("ModelView init!")
}

object ModelView {
  def apply(): ModelView = new ModelView()
}