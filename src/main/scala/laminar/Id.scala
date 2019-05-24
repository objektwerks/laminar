package laminar

class Id {
  private var autoinc = 0
  val increment = () => { autoinc = autoinc + 1; autoinc.toString }
}

object Id {
  def apply(): Id = new Id()
}