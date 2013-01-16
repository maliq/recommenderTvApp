package models

import org.bson.types.ObjectId
import play.modules.spring.Spring
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.CrudRepository
import scala.collection.JavaConversions._

@Document(collection="users")
case class User(id: ObjectId, name: String,email:String, var password: String)

trait UserRepository extends CrudRepository[User,ObjectId]

object User{
    val ur = Spring.getBeanOfType(classOf[UserRepository])
    def create(user: User) = {
        val md = java.security.MessageDigest.getInstance("SHA-1")
        val ha = new sun.misc.BASE64Encoder().encode(md.digest(user.password.getBytes))
        user.password = ha
        ur.save(user)
    }

    def all(): List[User] = {
        val users:List[User]  = ur.findAll().toList
        users
    }
}
