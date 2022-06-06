package org.grauulzz.scraper.extractors
import com.github.nscala_time.time.Imports.LocalDate
import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.browser.JsoupBrowser.JsoupElement
import net.ruippeixotog.scalascraper.dsl.DSL.{deepFunctorOps, extractor}
import net.ruippeixotog.scalascraper.model.{Document, Element, ElementQuery}
import net.ruippeixotog.scalascraper.scraper.ContentExtractors.{
  allText,
  element,
  elementList,
  elements,
  text,
  texts
}
import net.ruippeixotog.scalascraper.scraper.ContentParsers.{
  asDouble,
  asIs,
  asLocalDate
}
import net.ruippeixotog.scalascraper.scraper.HtmlExtractor
import net.ruippeixotog.scalascraper.model.Element
import org.grauulzz.scraper.extractors.ParamExtraction.doc
import org.grauulzz.scraper.format.CssQueryString
import org.grauulzz.scraper.html
import org.grauulzz.scraper.html
import org.grauulzz.scraper.html
import org.grauulzz.scraper.html.{HtmlTag, HtmlTagListBuilder}
import scalaz.Leibniz.subst

import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

abstract class ParamExtraction

object ParamExtraction {
  def doc(
      url: String = "https://eldenring.wiki.fextralife.com/Patch+Notes"
  ): Document = {
    val browser: Browser = JsoupBrowser()
    browser.get(url)
  }

  def tagToElement(tag: HtmlTag): Element = {
    doc() >> extractor(html.HtmlTag(tag), element, asIs[Element])
  }

  def parseDivToDiv(): String = {
    val regex: Regex = """(?<=<div>).*?(?=</div>)""".r
    regex.findAllIn(doc() >> allText).mkString("\n")
  }

  def parseDivToDiv(tag: HtmlTag): String = {
    val regex: Regex = """(?<=<div>).*?(?=</div>)""".r
    regex.findAllIn(doc() >> allText).mkString("\n")
  }

  def parseDivContent(content: String): String = {
    val regex = """(?s)<div class="content">(.*?)</div>""".r
    regex.findFirstMatchIn(content).map(_.group(1)).getOrElse("")
  }

  def parseTagToTag(tag: HtmlTag): String = {
    val regex = """(?s)<(.*?)>(.*?)</\1>""".r
    regex.findFirstMatchIn(html.HtmlTag(tag)).map(_.group(2)).getOrElse("")
  }
}

object CssQueryFormatter {
  val prefix: String => String = (str: String) => {
    if (!str.startsWith("#")) s"#$str" else str
  }
  def apply(query: String, htmlTagList: List[HtmlTag]): String = {
    val str: String = prefix(query)
    s"$str ${htmlTagList.map(HtmlTag.apply).mkString(" ")}"
  }
  def apply(query: String): String = {
    val str: String = prefix(query)
    s"$str"
  }
}

case class Search() extends ParamExtraction {
  def extractElement(
      css: String,
      search: String
  ): Element = {
    doc() >> extractor(
      CssQueryFormatter.apply(css),
      elementList,
      (elements: List[Element]) => elements.find(_.text.contains(search)).get
    )
  }

  def extractElement(
      css: String,
      tags: List[HtmlTag],
      search: String
  ): Element = {
    doc() >> extractor(
      CssQueryFormatter.apply(css, tags),
      elementList,
      (elements: List[Element]) => elements.find(_.text.contains(search)).get
    )
  }

  def extractIterableElement(
      css: String,
      name: String
  ): Iterable[Element] = {
    val ele: Element = doc() >> extractor(css, element, asIs[Element])
    for {
      weapon <- ele.select(name).extract(elements)
    } yield weapon
  }
}

case class RegPattern() extends ParamExtraction {
  def extractRegex(css: CssQueryString, search: String): Element = {
    val reg: Regex = search.r
    doc() >> extractor(
      css.query,
      elementList,
      (elements: List[Element]) =>
        elements.find(e => reg.findFirstIn(e.text).isDefined).get
    )
  }

  // TODO: refactor to use this files CssQueryFormatter object instead of CssQueryString
  def extractRegex(css: CssQueryString, search: Regex): Element = {
    doc() >> extractor(
      css.query,
      elementList,
      (elements: List[Element]) =>
        elements.find(e => search.findFirstIn(e.text).isDefined).get
    )
  }
}
