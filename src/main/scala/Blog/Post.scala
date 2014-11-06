package Blog

import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

/**
 * Created by Simon on 06.11.2014.
 */
trait Post extends ScalatraServlet with ScalateSupport {

  get("/posts/:id") {
    "This is post trait"
  }

  post("/posts") {
    // create comment
  }

  put("/posts/:id") {
    // update comment
  }

  delete("/posts/:id") {
    // delete comment
  }

}

