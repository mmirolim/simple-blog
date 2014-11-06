package blog.routes

import blog.{models => M}
/**
 * Created by Mirzakhmedov Mirolim on 06.11.2014.
 */
trait User extends Base {


  private val _ns = "/users"
  private val _nsId = _ns + "/:id"

  get(_ns) {
    "User trait"
  }

  get(_nsId) {
    params("id")
    val u = new M.User()
    u.name
  }

  get(_nsId + "/posts") {
    "GET user posts"
  }

  get(_nsId + "/comments") {
    "GET user comments"
  }

  post(_ns) {
    // create new user
  }

  post(_nsId) {
    // update user
  }

  delete(_nsId) {
    //delete user
  }

}

