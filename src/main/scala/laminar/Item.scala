package laminar

import java.util.concurrent.atomic.AtomicInteger

sealed trait Item {
  def render: String
}

object Item {
  private val autoinc = new AtomicInteger()

  def newId: Int = autoinc.incrementAndGet
}

final case class StringItem(id: Int = Item.newId, value: String) extends Item {
  def render:String = value
}