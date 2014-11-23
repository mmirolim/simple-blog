package backend.routes

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.ScalatraServlet
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.scalate.ScalateSupport
import org.slf4j.Logger
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by Mirzakhmedov Mirolim on 07.11.2014.
 */
trait Base extends ScalatraServlet with ScalateSupport with JacksonJsonSupport {

  val logger: Logger
  val db: Database
  val baseProp = "base prop"
  protected implicit val jsonFormats: Formats = DefaultFormats

  case class Msg(status: Int, content: String)
//  case class LoginData(login: String, pass: String)

  before() {
    contentType = formats("json")
  }

}

