import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "springDataMongodbApp"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
        "org.mongodb" % "mongo-java-driver" % "2.9.0-RC1",
        "org.springframework" % "spring-core" % "3.1.0.RELEASE",
        "org.springframework" % "spring-context" % "3.1.0.RELEASE",
        "org.springframework" % "spring-beans" % "3.1.0.RELEASE",
        "org.springframework" % "spring-aspects" % "3.1.0.RELEASE",
        "org.aspectj" % "aspectjrt" % "1.7.1",
        "org.springframework.data" % "spring-data-mongodb" % "1.1.1.RELEASE",
        "cglib" % "cglib" % "2.2"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here
      resolvers ++= Seq("Spring Maven MILESTONE Repository" at "http://maven.springframework.org/milestone",
                    "TAMU Release Repository" at "https://maven.library.tamu.edu/content/repositories/releases/",
                    "Spring Snapshot" at "http://repo.springsource.org/snapshot"),
      offline := true
    )

}
