package models

import scala.collection.JavaConversions.iterableAsScalaIterable
import scala.collection.JavaConversions.seqAsJavaList
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.CrudRepository
import global.GlobalContext
import org.springframework.data.domain._
import org.springframework.data.repository.PagingAndSortingRepository

@Document(collection = "programs")
case class Program(id: String,
  name: String,
  description: String,
  episode: String,
  web: String,
  year: String,
  imdbSelected: String,
  imdbResults: java.util.List[ImdbResult],
  wikipediaSelected: String,
  wikipediaResults: java.util.List[WikipediaResult]) {
}

case class ImdbResult(url: String,
  imdbId: String,
  title: String,
  postTitle: String)

case class WikipediaResult(title: String)

trait ProgramRepository extends PagingAndSortingRepository[Program, String]

object Program {
  //  val pr = Spring.getBeanOfType(classOf[ProgramRepository])
  val pr = GlobalContext.getControllerInstance(classOf[ProgramRepository])
  def all(): List[Program] = {

    // val mongoOperations: MongoOperations = Spring.getBean("mongoTemplate").asInstanceOf[MongoOperations]
    // val results = mongoOperations.find(
    //     new Query(Criteria where("name") is("House")),classOf[Program],"programs")
    val programs: List[Program] = pr.findAll().toList
    programs
  }

  def list(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): Page[Program] = {

    val offest = pageSize * page
    val programs: Page[Program] = pr.findAll(new PageRequest(page,pageSize))
    programs
  }

  def findOne(id: String): Option[Program] = {
    Some(pr.findOne(id))
  }

  def create(label: String) {}

  def applyCustom(id: String,
    name: String,
    description: String,
    episode: String,
    web: String,
    year: String,
    imdbSelected: String,
    imdbResults: List[ImdbResult],
    wikipediaSelected: String,
    wikipediaResults: List[WikipediaResult]): Program = {
    Program(id,
      name,
      description,
      episode,
      web,
      year,
      imdbSelected,
      seqAsJavaList(imdbResults),
      wikipediaSelected,
      seqAsJavaList(wikipediaResults))
  }

  def unapplyCustom(program: Program): Option[(String, String, String, String, String, String, String, List[ImdbResult], String, List[WikipediaResult])] = {
    val t = (program.id, program.name, program.description, program.episode, program.web, program.year,
      program.imdbSelected, program.imdbResults.toList, program.wikipediaSelected, program.wikipediaResults.toList)
    Some(t)
  }
}


