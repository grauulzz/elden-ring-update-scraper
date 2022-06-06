package org.grauulzz.scraper.html

import org.scalatest.funsuite.AnyFunSuite
import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.model.{Document, Element}
import net.ruippeixotog.scalascraper.dsl.DSL.*
import net.ruippeixotog.scalascraper.dsl.DSL.Extract.*
import net.ruippeixotog.scalascraper.dsl.DSL.Parse.*
import org.grauulzz.scraper.TestSuite

import scala.collection.immutable

class HtmlElementScraperTest extends TestSuite {
  val browser: Browser = JsoupBrowser()
  val patchNotesDoc: Document =
    browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")

  test("HtmlElementScraper.getHeaderElement") {
    val header: Option[String] =
      HtmlElementScraper.apply(patchNotesDoc).getHeaderElement("h1")
    println(header)
  }

  test("HtmlElementScraper.getListOfHeaderElements") {
    // h6 is included to show the option will return "None" because there is no h6 element
    val res = HtmlElementScraper
      .apply(patchNotesDoc)
      .getListOfHeaderElements(List("h1", "h2", "h3", "h4", "h5", "h6"))
    println(res)
  }

  test("HtmlElementScraper.getAnchorElement") {
    val anchor: List[String] =
      HtmlElementScraper.apply(patchNotesDoc).getAnchorElement("#sub-main")
    println(anchor)
  }

  test("HtmlElementScraper.getListElement") {
    val list =
      HtmlElementScraper.apply(patchNotesDoc).getListElement("#sub-main")
    println(list)
  }

  test("HtmlElementScraper.getUnOrderedListElement") {
    val unOrderedList =
      HtmlElementScraper.apply(patchNotesDoc).getListElement("#sub-main")
    println(unOrderedList)
  }
}
