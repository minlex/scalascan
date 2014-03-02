/**
 * Created by alexmin on 3/2/14.
 */
package org.scalascan

object ScalaScan {

  def main(args: Array[String]) {
    val spider = new Spider()
    spider.parse(args(0))
  }

}
