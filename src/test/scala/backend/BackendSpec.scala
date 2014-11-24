package backend

import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class BackendSpec extends ScalatraSpec with DB { def is =
  "GET /backend on Backend"                     ^
    "should return status 200"                  ! root200^
    end

  addServlet(new Backend(db, logger), "/backend/*")

  def root200 = get("/") {
    status must_== 200
  }
}
