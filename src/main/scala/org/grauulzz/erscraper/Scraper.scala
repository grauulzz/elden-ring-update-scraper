package org.grauulzz.erscraper

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model.{Document, Element}
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._

object Scraper extends App {
  val browser = JsoupBrowser()
  val patchNotesDoc: Document = browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")

  def anchorElementText(doc: Document): List[String] = {
    val items = doc >> elementList("#sub-main")
    // From each item, extract all the text inside their <a> elements
    items.map(_ >> allText("a"))
  }

  def headerFiveElementText(doc: Document): List[String] = {
    val items = doc >> elementList("#sub-main")
    items.map(_ >> allText("h5"))
  }

  def headerElementText(doc: Document, header: String): Option[String] = {
    for {
      headerElement: Element <- doc >?> element(header)
    } yield headerElement.text
  }

  def headerElementsListText(doc: Document, header: List[String]): List[Option[String]] = {
    header.map(h => headerElementText(doc, h))
  }

}
