package models

import play.modules.spring.Spring
import org.springframework.data.mongodb.core._
import org.springframework.data.mongodb.core.query._
import scala.collection.JavaConversions._

case class Program(id: Long, name: String,description:String,episode:String)

object Program {

  def all(): List[Program] = {
    val mongoOperations: MongoOperations = Spring.getBean("mongoTemplate").asInstanceOf[MongoOperations]
    val results = mongoOperations.find(
        new Query(Criteria where("name") is("House")),classOf[Program],"programs")
    val programs:List[Program] = asScalaBuffer(results).toList
    programs
    }

  def create(label: String) {}

  def delete(id: Long) {}
}
