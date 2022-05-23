package org.grauulzz.erscraper

import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.model.{Document, Element}
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import scalaz.std.java.`enum`

import scala.collection.immutable

object HtmlElementScraper {
  def parseFile(path: String): HtmlElementScraper = {
    val browser = JsoupBrowser()
    val doc: Document = browser.parseFile(path)
    new HtmlElementScraper(doc)
  }
  def parseUrl(url: String): HtmlElementScraper = {
    val browser = JsoupBrowser()
    val doc: Document = browser.get(url)
    new HtmlElementScraper(doc)
  }
}

case class HtmlElementScraper(doc: Document) {

  def getHeaderElement(headerTag: String): Option[String] = {
    for {
      headerElement: Element <- doc >?> element(headerTag)
    } yield headerElement.text
  }

  def getListOfHeaderElements(headerTags: List[String]): List[Option[String]] = {
    headerTags.map(h => getHeaderElement(h))
  }

  def getAnchorElement(id: String): List[String] = {
    val items = doc >> elementList(id)
    items.map(_ >> allText("a"))
  }

  def getListElement(id: String): immutable.Seq[List[Element]] = {
    val items = doc >> elementList(id)
    items.map(_ >> elementList("li"))
  }

  def getUnOrderedListElement(id: String): immutable.Seq[List[Element]] = {
    val items = doc >> elementList(id)
    items.map(_ >> elementList("ol"))
  }

  def getHeadListElement(html: immutable.Seq[List[Element]]): Seq[Element] = {
    html.map(_.head)
  }

}


//   def >>(id: String): String = s"$tag#$id"
//  def >>(className: String): String = s"$tag.$className"
//  def >>(tag: String): String = s"$tag"

