//package org.grauulzz.scraper.extractors
//
//import com.github.nscala_time.time.Imports
//import org.grauulzz.scraper.TestSuite
//import org.grauulzz.scraper.html.{HtmlTag, HtmlTagListBuilder}
//import org.scalatest.funsuite.AnyFunSuite
//
//class ParamExtractionTest extends TestSuite {
//  val extractor: Search = Search.apply()
//
//  test("ParamExtraction.extractWeapon") {
//    val result = extractor
//      .extractElement(
//        "#wiki-content-block ul li",
//        "Grafted Blade Greatsword"
//      )
//      .text
//    assert("Increased the damage of Grafted Blade Greatsword.".equals(result))
//  }
//
//  test("ParamExtraction.extractHeader") {
//    // also tests HtmlTag class implementation
//    val h1Content = extractor.tagToElement(HtmlTag.apply("h1")).text
//    assert("Patch Notes | Elden Ring Wiki".equals(h1Content))
//
//    val tags: HtmlTagListBuilder.type = HtmlTagListBuilder
//    tags.addTag(HtmlTag.UL)
//    tags.addTag(HtmlTag.LI)
//    println(tags.getTags)
//    val cssQueryStr: String = "#wiki-content-block"
//    val foldedTags: String =
//      cssQueryStr + (tags.getTags.fold("")((x, y) => x + " " + y))
//    println(foldedTags)
//  }
//}
