package org.grauulzz.erscraper.format

import org.grauulzz.erscraper.html.HtmlTag
import org.scalatest.funsuite.AnyFunSuite

class CssQueryStringFormatterTest extends AnyFunSuite{
  test("CssQueryString.format") {
    val queryString: CssQueryString = CssQueryString("wiki-content-block")
    assert(queryString.toString.equals("CssQueryString(wiki-content-block)"))
    val formatted = CssQueryStringFormatter.format(queryString)
    assert(formatted.equals("#wiki-content-block"))
  }
  test("CssQueryStringFormatter.format") {
    val htmlTag: HtmlTag = HtmlTag.apply("ol")
    val queryString: CssQueryStringWithHtmlTag = CssQueryStringWithHtmlTag("wiki-content-block", htmlTag)
    val formatted: String = CssQueryStringFormatter.format(queryString)
    assert(formatted.equals("#wiki-content-block ol"))
  }
  test("CssQueryStringWithHtmlTagList.format") {
    val queryString: CssQueryStringWithHtmlTagList =
      CssQueryStringWithHtmlTagList("wiki-content-block", List(HtmlTag.OL, HtmlTag.LI))
    val formatted: String = CssQueryStringFormatter.format(queryString)
    assert(formatted.equals("#wiki-content-block ol li"))
  }
}
