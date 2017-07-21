package controllers

import play.api.mvc._
/**
  * Created by osamukimura on 2017/07/15.
  */
object Clients extends Controller {

    def show(id: String) = Action {
      Ok("client: " + id)
    }
}
