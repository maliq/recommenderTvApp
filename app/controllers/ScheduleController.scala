package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Schedule

object ScheduleController extends Controller {

    def schedules= Action{
        Ok(views.html.schedule(Schedule.all()))
    }

}
