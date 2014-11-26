package models

import java.time.LocalDateTime._
import java.time.{LocalDate, LocalDateTime, ZoneOffset}
import java.sql.{Date, Timestamp}
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import utils.Slug
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.Tag

/**
 * Created by Mirzakhmedov Mirolim on 22.11.2014.
 */
object Tables {

  @JsonIgnoreProperties(ignoreUnknown = true)
  case class User(id: Int = 0,
                  login: String,
                  pass: String,
                  name: String,
                  email: String,
                  roleId: Int = Roles.User,
                  createdAt: LocalDateTime = now,
                  updatedAt: LocalDateTime = now)


  class Users(tag: Tag) extends Table[User](tag, "users") {

    def id = column[Int]("id", O.PrimaryKey)

    def login = column[String]("login")

    def pass = column[String]("pass")

    def name = column[String]("name")

    def email = column[String]("email")

    def roleId = column[Int]("role")

    def createdAt = column[LocalDateTime]("created_at")

    def updatedAt = column[LocalDateTime]("updated_at")

    // projection to *
    def * = (id, login, pass, name, email, roleId, createdAt, updatedAt) <> (User.tupled, User.unapply _)

    def role = foreignKey("roles", roleId, roles)(_.id)

  }

  val users = TableQuery[Users]

  object Users {

    def map(u: User) = Map("id" -> u.id, "name" -> u.name, "login" -> u.login, "email" -> u.email, "createdAt" -> u.createdAt.toString)

  }

  case class Role(id: Int, title: String)

  case class RoleType(id: Int)

  class Roles(tag: Tag) extends Table[Role](tag, "roles") {

    def id = column[Int]("id", O.PrimaryKey)

    def title = column[String]("title")

    // projection to *
    def * = (id, title) <> (Role.tupled, Role.unapply _)

  }

  val roles = TableQuery[Roles]

  object Roles {
    val Admin = 1
    val Editor = 2
    val User = 3

    val dic = Map(Admin -> "admin", Editor -> "editor", User -> "user")
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  case class Post(id: Int = 0,
                  title: String,
                  slug: String = "",
                  content: String,
                  authorId: Int = 0,
                  createdAt: LocalDateTime = now,
                  updatedAt: LocalDateTime = now)

  class Posts(tag: Tag) extends Table[Post](tag, "posts") {

    def id = column[Int]("id", O.PrimaryKey)

    def title = column[String]("title")

    def slug = column[String]("slug")

    def content = column[String]("content")

    def authorId = column[Int]("author")

    def createdAt = column[LocalDateTime]("created_at")

    def updatedAt = column[LocalDateTime]("updated_at")

    // projection to *
    def * = (id, title, slug, content, authorId, createdAt, updatedAt) <> (Post.tupled, Post.unapply _)

    def author = foreignKey("users", authorId, users)(_.id)

  }

  val posts = TableQuery[Posts]

  object Posts {

    def map(p: Post) = Map(
      "id" -> p.id, 
      "title" -> p.title, 
      "slug" -> p.slug, 
      "content" -> p.content, 
      "authorId" -> p.authorId, 
      "createdAt" -> p.createdAt.toString, 
      "updatedAt" -> p.updatedAt.toString)
    
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  case class Comment(id: Int = 0,
                  title: String,
                  content: String,
                  authorId: Int,
                  postId: Int,
                  createdAt: LocalDateTime = now,
                  updatedAt: LocalDateTime = now)

  class Comments(tag: Tag) extends Table[Comment](tag, "comments") {

    def id = column[Int]("id", O.PrimaryKey)

    def title = column[String]("title")

    def content = column[String]("content")

    def authorId = column[Int]("author")

    def postId = column[Int]("post")

    def createdAt = column[LocalDateTime]("created_at")

    def updatedAt = column[LocalDateTime]("updated_at")

    // projection to *
    def * = (id, title, content, authorId, postId, createdAt, updatedAt) <> (Comment.tupled, Comment.unapply _)

    def author = foreignKey("users", authorId, users)(_.id)

    def post = foreignKey("posts", postId, posts)(_.id)

  }

  val comments = TableQuery[Comments]
  
  object Comments {
    
     def map(c: Comment) = Map(
      "id" -> c.id,
      "title" -> c.title,
      "content" -> c.content,
      "authorId" -> c.authorId,
      "postId" -> c.postId,
      "createdAt" -> c.createdAt.toString,
      "updatedAt" -> c.updatedAt.toString)

  }

  implicit val JavaLocalDateTimeMapper = MappedColumnType.base[LocalDateTime, Timestamp](
    {d => Timestamp.from(d.toInstant(ZoneOffset.ofHours(0)))},
    {d => d.toLocalDateTime}
  )

  /**
   * map between java.time.LocalDate and sql.Date
   */
  implicit val JavaLocalDateMapper = MappedColumnType.base[LocalDate, Date](
  {d => Date.valueOf(d)},
  {d => d.toLocalDate}
  )

}


