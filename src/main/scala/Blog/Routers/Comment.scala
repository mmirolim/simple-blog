package Blog.Routers

import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

/**
 * Created by Simon on 06.11.2014.
 */
trait Comment extends ScalatraServlet with ScalateSupport {


  post("/comments") {
    // create comment
  }

  get("/comments/:id") {
    "This is comment trait"
  }

  post("/comments/:id") {
    "This is comment update"
  }

  delete("/comments/:id") {
    "this is DELETE method"
  }

}

