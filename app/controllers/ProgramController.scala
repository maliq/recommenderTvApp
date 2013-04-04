package controllers

import org.springframework.data.mongodb.core.mapping.Document
import models.Program
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.data.Forms.text
import play.api.data.Forms.list
import play.api.mvc.{ Action, Controller }
import models.ImdbResult
import scala.collection.JavaConversions._
import scala.collection.JavaConversions.asList
import models.ImdbResult

object ProgramController extends Controller {

  val programForm = Form(
    mapping(
      "id" -> nonEmptyText,
      "name" -> nonEmptyText,
      "description" -> text,
      "episode" -> text,
      "web" -> text,
      "year" -> text,
      "imdbSelected" -> text,
      "imdbResults" -> list(mapping(
        "url" -> text,
        "imdbId" -> text,
        "title" -> text,
        "postTitle" -> text)(ImdbResult.apply)(ImdbResult.unapply)))(Program.applyCustom)(Program.unapplyCustom))

  def index = Action {
    Redirect(routes.ProgramController.programs)
  }

  def programs = Action {
    Ok(views.html.programs(Program.all()))
  }

  def editProgram() = TODO

  def setupProgram(id: String) = Action {
//     Ok (views.html.editProgram(programForm.fill(Program.findOne(id))))
    Program.findOne(id).map { program =>
//      val options:Map[String,String] = for(imdbResult<-program.imdbResults.toList) yield (imdbResult.imdbId, imdbResult.title+imdbResult.postTitle)
      val options:Seq[(String,String)] = program.imdbResults.toList.map{imdbResult =>
        (imdbResult.imdbId, imdbResult.title+imdbResult.postTitle)
      }
      Ok(views.html.editProgram(programForm.fill(program),options))
    }.getOrElse(NotFound)
  }

  def deleteProgram(id: Long) = TODO

  def searchProgram = TODO

}
