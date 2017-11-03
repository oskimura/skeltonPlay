package dao

import scala.concurrent.Future
import javax.inject.Inject
import models.Login
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global

class LoginDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile]{
  import driver.api._

  private val logins = TableQuery[LoginTable]

  // DB にinsert
  def insert(login:Login) : Future[Unit] = db.run(logins += login).map{_=>()}



  private  class LoginTable(tag:Tag) extends Table[Login](tag,"LOGIN") {
    def name  = column[String]("EMAIL", O.PrimaryKey)
    def color = column[String]("PASSWORD")

    def * = (name, color) <> (Login.tupled, Login.unapply _)
  }


}