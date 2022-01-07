package objektwerks

object Serializer:
  import upickle.default.*

  given itemRW: ReadWriter[Item] = macroRW
  given pageRW: ReadWriter[Page] = macroRW