package org.grauulzz.erscraper.html

import org.scalatest.funsuite.AnyFunSuite

class HtmlTagListBuilderTest extends AnyFunSuite {
  test("HtmlTagListBuilder") {
    HtmlTagListBuilder.addTag(HtmlTag.A)
    HtmlTagListBuilder.addTag(HtmlTag.B)
    HtmlTagListBuilder.addTag(HtmlTag.DIV)
    HtmlTagListBuilder.addTag(HtmlTag.LI)
    assert(HtmlTagListBuilder.getTags.size == 4)
    HtmlTagListBuilder.removeTag(HtmlTag.A)
    HtmlTagListBuilder.removeTag(HtmlTag.B)
    HtmlTagListBuilder.removeTag(HtmlTag.DIV)
    assert(HtmlTagListBuilder.getTags.size == 1)
    // HtmlTag.apply() converts the enum HtmlTag type to a string
    assert(HtmlTagListBuilder.getTags.contains(HtmlTag.apply(HtmlTag.LI)))
  }
}
