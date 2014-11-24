package backend.routes

import java.sql.Timestamp
import java.util.Date

import models.Tables._
import org.mindrot.jbcrypt.BCrypt
import org.scalatra.{Ok, Unauthorized, BadRequest}
import utils.Helpers._
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait UserRoute extends Base {

  private val _ns = "/users"
  private val _nsId = _ns + "/:id"

  post("/login") {

    try {
      val data: LoginData = parsedBody.extract[LoginData]
      // hash password

      db withSession {
        implicit session: Session =>
          users.filter(_.login === data.login).list
      } match {
        case Nil => Unauthorized(ResMsg(401, "login/password is wrong"))
        case u +: _ =>
          if (BCrypt.checkpw(data.pass, u.pass)) {
            //save user id in session
            session.setAttribute("uid", u.id)
            Ok(ResMsg(200, "you are logged in"))
          } else Unauthorized(ResMsg(401, "login/password is wrong"))

      }
    } catch {
      case e: Exception => BadRequest(ResMsg(400, e.toString))
    }
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

      ResMsg(200, "new user created")

    } catch {
      case e: Exception => BadRequest(ResMsg(400,e.toString))
    }

  }

  post(_nsId) {
    val users = TableQuery[Users]
    try {
      val u = parsedBody.extract[User]
      // hash password
      val passHashed = BCrypt.hashpw(u.pass, BCrypt.gensalt)
      val now = new Timestamp(new Date().getTime)
      db withSession {
        implicit session: Session =>
          users.map(n => (n.login, n.pass, n.name, n.email, n.roleId, n.createdAt)) += (u.login, passHashed, u.name, u.email, rolesDic("User"), now)
      }

      ResMsg(200, "new user created")

    } catch {
      case e: Exception => BadRequest(ResMsg(400,e.toString))
    }

  }

  delete(_nsId) {
    //delete user
  }

  get(_ns + "/roles") {

  }

}

