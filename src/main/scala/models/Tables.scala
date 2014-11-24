package models

import java.sql.Timestamp
import java.util.Date

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.Tag

/**
 * Created by Mirzakhmedov Mirolim on 22.11.2014.
 */
object Tables {

  val rolesDic = Map("Admin" -> 1, "Editor" -> 2, "User" -> 3)

  @JsonIgnoreProperties(ignoreUnknown = true)
  case class User(id: Int = 0,
                  login: String,
                  pass: String,
                  name: String,
                  email: String,
                  roleId: Int = rolesDic("User"),
                  createdAt: Timestamp = new Timestamp(new Date().getTime),
                  updatedAt: Timestamp = new Timestamp(new Date().getTime))

  class Users(tag: Tag) extends Table[User](tag, "users") {

    def id = column[Int]("id", O.PrimaryKey)

    def login = column[String]("login")

    def pass = column[String]("pass")

    def name = column[String]("name")

    def email = column[String]("email")

    def roleId = column[Int]("role")

    def createdAt = column[Timestamp]("created_at")

    def updatedAt = column[Timestamp]("updated_at")

    // projection to *
    def * = (id, login, pass, name, email, roleId, createdAt, updatedAt) <> (User.tupled, User.unapply _)

    def role = foreignKey("roles", roleId, roles)(_.id)
  }

  val users = TableQuery[Users]


  case class Role(id: Int, title: String)

  class Roles(tag: Tag) extends Table[(Int, String)](tag, "roles") {

    def id = column[Int]("id", O.PrimaryKey)

    def title = column[String]("title")

    // projection to *
    def * = (id, title)

  }

  val roles = TableQuery[Roles]

}
