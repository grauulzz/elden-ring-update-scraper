package org.grauulzz.scrapper

import org.scalatest.funsuite.AnyFunSuite
import net.ruippeixotog.scalascraper.browser.{ Browser, JsoupBrowser }
import net.ruippeixotog.scalascraper.model.{ Document, Element }
import net.ruippeixotog.scalascraper.dsl.DSL.*
import net.ruippeixotog.scalascraper.dsl.DSL.Extract.*
import net.ruippeixotog.scalascraper.dsl.DSL.Parse.*

import scala.collection.immutable

object HtmlElementScraperTest:
  val browser: Browser = JsoupBrowser()
  val doc: Document = browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")
  val table: Element = doc >> element("table")
  val tableRows: immutable.Seq[Element] = table >> elementList("tr")
  val tableHeaders: immutable.Seq[Element] = tableRows.head >> elementList("th")
  val tableCells: immutable.Seq[Element] = tableRows.tail.flatMap(_ >> elementList("td"))

  def getTableHeaders: immutable.Seq[String] = tableHeaders.map(_.text)
  def getTableCells: immutable.Seq[String] = tableCells.map(_.text)
  def getTableHeadersAndCells: immutable.Seq[(String, String)] = getTableHeaders.zip(getTableCells)


class HtmlElementScraperTest extends TestSuite:
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
     // sub-main is the id of the div tag in question
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
