package org.grauulzz.erscraper

import org.scalatest.funsuite.AnyFunSuite
import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.model.Document
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._

class ScraperTest extends AnyFunSuite {
  val browser: Browser = JsoupBrowser()
  val patchNotesDoc: Document = browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")

  test("Scraper.anchorElementText") {
    val res = Scraper.anchorElementText(patchNotesDoc)
    println(res)
  }

  test("Scraper.headerElementText") {
    val res = Scraper.headerElementText(patchNotesDoc, "h1")
    println(res)
  }

  test("Scraper.headerElementsListText") {
    val res = Scraper.headerElementsListText(patchNotesDoc, List("h1", "h2", "h3", "h4", "h5", "h6"))
    println(res)
  }



}


// test("Scraper.anchorElementText") {
//    val html =
//      """
//        |<html>
//        |  <body>
//        |    <a href="http://www.google.com">Google</a>
//        |  </body>
//        |</html>
//      """.stripMargin
//    val doc = Jsoup.parse(html)
//    val a = doc.select("a").first()
//    assert(Scraper.anchorElementText(a) === "Google")
//  }
