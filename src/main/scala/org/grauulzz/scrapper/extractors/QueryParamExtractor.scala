package org.grauulzz.scrapper.extractors
import com.github.nscala_time.time.Imports.LocalDate
import net.ruippeixotog.scalascraper.browser.{ Browser, JsoupBrowser }
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
import org.grauulzz.scrapper.html
import org.grauulzz.scrapper.html.{ HtmlTag, HtmlTagListBuilder }
import scalaz.Leibniz.subst

// in Scala, we separated the code in the class (for instance-dependent logic)
class QueryParamExtractor(searchParam: String):
   val doc: Document = JsoupBrowser().get("https://eldenring.wiki.fextralife.com/Patch+Notes")

   var tags: Option[HtmlTagListBuilder.type] = None
   var _searchParam: String = searchParam

//...and the companion object (for instance-independent logic)
object QueryParamExtractor:
   def apply(searchParam: String): QueryParamExtractor =
     new QueryParamExtractor(searchParam)

   def apply(searchParam: String, tags: Option[HtmlTagListBuilder.type]): QueryParamExtractor =
      var qp = new QueryParamExtractor(searchParam)
      qp.tags = tags
      qp

   def unapply(qp: QueryParamExtractor): Option[HtmlTagListBuilder.type] =
     qp.tags

def extractResponse(q: QueryParamExtractor): Element =
   val prefix: String => String = (str: String) => if (!str.startsWith("#")) s"#$str" else str
   if (q.tags.isDefined)
     val t: HtmlTagListBuilder.type = q.tags.get
     val param: String =
       prefix.apply(q._searchParam) + (t.getTags.fold("")((x, y) => x + " " + y))
     q.doc >> extractor(param, element, asIs[Element])
   else
     q.doc >> extractor(prefix.apply(q._searchParam), element)

// class QueryParamExtractor[A](val extractor: HtmlExtractor[A])
