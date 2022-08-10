import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import Traversal._

object Main extends App {

    var logLevel = -1
    val noLogging = 0
    val stackTrace = 1
    val deepTrace = 2
    val logging:Int=>Boolean = (level:Int) => level<=logLevel

    args.length match {
      case 0 => 
        println("Please supply site for traversal")
        sys.exit(1)
      case 1 => 
        val site = args(0).toString()
        println(s"Traversing :: $site")
        logLevel = noLogging
        run(site)
      case 2 => 
        val site = args(0).toString()
        println(s"Traversing :: $site :: LogLevel :: ${args(1).toString()}")
        logLevel = args(1).toInt
        run(site)
      case _ => 
        println("Input Invalid")
    }

    def run(site: String) = {traverseSite(site)}

}
