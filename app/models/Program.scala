package models

import play.modules.spring.Spring
import org.springframework.data.mongodb.core._
import org.springframework.data.mongodb.core.query._
import scala.collection.JavaConversions._
import org.springframework.data.repository.CrudRepository
import org.springframework.data.mongodb.core.mapping.Document
import org.bson.types.ObjectId

@Document(collection="programs")
case class Program(id: String, name: String,description:String,episode:String)

trait ProgramRepository extends CrudRepository[Program,String]

object Program {
    val pr= Spring.getBeanOfType(classOf[ProgramRepository])
    def all(): List[Program] = {

    // val mongoOperations: MongoOperations = Spring.getBean("mongoTemplate").asInstanceOf[MongoOperations]
    // val results = mongoOperations.find(
    //     new Query(Criteria where("name") is("House")),classOf[Program],"programs")
        val programs:List[Program] = pr.findAll().toList
        programs
    }

    def create(label: String) {}
}
