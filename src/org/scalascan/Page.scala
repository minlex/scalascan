package org.scalascan

import scala.xml.{Node, NodeSeq}

/**
 * Created by alexmin on 3/2/14.
 */
class Page(newUrl :String,newContent :String) {

  val url     = newUrl
  val content = newContent

  def getLinks(): Seq[String] = {
    val parserFactory = new org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl
    val parser = parserFactory.newSAXParser()
    val source = new org.xml.sax.InputSource(url)
    val adapter = new scala.xml.parsing.NoBindingFactoryAdapter
    val node = adapter.loadXML(source, parser)
    var links = (node \\ "@href").map((node: Node) =>  node.toString())
    return links
  }


}
