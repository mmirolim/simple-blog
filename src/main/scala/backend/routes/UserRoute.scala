package backend.routes

import java.sql.Timestamp
import java.util.Date

import models.Tables._
import org.mindrot.jbcrypt.BCrypt
import org.scalatra.BadRequest
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
          val u = (for(u <- users if u.login === data.login) yield (u.id, u.pass)).run
          if (BCrypt.checkpw(data.pass, u(0)._2))
            ResMsg(200, "password is corret")
          else ResMsg(401, "wrong password")
      }

      ResMsg(200, "authorized")

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

