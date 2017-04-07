package net.devkat.timetracking.client

import scala.scalajs.js
import org.scalajs.dom

object Client extends js.JSApp {
  def main(): Unit = {
    dom.document.getElementById("main").textContent = "It works"
  }
}
