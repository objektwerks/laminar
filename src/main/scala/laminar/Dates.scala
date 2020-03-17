package laminar

import scala.scalajs.js.Date
import org.scalajs.dom.console._

object Dates {
  def nowAsString(): String = toDateAsString(Date.now())

  def toDateAsString(timestamp: Double): String = {
    val iso = new Date(timestamp).toISOString()
    val isoDate = iso.substring(0, iso.lastIndexOf("T"))
    log("iso: ", iso)
    log("iso date: ", isoDate)
    isoDate
  }

  def toDataAsDouble(date: String): Double = {
    val isoDate = new Date(date).valueOf()
    log("iso date: ", isoDate)
    isoDate
  }
}