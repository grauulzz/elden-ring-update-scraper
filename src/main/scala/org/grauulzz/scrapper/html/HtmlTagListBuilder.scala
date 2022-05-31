package org.grauulzz.scrapper.html

import org.grauulzz.scrapper.html

import scala.collection.mutable.ArrayBuffer

trait HtmlTag

case object HtmlTag {
  case object A extends HtmlTag
  case object B extends HtmlTag
  case object BR extends HtmlTag
  case object DIV extends HtmlTag
  case object EM extends HtmlTag
  case object H1 extends HtmlTag
  case object H2 extends HtmlTag
  case object H3 extends HtmlTag
  case object H4 extends HtmlTag
  case object H5 extends HtmlTag
  case object H6 extends HtmlTag
  case object I extends HtmlTag
  case object LI extends HtmlTag
  case object OL extends HtmlTag
  case object P extends HtmlTag
  case object SPAN extends HtmlTag
  case object STRONG extends HtmlTag
  case object UL extends HtmlTag

  def apply(name: String): HtmlTag = name match {
    case "a" => A
    case "b" => B
    case "br" => BR
    case "div" => DIV
    case "em" => EM
    case "h1" => H1
    case "h2" => H2
    case "h3" => H3
    case "h4" => H4
    case "h5" => H5
    case "h6" => H6
    case "i" => I
    case "li" => LI
    case "ol" => OL
    case "p" => P
    case "span" => SPAN
    case "strong" => STRONG
    case "ul" => UL
    case _ => throw new IllegalArgumentException(s"Unknown tag: $name")
  }

  // the apply method is setup both ways, to and from a string
  def apply(tag: HtmlTag): String = tag match {
    case A => "a"
    case B => "b"
    case BR => "br"
    case DIV => "div"
    case EM => "em"
    case H1 => "h1"
    case H2 => "h2"
    case H3 => "h3"
    case H4 => "h4"
    case H5 => "h5"
    case H6 => "h6"
    case I => "i"
    case LI => "li"
    case OL => "ol"
    case P => "p"
    case SPAN => "span"
    case STRONG => "strong"
    case UL => "ul"
  }

}

object HtmlTagListBuilder {
  val tags: ArrayBuffer[String] = ArrayBuffer[String]()
  def addTag(t: HtmlTag): Unit = tags += HtmlTag.apply(t)
  def removeTag(t: HtmlTag): Unit = tags -= HtmlTag.apply(t)
  def removalAllTags(): Unit = tags.clear()
  def getTags: ArrayBuffer[String] = tags
  def isSupportedTag(t: String): Boolean = {
    // if Html.apply(t) is not a known tag, it will throw an exception
    try {
      HtmlTag.apply(t)
      true
    } catch case _: IllegalArgumentException => false
  }
}
