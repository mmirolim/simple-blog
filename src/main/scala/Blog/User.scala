package Blog

import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

/**
 * Created by Simon on 06.11.2014.
 */
trait User extends ScalatraServlet with ScalateSupport {

  get("/users/:id") {
    params("id")
  }

  get("/users") {
    "User trait"
  }

  post("/users") {
    // create new user
  }

  put("/users/:id") {
    // update user
  }

  delete("/users/:id") {
    //delete user
  }

}

