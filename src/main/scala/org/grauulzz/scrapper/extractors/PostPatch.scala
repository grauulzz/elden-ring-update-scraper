package org.grauulzz.scrapper.extractors

import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.model.{Document, Element, ElementQuery}
import net.ruippeixotog.scalascraper.dsl.DSL.*
import net.ruippeixotog.scalascraper.dsl.DSL.Extract.*
import net.ruippeixotog.scalascraper.dsl.DSL.Parse.*

import scala.collection.immutable


object PostPatch:
   val browser: Browser = JsoupBrowser()
   val doc: Document = browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")
   val table: Element = doc >> element("table")
   val tableRows: immutable.Seq[Element] = table >> elementList("tr")
   val tableHeaders: immutable.Seq[Element] = tableRows.head >> elementList("th")
   val tableCells: immutable.Seq[Element] = tableRows.tail.flatMap(_ >> elementList("td"))

   def getTableHeaders: immutable.Seq[String] = tableHeaders.map(_.text)
   def getTableCells: immutable.Seq[String] = tableCells.map(_.text)
   def getTableHeadersAndCells: immutable.Seq[(String, String)] = getTableHeaders.zip(getTableCells)
