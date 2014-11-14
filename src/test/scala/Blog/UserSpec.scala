package blog

import org.scalatra.test.specs2._
/**
 * Created by Mirzakhmedov Mirolim on 15.11.2014.
 */
class UserSpec extends MutableScalatraSpec {

  addServlet(classOf[SimpleBlog], "/*")

  "GET /users User" should {
    "return status 200" in {
      get("/users") {
        status must_== 200
      }
    }
  }

  "GET /users/1 User" should {
    "return status 200" in {
      get("/users/id") {
        status must_== 200
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

  "POST /users User" should {
    "return status 200" in {
      post("/users") {
        status must_== 200
      }
    }
  }

  "POST /users/1 User" should {
    "return status 200" in {
      post("/users/1") {
        status must_== 200
      }
    }
  }

  "DELETE /users/1 User" should {
    "return status 200" in {
      delete("/users/1") {
        status must_== 200
      }
    }
  }

}
