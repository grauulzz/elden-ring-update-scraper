package org.grauulzz.scrapper.format

import org.grauulzz.scrapper.TestSuite
import org.grauulzz.scrapper.html.HtmlTag
import org.scalatest.funsuite.AnyFunSuite

class CssQueryStringFormatterTest extends TestSuite {

  test("CssQueryString.format") {
    val queryString: CssQueryString = CssQueryString("wiki-content-block")
    assert(queryString.toString.equals("CssQueryString(wiki-content-block)"))
    val formatted = CssQueryStringFormatter.format(queryString)
    assert(formatted.equals("#wiki-content-block"))
  }

  test("CssQueryStringFormatter.format") {
    val htmlTag: HtmlTag = HtmlTag.apply("ol")
    val queryString: CssQueryStringWithHtmlTag =
      CssQueryStringWithHtmlTag("wiki-content-block", htmlTag)
    val formatted: String = CssQueryStringFormatter.format(queryString)
    assert(formatted.equals("#wiki-content-block ol"))
  }

  test("CssQueryStringWithHtmlTagList.format") {
    val queryString: CssQueryStringWithHtmlTagList =
      CssQueryStringWithHtmlTagList(
        "wiki-content-block",
        List(HtmlTag.OL, HtmlTag.LI)
      )
    val formatted: String = CssQueryStringFormatter.format(queryString)
    assert(formatted.equals("#wiki-content-block ol li"))
  }

}
