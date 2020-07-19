package org.example.validators

import org.example.Validated_playground.RegistrationData
import org.example.domain._

sealed trait FormValidator {

  def validateUserName(username: String): Either[ValidationError, String] =
    Either.cond(
      username.matches("^[a-zA-Z0-9]+$"),
      username,
      UserNamehasSpecialCharacters
    )

  def validatePassword(password: String): Either[ValidationError, String] =
    Either.cond(
      password.matches(
        "(?=^.{10,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"
      ),
      password,
      PasswordDoesNotMeetCriteria
    )

  def validateFirstName(
      firstName: String
  ): Either[ValidationError, String] =
    Either.cond(
      firstName.matches("^[a-zA-Z0-9]+$"),
      firstName,
      FirstNameHasSpecialCharacters
    )

  def validateLastName(
      lastName: String
  ): Either[ValidationError, String] =
    Either.cond(
      lastName.matches("^[a-zA-Z0-9]+$"),
      lastName,
      LastNameHasSpecialCharacters
    )

  def validateAge(age: Int): Either[ValidationError, Int] =
    Either.cond(age >= 18 && age <= 75, age, AgeIsInvalid)

  def validateForm(
      userName: String,
      password: String,
      firstName: String,
      lastName: String,
      age: Int
  ): Either[ValidationError, RegistrationData] =
    for {
      validatedUserName <- validateUserName(userName)
      validatePassword <- validatePassword(password)
      validatedFirstName <- validateFirstName(firstName)
      validatedLastName <- validateLastName(lastName)
      validatedAge <- validateAge(age)
    } yield (RegistrationData(
      validatedUserName,
      validatePassword,
      validatedFirstName,
      validatedLastName,
      validatedAge
    ))
}

object FormValidator extends FormValidator
