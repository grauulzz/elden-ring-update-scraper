package org.grauulzz.erscraper.weapon
import scala.reflect.ClassManifestFactory.Any

class Weapon {

}

// This Attribute Trait interface is used to define the attributes of a weapon
// It is overkill at the moment, but once I create support for more in game items (ie.. armor, talismans, etc)
// the ability to override the default behavior of item attributes will be needed
sealed trait Attr
case object Phy extends Attr
case object Mag extends Attr
case object Fire extends Attr
case object Ligt extends Attr
case object Holy extends Attr
case object Crit extends Attr





