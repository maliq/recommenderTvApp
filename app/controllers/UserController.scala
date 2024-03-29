package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.Logger
import models.User
import org.bson.types.ObjectId
import play.api.data.format.Formats._

object UserController extends Controller {

    val registerForm = Form(
        mapping(
            "id" -> ignored("1"),
            "name" -> nonEmptyText,
            "email" -> nonEmptyText,
            "password" -> nonEmptyText
        ) ((id,name,email,password) => User(
                id = new ObjectId(),
                name = name,
                email = email,
                password = password
          ))(user => Some(
            user.id.toString,
            user.name,
            user.email,
            user.password
           ))
        // (User.apply)(User.unapply)
    )

    val loginForm = Form(
        tuple(
            "email" -> nonEmptyText,
            "password" -> nonEmptyText
            )
        )

    def register = Action{
        Ok(views.html.register(registerForm))
    }

    def create = Action{ implicit request =>
    registerForm.bindFromRequest.fold(
        errors => {
            Logger("application").debug("form with errores:%s".format(errors))
            BadRequest(views.html.register(errors))
            },
        newUser => {
            Logger("application").debug("Creating use with: %s".format(newUser))
            User.create(newUser)
            Redirect(routes.UserController.users)
        }
        )
    }


    def users = Action{
        Ok(views.html.users(User.all()))
    }
}
