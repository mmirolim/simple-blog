package backend.routes

import com.fasterxml.jackson.core.JsonParseException
import com.mysql.jdbc.exceptions.jdbc4.{MySQLIntegrityConstraintViolationException, MySQLDataException}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.servlet.SizeConstraintExceededException
import org.scalatra.{RequestEntityTooLarge, BadRequest, ScalatraServlet}
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.scalate.ScalateSupport
import org.slf4j.Logger
import utils.{Msg, ResMsg, Slug}
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by Mirzakhmedov Mirolim on 07.11.2014.
 */
trait Base extends ScalatraServlet with ScalateSupport with JacksonJsonSupport {

  val logger: Logger
  val db: Database
  val baseProp = "base prop"

  protected implicit val jsonFormats: Formats = DefaultFormats

  protected implicit class stringToSlug(s: String) {
    def slug = Slug(s)
  }

  // @todo delete after impl routes
  val uid: Int = 21
  val urole: Int = 1

  before() {
    //if (session.getAttribute("uid") == null) halt(401)
    //if (session.getAttribute("urole") == null) halt(401)
    contentType = formats("json")
  }

  error {
    case e: MySQLIntegrityConstraintViolationException => BadRequest(ResMsg(400, Msg.DataIntegrityViolation))
    case e: JsonParseException => BadRequest(ResMsg(400, Msg.JsonParseError))
    case e: SizeConstraintExceededException => RequestEntityTooLarge(ResMsg(400, "too large"))
    case e: Exception => BadRequest(e.toString)
  }

}

