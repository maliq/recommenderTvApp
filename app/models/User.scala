package models

import scala.collection.JavaConversions.iterableAsScalaIterable

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.CrudRepository

import play.modules.spring.Spring

@Document(collection="users")
case class User(id: ObjectId, name: String,email: String, var password: String)

trait UserRepository extends CrudRepository[User,ObjectId]{
    def findByEmailAndPassword(email: String, password: String): User
}

object User{
    val ur = Spring.getBeanOfType(classOf[UserRepository])
    val md = java.security.MessageDigest.getInstance("SHA-1")

    def create(user: User) = {
        val ha = new sun.misc.BASE64Encoder().encode(md.digest(user.password.getBytes))
        user.password = ha
        ur.save(user)
    }

    def all(): List[User] = {
        val users:List[User]  = ur.findAll().toList
        users
    }
    def authenticate(email: String, password: String): Option[User] = {
        val ha = new sun.misc.BASE64Encoder().encode(md.digest(password.getBytes))
        var user: User = ur.findByEmailAndPassword(email,ha)
        Option(user)
    }
}
