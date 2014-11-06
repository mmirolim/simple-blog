package Blog.Routers

import Blog.{Models => M}
import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

/**
 * Created by Simon on 06.11.2014.
 */
trait User extends ScalatraServlet with ScalateSupport {


  val ns = "/users"
  val nsId = ns + "/:id"

  get(ns) {
    "User trait"
  }

  get(nsId) {
    params("id")
    val u = new M.User()
    u.name
  }

  get(nsId + "/posts") {
    "GET user posts"
  }

  get(nsId + "/comments") {
    "GET user comments"
  }

  post(ns) {
    // create new user
  }

  post(nsId) {
    // update user
  }

  delete(nsId) {
    //delete user
  }

}

