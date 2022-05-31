package org.grauulzz.scrapper

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model.Document

object Main extends App:
  // Browser implementations are the entrypoint for obtaining Document instances
  // they implement get, post, parseFile and parseString methods for retrieving documents from different sources
  // JsoupBrowser is a browser implementation for Jsoup library (jsoup is a Java HTML parser)
  val browser = JsoupBrowser()
  val PatchNotesDoc: Document =
    browser.get("https://eldenring.wiki.fextralife.com/Patch+Notes")
