package org.grauulzz.scraper
import net.ruippeixotog.scalascraper.browser.JsoupBrowser.JsoupDocument
import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.model.{Document, Element}
import net.ruippeixotog.scalascraper.dsl.DSL.*
import net.ruippeixotog.scalascraper.dsl.DSL.Extract.*
import net.ruippeixotog.scalascraper.dsl.DSL.Parse.*
import org.grauulzz.scraper.html.HtmlTag

import org.grauulzz.scraper.html.HtmlTag.H1
import org.grauulzz.scraper.html.HtmlTag.DIV

object Main {
  def main(args: Array[String]): Unit = {}

}

// standard singleton
object PatchNotesDoc {
  private val browser: Browser = JsoupBrowser()
  val content: Document =
    browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")

  def get(): Document = content
}
