package org.grauulzz.scrapper.extractors

class RegexParser {
  def parseContentDiv(content: String): String = {
    val regex = """(?s)<div class="content">(.*?)</div>""".r
    return regex.findFirstMatchIn(content).map(_.group(1)).getOrElse("")
  }
}
