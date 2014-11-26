package blog.routes

import models.Tables._
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by Mirzakhmedov Mirolim 06.11.2014.
 */
trait CommentRoute extends Base  {

  private val _ns = "/comments" // route namespace
  private val _nsId = _ns + "/:id"

  // get comment
  get(_nsId) {

    val id = params("id").toInt

    db withSession { implicit session =>

      val r = comments.filter(_.id === id).join(users).on(_.authorId === _.id).first

      Comments.map(r._1) ++ Map("author" -> Users.map(r._2))

    }

  }

}

