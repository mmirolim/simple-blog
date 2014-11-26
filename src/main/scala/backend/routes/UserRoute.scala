package backend.routes

import java.time.LocalDateTime._
import models.Tables._
import org.mindrot.jbcrypt.BCrypt
import org.scalatra.{Ok, Unauthorized, BadRequest}
import utils._
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

    val user = parsedBody.extract[User]
    // hash password
    val passHashed = BCrypt.hashpw(user.pass, BCrypt.gensalt)

    db withSession {
      implicit session =>
        users.insert(user.copy(pass = passHashed, roleId = rolesDic("User")))
    }

  }

  // update user data
  put(_nsId) {

    val user = parsedBody.extract[Map[String,String]]

    db withSession {
        implicit session =>
        val q = users.filter(_.id === uid)
        if (user.contains("name")) q.map(u => u.name ).update(user("name"))
        if (user.contains("email")) q.map(u => u.email).update(user("email"))

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

