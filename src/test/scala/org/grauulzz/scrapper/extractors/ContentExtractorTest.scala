package org.grauulzz.scrapper.extractors

import com.github.nscala_time.time.Imports
import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.model.{Document, Element, ElementQuery}
import org.grauulzz.scrapper.TestSuite
import org.grauulzz.scrapper.html.{HtmlTag, HtmlTagListBuilder}
import org.scalatest.funsuite.AnyFunSuite

class ContentExtractorTest extends TestSuite {
  private val browser: Browser = JsoupBrowser()
  val content: Document =
    browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")
  val extractor: ContentExtractor = new ContentExtractor(content)

  test("ContentExtractor.extractAllText") {
    val result: String = extractor.extractAllText()
    println(result)
  }

  test("ContentExtractor.extractWeapon") {
    val result = extractor
      .extractWeapon(
        "#wiki-content-block ul li",
        "Grafted Blade Greatsword"
      )
      .text
    assert("Increased the damage of Grafted Blade Greatsword.".equals(result))
  }

  test("ContentExtractor.extractHeader") {
    // also tests HtmlTag class implementation
    val h1Content = extractor.tagToElement(HtmlTag.apply("h1")).text
    assert("Patch Notes | Elden Ring Wiki".equals(h1Content))

    val tags: HtmlTagListBuilder.type = HtmlTagListBuilder
    tags.addTag(HtmlTag.UL)
    tags.addTag(HtmlTag.LI)
    println(tags.getTags)
    val cssQueryStr: String = "#wiki-content-block"
    val foldedTags: String =
      cssQueryStr + (tags.getTags.fold("")((x, y) => x + " " + y))
    println(foldedTags)
  }
}
