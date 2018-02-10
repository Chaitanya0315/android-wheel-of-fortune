# Software Design
1. The user class has the ability to create OR login within its operation
2. After Logging in  
 1. is handled by the operation between _User_ and _Puzzle_ with **createPuzzle**
 2. is handled by the operation between _User_ and _Puzzle_ with **playPuzzle** that would allow the user to play an appropriate puzzle based on other requirement restrictions
 3. is handled by the operation between _User_ and _Tournament_ with **createTournament**
 4. is handled by the operation between _User_ and _Tournament_ with **playTournament**
 5. is handled by the operation between _User_ and _PuzzleRecord_ with **getPuzzleStatistics**
3. each class _User_ _Puzzle_ _PuzzleRecord_ _Tournament_ _TournamentRecord_ has a DB representation that would be modeled/designed in the DB designed
4. (a-d) creating a player is represented by the _User_.**create()** operation, (e-f) **create()** would return a Boolean signifying success or failure
5. (a-b) is handled by the operation between _User_ and _Puzzle_ with **createPuzzle**, this would create a DB record of the Puzzle (c) **createPuzzle** would return the DB record id, UI would handle the displaying of it and not allowing editing would be handled by NOT providing an update operation in tandem with appropriate DB create only restriction
6. solving a pozzle
 - (a) _PuzzleRecord_.**getPhraseDisplay()** would return the appropriate data to display taking into account the non-alphabetic character and regular letters based upon the current set of _charactersGuessed_ which stores all guessed characters (both consonants and vowels), _prize_ has the currently stored total prize, _wrongGuesses_ contains the current wrong guess count that can be compared to the Puzzles max_guesses, _charactersGuessed_ can be used to determine the remaining available characters, the GUI would use these to display the appropriate info
 - (b) guess a consonant, buy a vowel, and solve the puzzle will be achieved by **guessConsonant**, **guessVowel**, and **solve** respectively in _PuzzleRecord_
     - (i) **guessConsonant** will use the result of **generateGuessPrize** (which will only return values between $100 and $1000 in multiples of $100) and apply the appropriate logic to _charactersGuessed_ and _prize_ for either correct or incorrect guesses (the next call to _PuzzleRecord_.**getPhraseDisplay()** would have the appropriate display)
     - (ii) **guessVowel** will apply the appropriate logic to _charactersGuessed_ and _prize_ for either correct or incorrect guesses with a cost of $300 taken from _prize_ accordingly
     - (iii) **guessConsonant** and **guessVowel** will handle the incorrect guess and call **exit** if the all guesses are used (**exit** will mark the puzzle complete and zero _prize_)
     - (iv) **solve** will compare the guessed solution and apply the appropriate math of $1000 for each letter not yet revealed to the _prize_ total if correct, if not correct **exit** occurs
7. the UI will handle the prompting while _PuzzleRecord_.**exit()** provides the requirement
8. the operation between _User_ and _Puzzle_ with **playPuzzle** will only allow the user to play puzzles returned from **getPlayablePuzzles** which only returns valid puzzles that can be played based on these criteria
9. internally the DB will have records of the _PuzzleRecord_ associated with a _TournamentRecord_ already stored if they have been played preserving the puzzle results across tournaments. When a tournament is played/continued completePuzzles is populated with the corresponding _PuzzleRecord_ from the DB if they exist (previously played puzzles will have an associated puzzlerecord stored in the DB)
10. to create a tournament
 - (a) the association between _Tournament_ and _Puzzle_ shows a tournament can have only 1 to 5 puzzles, restricting which are selectable can be accomplished using a combination of created and played that can be obtained from **getOwnedPuzzles** and **getPlayedPuzzles** respectively
 - (a-b) are handled with **createTournament**
 - (c) **createTournament** would return success or failure as a boolean, GUI would handle the prompting
11. play a tournament
 - (a) **getPlayableTournaments** handles the filtering of playable tournaments
 - (b) **getPlayableTournaments** handles the filtering of played tournaments
 - (c) _TournamentRecord_.**exit** will complete the tournament, total prize is calculated from the completePuzzles records
12. puzzle statics
 - (a) modeled with **getPlayedPuzzles** as it returns the list of all completed (whether success or not) _PuzzleRecord_'s for the user
 - (b) modeled with **getPlayedTournaments** as it returns the list of all completed (whether success or not) _TournamentRecord_'s for the user
 - (c) can be modeled by correlating the data from all DB records for _Puzzle_ with all the records in _PuzzleRecord_ using **retriaveAll** as the DB contains the complete list of puzzles and the records contain the information needed to calculate the number of players who played it and the top prize winner.
 - (d) can be modeled by correlating the data from all DB records for _Tournament_ with all the records in _TournamentRecord_ using **retriaveAll** as the DB contains the complete list of tournaments and the records contain the information needed to calculate the number of players who played it and the top prize winner.
13. not modeled in the Class Diagram as requirement explicitly excluded UI
14. not modeled in the Class Diagram
