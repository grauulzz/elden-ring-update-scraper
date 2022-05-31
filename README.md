## Elden Ring Update Scraper (WIP)

- Standalone Command Line Application for elden ring weapon updates
- This tool aims to automate the process of checking elden ring update info  

## CLI  
> - Web scrapper [scala-scrapper](https://index.scala-lang.org/ruippeixotog/scala-scraper)
> - CLI [micronaut: picocli, http, runtime](https://micronaut-projects.github.io/micronaut-picocli/)
> - Graalvm [nativeimage](https://www.graalvm.org/22.0/reference-manual/native-image/)

#### Example response of "Grafted Blade Greatsword" buffed in recent patch

```json
{
  "name": "Grafted Blade GreatSword",
  "type": "Colossal Sword",
  "version": "1.04.1",
  "released": "04-27-2022",
  "attributes": {
    "attack": [
      {
        "phy": 162,
        "mag": 0,
        "fire": 0,
        "ligt": 0,
        "holy": 0,
        "crit": 100
      }
    ],
    "guard": [
      {
        "phy": 80,
        "mag": 48,
        "fire": 48,
        "ligt": 48,
        "holy": 48,
        "boost": 53
      }
    ],
    "scaling": [
      {
        "str": "C",
        "dex": "E"
      }
    ],
    "requires": {
      "str": 40,
      "dex": 14
    },
    "other": [
      {
        "skill": "Oath of Vengence",
        "fp": 20,
        "wgt": 21,
        "passive": "na"
      }
    ]
  },
  "buffed": {
    "attack": [
      {
        "phy": "+5"
      }
    ],
    "guard": [
      {
        "boost": "+10"
      }
    ]
  },
  "nurfed": {}
}
```

Comparison data (ie... buffed and/or nurfed) is scraped from archieved versions of Elden Ring Wiki, "April 21 2022" a few days before the patch, referenced against the current "Grafted Blade Greatsword" entry on Elden Ring Wiki
#### [Grafted Blade Wiki Current Entry](https://eldenring.wiki.fextralife.com/Grafted+Blade+Greatsword)


## Project Notes:
1. Input search term fires CLI "search" command  
2. Web scrapper parses the Elden Ring Wiki Patch Notes page
3. If the scrapper finds mention of the weapon name, a new scrapper mines the current info of the weapon's attributes or else throws an error
4. Post-patch and Pre-patch scrappers gather data of current and previous weapon attributes
5. Compare post-patch and pre-patch data
6. Calculate nerfed and buffed entries with the data
7. Client Response with JSON output outlined above


## Concerns
- scraping the waybackmachine may only find an extremely old entry, or no entry at all.
- the sweet spot should be found between patches, so probably no more than a month old

## Out of scope
- As of right now, I'm only looking at weapons. Armor, Ashes of War, Talismans, Arrows, etc... could be implemented in the future
- When looking through recent updates, I found that sometimes the elden ring devs will make adjustments to weapon attributes like,
"jump attack". Any other damage buff or nurf other than the attributes measurable from what is available
on the elden ring wiki weapon entry will be omitted. 
- Another example is "Casting speed", which appears to be adjusted
for magic type weapons often. Updates to something like casting speed should theoretically increase the dps of a weapon,
however, once again, this would fall out of scope because dps or "Casting speed" is not listed under a weapons attributes by default.
- If it is possible to calculate these values than I might include features like these in the future.








