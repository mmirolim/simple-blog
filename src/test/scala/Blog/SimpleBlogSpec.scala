import backend.DB
import blog.SimpleBlog
import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class SimpleBlogSpec extends ScalatraSpec with DB { def is =
  "GET / on SimpleBlog"                     ^
    "should return status 200"                  ! root200^
                                                end



  addServlet(new SimpleBlog(db, logger), "/*")

  def root200 = get("/") {
    status must_== 200
  }
}
