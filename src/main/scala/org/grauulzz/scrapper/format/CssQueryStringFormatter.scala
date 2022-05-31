package org.grauulzz.scrapper.format

import org.grauulzz.scrapper.html.HtmlTag

abstract class QueryString

case class CssQueryString(query: String) extends QueryString
case class CssQueryStringWithHtmlTag(query: String, htmlTag: HtmlTag) extends QueryString
case class CssQueryStringWithHtmlTagList(query: String, htmlTagList: List[HtmlTag])
    extends QueryString

object CssQueryStringFormatter:
   // The css query string in the Scala Web Scraper library requires a "#" prefix
   val prefix: String => String = (str: String) => if (!str.startsWith("#")) s"#$str" else str

   def format(queryString: QueryString): String = queryString match

      case CssQueryString(query) => prefix(query)

      case CssQueryStringWithHtmlTag(query, htmlTag) =>
        val str: String = prefix(query)
        s"$str ${HtmlTag.apply(htmlTag)}"

      case CssQueryStringWithHtmlTagList(query, htmlTagList) =>
        val str: String = prefix(query)
        s"$str ${htmlTagList.map(HtmlTag.apply).mkString(" ")}"
