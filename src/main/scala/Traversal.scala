import scala.collection.mutable.Stack
import scala.annotation.tailrec
import Main.{logging,stackTrace,deepTrace}
import Parser.getLinks;

object Traversal extends Thread {
  def traverseSite(root: String) = {
    val dfsSolution = dfs(root)
    dfsSolution.map(l => println(s"${l}\n"))
    println(s"${dfsSolution.length} :: ${dfsSolution.distinct.length}")
  }


  def dfs(root: String): List[String] = {
    def dfsInt(curPage: String, visited: List[String]): List[String] = {
      if (logging(stackTrace)) {println(s"$curPage :: ${visited.length}")}
      if (visited.contains(curPage)) {
        visited
      } else {
        val pageLinks: List[String] = getLinks(root, curPage).filterNot(visited.toSet)
        pageLinks.foldLeft(curPage :: visited) ((b,a) => dfsInt(a,b))
      }
    }

    dfsInt(root,List()).reverse
  }


}

