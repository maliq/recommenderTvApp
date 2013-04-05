import global.GlobalContext
import play.api._
import play.Logger
object Global extends GlobalSettings {

  override def onStart(app: Application) {
    GlobalContext.onStart();
    Logger.info("Application has started")
  }  
  
  override def onStop(app: Application) {
    GlobalContext.onStop();
    Logger.info("Application shutdown...")
  }
    
}