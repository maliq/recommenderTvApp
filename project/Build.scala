import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "springDataMongodbApp"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      "org.mongodb" % "mongo-java-driver" % "2.6",
        "org.springframework" % "spring-core" % "3.0.5.RELEASE",
        "org.springframework" % "spring-context" % "3.0.5.RELEASE",
        "org.springframework" % "spring-beans" % "3.0.5.RELEASE",
        "org.springframework" % "spring-aspects" % "3.0.5.RELEASE",
        "org.aspectj" % "aspectjrt" % "1.7.1",
        "org.springframework.data" % "spring-data-mongodb" % "1.0.0.M4",
        "cglib" % "cglib" % "2.2",
        "play" % "spring_2.9.1" % "2.0"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here
      resolvers ++= Seq("Spring Maven MILESTONE Repository" at "http://maven.springframework.org/milestone",
                    "TAMU Release Repository" at "https://maven.library.tamu.edu/content/repositories/releases/")
    )

}
