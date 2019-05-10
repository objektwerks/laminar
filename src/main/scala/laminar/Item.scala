package laminar

import java.util.concurrent.atomic.AtomicInteger

case class Item(id: String = Item.newId, value: String)

object Item {
  private val autoinc = new AtomicInteger()

  def newId: String = autoinc.incrementAndGet.toString
}