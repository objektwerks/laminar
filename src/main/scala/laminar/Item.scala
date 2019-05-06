package laminar

import java.util.concurrent.atomic.AtomicInteger

case class Item(id: Int = Item.autoinc.incrementAndGet(), value: String)

object Item {
  val autoinc = new AtomicInteger()
  val items = List(Item(value = "one"), Item(value = "two"), Item(value = "three"))
}