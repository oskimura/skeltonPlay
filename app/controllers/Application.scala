package controllers


import javax.inject.Inject

import play.api.libs.concurrent.Execution.Implicits.defaultContext

import dao.CatDao
import models.Cat

import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

case class Item(name:String, price:Integer)


class Application @Inject()(cats: CatDao) extends Controller {

	def index = Action.async {
		cats.all.map {  cs => Ok(views.html.Application.index(cs)) }
	}

	val catForm = Form(
		mapping(
			"name" -> text(),
			"color" -> text()
		)(Cat.apply)(Cat.unapply)
	)
	def insert = Action.async { implicit rs =>
		val cat: Cat = catForm.bindFromRequest.get
		cats.insert(cat).map(_ => Redirect(routes.Application.index))
	}
}
