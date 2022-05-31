## Elden Ring Update Scraper (WIP)

- Standalone Command Line Application for elden ring weapon updates
- This tool aims to automate the process of checking elden ring update info  

## CLI  
> - Web scrapper : [scala-scrapper](https://index.scala-lang.org/ruippeixotog/scala-scraper)
> - Micronaut CLI : [picocli, http, runtime](https://micronaut-projects.github.io/micronaut-picocli/)
> - Graalvm : [nativeimage](https://www.graalvm.org/22.0/reference-manual/native-image/)

#### Example response of [Grafted Blade GreatSword](https://eldenring.wiki.fextralife.com/Grafted+Blade+Greatsword) buffed in recent patch

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

Comparison data (ie... buffed and/or nurfed data) is scraped from an archive version of the weapon entry, then referenced against the current "Grafted Blade Greatsword" entry


## Project Notes:
1. Input search term fires CLI "search" command  
2. Web scrapper parses the Elden Ring Wiki Patch Notes page
3. If the scrapper finds mention of the weapon name, a new scrapper mines the current info of the weapon's attributes or else throws an error
4. Post-patch and Pre-patch scrappers gather data of current and previous weapon attributes
5. Compare post-patch and pre-patch data
6. Calculate nerfed and buffed entries with the data
7. Client Response with JSON output outlined above
