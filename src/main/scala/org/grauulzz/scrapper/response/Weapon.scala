package org.grauulzz.scrapper.response

import com.google.gson.{Gson, GsonBuilder, JsonArray, JsonObject}

import scala.collection.mutable
import scala.reflect.ClassManifestFactory.Any

/** Wt stands for "WeaponTrait"
  */
sealed trait Wt {
  var _value: Any = Any
  def value: Any = _value
  def value_=(newVal: Any): Unit = _value = newVal
  def builder: (Wt, Any) = (this, _value)
}

abstract class WtImpl(var traits: List[Wt]) {
  def add(wt: Wt): Unit =
    traits = traits :+ wt
  def addAll(wt: List[Wt]): Unit =
    traits = traits ++ wt
}

//  override def builder: (Wt, Any) = (this, _value)

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

case class AttackTraits(var attackTraits: List[Wt] = List())
    extends WtImpl(attackTraits)
    with Wt {
  def getAttackTraits: Map[String, List[(Wt, Any)]] =
    Map("Attack" -> attackTraits.map(_.builder))
}

case class GuardTraits(var guardTraits: List[Wt] = List()) {
  def getGuardTraits: Map[String, List[(Wt, Any)]] =
    Map("Guard" -> guardTraits.map(_.builder))
}

class WeaponAttribute extends Wt {

  /** "attack" list should consist only of Wt.Phy, Wt.Mag, Wt.Fire, Wt.Ligt,
    * Wt.Holy, Wt.Crit These WeaponTraits(Wt) are none optional and should be
    * including even if the values are 0
    * @return
    *   a map of attack values
    */
  def attack(attackTraits: List[Wt]): Map[String, List[(Wt, Any)]] = Map(
    "Attack" -> attackTraits.map(_.builder)
  )

  /** "guard" list should consist only of Wt.Phy, Wt.Mag, Wt.Fire, Wt.Ligt,
    * Wt.Holy, Wt.Boost These WeaponTraits(Wt) are none optional and should be
    * including even if the values are 0
    * @return
    *   a map of guard values
    */
  def guard(guardTraits: List[Wt]): Map[String, List[(Wt, Any)]] = Map(
    "Guard" -> guardTraits.map(_.builder)
  )

  /** "scaling" list is usually only one, two, or three elements long because no
    * weapon has more than three scaling values in game Possible scaling values
    * are: Wt.Str, Wt.Dex, Wt.Int, Wt.Fai, Wt.Arc However, these are all
    * optional and can be omitted if the weapon does not scale in that
    * particular stat
    *
    * @return
    *   a map of the scaling values, omitting any that are not present
    */
  def scaling(scalingTraits: List[Wt]): Map[String, List[(Wt, Any)]] = Map(
    "Scaling" -> scalingTraits.map(_.builder)
  )

  /** "requires" list will be similar to the scaling list however, with the
    * added Wgt WeaponTrait
    *
    * @return
    *   a map of the requires values, including the Wgt WeaponTrait, while
    *   omitting any that are not present
    */
  def requires(requiresTraits: List[Wt]): Map[String, List[(Wt, Any)]] = Map(
    "Requires" -> requiresTraits.map(_.builder)
  )

  def allAttr(
      attackTraits: List[Wt],
      guardTraits: List[Wt],
      scalingTraits: List[Wt],
      requiresTraits: List[Wt]
  ): Map[String, List[(Wt, Any)]] = {
    List(
      attack(attackTraits),
      guard(guardTraits),
      scaling(scalingTraits),
      requires(requiresTraits)
    ).foldLeft(Map[String, List[(Wt, Any)]]())((acc, map) => acc ++ map)
  }
}

class Weapon(
    var name: String = "",
    var group: String = "",
    var version: String = "",
    var released: String = "",
    var attr: Map[String, List[(Wt, Any)]] = Map()
) {
  var _name: String = name
  var _group: String = group
  var _version: String = version
  var _released: String = released
  var _attributes: Map[String, List[(Wt, Any)]] = attr

  def toJson: String = {
    val json = new mutable.StringBuilder
    json.append("{")
    json.append("\"Name\":\"" + _name + "\",")
    json.append("\"Group\":\"" + _group + "\",")
    json.append("\"Version\":\"" + _version + "\",")
    json.append("\"Released\":\"" + _released + "\",")
    json.append("\"Attributes\":{")
    json.append(
      _attributes.foldLeft("")((acc, map) =>
        acc + "\"" + map._1 + "\":{" + map._2.foldLeft("")((acc2, map2) =>
          acc2 + "\"" + map2._1 + "\":" + map2._2 + ","
        ) + "},"
      )
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
      _attributes.foldLeft("")((acc, map) =>
        acc + "\n\t\t\"" + map._1 + "\":{" + map._2.foldLeft("")((acc2, map2) =>
          acc2 + "\n\t\t\t\"" + map2._1 + "\":" + map2._2 + ","
        ) + "\n\t\t},"
      )
    )
    json.append("\n\t}")
    json.append("\n}")
    json.toString()
  }
}

//  def name_=(newValue: String): Unit = _name = newValue
//  def group_=(newValue: String): Unit = _group = newValue
//  def version_=(newValue: String): Unit = _version = newValue
//  def released_=(newValue: String): Unit = _released = newValue
//  def attributes_=(newValue: Map[String, List[(Wt, Any)]]): Unit = _attributes = newValue
