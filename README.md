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
  "platform" : "PlayStation 4 / PlayStation 5 / Xbox One / Xbox Series X|S / Steam"
  "attributes" : [
    {
      "Attack" : ["Phy" : 162, "Mag" : 0, "Fire" : 0, "Ligt" : 0, "Holy" : 0, "Crit" : 100],
      "Guard" : ["Phy" : 80, "Mag" : 48, "Fire" : 48, "Ligt" : 48, "Holy" : 48, "Boost": 53],
      "Scaling" : ["Str" : "C", "Dex" : "E"],
      "Requires" : ["Str" : 40, "Dex" : 14],
      "Other" : ["Skill" : "Oath of Vengeance", "FP" : 20, "Wgt" : 21.0, "Passive" : null]
    }
  ]
  "buffed" : [
    {
      "Attack" : ["Phy" : "+5"],
      "Guard" : ["Boost" : "+10"]
    }
  ]
  "nerfed" : []
}
```

Comparison data (ie... buffed and/or nurfed) is scraped from archieved versions of Elden Ring Wiki, "April 21 2022" a few days before the patch, referenced against the current "Grafted Blade Greatsword" entry on Elden Ring Wiki
#### [Grafted Blade Wiki Current Entry](https://eldenring.wiki.fextralife.com/Grafted+Blade+Greatsword)


## Project Notes:
1. Web scraper needs to parse the Elden Ring Wiki Patch Notes page first
2. Once a weapon name is found, when parsing the text of the patch notes, (probably have to generate a massive json file of Weapon names regex in order to achieve parsed info),
create a new web scraper to parse info from the Wiki and generate a new Json file from that info. This will account for the current version of the weapon
3. Now it's time to scrape the way back machine
4. Get the released date of the patch from the newly created Json file
5. Parse way back machine for an elden ring wiki entry no greater than a month before the current patch
6. Once the before-patch scraper finds an entry, generate the before-patch json file
7. compare the values of the weapon attributes and generate buffed and nerfed data entries
8. Generate post-patch json that includes the wiki entry + nurfed and buffed entries
9. Respond with the post-patch json

## Plan:
1. parse elden ring wiki patch notes
2. generate a list of weapon name mentions in the patch notes
3. for each weapon name in the list, lookup current entry and previous entry (way back machine) of elden ring wiki for that weapon name
4. generate json file as described above

## Concerns
- scraping the waybackmachine may only find an extremely old entry, or no entry at all.
- the sweet spot should be found between patches, so probably no more than a month old

## Out of scope
- when looking through recent updates, I found that sometimes the elden ring devs will make adjustments to lets say
"jump attack" damage. Any other damage buff or nurf other than the attributes measurable from what is available
on the elden ring wiki will be omitted. There is no "jump attack" attribute for a weapon. If it is possible to calculate
this param than I might include it in the future. Another example is "Casting speed", which appears to be adjusted
for magic type weapons often. Updates to something like casting speed should theoretically increase the dps of a weapon,
however, once again, this would fall out of scope because dps is not listed under a weapons attributes by default.

- As of right now, I'm only looking at weapons. Armor, Ashes of War, Talismans, Arrows, etc... could be implemented in the future






