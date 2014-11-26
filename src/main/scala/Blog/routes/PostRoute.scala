package blog.routes

import models.Tables._
import org.scalatra.NotFound
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait PostRoute extends Base {

  private val _ns = "/posts"
  private val _nsId = _ns + "/:id"

  get(_ns) {

    db withSession { implicit session =>

      posts.list

    }

  }

  get(_nsId) {

    val id = params("id").toInt

    db withSession { implicit session =>

      // get post and author
      val (p, u) = posts.filter(_.id === id).join(users).on(_.authorId === _.id).first

      // jon maps
      Posts.map(p) ++ Map("author" -> Users.map(u))

    }

  }

  // @important router match from bottom to top goes
  // get all comments in a post
  get(_nsId + "/comments") {

    val id = params("id").toInt

    db withSession { implicit session =>

      // get post and author
      val qp = posts.filter(_.id === id).join(users).on(_.authorId === _.id)

      // get all comments for post and authors
      val qc = comments.filter(_.postId === id).join(users).on(_.authorId === _.id).sortBy(_._1.createdAt.asc)

      // destruct tuples
      val ((p, u), cs) = (qp.first, qc.list)

      // create nice maps from comments to serialize
      val m = Map("comments" -> cs.map(l =>Comments.map(l._1) ++ Map("author" -> Users.map(l._2))))

      // jon maps
      Posts.map(p) ++ Map("author" -> Users.map(u)) ++ m

    }

  }

}

