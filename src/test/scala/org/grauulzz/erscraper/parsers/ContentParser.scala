package org.grauulzz.erscraper.parsers

object ContentParser {
  def parseContentDiv(content: String): String = {
    val regex = """(?s)<div class="content">(.*?)</div>""".r
    regex.findFirstMatchIn(content).map(_.group(1)).getOrElse("")
  }
}
