package objektwerks

object Serializer:
  import upickle.default.*

  given itemRW: ReadWriter[Task] = macroRW
  given pageRW: ReadWriter[Page] = macroRW