package org.grauulzz.scraper
import net.ruippeixotog.scalascraper.browser.JsoupBrowser.JsoupDocument
import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.model.{Document, Element}
import net.ruippeixotog.scalascraper.dsl.DSL.*
import net.ruippeixotog.scalascraper.dsl.DSL.Extract.*
import net.ruippeixotog.scalascraper.dsl.DSL.Parse.*
import org.grauulzz.scrapper.html.HtmlTag

import org.grauulzz.scrapper.html.HtmlTag.H1
import org.grauulzz.scrapper.html.HtmlTag.DIV
import org.grauulzz.scrapper.extractors.ContentExtractor
object Main {
  def main(args: Array[String]): Unit = {
    val extractor: ContentExtractor = new ContentExtractor()

    println(extractor.tagToElement(HtmlTag.H1))

  }

}

// standard singleton
object PatchNotesDoc {
  private val browser: Browser = JsoupBrowser()
  val content: Document =
    browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")

  def get(): Document = content
}
