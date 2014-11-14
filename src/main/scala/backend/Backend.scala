package backend

/**
 * Created by Mirzakhmedov Mirolim on 14.11.2014.
 */

import org.scalatra._

class Backend
  extends BackendStack
  with MethodOverride
  with GZipSupport  {

  get("/") {
    "This is backend!"
  }

}
