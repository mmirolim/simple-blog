package blog.routes

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.ScalatraServlet
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.scalate.ScalateSupport

/**
 * Created by Mirzakhmedov Mirolim on 07.11.2014.
 */
trait Base extends ScalatraServlet with ScalateSupport with JacksonJsonSupport {

  val baseProp = "base prop"
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

}

