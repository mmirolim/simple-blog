package Blog

import Blog.Routers.{Post, Comment, User}
import org.scalatra._
import scalate.ScalateSupport

class SimpleBlog
  extends SimpleBlogStack
  with Post
  with Comment
  with User
  with MethodOverride {

  get("/") {
    "Hello World!"
  }

}
