import org.example.validators.FormValidatorNec
import org.example.validators.FormValidator
//import cats.implicits._
//import cats.syntax._
//
val hallo: Int = 2

val eith: Either[Throwable, Int] = Right(23)

FormValidator.validateForm(
  userName = "Carlita",
  password = "12345",
  firstName = "Carlita",
  lastName = "Miguez",
  age = 12
)

FormValidatorNec.validateForm(
  userName = "Ca^lita",
  password = "12345",
  firstName = "Carlita",
  lastName = "Miguez",
  age = 12
)

// Turning Validated on Either
// Success
FormValidatorNec
  .validateForm(
    userName = "Carl1t4",
    password = "128fB34_lovely5",
    firstName = "Carlita",
    lastName = "Toboso",
    age = 19
  )
  .toEither

// Invalid Case
FormValidatorNec
  .validateForm(
    userName = "Carl1t4",
    password = "password",
    firstName = "Carlita",
    lastName = "Toboso",
    age = 19
  )
  .toEither
