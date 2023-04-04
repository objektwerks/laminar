package objektwerks

import upickle.default.*

object Serializer:
  given pageRW: ReadWriter[Page] = macroRWAll

  given taskRW: ReadWriter[Task] = macroRW