package blog.routes

import models._
import scala.slick.driver.MySQLDriver.simple._

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
   "GET user"
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

  get(_ns + "/roles") {
    db withSession {
      implicit session: Session =>
        Roles.getAllTitles.list
    }
  }

}

