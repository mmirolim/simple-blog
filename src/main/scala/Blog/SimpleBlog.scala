package blog

import blog.routes._
import org.scalatra._

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
