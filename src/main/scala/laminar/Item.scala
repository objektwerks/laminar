package laminar

import java.util.concurrent.atomic.AtomicInteger

import scala.util.Try

sealed trait Item {
  def id: Int
  def idToString: String = id.toString
  def idToInt(id: String): Int = Try(id.toInt).getOrElse(-1)
  def render: String
}

object Item {
  private val autoinc = new AtomicInteger()

  def newId: Int = autoinc.incrementAndGet
}

final case class StringItem(id: Int = Item.newId, value: String) extends Item {
  def render:String = value
}