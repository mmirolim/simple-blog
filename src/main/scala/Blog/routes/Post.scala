package blog.routes

/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait Post extends Base {

  private val _ns = "/posts"
  private val _nsId = _ns + "/:id"

  get(_ns) {
    "GET all posts"
  }

  get(_nsId) {
    "This is post trait"
  }

  // router match from bottom to top goes
  get(_nsId  + "/comments") {
    "GET post comments"
  }

  post(_ns) {
    // create comment
  }

  post(_nsId) {
    // update comment
  }

  delete(_nsId) {
    // delete comment
  }

}

