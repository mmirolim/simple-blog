package Blog.Routers

import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

/**
 * Created by Simon on 06.11.2014.
 */
trait Comment extends ScalatraServlet with ScalateSupport {

  val ns = "/comments" // route namespace
  val nsId = ns + "/:id"

  post(ns) {
    // create comment
  }

  get(nsId) {
    "This is comment trait"
  }

  post(nsId) {
    "This is comment update"
  }

  delete(nsId) {
    "this is DELETE method"
  }

}

