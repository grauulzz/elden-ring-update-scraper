package org.grauulzz.scrapper.extractors

import com.github.nscala_time.time.Imports.LocalDate
import net.ruippeixotog.scalascraper.browser.JsoupBrowser.JsoupElement
import net.ruippeixotog.scalascraper.dsl.DSL.{ deepFunctorOps, extractor }
import net.ruippeixotog.scalascraper.model.{ Document, Element, ElementQuery }
import net.ruippeixotog.scalascraper.scraper.ContentExtractors.{
  allText,
  element,
  elementList,
  elements,
  text,
  texts,
}
import net.ruippeixotog.scalascraper.scraper.ContentParsers.{ asDouble, asIs, asLocalDate }
import net.ruippeixotog.scalascraper.scraper.HtmlExtractor
import net.ruippeixotog.scalascraper.model.Element

class ContentExtractor:

  /** example usage: doc >> extractor(<cssQuery>, <contentExtractor>, <contentParser>)
    *
    * cssQuery: the CSS query used to select the elements to be processed;
    *
    * contentExtractor: the content to be extracted from the selected elements, e.g. the element objects themselves,
    * their text, a specific attribute, form data;
    *
    * contentParser: an optional parser for the data extracted in the step above, such as parsing numbers and
    * dates or using regexes. (contentParse date ex: asLocalDate("yyyy-MM-dd")
    * @param doc the document to be processed
    * @return the extracted data
    */
  def ex(doc: Document): Element =
    doc >> extractor("h1", element, asIs[Element])

  def extractAllText(doc: Document): String =
    doc >> allText

  def extractWeapon(
      doc: Document,
      cssQuery: String,
      name: String,
    ): Element =
    doc >> extractor(
      cssQuery,
      elementList,
      (elements: List[Element]) => elements.find(_.text.contains(name)).get,
    )

  def extractWeapon2(
      doc: Document,
      cssQuery: String,
      name: String,
    ): Iterable[Element] =
    val ele: Element = doc >> extractor(cssQuery, element, asIs[Element])
    for weapon <- ele.select(name).extract(elements) yield weapon
