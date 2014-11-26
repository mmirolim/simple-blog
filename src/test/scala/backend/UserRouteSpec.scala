package backend

import org.scalatra.test.specs2._
/**
 * Created by Mirzakhmedov Mirolim on 15.11.2014.
 */
class UserRouteSpec extends MutableScalatraSpec with DB {

  addServlet(new Backend(db, logger), "/*")

  "POST /backend/users User" should {
    "return status 400" in {
      post("/users") {
        status must_== 400
      }
    }
  }

  "PUT /backend/users/1 User" should {
    "return status 400" in {
      put("/users/1") {
        status must_== 400
      }
    }
  }

  "DELETE /backend/users/1 User" should {
    "return status 200" in {
      delete("/users/1") {
        status must_== 204
      }
    }
  }

}
