package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.User
import views._
import play.api.Logger

object Application extends Controller {

    def index = Action {
        Ok(html.index())
    }

    // -- Authentication

  val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> text
    ) verifying ("Email o password invalido", result => result match {
      case (email, password) => User.authenticate(email, password).isDefined})
  )

  /**
   * Login page.
   */
  def login = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  /**
   * Handle login form submission.
   */
  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => {
        Logger("application").debug("fail login of %s".format(formWithErrors))
        BadRequest(html.login(formWithErrors))
        },
      user => {
        Logger("application").debug("login success %s".format(user))
        Redirect(routes.Application.index).withSession("email" -> user._1)
    }
    )
  }

  /**
   * Logout and clean the session.
   */
  def logout = Action {
    Redirect(routes.Application.login).withNewSession.flashing(
      "success" -> "Tu te has desconectado"
    )
  }


}
