package backend.routes

import java.time.LocalDateTime._
import models.Tables._
import org.mindrot.jbcrypt.BCrypt
import org.scalatra.{Ok, Unauthorized, BadRequest}
import utils.Helpers._
import scala.slick.driver.MySQLDriver.simple._
import scala.util.{Failure, Success, Try}

/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait UserRoute extends Base {

  private val _ns = "/users"
  private val _nsId = _ns + "/:id"

  // save new user
  post(_ns) {

    try {
      val u = parsedBody.extract[User]
      // hash password
      val passHashed = BCrypt.hashpw(u.pass, BCrypt.gensalt)

      db withSession {
        implicit session: Session =>
          users.map(n => (n.login, n.pass, n.name, n.email, n.roleId, n.createdAt)) += (u.login, passHashed, u.name, u.email, rolesDic("User"), now)
      }

      ResMsg(200, "new user created")

    } catch {
      case e: Exception => BadRequest(ResMsg(400,e.toString))
    }

  }
  
  // update user data
  put(_nsId) {

    val uid: Int = 21
    val u = Try(parsedBody.extract[Map[String,String]]) match {
      case Success(v) => v
      case Failure(e) => halt(400)
    }
    try {
      // hash password
      val passHashed = if (u.contains("pass")) BCrypt.hashpw(u("pass"), BCrypt.gensalt) else ""

      db withSession {
        implicit session: Session =>
        val q = users.filter(_.id === uid)
        if (u.contains("name")) q.map(u => u.name ).update(u("name"))
        if (u.contains("email")) q.map(u => u.email).update(u("email"))

      }

      ResMsg(200, "user updated")

    } catch {
      case e: Exception => BadRequest(ResMsg(400,e.toString))
    }

  }

  // delete user
  delete(_nsId) {
    //delete user
  }

  // get all roles
  get("/roles") {
    rolesDic
  }

}

