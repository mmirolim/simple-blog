package blog.routes

import models.Tables._
import org.mindrot.jbcrypt.BCrypt
import org.scalatra.{BadRequest, Ok, Unauthorized}
import utils.Helpers._

import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by Mirzakhmedov Mirolim on 24.11.2014.
 */
trait AuthRoute extends Base {

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

  get("/logout") {
    session.invalidate()
    Ok(ResMsg(200, "logout"))
  }

}
