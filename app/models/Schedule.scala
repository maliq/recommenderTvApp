package models

import play.modules.spring.Spring
import org.springframework.data.mongodb.core._
import org.springframework.data.mongodb.core.query._
import scala.collection.JavaConversions._
import org.bson.types.ObjectId
import java.util.Date
import org.springframework.data.repository.CrudRepository
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection="schedules")
case class Schedule(id: ObjectId, chn: String, prog:String, start: Date, duration: Int, minificha: String, ficha: String)

trait ScheduleRepository extends CrudRepository[Schedule,ObjectId]

object Schedule {

  def all(): List[Schedule] = {
    val sr = Spring.getBeanOfType(classOf[ScheduleRepository])
    val schedules:List[Schedule]  = sr.findAll().toList
    schedules
    }

  def create(label: String) {}

  def delete(id: Long) {}
}
