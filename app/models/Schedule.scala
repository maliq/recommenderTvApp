package models

import java.util.Date

import scala.collection.JavaConversions.iterableAsScalaIterable

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.CrudRepository

import global.GlobalContext

@Document(collection="schedules")
case class Schedule(id: ObjectId, prog: Shortcut, chn: Shortcut, start: Date, 
    duration: Int, minificha: String, ficha: String)


case class Shortcut(id: String, cod: String, name: String)
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
//    val sr = Spring.getBeanOfType(classOf[ScheduleRepository])
    val sr = GlobalContext.getControllerInstance(classOf[ScheduleRepository])
    def all(): List[Schedule] = {
        val schedules:List[Schedule]  = sr.findAll().toList
        schedules
    }

    def findByProg(programId: String) = {
        val schedules:List[Schedule]  = sr.findAll().toList
        schedules
    }
}
