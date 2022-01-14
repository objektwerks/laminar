package objektwerks

object Validator:
    extension (value: String)
      def isEmailAddress(): Boolean = value.nonEmpty && value.length >= 3 && value.contains("@")
      def isPin(): Boolean = value.length == 6