package laminar

import java.util.concurrent.atomic.AtomicInteger

sealed trait Item {
  def id: String
  def render: String
}

object Item {
  private val autoinc = new AtomicInteger()

  def newId: String = autoinc.incrementAndGet.toString
}

final case class StringItem(id: String = Item.newId, value: String) extends Item {
  def render:String = value
}