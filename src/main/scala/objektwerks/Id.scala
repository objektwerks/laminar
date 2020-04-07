package objektwerks

class Id() {
  private var autoinc = 0
  def increment(): String = {
    autoinc = autoinc + 1
    autoinc.toString
  }
}

object Id {
  def apply(): Id = new Id()
}