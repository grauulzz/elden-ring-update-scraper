package org.grauulzz.scrapper.extractors

import com.github.nscala_time.time.Imports
import net.ruippeixotog.scalascraper.browser.{ Browser, JsoupBrowser }
import net.ruippeixotog.scalascraper.model.{ Document, Element, ElementQuery }
import org.grauulzz.scrapper.TestSuite
import org.scalatest.funsuite.AnyFunSuite

class ContentExtractorTest extends TestSuite:
  private val browser: Browser = JsoupBrowser()
  val content: Document =
    browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")
  val extractor: ContentExtractor = new ContentExtractor()

  test("ContentExtractor.extractAllText") {
    val result: String = extractor.extractAllText(content)
    println(result)
  }
  test("ContentExtractor.extractWeapon") {
    val result =
      extractor.extractWeapon(content, "#wiki-content-block ul li", "Grafted Blade Greatsword")
    println(result)
  }

