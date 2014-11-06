package Blog.Routers

import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

/**
 * Created by Simon on 06.11.2014.
 */
trait Post extends ScalatraServlet with ScalateSupport {

  val ns = "/posts"
  val nsId = ns + "/:id"

  get(ns) {
    "GET all posts"
  }

  get(nsId) {
    "This is post trait"
  }

  // router match from bottom to top goes
  get(nsId  + "/comments") {
    "GET post comments"
  }

  post(ns) {
    // create comment
  }

  post(nsId) {
    // update comment
  }

  delete(nsId) {
    // delete comment
  }

}

