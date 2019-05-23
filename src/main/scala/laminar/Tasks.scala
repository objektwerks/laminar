package laminar

import java.time.Instant

case class Task(id: String = Task.newId(),
                task: String,
                opened: Long = Instant.now.toEpochMilli,
                closed: Long = Instant.now.toEpochMilli)

object Task {
  private var autoinc = 0
  val newId = () => { autoinc = autoinc + 1; autoinc.toString }
  val empty = Task("", "", 0, 0)
}

object Tasks {

}