package blog.routes

import models.Tables._
import org.mindrot.jbcrypt.BCrypt
import org.scalatra.{BadRequest, Ok, Unauthorized}
import utils._

import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by Mirzakhmedov Mirolim on 24.11.2014.
 */
trait AuthRoute extends Base {

  post("/login") {

    val data = parsedBody.extract[LoginData]

    db withSession {
      implicit session =>
        users.filter(_.login === data.login).list
    } match {
      case Nil => Unauthorized(ResMsg(401, "login/password is wrong"))
      case u +: _ =>
        if (BCrypt.checkpw(data.pass, u.pass)) {
          //save user id in session
          session.setAttribute("uid", u.id)
          ResMsg(200, "you are logged in")
        } else Unauthorized(ResMsg(401, "login/password is wrong"))
    }

  }

  get("/logout") {

    session.invalidate()

  }

}
