package org.grauulzz.erscraper.extractors

import com.github.nscala_time.time.Imports
import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.model.{Document, Element, ElementQuery}
import org.scalatest.funsuite.AnyFunSuite

class ContentExtractorTest extends AnyFunSuite {
  private val browser: Browser = JsoupBrowser()
  val content: Document = browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")
  val extractor: ContentExtractor = new ContentExtractor()
  val doc: Document = browser.parseFile("src/main/scala/resources/example.html")

  test("ex1") {
    val result: Element = extractor.ex1(doc)
    println(result)
  }
  test("ex2") {
    val result: Imports.LocalDate = extractor.ex2(doc)
    println(result)
  }
  test("ContentExtractor.extractAllText") {
    val result: String = extractor.extractAllText(content)
    println(result)
  }
  test("ContentExtractor.extractWeapon") {
    val result =
      extractor.extractWeapon(content, "#wiki-content-block ul li", "Grafted Blade Greatsword")
    println(result)
  }

  test("ContentExtractor.extractWeapon2") {
    val result =
      extractor.extractWeapon2(content, "#wiki-content-block", "Grafted Blade Greatsword")
//    println(result)
  }

}

