package laminar

import com.raquo.airstream.ownership.Owner
import com.raquo.airstream.signal.Var
import org.scalajs.dom.console._

class Logger(itemsVar: Var[List[Item]]) {
  implicit val owner = new Owner {}
  itemsVar.signal.foreach(items => log(s"items change event -> ${items.toString}"))
}

object Logger {
  def apply(itemsVar: Var[List[Item]]): Logger = new Logger(itemsVar)
}