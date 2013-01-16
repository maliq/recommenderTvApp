package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Program

object ProgramController extends Controller {

    def index = Action {
        Redirect(routes.ProgramController.programs)
    }

    def programs= Action{
        Ok(views.html.programs(Program.all()))
    }

    def deleteProgram(id: Long) = TODO

    def searchProgram = TODO

}
