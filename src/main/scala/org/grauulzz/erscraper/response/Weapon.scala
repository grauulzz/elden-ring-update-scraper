package org.grauulzz.erscraper.response

import com.google.gson.{Gson, GsonBuilder, JsonArray, JsonObject}

import scala.collection.mutable
import scala.reflect.ClassManifestFactory.Any

/**
 * Wt stands for "WeaponTrait"
 *
 */
sealed trait Wt {
  var _value: Any = Any
  def value: Any = _value
  def value_=(newVal: Any): Unit = _value = newVal
  def builder: (Wt, Any) = (this, _value)
}

abstract class WtImpl(var traits: List[Wt]) {
  def add(wt: Wt): Unit = {
    traits = traits :+ wt
  }
  def addAll(wt: List[Wt]): Unit = {
    traits = traits ++ wt
  }
//  override def builder: (Wt, Any) = (this, _value)
}

case object Wt {
  case object Phy extends Wt
  case object Mag extends Wt
  case object Fire extends Wt
  case object Ligt extends Wt
  case object Holy extends Wt
  case object Crit extends Wt
  case object Boost extends Wt
  case object Str extends Wt
  case object Dex extends Wt
  case object Int extends Wt
  case object Fai extends Wt
  case object Arc extends Wt
  case object Wgt extends Wt
}

case class AttackTraits(var attackTraits: List[Wt] = List()) extends WtImpl(attackTraits) with Wt {
  def getAttackTraits: Map[String, List[(Wt, Any)]] = {
    Map("Attack" -> attackTraits.map(_.builder))
  }
}

case class GuardTraits(var guardTraits: List[Wt] = List()) {

  def getGuardTraits: Map[String, List[(Wt, Any)]] = {
    Map("Guard" -> guardTraits.map(_.builder))
  }
}



class WeaponAttribute extends Wt {

  /**
   * The "attack" list should consist only of Wt.Phy, Wt.Mag, Wt.Fire, Wt.Ligt, Wt.Holy, Wt.Crit
   * These WeaponTraits(Wt) are none optional and should be including even if the values are 0
   * @return a map of attack values
   */
  def attack(attackTraits: List[Wt]): Map[String, List[(Wt, Any)]] = {
//    Map("Attack" -> attackTraits.map(wt => wt -> wt.value))
    Map("Attack" -> attackTraits.map(_.builder))
  }

  /**
   * The "guard" list should consist only of Wt.Phy, Wt.Mag, Wt.Fire, Wt.Ligt, Wt.Holy, Wt.Boost
   * These WeaponTraits(Wt) are none optional and should be including even if the values are 0
   * @return a map of guard values
   */
  def guard(guardTraits: List[Wt]): Map[String, List[(Wt, Any)]] = {
//    Map("Guard" -> guardTraits.map(wt => wt -> wt.value))
    Map("Guard" -> guardTraits.map(_.builder))
  }

  /**
   * The "scaling" list is usually only one, two, or three elements long because no weapon has more than three scaling values in game
   * Possible scaling values are: Wt.Str, Wt.Dex, Wt.Int, Wt.Fai, Wt.Arc
   * However, these are all optional and can be omitted if the weapon does not scale in that particular stat
   *
   * @return a map of the scaling values, omitting any that are not present
   */
  def scaling(scalingTraits: List[Wt]): Map[String, List[(Wt, Any)]] = {
//    Map("Scaling" -> scalingTraits.map(wt => wt -> wt.value))
    Map("Scaling" -> scalingTraits.map(_.builder))
  }

  /**
   * The "requires" list will be similar to the scaling list however, with the added Wgt WeaponTrait
   *
   * @return a map of the requires values, including the Wgt WeaponTrait, while omitting any that are not present
   */
  def requires(requiresTraits: List[Wt]): Map[String, List[(Wt, Any)]] = {
//    Map("Requires" -> requiresTraits.map(wt => wt -> wt.value))
    Map("Requires" -> requiresTraits.map(_.builder))
  }

  def allAttr(attackTraits: List[Wt], guardTraits: List[Wt], scalingTraits: List[Wt],
              requiresTraits: List[Wt]): Map[String, List[(Wt, Any)]] = {

    List(attack(attackTraits), guard(guardTraits), scaling(scalingTraits), requires(requiresTraits))
      .foldLeft(Map[String, List[(Wt, Any)]]())((acc, map) => acc ++ map)
  }

}

class Weapon(var name: String = "", var group: String = "", var version: String = "", var released: String = "",
             var attr:  Map[String, List[(Wt, Any)]] = Map()) {

  var _name: String = name
  var _group: String = group
  var _version: String = version
  var _released: String = released
  var _attributes: Map[String, List[(Wt, Any)]] = attr

//  def name_=(newValue: String): Unit = _name = newValue
//  def group_=(newValue: String): Unit = _group = newValue
//  def version_=(newValue: String): Unit = _version = newValue
//  def released_=(newValue: String): Unit = _released = newValue
//  def attributes_=(newValue: Map[String, List[(Wt, Any)]]): Unit = _attributes = newValue

  def toJson: String = {
    val json = new mutable.StringBuilder
    json.append("{")
    json.append("\"Name\":\"" + _name + "\",")
    json.append("\"Group\":\"" + _group + "\",")
    json.append("\"Version\":\"" + _version + "\",")
    json.append("\"Released\":\"" + _released + "\",")
    json.append("\"Attributes\":{")
    json.append(
      _attributes.foldLeft("")((acc, map) => acc + "\"" + map._1 + "\":{" + map._2.foldLeft("")
      ((acc2, map2) => acc2 + "\"" + map2._1 + "\":" + map2._2 + ",") + "},")
    )
    json.append("}")
    json.append("}")
    json.toString()
  }

  def toJsonPretty: String = {
    val json = new mutable.StringBuilder
    json.append("{")
    json.append("\n\t\"Name\":\"" + _name + "\",")
    json.append("\n\t\"Group\":\"" + _group + "\",")
    json.append("\n\t\"Version\":\"" + _version + "\",")
    json.append("\n\t\"Released\":\"" + _released + "\",")
    json.append("\n\t\"Attributes\":{")
    json.append(
      _attributes.foldLeft("")((acc, map) => acc + "\n\t\t\"" + map._1 + "\":{" + map._2.foldLeft("")
      ((acc2, map2) => acc2 + "\n\t\t\t\"" + map2._1 + "\":" + map2._2 + ",") + "\n\t\t},")
    )
    json.append("\n\t}")
    json.append("\n}")
    json.toString()
  }


}

// regular ass rudy case class for weapon attr
// case class GuardTraits(var guardTraits: List[Wt] = List()) {
//
//  def getGuardTraits: Map[String, List[(Wt, Any)]] = {
//    Map("Guard" -> guardTraits.map(_.builder))
//  }
//}




//  var _attackTraits: List[Wt] = List[Wt]()
//  var _guardTraits: List[Wt] = List[Wt]()
//  var _scalingTraits: List[Wt] = List[Wt]()
//  var _requiresTraits: List[Wt] = List[Wt]()
//
//  def attackTraits: List[Wt] = _attackTraits
//  def guardTraits: List[Wt] = _guardTraits
//  def scalingTraits: List[Wt] = _scalingTraits
//  def requiresTraits: List[Wt] = _requiresTraits

//  def attackTraits_=(newValue: List[Wt]): Unit = _attackTraits = newValue
//  def guardTraits_=(newValue: List[Wt]): Unit = _guardTraits = newValue
//  def scalingTraits_=(newValue: List[Wt]): Unit = _scalingTraits = newValue
//  def requiresTraits_=(newValue: List[Wt]): Unit = _requiresTraits = newValue


//     Map(ScalingKey -> _weaponTraits.map(_.glorifiedStrSetter))
//      .filter(x =>
//        x._1 == Wt.Str.toString ||
//        x._1 == Wt.Dex.toString ||
//        x._1 == Wt.Int.toString ||
//        x._1 == Wt.Fai.toString ||
//        x._1 == Wt.Arc.toString
//      )







//  override def scaling(): Map[String, List[Map[Wt, String]]] = {
//    // the scaling list is usually only one, two, or three elements long because no weapon has more than three scaling values in game
//    // the possible scaling values are: Wt.Str, Wt.Dex, Wt.Int, Wt.Fai, Wt.Arc
//    // we should first wrap the list in an Option, then unwrap it if it is not empty
//    // if it is empty, we should return an empty list
//    val optionalValues: immutable.Seq[Option[Map[Wt, Int]]] = _weaponTraits.map(_.glorifiedSetter).map(x => Option(x))
//    val unwrappedValues: immutable.Seq[Map[Wt, Int]] = optionalValues.flatten.filter(x =>
//      x.keySet.contains(Wt.Str) ||
//      x.keySet.contains(Wt.Dex) ||
//      x.keySet.contains(Wt.Int) ||
//      x.keySet.contains(Wt.Fai) ||
//      x.keySet.contains(Wt.Arc)
//    )
//
//    Map(ScalingKey -> _weaponTraits.map(_.glorifiedStrSetter))
//      .filter(x =>
//        x._1 == Wt.Str.toString ||
//        x._1 == Wt.Dex.toString ||
//        x._1 == Wt.Int.toString ||
//        x._1 == Wt.Fai.toString ||
//        x._1 == Wt.Arc.toString
//      )
//  }





// package org.grauulzz.erscraper.response
//
//import com.google.gson.{Gson, GsonBuilder}
//
//import scala.collection.immutable
//
///**
// * Wt stands for "WeaponTrait"
// *
// */
//class Wt {
//  var _value = 0
//  var _strValue = ""
//  def value: Int = _value
//  def strValue: String = _strValue
//  def value_=(newVal:Int): Unit = _value = newVal
//  def value_=(newVal:String): Unit = _strValue = newVal
//  def glorifiedSetter: Map[Wt, Int] = Map(this -> value)
//  def glorifiedStrSetter: Map[Wt, String] = Map(this -> strValue)
//}
//
//case object Wt {
//  case object Phy extends Wt {
//    override def toString: String = "Phy"
//  }
//  case object Mag extends Wt {
//    override def toString: String = "Mag"
//  }
//  case object Fire extends Wt {
//    override def toString: String = "Fire"
//  }
//  case object Ligt extends Wt {
//    override def toString: String = "Ligt"
//  }
//  case object Holy extends Wt {
//    override def toString: String = "Holy"
//  }
//  case object Crit extends Wt {
//    override def toString: String = "Crit"
//  }
//  case object Boost extends Wt {
//    override def toString: String = "Boost"
//  }
//  case object Str extends Wt {
//    override def toString: String = "Str"
//  }
//  case object Dex extends Wt {
//    override def toString: String = "Dex"
//  }
//  case object Int extends Wt {
//    override def toString: String = "Int"
//  }
//  case object Fai extends Wt {
//    override def toString: String = "Fai"
//  }
//  case object Arc extends Wt {
//    override def toString: String = "Arc"
//  }
//  case object Wgt extends Wt {
//    override def toString: String = "Wgt"
//  }
//}
//
//abstract class WeaponAttribute {
//  def attack(): Map[String, List[Map[Wt, Int]]]
//  def guard(): Map[String, List[Map[Wt, Int]]]
//  def scaling(): Map[String, List[Map[Wt, String]]]
//  def requires(): Map[String, List[Map[Wt, Int]]]
//}
//
//class Weapon(n: String, g: String, v: String, r: String,
//             traits: List[Wt]) extends WeaponAttribute {
//
//  var _name: String = n
//  var _group: String = g
//  var _version: String = v
//  var _released: String = r
//  var _weaponTraits: List[Wt] = traits
//  var _attack: List[Map[Wt, Int]] = List()
//  var _guard: List[Map[Wt, Int]] = List()
//  var _scaling: List[Map[Wt, String]] = List()
//  var _requires: List[Map[Wt, Int]] = List()
//
//  def name: String = _name
//  def group: String = _group
//  def version: String = _version
//  def released: String = _released
//  def weaponTraits: List[Wt] = _weaponTraits
//
//  def name_=(newVal:String): Unit = _name = newVal
//  def group_=(newVal:String): Unit = _group = newVal
//  def version_=(newVal:String): Unit = _version = newVal
//  def released_=(newVal:String): Unit = _released = newVal
//  def attack_=(newVal:List[Map[Wt, Int]]): Unit = _attack = newVal
//  def guard_=(newVal:List[Map[Wt, Int]]): Unit = _guard = newVal
//  def scaling_=(newVal:List[Map[Wt, String]]): Unit = _scaling = newVal
//  def requires_=(newVal:List[Map[Wt, Int]]): Unit = _requires = newVal
//
//  /**
//   * The "attack" list should consist only of Wt.Phy, Wt.Mag, Wt.Fire, Wt.Ligt, Wt.Holy, Wt.Crit
//   * These WeaponTraits(Wt) are none optional and should be including even if the values are 0
//   * @return a map of attack values
//   */
//  override def attack(): Map[String, List[Map[Wt, Int]]] = {
//
//    Map("Attack" -> weaponTraits.map(_.glorifiedSetter))
//      .filter(x =>
//        x._1 == Wt.Phy.toString ||
//        x._1 == Wt.Mag.toString ||
//        x._1 == Wt.Fire.toString ||
//        x._1 == Wt.Ligt.toString ||
//        x._1 == Wt.Holy.toString ||
//        x._1 == Wt.Crit.toString
//      )
//  }
//
//  /**
//   * The "guard" list should consist only of Wt.Phy, Wt.Mag, Wt.Fire, Wt.Ligt, Wt.Holy, Wt.Boost
//   * These WeaponTraits(Wt) are none optional and should be including even if the values are 0
//   * @return a map of guard values
//   */
//  override def guard(): Map[String, List[Map[Wt, Int]]] = {
//
//    Map("Guard" -> weaponTraits.map(_.glorifiedSetter))
//      .filter(x =>
//        x._1 == Wt.Phy.toString ||
//        x._1 == Wt.Mag.toString ||
//        x._1 == Wt.Fire.toString ||
//        x._1 == Wt.Ligt.toString ||
//        x._1 == Wt.Holy.toString ||
//        x._1 == Wt.Boost.toString
//      )
//
//  }
//
//  /**
//   * The "scaling" list is usually only one, two, or three elements long because no weapon has more than three scaling values in game
//   * Possible scaling values are: Wt.Str, Wt.Dex, Wt.Int, Wt.Fai, Wt.Arc
//   * However, these are all optional and can be omitted if the weapon does not scale in that particular stat
//   *
//   * @return a map of the scaling values, omitting any that are not present
//   */
//  override def scaling(): Map[String, List[Map[Wt, String]]] = {
//    val optionalValues: List[Option[Map[Wt, String]]] = weaponTraits.map(_.glorifiedStrSetter).map(x => Option(x))
//    Map("Scaling" -> optionalValues.flatten.filter(x =>
//      x.keySet.contains(Wt.Str) ||
//        x.keySet.contains(Wt.Dex) ||
//        x.keySet.contains(Wt.Int) ||
//        x.keySet.contains(Wt.Fai) ||
//        x.keySet.contains(Wt.Arc)
//    ))
//
//  }
//
//  /**
//   * The "requires" list will be similar to the scaling list however, with the added Wgt WeaponTrait
//   *
//   * @return a map of the requires values, including the Wgt WeaponTrait, while omitting any that are not present
//   */
//  override def requires(): Map[String, List[Map[Wt, Int]]] = {
//    val optionalValues: List[Option[Map[Wt, Int]]] = weaponTraits.map(_.glorifiedSetter).map(x => Option(x))
//    Map("Requires" -> optionalValues.flatten.filter(x =>
//      x.keySet.contains(Wt.Str) ||
//        x.keySet.contains(Wt.Dex) ||
//        x.keySet.contains(Wt.Int) ||
//        x.keySet.contains(Wt.Fai) ||
//        x.keySet.contains(Wt.Arc) ||
//        x.keySet.contains(Wt.Wgt)))
//  }
//
//  override def toString: String = {
//    new GsonBuilder().setPrettyPrinting().create().toJson(this)
//  }
//
//}
//
//
//
//
////     Map(ScalingKey -> _weaponTraits.map(_.glorifiedStrSetter))
////      .filter(x =>
////        x._1 == Wt.Str.toString ||
////        x._1 == Wt.Dex.toString ||
////        x._1 == Wt.Int.toString ||
////        x._1 == Wt.Fai.toString ||
////        x._1 == Wt.Arc.toString
////      )
//
//
//
//
//
//
//
////  override def scaling(): Map[String, List[Map[Wt, String]]] = {
////    // the scaling list is usually only one, two, or three elements long because no weapon has more than three scaling values in game
////    // the possible scaling values are: Wt.Str, Wt.Dex, Wt.Int, Wt.Fai, Wt.Arc
////    // we should first wrap the list in an Option, then unwrap it if it is not empty
////    // if it is empty, we should return an empty list
////    val optionalValues: immutable.Seq[Option[Map[Wt, Int]]] = _weaponTraits.map(_.glorifiedSetter).map(x => Option(x))
////    val unwrappedValues: immutable.Seq[Map[Wt, Int]] = optionalValues.flatten.filter(x =>
////      x.keySet.contains(Wt.Str) ||
////      x.keySet.contains(Wt.Dex) ||
////      x.keySet.contains(Wt.Int) ||
////      x.keySet.contains(Wt.Fai) ||
////      x.keySet.contains(Wt.Arc)
////    )
////
////    Map(ScalingKey -> _weaponTraits.map(_.glorifiedStrSetter))
////      .filter(x =>
////        x._1 == Wt.Str.toString ||
////        x._1 == Wt.Dex.toString ||
////        x._1 == Wt.Int.toString ||
////        x._1 == Wt.Fai.toString ||
////        x._1 == Wt.Arc.toString
////      )
////  }


// package org.grauulzz.erscraper.response
//
//import com.google.gson.{Gson, GsonBuilder}
//
//import scala.collection.immutable
//
///**
// * Wt stands for "WeaponTrait"
// *
// */
//class Wt {
//  var _iValue = 0
//  var _sValue = ""
//  def iValue: Int = _iValue
//  def sValue: String = _sValue
//  def iValue_=(newVal:Int): Unit = _iValue = newVal
//  def sValue_=(newVal:String): Unit = _sValue = newVal
//  def glorifiedIntSetter: Map[Wt, Int] = Map(this -> iValue)
//  def glorifiedStrSetter: Map[Wt, String] = Map(this -> sValue)
//}
//
//case object Wt {
//  case object Phy extends Wt {
//    override def toString: String = "Phy"
//  }
//  case object Mag extends Wt {
//    override def toString: String = "Mag"
//  }
//  case object Fire extends Wt {
//    override def toString: String = "Fire"
//  }
//  case object Ligt extends Wt {
//    override def toString: String = "Ligt"
//  }
//  case object Holy extends Wt {
//    override def toString: String = "Holy"
//  }
//  case object Crit extends Wt {
//    override def toString: String = "Crit"
//  }
//  case object Boost extends Wt {
//    override def toString: String = "Boost"
//  }
//  case object Str extends Wt {
//    override def toString: String = "Str"
//  }
//  case object Dex extends Wt {
//    override def toString: String = "Dex"
//  }
//  case object Int extends Wt {
//    override def toString: String = "Int"
//  }
//  case object Fai extends Wt {
//    override def toString: String = "Fai"
//  }
//  case object Arc extends Wt {
//    override def toString: String = "Arc"
//  }
//  case object Wgt extends Wt {
//    override def toString: String = "Wgt"
//  }
//}
//
//abstract class WeaponAttribute {
//  def attack(): Map[String, List[Map[Wt, Int]]]
//  def guard(): Map[String, List[Map[Wt, Int]]]
//  def scaling(): Map[String, List[Map[Wt, String]]]
//  def requires(): Map[String, List[Map[Wt, Int]]]
//}
//
//class Weapon(n: String, g: String, v: String, r: String,
//             traits: List[Wt]) extends WeaponAttribute {
//
//  var _name: String = n
//  var _group: String = g
//  var _version: String = v
//  var _released: String = r
//
//
//  def name: String = _name
//  def group: String = _group
//  def version: String = _version
//  def released: String = _released
//
//  /**
//   * The "attack" list should consist only of Wt.Phy, Wt.Mag, Wt.Fire, Wt.Ligt, Wt.Holy, Wt.Crit
//   * These WeaponTraits(Wt) are none optional and should be including even if the values are 0
//   * @return a map of attack values
//   */
//  override def attack(): Map[String, List[Map[Wt, Int]]] = {
//    val attackTraits =
//      traits.filter(x => x == Wt.Phy || x == Wt.Mag || x == Wt.Fire || x == Wt.Ligt || x == Wt.Holy || x == Wt.Crit)
//    // perserve order of traits
//    val attackTraitsMap = attackTraits.map(x => x.toString -> x.glorifiedIntSetter).toMap
//    Map("Attack" -> List(Map(attackTraits.head -> traits.head.iValue,
//      attackTraits(1) -> traits(1).iValue,
//      attackTraits(2) -> traits(2).iValue,
//      attackTraits(3) -> traits(3).iValue,
//      attackTraits(4) -> traits(4).iValue,
//      attackTraits(5) -> traits(5).iValue))
//    )
//  }
//
//  /**
//   * The "guard" list should consist only of Wt.Phy, Wt.Mag, Wt.Fire, Wt.Ligt, Wt.Holy, Wt.Boost
//   * These WeaponTraits(Wt) are none optional and should be including even if the values are 0
//   * @return a map of guard values
//   */
//  override def guard(): Map[String, List[Map[Wt, Int]]] = {
//    val guardTraits =
//      traits.filter(x => x == Wt.Phy || x == Wt.Mag || x == Wt.Fire || x == Wt.Ligt || x == Wt.Holy || x == Wt.Boost)
//    Map("Guard" -> List(Map(guardTraits.head -> traits.head.iValue,
//      guardTraits(1) -> traits(1).iValue,
//      guardTraits(2) -> traits(2).iValue,
//      guardTraits(3) -> traits(3).iValue,
//      guardTraits(4) -> traits(4).iValue,
//      guardTraits(5) -> traits(5).iValue))
//    )
//
//  }
//
//  /**
//   * The "scaling" list is usually only one, two, or three elements long because no weapon has more than three scaling values in game
//   * Possible scaling values are: Wt.Str, Wt.Dex, Wt.Int, Wt.Fai, Wt.Arc
//   * However, these are all optional and can be omitted if the weapon does not scale in that particular stat
//   *
//   * @return a map of the scaling values, omitting any that are not present
//   */
//  override def scaling(): Map[String, List[Map[Wt, String]]] = {
//    val scalingTraits = traits.filter(x => x == Wt.Str || x == Wt.Dex || x == Wt.Int || x == Wt.Fai || x == Wt.Arc)
//    Map("Scaling" -> List(Map(scalingTraits.head -> traits.head.sValue,
//      scalingTraits(1) -> traits(1).sValue,
//      scalingTraits(2) -> traits(2).sValue,
//      scalingTraits(3) -> traits(3).sValue,
//      scalingTraits(4) -> traits(4).sValue))
//    )
//  }
//
//  /**
//   * The "requires" list will be similar to the scaling list however, with the added Wgt WeaponTrait
//   *
//   * @return a map of the requires values, including the Wgt WeaponTrait, while omitting any that are not present
//   */
//  override def requires(): Map[String, List[Map[Wt, Int]]] = {
//    val optionalValues: List[Option[Map[Wt, Int]]] = traits.map(_.glorifiedIntSetter).map(x => Option(x))
//    Map("Requires" -> optionalValues.flatten.filter(x =>
//      x.keySet.contains(Wt.Str) ||
//        x.keySet.contains(Wt.Dex) ||
//        x.keySet.contains(Wt.Int) ||
//        x.keySet.contains(Wt.Fai) ||
//        x.keySet.contains(Wt.Arc) ||
//        x.keySet.contains(Wt.Int) ||
//        x.keySet.contains(Wt.Wgt)))
//  }
//
//  override def toString: String = {
//    new GsonBuilder().setPrettyPrinting().create().toJson(this)
//  }
//
//}
//
//
//
//
////     Map(ScalingKey -> _weaponTraits.map(_.glorifiedStrSetter))
////      .filter(x =>
////        x._1 == Wt.Str.toString ||
////        x._1 == Wt.Dex.toString ||
////        x._1 == Wt.Int.toString ||
////        x._1 == Wt.Fai.toString ||
////        x._1 == Wt.Arc.toString
////      )
//
//
//
//
//
//
//
////  override def scaling(): Map[String, List[Map[Wt, String]]] = {
////    // the scaling list is usually only one, two, or three elements long because no weapon has more than three scaling values in game
////    // the possible scaling values are: Wt.Str, Wt.Dex, Wt.Int, Wt.Fai, Wt.Arc
////    // we should first wrap the list in an Option, then unwrap it if it is not empty
////    // if it is empty, we should return an empty list
////    val optionalValues: immutable.Seq[Option[Map[Wt, Int]]] = _weaponTraits.map(_.glorifiedSetter).map(x => Option(x))
////    val unwrappedValues: immutable.Seq[Map[Wt, Int]] = optionalValues.flatten.filter(x =>
////      x.keySet.contains(Wt.Str) ||
////      x.keySet.contains(Wt.Dex) ||
////      x.keySet.contains(Wt.Int) ||
////      x.keySet.contains(Wt.Fai) ||
////      x.keySet.contains(Wt.Arc)
////    )
////
////    Map(ScalingKey -> _weaponTraits.map(_.glorifiedStrSetter))
////      .filter(x =>
////        x._1 == Wt.Str.toString ||
////        x._1 == Wt.Dex.toString ||
////        x._1 == Wt.Int.toString ||
////        x._1 == Wt.Fai.toString ||
////        x._1 == Wt.Arc.toString
////      )
////  }
//
//
//
//
//
//// package org.grauulzz.erscraper.response
////
////import com.google.gson.{Gson, GsonBuilder}
////
////import scala.collection.immutable
////
/////**
//// * Wt stands for "WeaponTrait"
//// *
//// */
////class Wt {
////  var _value = 0
////  var _strValue = ""
////  def value: Int = _value
////  def strValue: String = _strValue
////  def value_=(newVal:Int): Unit = _value = newVal
////  def value_=(newVal:String): Unit = _strValue = newVal
////  def glorifiedSetter: Map[Wt, Int] = Map(this -> value)
////  def glorifiedStrSetter: Map[Wt, String] = Map(this -> strValue)
////}
////
////case object Wt {
////  case object Phy extends Wt {
////    override def toString: String = "Phy"
////  }
////  case object Mag extends Wt {
////    override def toString: String = "Mag"
////  }
////  case object Fire extends Wt {
////    override def toString: String = "Fire"
////  }
////  case object Ligt extends Wt {
////    override def toString: String = "Ligt"
////  }
////  case object Holy extends Wt {
////    override def toString: String = "Holy"
////  }
////  case object Crit extends Wt {
////    override def toString: String = "Crit"
////  }
////  case object Boost extends Wt {
////    override def toString: String = "Boost"
////  }
////  case object Str extends Wt {
////    override def toString: String = "Str"
////  }
////  case object Dex extends Wt {
////    override def toString: String = "Dex"
////  }
////  case object Int extends Wt {
////    override def toString: String = "Int"
////  }
////  case object Fai extends Wt {
////    override def toString: String = "Fai"
////  }
////  case object Arc extends Wt {
////    override def toString: String = "Arc"
////  }
////  case object Wgt extends Wt {
////    override def toString: String = "Wgt"
////  }
////}
////
////abstract class WeaponAttribute {
////  def attack(): Map[String, List[Map[Wt, Int]]]
////  def guard(): Map[String, List[Map[Wt, Int]]]
////  def scaling(): Map[String, List[Map[Wt, String]]]
////  def requires(): Map[String, List[Map[Wt, Int]]]
////}
////
////class Weapon(n: String, g: String, v: String, r: String,
////             traits: List[Wt]) extends WeaponAttribute {
////
////  var _name: String = n
////  var _group: String = g
////  var _version: String = v
////  var _released: String = r
////  var _weaponTraits: List[Wt] = traits
////  var _attack: List[Map[Wt, Int]] = List()
////  var _guard: List[Map[Wt, Int]] = List()
////  var _scaling: List[Map[Wt, String]] = List()
////  var _requires: List[Map[Wt, Int]] = List()
////
////  def name: String = _name
////  def group: String = _group
////  def version: String = _version
////  def released: String = _released
////  def weaponTraits: List[Wt] = _weaponTraits
////
////  def name_=(newVal:String): Unit = _name = newVal
////  def group_=(newVal:String): Unit = _group = newVal
////  def version_=(newVal:String): Unit = _version = newVal
////  def released_=(newVal:String): Unit = _released = newVal
////  def attack_=(newVal:List[Map[Wt, Int]]): Unit = _attack = newVal
////  def guard_=(newVal:List[Map[Wt, Int]]): Unit = _guard = newVal
////  def scaling_=(newVal:List[Map[Wt, String]]): Unit = _scaling = newVal
////  def requires_=(newVal:List[Map[Wt, Int]]): Unit = _requires = newVal
////
////  /**
////   * The "attack" list should consist only of Wt.Phy, Wt.Mag, Wt.Fire, Wt.Ligt, Wt.Holy, Wt.Crit
////   * These WeaponTraits(Wt) are none optional and should be including even if the values are 0
////   * @return a map of attack values
////   */
////  override def attack(): Map[String, List[Map[Wt, Int]]] = {
////
////    Map("Attack" -> weaponTraits.map(_.glorifiedSetter))
////      .filter(x =>
////        x._1 == Wt.Phy.toString ||
////        x._1 == Wt.Mag.toString ||
////        x._1 == Wt.Fire.toString ||
////        x._1 == Wt.Ligt.toString ||
////        x._1 == Wt.Holy.toString ||
////        x._1 == Wt.Crit.toString
////      )
////  }
////
////  /**
////   * The "guard" list should consist only of Wt.Phy, Wt.Mag, Wt.Fire, Wt.Ligt, Wt.Holy, Wt.Boost
////   * These WeaponTraits(Wt) are none optional and should be including even if the values are 0
////   * @return a map of guard values
////   */
////  override def guard(): Map[String, List[Map[Wt, Int]]] = {
////
////    Map("Guard" -> weaponTraits.map(_.glorifiedSetter))
////      .filter(x =>
////        x._1 == Wt.Phy.toString ||
////        x._1 == Wt.Mag.toString ||
////        x._1 == Wt.Fire.toString ||
////        x._1 == Wt.Ligt.toString ||
////        x._1 == Wt.Holy.toString ||
////        x._1 == Wt.Boost.toString
////      )
////
////  }
////
////  /**
////   * The "scaling" list is usually only one, two, or three elements long because no weapon has more than three scaling values in game
////   * Possible scaling values are: Wt.Str, Wt.Dex, Wt.Int, Wt.Fai, Wt.Arc
////   * However, these are all optional and can be omitted if the weapon does not scale in that particular stat
////   *
////   * @return a map of the scaling values, omitting any that are not present
////   */
////  override def scaling(): Map[String, List[Map[Wt, String]]] = {
////    val optionalValues: List[Option[Map[Wt, String]]] = weaponTraits.map(_.glorifiedStrSetter).map(x => Option(x))
////    Map("Scaling" -> optionalValues.flatten.filter(x =>
////      x.keySet.contains(Wt.Str) ||
////        x.keySet.contains(Wt.Dex) ||
////        x.keySet.contains(Wt.Int) ||
////        x.keySet.contains(Wt.Fai) ||
////        x.keySet.contains(Wt.Arc)
////    ))
////
////  }
////
////  /**
////   * The "requires" list will be similar to the scaling list however, with the added Wgt WeaponTrait
////   *
////   * @return a map of the requires values, including the Wgt WeaponTrait, while omitting any that are not present
////   */
////  override def requires(): Map[String, List[Map[Wt, Int]]] = {
////    val optionalValues: List[Option[Map[Wt, Int]]] = weaponTraits.map(_.glorifiedSetter).map(x => Option(x))
////    Map("Requires" -> optionalValues.flatten.filter(x =>
////      x.keySet.contains(Wt.Str) ||
////        x.keySet.contains(Wt.Dex) ||
////        x.keySet.contains(Wt.Int) ||
////        x.keySet.contains(Wt.Fai) ||
////        x.keySet.contains(Wt.Arc) ||
////        x.keySet.contains(Wt.Wgt)))
////  }
////
////  override def toString: String = {
////    new GsonBuilder().setPrettyPrinting().create().toJson(this)
////  }
////
////}
////
////
////
////
//////     Map(ScalingKey -> _weaponTraits.map(_.glorifiedStrSetter))
//////      .filter(x =>
//////        x._1 == Wt.Str.toString ||
//////        x._1 == Wt.Dex.toString ||
//////        x._1 == Wt.Int.toString ||
//////        x._1 == Wt.Fai.toString ||
//////        x._1 == Wt.Arc.toString
//////      )
////
////
////
////
////
////
////
//////  override def scaling(): Map[String, List[Map[Wt, String]]] = {
//////    // the scaling list is usually only one, two, or three elements long because no weapon has more than three scaling values in game
//////    // the possible scaling values are: Wt.Str, Wt.Dex, Wt.Int, Wt.Fai, Wt.Arc
//////    // we should first wrap the list in an Option, then unwrap it if it is not empty
//////    // if it is empty, we should return an empty list
//////    val optionalValues: immutable.Seq[Option[Map[Wt, Int]]] = _weaponTraits.map(_.glorifiedSetter).map(x => Option(x))
//////    val unwrappedValues: immutable.Seq[Map[Wt, Int]] = optionalValues.flatten.filter(x =>
//////      x.keySet.contains(Wt.Str) ||
//////      x.keySet.contains(Wt.Dex) ||
//////      x.keySet.contains(Wt.Int) ||
//////      x.keySet.contains(Wt.Fai) ||
//////      x.keySet.contains(Wt.Arc)
//////    )
//////
//////    Map(ScalingKey -> _weaponTraits.map(_.glorifiedStrSetter))
//////      .filter(x =>
//////        x._1 == Wt.Str.toString ||
//////        x._1 == Wt.Dex.toString ||
//////        x._1 == Wt.Int.toString ||
//////        x._1 == Wt.Fai.toString ||
//////        x._1 == Wt.Arc.toString
//////      )
//////  }
