package objektwerks

import upickle.default.*

object Serializer:
  given pageRW: ReadWriter[Page] = macroRW

  given taskRW: ReadWriter[Task] = macroRW