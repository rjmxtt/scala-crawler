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


}

