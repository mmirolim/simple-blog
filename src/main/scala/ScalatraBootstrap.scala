import blog._
import backend._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new SimpleBlog, "/*")
    context.mount(new Backend, "/backend/*")
  }
}
