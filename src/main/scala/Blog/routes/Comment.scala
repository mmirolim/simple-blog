package blog.routes

/**
 * Created by Mirzakhmedov Mirolim 06.11.2014.
 */
trait Comment extends Base  {

  private val _ns = "/comments" // route namespace
  private val _nsId = _ns + "/:id"

  post(_ns) {
    // create comment
  }

  get(_nsId) {
    "This is comment trait"
  }

  post(_nsId) {
    "This is comment update"
  }

  delete(_nsId) {
    "this is DELETE method"
  }

}

