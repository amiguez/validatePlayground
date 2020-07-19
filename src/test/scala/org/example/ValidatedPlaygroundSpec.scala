package org.example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.EitherValues
import org.scalatest.matchers.should.Matchers
import org.example.domain.PasswordDoesNotMeetCriteria

import org.example.validators.FormValidator
import org.example.validators.FormValidatorNec
import cats.data.Chain
import org.example.domain.AgeIsInvalid
import cats.data.Validated.Invalid

class ValidatedPlaygroundSpec
    extends AnyFlatSpec
    with Matchers
    with EitherValues {

  "Test1" should "example test should pass" in {

    assert(1 + 1 === 2)

  }

  "Test2" should "don't validate not complex enought password" in {
    val result = FormValidator.validateForm(
      userName = "hola",
      password = "12",
      firstName = "carilta",
      lastName = "Miguez",
      age = 2
    )

    result.left.value shouldBe PasswordDoesNotMeetCriteria
  }

  "With Validated" should "don't validate not complex enought password and short age" in {

    val result = FormValidatorNec
      .validateForm(
        userName = "hola",
        password = "12",
        firstName = "carilta",
        lastName = "Miguez",
        age = 2
      )

    result.isInvalid shouldBe true

    result shouldBe Invalid(Chain(PasswordDoesNotMeetCriteria, AgeIsInvalid))

  }

}
