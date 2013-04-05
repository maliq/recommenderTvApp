package global

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

import play.Logger

object GlobalContext {
  var ctx: ApplicationContext = null

  def onStart() {
    ctx = new ClassPathXmlApplicationContext("application-context.xml")
    Logger.debug("Spring applicationContext started");
    
  }

  def onStop() {
//	ctx.stop();
	ctx = null;
    Logger.info("Application shutdown...")
    Logger.debug("Spring applicationContext stopped");
  }

  def getControllerInstance[A](clazz: Class[A]): A = {
    ctx.getBean(clazz);
  }
}