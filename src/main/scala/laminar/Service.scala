package laminar

import com.raquo.airstream.ownership.Owner
import com.raquo.airstream.signal.Var
import org.scalajs.dom.console._

class Service(itemsVar: Var[List[Item]]) {
  implicit val owner = new Owner {}
  itemsVar.signal.foreach(items => log(s"items change event -> ${items.toString}"))
}

object Service {
  def apply(itemsVar: Var[List[Item]]): Service = new Service(itemsVar)
}