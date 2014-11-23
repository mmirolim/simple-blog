package blog.routes

import org.scalatra.BadRequest

/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait PostRoute extends Base {

  private val _ns = "/posts"
  private val _nsId = _ns + "/:id"

  get(_ns) {
    "GET all posts"
  }

  get(_nsId) {
    "This is post trait"
  }

  // router match from bottom to top goes
  get(_nsId + "/comments") {
    "GET post comments"
  }

}

