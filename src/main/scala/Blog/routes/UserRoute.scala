package blog.routes

import java.sql.Timestamp
import java.util.Date

import models.Tables._
import org.mindrot.jbcrypt.BCrypt
import org.scalatra.BadRequest
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait UserRoute extends Base {


  private val _ns = "/users"
  private val _nsId = _ns + "/:id"

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

  post(_ns) {

    try {
      val u = parsedBody.extract[User]
      // hash password
      val passHashed = BCrypt.hashpw(u.pass, BCrypt.gensalt)
      val now = new Timestamp(new Date().getTime)

      db withSession {
        implicit session: Session =>
          users.map(n => (n.login, n.pass, n.name, n.email, n.roleId, n.createdAt)) += (u.login, passHashed, u.name, u.email, rolesDic("User"), now)
      }

      Msg(200, "new user created")

    } catch {
      case e: Exception => BadRequest(Msg(400,e.toString))
    }

  }

  post(_nsId) {
/*    val users = TableQuery[Users]
    try {
      val u = parsedBody.extract[models.User]
      // hash password
      val passHashed = BCrypt.hashpw(u.pass, BCrypt.gensalt)

      db withSession {
        implicit session: Session =>
          users.map(n => (n.login, n.pass, n.name, n.email, n.roleId, n.createdAt)) += (u.login, passHashed, u.name, u.email, Roles.dic("User"), now)
      }

      Msg(200, "new user created")

    } catch {
      case e: Exception => BadRequest(Msg(400,e.toString))
    }*/

  }

  delete(_nsId) {
    //delete user
  }

  get(_ns + "/roles") {

  }

}

