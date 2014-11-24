package models

import org.slf4j.Logger
import com.mchange.v2.c3p0.ComboPooledDataSource
import scala.slick.jdbc.JdbcBackend.Database
/**
 * Created by Mirzakhmedov Mirolim on 18.11.2014.
 */
trait DBInit {

  val logger: Logger
  val cpds = new ComboPooledDataSource("dev")

  def dbInit: Database = {
    logger.info("create a db from datasource")
    Database.forDataSource(cpds)
  }

  def dbConnClose {
    logger.info("Closing c3p0 connection pool")
    cpds.close()
  }

}
