package org.example

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

object WebServer {
  def routes() =
    path("health") {
      get {
        complete(
          HttpEntity(
            ContentTypes.`text/html(UTF-8)`,
            "<h1>Hello there, I am a healthy guy!</h1>"
          )
        )
      }
    }
}
