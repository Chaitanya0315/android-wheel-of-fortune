# Design Discussion
## Design 1 (rsilva32)
![rsilva32 design image](../Design-Individual/rsilva32/design.png)
- Beginning of discussion (TBD)
## Design 2 (bhawley3)
![bhawley3 design image](../Design-Individual/bhawley3/design.png)
- Beginning of discussion (TBD)
## Design 3 (rjohnson348)
![rjohnson348 design image](../Design-Individual/rjohnson348/design.png)
- Beginning of discussion (TBD)
## Design 4 (hwang404)
![hwang404 design image](../Design-Individual/hwang404/design.png)
- Beginning of discussion (TBD)
## Team Design
Team Design to come (TBD)
## Summary
The team decided to go with a design that was a mixture/compilation of Design 2 and Design 3. We decided to remove several classes and associated definitions from Design 2 incorporating aspects from Design 3 that handle these removals. Below is a list of the adjustments we made between Design 2 and 3 to come up with our final Team Design.


1. Incorporate Record interface/inheritance from Design 3 into Design 2
2. Remove InProgress classes from Design 2 mirroring Design 3 in which the Record has the completed state flag/marker
3. Remove generateRecord methods and replace with a exit/finalize to indicate completed record
4. Removed Singleton “getInstance” in favor of Dependency Injection Design 2
5. ‘getTournamentViablePuzzlesForUser()’ language does not match the get tournaments for user. Should make these use the same nomenclature
6. Add note to UML for buyVowel to indicate that prize value will decrement by 300
7. Perhaps same thing for ‘‘getTournamentViablePuzzlesForUser’ - what does this mean? (puzzles can’t have been completed or created by the user).
