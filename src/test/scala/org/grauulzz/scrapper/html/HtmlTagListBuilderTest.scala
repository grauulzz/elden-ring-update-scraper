package org.grauulzz.scrapper.html

import org.grauulzz.scrapper.TestSuite

class HtmlTagListBuilderTest extends TestSuite {

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
    assert(HtmlTagListBuilder.getTags.contains(HtmlTag.apply(HtmlTag.LI)))
  }

  test("HtmlTagListBuilder.isSupportedTag") {
    assert(HtmlTagListBuilder.isSupportedTag("li"))
  }

  test("HtmlTagListBuilder.removalAllTags") {
    List(HtmlTag.A, HtmlTag.B, HtmlTag.DIV, HtmlTag.LI).foreach(
      HtmlTagListBuilder.addTag
    )
    assert(HtmlTagListBuilder.getTags.size == 4)
    HtmlTagListBuilder.removalAllTags()
    assert(HtmlTagListBuilder.getTags.isEmpty)
  }
}
