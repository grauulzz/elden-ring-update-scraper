package org.grauulzz.erscraper

import org.scalatest.funsuite.AnyFunSuite
import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.model.{Document, Element}
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._

import scala.collection.immutable

class HtmlElementScraperTest extends AnyFunSuite {
  val browser: Browser = JsoupBrowser()
  val patchNotesDoc: Document = browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")

  test("HtmlElementScraper.getHeaderElement") {
    val header: Option[String] = HtmlElementScraper.apply(patchNotesDoc).getHeaderElement("h1")
    println(header)
  }

  test("HtmlElementScraper.getListOfHeaderElements") {
    // h6 is included to show the option will return "None" because there is no h6 element
    val res = HtmlElementScraper.apply(patchNotesDoc).getListOfHeaderElements(List("h1", "h2", "h3", "h4", "h5", "h6"))
    println(res)
  }

  test("HtmlElementScraper.getAnchorElement") {
    // sub-main is the id of the div tag in question
    val anchor: List[String] = HtmlElementScraper.apply(patchNotesDoc).getAnchorElement("#sub-main")
    println(anchor)
  }

  test("HtmlElementScraper.getListElement") {
    val list = HtmlElementScraper.apply(patchNotesDoc).getListElement("#sub-main")
    println(list)
  }

  test("HtmlElementScraper.getUnOrderedListElement") {
    val unOrderedList = HtmlElementScraper.apply(patchNotesDoc).getListElement("#sub-main")
    println(unOrderedList)
  }

  // tests parseFile constructor as well as getListOfHeaderElements
  test("HtmlElementScraper.parseString.getHeadListElementFormListElements") {

    val parsedFromFileHeaders = HtmlElementScraper.parseFile("src/main/scala/resources/PatchNotesSubMain.html")
    val subMainLists = parsedFromFileHeaders.getListElement("#sub-main")
    val subMainListsHead = parsedFromFileHeaders.getHeadListElement(subMainLists)

    val result: Boolean = subMainListsHead.toString().equals(
      "List(JsoupElement(<li>Fixed a bug in which the effect duration of the <a class=\"wiki_link\" " +
        "title=\"Elden Ring Cerulean Hidden Tear\" href=\"/Cerulean+Hidden+Tear\" " +
        "target=\"\">Cerulean Hidden Tear</a> was revised downward</li>))")

    assert(result)

  }



}
