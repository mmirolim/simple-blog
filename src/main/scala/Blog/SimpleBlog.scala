package blog

import blog.routes._
import org.scalatra._
import org.slf4j.Logger
import scala.slick.driver.MySQLDriver.simple._

class SimpleBlog(val db: Database, val logger: Logger)
  extends SimpleBlogStack
  with PostRoute
  with CommentRoute
  with UserRoute
  with MethodOverride
  with GZipSupport  {

  get("/") {
    "Hello World!"
  }

}
