package objektwerks

object Validator:
    extension (value: String)
      def isEmailAddressValid: Boolean = value.nonEmpty && value.length >= 3 && value.contains("@")
      def isPinValid: Boolean = value.length == 6