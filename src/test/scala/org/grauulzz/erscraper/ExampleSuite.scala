package org.grauulzz.erscraper

import org.grauulzz.erscraper.TestSuite

final class ExampleSuite extends TestSuite {
  test("hello world") {
    val startTime = System.nanoTime()
    println(s"${Console.GREEN}<er>-<scraper>-<exe>-<time>-<${System.nanoTime() - startTime}>-<ns>")
    println(Console.RESET)
    1 `shouldBe` 1
  }
}
