package org.grauulzz.erscraper.response
import org.grauulzz.erscraper.response.TestWeapon.{a1, a2, a3, a4, a5, a6, g1, g2, g3, g4, g5, g6, r1, r2, s1, s2}

object TestWeapon {
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
  val s1: Wt.Str.type = Wt.Str
  val s2: Wt.Dex.type = Wt.Dex
  s1.value_=("C")
  s2.value_=("E")
  val r1: Wt.Str.type = Wt.Str
  val r2: Wt.Dex.type = Wt.Dex
  r1.value_=(40)
  r2.value_=(14)
}

class TestHelpers {

}
