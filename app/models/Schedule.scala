package models

import play.modules.spring.Spring
import org.springframework.data.mongodb.core._
import org.springframework.data.mongodb.core.query._
import scala.collection.JavaConversions._
import org.bson.types.ObjectId
import java.util.Date
import org.springframework.data.repository.CrudRepository
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.core.convert.converter.Converter
import com.mongodb.DBObject

@Document(collection="schedules")
case class Schedule(id: ObjectId, chn: String, prog: Prog, start: Date, duration: Int, minificha: String, ficha: String)


case class Prog(id: String, cod: String, name: String)
// class Prog(idc: String,namec: String){
//
//     var idProgram: String = idc
//     var name: String = namec
//     override def toString = {
//         "Person [id=" + idProgram + ", name=" + name +"]"
//     }
// }

// class ProgReadConverter extends Converter[DBObject, Prog] {
//   override def convert(source: DBObject) :Prog = {
//     var p: Prog = new Prog("",source.get("id").asInstanceOf[String], source.get("name").asInstanceOf[String])
//     p
//   }
// }

trait ScheduleRepository extends CrudRepository[Schedule,ObjectId]{
    def findByProg_id( prog: String ): java.util.List[Schedule]
}

object Schedule {
    val sr = Spring.getBeanOfType(classOf[ScheduleRepository])
    def all(): List[Schedule] = {
        val schedules:List[Schedule]  = sr.findAll().toList
        schedules
    }

    def findByProg(programId: String) = {
        val schedules:List[Schedule]  = sr.findAll().toList
        schedules
    }
}
