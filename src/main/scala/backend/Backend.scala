package backend

import org.slf4j.Logger
import scala.slick.driver.MySQLDriver.simple._
import backend.routes.{CommentRoute, PostRoute, UserRoute}
import org.scalatra._

/**
 * Created by Mirzakhmedov Mirolim on 14.11.2014.
 */

class Backend(val db: Database, val logger: Logger)
  extends BackendStack
  with UserRoute
  with PostRoute
  with CommentRoute
  with MethodOverride
  with GZipSupport  {

  get("/") {
    "This is backend!"
  }

}
