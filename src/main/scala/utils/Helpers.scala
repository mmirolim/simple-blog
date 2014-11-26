package utils

/**
 * Created by Mirzakhmedov Mirolim on 24.11.2014.
 */
case class ResMsg(status: Int, msg: String)

case class LoginData(login: String, pass: String)

object Msg {

  val DataIntegrityViolation = "DataIntegrityViolation"
  val JsonParseError = "JsonParseError"
}
object Helpers {

}

object Slug {
  def apply(s: String) = slugify(s)

  def slugify(s: String): String = {
    ("[^\\w\\s-]".r replaceAllIn (s, "")).replace("-", " ").trim.replaceAll("\\s+", "-")
      .toLowerCase.dropRight(s.length  - 200)
  }

}
