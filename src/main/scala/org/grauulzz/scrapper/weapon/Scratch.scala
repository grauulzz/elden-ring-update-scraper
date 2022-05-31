package org.grauulzz.scrapper.weapon

class Scratch {
//  case class Scaling(var scalingTraits: List[Wt] = List()) extends WtImpl(scalingTraits) {
//    // only possible Scaling Character values
//    private val S = 'S'
//    private val A = 'A'
//    private val B = 'B'
//    private val C = 'C'
//    private val D = 'D'
//    private val E = 'E'
//    val GradeList: List[Char] = List(S, A, B, C, D, E)
//    case object Str extends Wt {
//      def isValidGrade(grade: Char): Boolean = {
//        grade match {
//          case S => true
//          case A => true
//          case B => true
//          case C => true
//          case D => true
//          case E => true
//          case _ => false
//        }
//      }
//
//      def returnValue(c: Char): Char = {
//        if (isValidGrade(Wt.Str._value.asInstanceOf[Char])) c else Nil.asInstanceOf[Char]
//
//      }
//      override def builder: (Wt, Any) = (this, Char)
//
//
//
//    }

  // sealed trait Attr {
  //  def builder: (Attr, Any)
  //}
  //
  //abstract class AttrImpl(var currentAttributes: List[Attr] = List[Attr](), var attr: Attr = null) {
  //  def add(attribute: Attr): Unit = {
  //    currentAttributes = currentAttributes :+ attribute
  //  }
  //  def addAll(attributes: List[Attr]): Unit = {
  //    currentAttributes = currentAttributes ++ attributes
  //  }
  //}
  //
  //case class AttackTraits(var attackTraits: List[Attr] = List()) extends AttrImpl(attackTraits) {
  //  case object Phy extends Attr { override def builder: (Attr, Any) = (this, Int) }
  //  case object Mag extends Attr { override def builder: (Attr, Any) = (this, Int) }
  //  case object Fire extends Attr { override def builder: (Attr, Any) = (this, Int) }
  //  case object Ligt extends Attr { override def builder: (Attr, Any) = (this, Int) }
  //  case object Holy extends Attr { override def builder: (Attr, Any) = (this, Int) }
  //  case object Crit extends Attr { override def builder: (Attr, Any) = (this, Int) }
  //
  //  def getAttackTraits: Map[String, List[(Attr, Any)]] = {
  //    Map("Attack" -> attackTraits.map(_.builder))
  //  }
  //}

}
