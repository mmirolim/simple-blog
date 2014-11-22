package models

import scala.slick.lifted.Tag
import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by Mirzakhmedov Mirolim on 14.11.2014.
 */
case class Role(id: Int, title: String)

class Roles(tag: Tag) extends Table[(Int, String)](tag, "roles") {

  def id = column[Int]("id", O.PrimaryKey)
  def title = column[String]("title")
  // projection to *
  def * = (id, title)

}

object Roles {

  val dic = Map("Admin" -> 1, "Editor" -> 2, "User" -> 3)

  val roles = TableQuery[Roles]

  def getAllTitles = for(r <- roles) yield r.title

}


