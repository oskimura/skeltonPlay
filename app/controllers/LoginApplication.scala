package controllers

import javax.inject.Inject

import dao.LoginDao
import models.Login
import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.mvc.{Action, Controller}

/**
  * Created by osamukimura on 2017/10/30.
  */
class LoginApplication @Inject()(logins: LoginDao) extends Controller {
  def authenticate = Action { implicit  rs =>
    val loginForm = Form(mapping(
      "name" -> text(),
      "password" -> text()
    )(Login.apply)(Login.unapply))

    val log = loginForm.bindFromRequest
    Redirect(routes.Application.index)
  }

  def login = Action {
    val log = Login.apply("","")
    Ok(views.html.Application.login(log))
  }






}
