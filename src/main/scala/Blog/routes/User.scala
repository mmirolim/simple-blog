package blog.routes

import java.sql.Timestamp
import java.util.Date

import models._
import org.scalatra.BadRequest
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait User extends Base {


  private val _ns = "/users"
  private val _nsId = _ns + "/:id"

  get(_ns) {
    "User trait"
  }

  get(_nsId) {
   "GET user"
  }

  get(_nsId + "/posts") {
    "GET user posts"
  }

  get(_nsId + "/comments") {
    "GET user comments"
  }

  post(_ns) {

    try {
      val u = parsedBody.extract[models.User]
      db withSession {
        implicit session: Session =>
          val users = TableQuery[Users]
          users.map(n => (n.login, n.pass, n.name, n.email, n.roleId, n.createdAt)) += (u.login, u.pass, u.name, u.email, u.roleId, u.createdAt)
      }
      Msg(200, "new user created")
    } catch {
      case e: Exception => BadRequest(Msg(400,e.toString))
    }
    // create new user
  }

  post(_nsId) {
    // update user
  }

  delete(_nsId) {
    //delete user
  }

  get(_ns + "/roles") {
    db withSession {
      implicit session: Session =>
        Roles.getAllTitles.list
    }
  }

}

