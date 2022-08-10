import scala.collection.mutable.Stack
import WebPage._
import scala.annotation.tailrec
import Main.{logging,stackTrace,deepTrace}
import Parser.getLinks;

object Traversal extends Thread{
  def traverseSite(root: String) = {
    // val s: Stack[Page] = new Stack()
    // val entry = Page(root, findLinks(root, root))

    val dfsSolution = dfs(root)
    dfsSolution.map(l => println(s"${l}\n"))
    println(s"${dfsSolution.length} :: ${dfsSolution.distinct.length}")
  }


  def dfs(start: String): List[String] = {
    
    def dfs0(v: String, visited: List[String]): List[String] = {
      if (logging(stackTrace)) {println(s"${v} :: ${visited.length}")}
      if (visited.contains(v)) {
        visited
      } else {
        val neighbours: List[String] = getLinks(start, v).filterNot(visited.toSet)
        neighbours.foldLeft(v :: visited) ((b,a) => dfs0(a,b))
      }
    }

    dfs0(start,List()).reverse
  }



  // @deprecated
  // def t(root: String, stack: Stack[Page], curPage: Page, acc: List[String]): List[String] = {
  //   if (logging(stackTrace)) println(s"t :: ${stack.size} :: ${curPage.thisPage} :: ${curPage.unvisitedLinks.size} :: ${acc.length}")
    
  //   if (isExplored(curPage)) {
  //     stack.pop()
  //     if (stack.isEmpty) acc
  //     else {
  //       trimStackHead(stack,acc)
  //       if (logging(deepTrace)) print("Calling from A :: ")
  //       t(root, stack, stack.head, acc)
  //     }
  //   } else {
  //     val trimmedPage = getUnvisited(curPage, acc)
  //     nextPage(trimmedPage) match {
  //       case Some(nextPageStr) => 
  //         val newPage = GetNextPage(root, nextPageStr)
  //         if (logging(deepTrace)) print("Calling from B :: ")
  //         t(root, stack.push(newPage), newPage, acc.prepended(newPage.thisPage))
  //       case None => 
  //         if (logging(deepTrace)) print("Calling from C :: ")
  //         t(root, stack, trimmedPage, acc)
  //     }
  //   }
  // }
}

