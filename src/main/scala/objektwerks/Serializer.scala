package objektwerks

object Serializer:
  import upickle.default.*

  given itemRW: ReadWriter[Item] = macroRW
  given itemsPageRW: ReadWriter[ItemsPage] = macroRW
  given pageRW: ReadWriter[Page] = ReadWriter.merge( itemsPageRW )