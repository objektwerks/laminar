package objektwerks

import org.scalajs.dom.console._

import scala.scalajs.js.Date

object Dates {
  def nowAsString(): String = toDateAsString(Date.now())

  def toDateAsString(timestamp: Double): String = {
    val iso = new Date(timestamp).toISOString()
    val isoDate = iso.substring(0, iso.lastIndexOf("T"))
    log("iso: ", iso)
    log("iso date: ", isoDate)
    isoDate
  }

  def toDateAsDouble(date: String): Double = {
    val isoDate = new Date(date).valueOf()
    log("iso date: ", isoDate)
    isoDate
  }
}