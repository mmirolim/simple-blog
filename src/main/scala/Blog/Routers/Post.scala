package Blog.Routers

import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

/**
 * Created by Simon on 06.11.2014.
 */
trait Post extends ScalatraServlet with ScalateSupport {


  get("/posts") {
    "GET all posts"
  }

  get("/posts/:id") {
    "This is post trait"
  }

  // router match from bottom to top goes
  get("/posts/:id/comments") {
    "GET post comments"
  }

  post("/posts") {
    // create comment
  }

  post("/posts/:id") {
    // update comment
  }

  delete("/posts/:id") {
    // delete comment
  }

}

