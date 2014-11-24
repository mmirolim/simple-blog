package backend

import models.DBInit
import org.slf4j.LoggerFactory
/**
 * Created by Mirzakhmedov Mirolim on 24.11.2014.
 */
trait DB extends DBInit{

  val logger = LoggerFactory.getLogger(getClass)
  val db = dbInit
}
