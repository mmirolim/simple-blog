package Blog

import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

/**
 * Created by Simon on 06.11.2014.
 */
trait Comment extends ScalatraServlet with ScalateSupport {

  get("/comments/:id") {
    "This is comment trait"
  }

  post("/comments") {
    // create comment
  }

  put("/comments/:id") {
    // update comment
  }

  delete("/comments/:id") {
    // delete comment
  }

}

