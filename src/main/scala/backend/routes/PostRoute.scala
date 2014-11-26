package backend.routes

import com.fasterxml.jackson.core.JsonParseException
import models.Tables._
import org.scalatra.{Ok, Unauthorized, BadRequest}
import utils.ResMsg
import scala.slick.driver.MySQLDriver.simple._
import scala.util.{Failure, Success, Try}
/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait PostRoute extends Base {

  private val _ns = "/posts"
  private val _nsId = _ns + "/:id"

  // create post
  post(_ns) {

    val post = parsedBody.extract[Post]

    db withSession {
        implicit session =>
          posts.insert(post.copy(slug = post.title.slug, authorId = uid))
    }

  }

  // update comment
  put(_nsId) {


  }

  // delete comment
  delete(_nsId) {

  }

}

