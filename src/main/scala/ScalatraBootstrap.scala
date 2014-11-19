import blog._
import backend._
import models.DBInit
import org.scalatra._
import javax.servlet.ServletContext

import org.slf4j.LoggerFactory

class ScalatraBootstrap extends LifeCycle with DBInit {

  val logger = LoggerFactory.getLogger(getClass)
  val db = dbInit

  override def init(c: ServletContext) {
    c.mount(new SimpleBlog(db, logger), "/*")
    c.mount(new Backend, "/backend/*")
  }

  override def destroy(c: ServletContext): Unit = {
    dbConnClose // close c3p0 connection pool
  }
}
