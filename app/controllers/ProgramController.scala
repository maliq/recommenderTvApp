package controllers

import scala.collection.JavaConversions.asScalaBuffer

import org.springframework.data.mongodb.core.mapping.Document

import models.ImdbResult
import models.Program
import models.WikipediaResult
import play.api.data.Form
import play.api.data.Forms.list
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.data.Forms.text
import play.api.mvc.Action
import play.api.mvc.Controller

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
        "postTitle" -> text)(ImdbResult.apply)(ImdbResult.unapply)),
        "wikipediaSelected" -> text, 
        "wikipediaResults" -> list(mapping(
            "title"->text)(WikipediaResult.apply)(WikipediaResult.unapply))
    )(Program.applyCustom)(Program.unapplyCustom))

  def index = Action {
    Redirect(routes.ProgramController.programs(1))
  }
  
  def programs(page:Int) = Action {
    val pageLength = 10
    val (programs,count) = Program.list(page-1, pageLength)//Model.getNotifiactionsByUser(user, (page-1)*pageLength, pageLength)
    Ok(views.html.programs(programs, count.toInt, page, pageLength))
  }

//  def programs = Action {
//    Ok(views.html.programs(Program.all()))
//  }
//  
  

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
