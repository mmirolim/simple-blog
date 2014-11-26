package backend.routes

import models.Tables._
import scala.slick.driver.MySQLDriver.simple._
import org.scalatra.{Created, NoContent, Forbidden}

/**
 * Created by Mirzakhmedov Mirolim 06.11.2014.
 */
trait CommentRoute extends Base  {

  private val _ns = "/comments" // route namespace
  private val _nsId = _ns + "/:id"

  // create comment
  post(_ns) {

    val c = parsedBody.extract[Comment]

    db withSession { implicit session =>

      comments.insert(c.copy(authorId = uid))

    }

    Created()

  }

  delete(_nsId) {

    val id = params("id").toInt

    if (List(Roles.Admin, Roles.Editor).contains(urole)) Forbidden()

    db withSession { implicit session =>

      comments.filter(_.id === id).delete

    }

    NoContent()

  }

}

