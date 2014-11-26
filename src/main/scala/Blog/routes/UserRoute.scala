package blog.routes

import java.sql.Timestamp
import java.util.Date

import models.Tables._
import org.scalatra.{NotFound, BadRequest}
import utils._
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait UserRoute extends Base {


  private val _ns = "/users"
  private val _nsId = _ns + "/:id"

  get(_ns) {

    db withSession {
      implicit session =>
        users.sortBy(u => u.name.asc).list.map(u => Map("name" -> u.name, "login" -> u.login, "email" -> u.email))
    }

  }

  get(_nsId) {

    val uid = params("id").toInt

    db withSession {
      implicit session =>
        users.filter(u => u.id === uid).list.map(u => Map("name" -> u.name, "login" -> u.login, "email" -> u.email, "createdAt" -> u.createdAt.toString))
    }

  }

  get(_nsId + "/posts") {
    "GET user posts"
  }

  get(_nsId + "/comments") {
    "GET user comments"
  }

}

