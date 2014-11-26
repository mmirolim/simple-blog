package blog

import backend.DB
import org.scalatra.test.specs2._
/**
 * Created by Mirzakhmedov Mirolim on 15.11.2014.
 */
class UserRouteSpec extends MutableScalatraSpec with DB {

  addServlet(new SimpleBlog(db, logger), "/*")

  "GET /users User" should {
    "return status 200" in {
      get("/users") {
        status must_== 200
      }
    }
  }

  "GET /users/1 User" should {
    "return status 400" in {
      get("/users/id") {
        status must_== 400
      }
    }
  }

  "GET /users/1/posts User" should {
    "return status 200" in {
      get("/users/1/posts") {
        status must_== 200
      }
    }
  }

  "GET /users/1/comments User" should {
    "return status 200" in {
      get("/users/1/comments") {
        status must_== 200
      }
    }
  }

}
