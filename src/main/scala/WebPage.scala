// import scala.collection.mutable.Stack
// import Parser.getLinks;

// object WebPage {

//   final case class Page
//   (
//     thisPage: String,
//     unvisitedLinks: List[String] 
//   )

//   def findLinks(root: String, page: String): List[String] = {
//     getLinks(root, page)
//   }
  
//   def isExplored(page: Page): Boolean = {
//     page.unvisitedLinks.isEmpty
//   }

//   def getUnvisited(page: Page, visited: List[String]): Page = {
//     Page(page.thisPage, page.unvisitedLinks.filterNot(visited.toSet))
//   }

//   def trimStackHead(stack: Stack[Page],acc:List[String]):Unit = {
//     val trimmedHead = getUnvisited(stack.head,acc)
//     stack.pop()
//     stack.push(trimmedHead)
//   }

//   def nextPage(page: Page): Option[String] = {
//     if (page.unvisitedLinks.isEmpty) {
//       None
//     } else {
//       Some(page.unvisitedLinks.head)
//     }
//   }

//   def GetNextPage(root:String,nextPage: String): Page = {
//     Page(nextPage, findLinks(root, nextPage))
//   }

//   final case class DFSPage
//   (
//     thisPage: String
//   )

// }
