
package org.scalascan

import java.io.{InputStreamReader, BufferedReader}
import java.net.URL
import scala.collection.mutable.Queue
import java.util
import scala.collection.mutable
import com.apple.jobjc.NativeObjectLifecycleManager.Nothing

/**
 * Created by alexmin on 3/2/14.
 */


class Spider {

  var pages = List[Page]()


  def parse(url: String){

    var visited = Set[String]()
    var queary =  Set[String]()
    queary += url
    var count = 0

    var current = url
    var page = null: Page
    while ( !queary.isEmpty && count < 20){

      if (!visited.contains(current)){

        val content = get(current)

        if (content != null) {
          page = new Page(current, content)
          println("New Links: " + page.getLinks())
          queary ++= page.getLinks()
          pages = pages :+ page
        }


        visited += current
    //  pages += page
      }
      current = queary.head
      queary -= queary.head
      count = count + 1


    }


    println("Not visited:" + queary)
    println("Visited: " + visited)
    println("Pages",pages)

  }

  def get(str: String): String = {
    try{
      val url = new URL(str)
      val connection = url.openConnection()
      val in = connection.getInputStream()
      val reader = new BufferedReader(new InputStreamReader(in))

      var line, result = ""
      while (line != null) {
        result += line
        line = reader.readLine()
      }
      in.close()
      return result

    }
    catch {
      case e: Exception => return null
      }

  }

}
