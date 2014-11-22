package models

import java.sql.Timestamp
import java.util.Date

import com.fasterxml.jackson.annotation.{JsonIgnoreProperties, JsonIgnore, JsonProperty}

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.Tag

/**
 * Created by Mirzakhmedov Mirolim on 07.11.2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
case class User(id: Option[Int],
                login: String,
                pass: String,
                name: String,
                email: String,
                roleId: Int = Roles.dic("User"),
                createdAt: Timestamp = new Timestamp(new Date().getTime),
                updatedAt: Timestamp = new Timestamp(new Date().getTime))

class Users(tag: Tag) extends Table[(Int, String, String, String, String, Int, Timestamp, Timestamp)](tag, "users") {

  def id = column[Int]("id", O.PrimaryKey)

  def login = column[String]("login")

  def pass = column[String]("pass")

  def name = column[String]("name")

  def email = column[String]("email")

  def roleId = column[Int]("role")

  def createdAt = column[Timestamp]("created_at")

  def updatedAt = column[Timestamp]("updated_at")

  // projection to *
  def * = (id, login, pass, name, email, roleId, createdAt, updatedAt)

  def role = foreignKey("roles", roleId, Roles.roles)(_.id)
}

object Users {

  val users = TableQuery[Users]

}
