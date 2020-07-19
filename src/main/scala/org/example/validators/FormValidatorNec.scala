package org.example.validators

import cats.data.ValidatedNec
import cats.implicits._
import org.example.domain.ValidationError
import org.example.domain.FirstNameHasSpecialCharacters
import org.example.Validated_playground.RegistrationData

sealed trait FormValidatorNec {

  type ValidationResult[A] = ValidatedNec[ValidationError, A]

  private def validateUserName(userName: String): ValidationResult[String] =
    FormValidator.validateUserName(userName).toValidatedNec

  private def validatePassword(password: String): ValidationResult[String] =
    FormValidator.validatePassword(password).toValidatedNec

  private def validateFirstName(firstName: String): ValidationResult[String] =
    if (firstName.matches("^[a-zA-Z0-9]+$"))
      firstName.validNec
    else
      FirstNameHasSpecialCharacters.invalidNec

  private def validateLastName(lastName: String): ValidationResult[String] =
    FormValidator.validateLastName(lastName).toValidatedNec

  private def validateAge(age: Int): ValidationResult[Int] =
    FormValidator.validateAge(age).toValidatedNec

  def validateForm(
      userName: String,
      password: String,
      firstName: String,
      lastName: String,
      age: Int
  ): ValidationResult[RegistrationData] = {
    (
      validateUserName(userName),
      validatePassword(password),
      validateFirstName(firstName),
      validateLastName(lastName),
      validateAge(age)
    ) mapN RegistrationData

  }
}

object FormValidatorNec extends FormValidatorNec
