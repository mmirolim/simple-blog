package backend.routes

import models.Tables._
import org.mindrot.jbcrypt.BCrypt
import org.scalatra._

import scala.slick.driver.MySQLDriver.simple._

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

    db withSession { implicit session =>

      users.insert(user.copy(pass = passHashed, roleId = Roles.User))

    }

    Ok

  }

  // update user
  put(_nsId) {

    val user = parsedBody.extract[Map[String, String]]

    val uid = session.getAttribute("uid").asInstanceOf[Int]

    db withSession { implicit session =>

      val q = users.filter(_.id === uid)

      if (user.contains("name")) q.map(u => u.name).update(user("name"))

      if (user.contains("email")) q.map(u => u.email).update(user("email"))

    }

  }

  delete(_nsId) {

    val id = params("id").toInt

    val uid = session.getAttribute("uid").asInstanceOf[Int]

    val urole = session.getAttribute("urole").asInstanceOf[Int]

    // should not delete himself
    if (urole != Roles.Admin || uid == id) Forbidden()

    db withSession { implicit session =>

      users.filter(_.id === id).delete

    }

    NoContent()

  }

  // get all roles
  get("/roles") {

    Roles.dic

  }

}

