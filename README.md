## Elden Ring Update Scraper (WIP)

- Standalone Command Line Application for elden ring weapon updates
- This tool aims to automate the process of checking elden ring update info  

## CLI  
> - Scala [scala-scrapper]
> - Micronaut [picocli, http client, runtime]
> - Graalvm [nativeimage]

#### Example response of "Grafted Blade Greatsword" buffed in recent patch

```
{
  "name" : "Grafted Blade Greatsword",
  "type" : "Colossal Sword",
  "version" : 1.04.1,
  "released" : "April 27th 2022",
  "description" : "Increased the damage of Grafted Blade Greatsword"
  "attributes" : [
      "Attack" : ["Phy" : 162, "Mag" : 0, "Fire" : 0, "Ligt" : 0, "Holy" : 0, "Crit" : 100],
      "Guard" : ["Phy" : 80, "Mag" : 48, "Fire" : 48, "Ligt" : 48, "Holy" : 48, "Boost": 53],
      "Scaling" : ["Str" : "C", "Dex" : "E"],
      "Requires" : ["Str" : 40, "Dex" : 14],
      "Other" : ["Skill" : "Oath of Vengeance", "FP" : 20, "Wgt" : 21.0, "Passive" : null]
  ]
  "buffed" : [
      "Attack" : ["Phy" : "+5"],
      "Guard" : ["Boost" : "+10"]
  ]
  "nerfed" : []
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








