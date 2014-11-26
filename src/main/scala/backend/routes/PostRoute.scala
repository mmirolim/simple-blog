package backend.routes

import models.Tables._
import org.scalatra._

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait PostRoute extends Base {

  private val _ns = "/posts"
  private val _nsId = _ns + "/:id"

  // save new post
  post(_ns) {

    val post = parsedBody.extract[Post]

    db withSession { implicit session =>
      posts.insert(post.copy(slug = post.title.slug, authorId = uid))
    }

    Created()

  }

  // update post
  put(_nsId) {

    val id = params("id").toInt
    val m = parsedBody.extract[Map[String, String]]

    db withSession { implicit session =>
      // only author can update
      val q = posts.filter(p => p.id === id && p.authorId === uid)

      if (m.contains("title")) q.map(p => p.title).update(m("title"))

      if (m.contains("content")) q.map(p => p.content).update(m("content"))

    }

    Ok()

  }

  delete(_nsId) {

    val id = params("id").toInt

    if (urole != Roles.Admin) Forbidden()

    db withSession { implicit session =>
      // only author can delete
      posts.filter(_.id === id).delete

    }

    NoContent()

  }

}

