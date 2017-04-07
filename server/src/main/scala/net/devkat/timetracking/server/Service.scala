package net.devkat.timetracking.server

import akka.http.scaladsl.server.{Directives, Route}
import net.devkat.timetracking.shared.domain._

class Service extends Directives {

  val route: Route = {
    pathEndOrSingleSlash {
      get {
        complete {
          net.devkat.timetracking.html.index.render()
        }
      }
    } ~
    pathPrefix("assets" / Remaining) { file =>
      // optionally compresses the response with Gzip or Deflate
      // if the client accepts compressed responses
      encodeResponse {
        getFromResource("public/" + file)
      }
    }
  }
}