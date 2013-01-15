package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Program

object Application extends Controller {

    val programForm = Form(
            "name" -> nonEmptyText
            )

    def index = Action {
        Redirect(routes.Application.programs)
    }

    def programs= Action{
        Ok(views.html.index(Program.all(), programForm))
    }

    def newProgram = Action{
        implicit request =>programForm.bindFromRequest.fold(
            errors => BadRequest(views.html.index(Program.all(),errors)),
            label => {
                Program.create(label)
                Redirect(routes.Application.programs)
                }
            )
    }

    def deleteProgram(id: Long) = TODO

    def searchProgram = TODO

}
