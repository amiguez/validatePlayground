package org.example.domain

trait ValidationError {
  def error: String
}

case object UserNamehasSpecialCharacters extends ValidationError {
  override def error: String = "Username can not have special characters."

}

case object PasswordDoesNotMeetCriteria extends ValidationError {
  override def error: String = "Password don't match security criteria."
}

case object FirstNameHasSpecialCharacters extends ValidationError {
  override def error: String =
    "First name cannot contain spaces, numbers or special characters."
}

case object LastNameHasSpecialCharacters extends ValidationError {
  override def error: String =
    "Last name cannot contain spaces, numbers or special characters."
}

case object AgeIsInvalid extends ValidationError {
  override def error: String =
    "You must be aged 18 and not older than 75 to use our services."
}
