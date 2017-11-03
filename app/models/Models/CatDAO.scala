package dao

import scala.concurrent.Future
import javax.inject.Inject
import models.Cat
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global

class CatDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val cats = TableQuery[CatsTable]

  def all() : Future[Seq[Cat]] = db.run(cats.result)

  // DB にinsert
  def insert(cat:Cat) : Future[Unit] = db.run(cats += cat).map{_=>()}

  private  class CatsTable(tag:Tag) extends Table[Cat](tag,"CAT") {
    def name  = column[String]("NAME", O.PrimaryKey)
    def color = column[String]("COLOR")

    def * = (name, color) <> (Cat.tupled, Cat.unapply _)
  }
}

