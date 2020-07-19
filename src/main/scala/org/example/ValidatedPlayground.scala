package org.example

import org.example.validators.FormValidator
import scala.io.StdIn
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import org.example.WebServer

object ValidatedPlayground extends App {
  implicit val system = ActorSystem("validator-server-system")
  implicit val materializer = ActorMaterializer()

  implicit val executionContext = system.dispatcher

  FormValidator.validateForm(
    userName = "fakeUsername",
    password = "password",
    firstName = "John",
    lastName = "Doe",
    age = 15
  )

  val bindingServerF = Http().bindAndHandle(WebServer.routes, "localhost", 8080)
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return

  bindingServerF.flatMap(_.unbind()).onComplete(_ => system.terminate())
}
