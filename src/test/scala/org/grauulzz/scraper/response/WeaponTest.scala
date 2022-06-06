package org.grauulzz.scraper.response

import org.grauulzz.scraper.TestSuite
import org.scalatest.funsuite.AnyFunSuite

class WeaponTest extends TestSuite {
  test("Weapon") {
    val weapon: Weapon = new Weapon(
      "Grafted Great Sword",
      "Colossal Sword",
      "1.04.1",
      "April 27th 2022"
    )
    println(weapon)
  }

  test("WeaponAttribute.attackTraits") {
    val attackAttribute = new WeaponAttribute()
    val a1: Wt.Phy.type = Wt.Phy
    val a2: Wt.Mag.type = Wt.Mag
    val a3: Wt.Fire.type = Wt.Fire
    val a4: Wt.Ligt.type = Wt.Ligt
    val a5: Wt.Holy.type = Wt.Holy
    val a6: Wt.Crit.type = Wt.Crit
    a1.value_=(162)
    a2.value_=(0)
    a3.value_=(0)
    a4.value_=(0)
    a5.value_=(0)
    a6.value_=(100)
    val attackTraits: List[Wt] = List(a1, a2, a3, a4, a5, a6)
    val res: Map[String, List[(Wt, Any)]] = attackAttribute.attack(attackTraits)
    println(res)
  }

  test("WeaponAttribute.guardTraits") {
    val guardAttributes = new WeaponAttribute()
    val g1: Wt.Phy.type = Wt.Phy
    val g2: Wt.Mag.type = Wt.Mag
    val g3: Wt.Fire.type = Wt.Fire
    val g4: Wt.Ligt.type = Wt.Ligt
    val g5: Wt.Holy.type = Wt.Holy
    val g6: Wt.Boost.type = Wt.Boost
    g1.value_=(80)
    g2.value_=(48)
    g3.value_=(48)
    g4.value_=(48)
    g5.value_=(48)
    g6.value_=(53)
    val guardTraits: List[Wt] = List(g1, g2, g3, g4, g5, g6)
    val res: Map[String, List[(Wt, Any)]] = guardAttributes.guard(guardTraits)
    println(res)
  }

  test("WeaponAttribute.scalingTraits") {
    val scalingAttributes = new WeaponAttribute()
    val s1: Wt.Str.type = Wt.Str
    val s2: Wt.Dex.type = Wt.Dex
    s1.value_=("C")
    s2.value_=("E")
    val scalingTraits: List[Wt] = List(s1, s2)
    val res: Map[String, List[(Wt, Any)]] =
      scalingAttributes.scaling(scalingTraits)
    println(res)
  }

  test("WeaponAttribute.requiresTraits") {
    val requiresAttributes = new WeaponAttribute()
    val r1: Wt.Str.type = Wt.Str
    val r2: Wt.Dex.type = Wt.Dex
    r1.value_=(40)
    r2.value_=(14)
    val requiresTraits: List[Wt] = List(r1, r2)
    val res: Map[String, List[(Wt, Any)]] =
      requiresAttributes.requires(requiresTraits)
    println(res)
  }

  test("WeaponAttribute.allAttributes") {
    val weaponAttribute = new WeaponAttribute()
    var a1: Wt = Wt.Phy
    var a2: Wt = Wt.Mag
    var a3: Wt = Wt.Fire
    var a4: Wt = Wt.Ligt
    var a5: Wt = Wt.Holy
    var a6: Wt = Wt.Crit
    a1.value_=(162)
    a2.value_=(0)
    a3.value_=(0)
    a4.value_=(0)
    a5.value_=(0)
    a6.value_=(100)
    val attackTraits: List[Wt] = List(a1, a2, a3, a4, a5, a6)

    var a7: Wt = Wt.Boost
    a1.value_=(80)
    a2.value_=(48)
    a3.value_=(48)
    a4.value_=(48)
    a5.value_=(48)
    a7.value_=(53)
    val guardTraits: List[Wt] = List(a1, a2, a3, a4, a5, a7)

    val s1: Wt = Wt.Str
    val s2: Wt = Wt.Dex
    s1.value_=("C")
    s2.value_=("E")
    val scalingTraits: List[Wt] = List(s1, s2)

    val r1: Wt = Wt.Str
    val r2: Wt = Wt.Dex
    r1.value_=(40)
    r2.value_=(14)
    val requiresTraits: List[Wt] = List(r1, r2)

    val all: Map[String, List[(Wt, Any)]] =
      weaponAttribute.allAttr(
        attackTraits,
        guardTraits,
        scalingTraits,
        requiresTraits
      )

    val weapon: Weapon = new Weapon(
      "Grafted Great Sword",
      "Colossal Sword",
      "1.04.1",
      "April 27th 2022",
      all
    )

    println(weapon.toJsonPretty)
  }
}
