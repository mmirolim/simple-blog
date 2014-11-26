package blog.routes

import models.Tables._
import org.scalatra.{Ok, NotFound, BadRequest}
import utils._
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait UserRoute extends Base {


  private val _ns = "/users"
  private val _nsId = _ns + "/:id"

  // get all users
  get(_ns) {

    db withSession {implicit session =>

        users.sortBy(u => u.name.asc).list.map(u => Users.map(u))

    }

  }

  // get user
  get(_nsId) {

    val uid = params("id").toInt

    db withSession { implicit session =>
      
      Users map users.filter(_.id === uid).first

    }

  }

  // get all posts of a user
  get(_nsId + "/posts") {

    val id = params("id").toInt

    db withSession { implicit session =>

      val r = users.filter(_.id === id).join(posts).on(_.id === _.authorId).list

      Users.map(r.head._1) ++ Map("posts" -> r.map(_._2).map(p => Posts.map(p)))

    }

  }

  // get all comments of a user
  get(_nsId + "/comments") {

    val id = params("id").toInt

    db withSession { implicit session =>

      val r = users.filter(_.id === id).join(comments).on(_.id === _.authorId).list

      Users.map(r.head._1) ++ Map("comments" -> r.map(_._2).map(c => Comments.map(c)))

    }
  }

}

