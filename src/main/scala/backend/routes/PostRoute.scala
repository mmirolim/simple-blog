package backend.routes

import org.scalatra.BadRequest

/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait PostRoute extends Base {

  private val _ns = "/posts"
  private val _nsId = _ns + "/:id"

  post(_ns) {

  }

  post(_nsId) {
    // update comment
  }

  delete(_nsId) {
    // delete comment
  }

}

