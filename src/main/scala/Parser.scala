import org.jsoup.Jsoup;
import org.jsoup.HttpStatusException
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import scala.collection.JavaConverters._
import org.apache.xerces.util.URI.MalformedURIException
import org.jsoup.UnsupportedMimeTypeException
import com.gargoylesoftware.htmlunit.{WebClient,UnexpectedPage,Page,BrowserVersion}
import com.gargoylesoftware.htmlunit.html.HtmlPage
import java.net.MalformedURLException

object Parser {

  def isInDomain(domain: String, url: String): Boolean = {
    try url.substring(0, domain.length) == domain
    catch _ => false
  }
  
  def getLinks(domain: String, url: String): List[String] = {
    try {
      val htmldoc: Document = Jsoup.connect(url)
          .ignoreContentType(true)
          .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:64.0) Gecko/20100101 Firefox/64.0")
          .referrer("http://www.google.com")   
          .get()
      val links: Elements = htmldoc.select("a")

      if (links.isEmpty) {
        println("HTML Parse Failure :: Unreadable JS")
        List.empty
      } else {
        links
          .eachAttr("abs:href")
          .asScala.toList
          .filter(l => isInDomain(domain, l) && !l.equals(url) && !l.equals(url+"#"))
          .distinct
      }
    } catch { 
      case e: MalformedURIException => 
        println(s"MalformedURIException :: ${e}")  
        sys.exit(1)
      case e: MalformedURLException =>
        println(s"MalformedURLException :: ${e}")  
        List.empty
      case f: UnsupportedMimeTypeException => 
        println(s"UnsupportedMimeTypeException :: ${f}")
        sys.exit(1)
      case g: HttpStatusException => 
        println(s"HttpStatusException :: ${g}")
        List.empty
      case _@other => 
        println(s"Jsoup Error :: $other")
        sys.exit(1)
    }

  }
}
