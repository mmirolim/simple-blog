package blog.routes

import java.sql.Timestamp
import java.util.Date

import models.Tables._
import org.scalatra.BadRequest
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait UserRoute extends Base {


  private val _ns = "/users"
  private val _nsId = _ns + "/:id"

  case class Person(id: Int, name: String)
  get("/test") {
    Person(100, "mike")
  }
  post("/test") {
    try {
      val p = parsedBody.extract[Person]
      println(p)
    } catch {
      case e: Exception => BadRequest(Msg(500, e.toString))
    }
  }
  get(_ns) {
  }

  get(_nsId) {

  }

  get(_nsId + "/posts") {
    "GET user posts"
  }

  get(_nsId + "/comments") {
    "GET user comments"
  }

}

