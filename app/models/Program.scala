package models

import scala.collection.JavaConversions.iterableAsScalaIterable
import scala.collection.JavaConversions.seqAsJavaList
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.CrudRepository
import play.modules.spring.Spring
import org.springframework.data.mongodb.core.mapping.Field
import scala.annotation.target.field
import org.springframework.core.convert.converter.Converter
import com.mongodb.DBObject
import com.mongodb.BasicDBObject

@Document(collection = "programs")
case class Program(id: String, 
    name: String, 
    description: String, 
    episode: String, 
    web: String, 
    year: String, 
    imdbSelected: String, 
    imdbResults: java.util.List[ImdbResult]){
  
}


case class ImdbResult(url: String,
  imdbId: String,
  title: String,
  postTitle: String)

trait ProgramRepository extends CrudRepository[Program, String]

object Program {
  val pr = Spring.getBeanOfType(classOf[ProgramRepository])
  def all(): List[Program] = {

    // val mongoOperations: MongoOperations = Spring.getBean("mongoTemplate").asInstanceOf[MongoOperations]
    // val results = mongoOperations.find(
    //     new Query(Criteria where("name") is("House")),classOf[Program],"programs")
    val programs: List[Program] = pr.findAll().toList
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
    imdbResults:List[ImdbResult]):Program={
	    Program(id, 
	    name, 
	    description, 
	    episode, 
	    web, 
	    year, 
	    imdbSelected, 
	    seqAsJavaList(imdbResults))
  }
  
  def unapplyCustom(program:Program) : Option[(String, 
    String, 
    String, 
    String, 
    String, 
    String, 
    String, 
    List[ImdbResult])] ={
    val t = (program.id,program.name,program.description,program.episode,program.web,program.year,program.imdbSelected,program.imdbResults.toList)
    Some(t)
  }
}


