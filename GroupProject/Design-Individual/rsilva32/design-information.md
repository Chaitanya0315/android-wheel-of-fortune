## Design Information

---

#### **Requirement 1)** - *When starting the application, a user may choose to either create a new player or log in.  For simplicity in designing the application, you should consider the application to run locally; that is, only players on the same device will be able to share information (e.g., puzzles, tournaments, scores). In addition, you do not need to consider authentication or security requirements; that is, a (unique) username will be sufficient for logging in.*


* `I created a PlayGame class which allows the User to enter into the game by either Login or creating a new Player with a unique username. Login and creating a new Player operations use an Association Class(Utilities) which is a list of all the players registerd in the game(`**`playerList:Player[0..*]`**`).`

* `Player Class(Represents Players in the Game) has `**`userName:String`**` attribute as it's unique between Players and a password is not included as authentication is not supported. We have only one PlayGame Class in the Game while zero or many Players can coexist in the, So the cardinality is set accordingly(1 to 0..*) between PlayGame and Player classes.`


* **`Utilities Class depicts the PlayerList/Database Operations to make sure no two Players have the same Username during creating a Player and to load the player during Login Operation'**



---

#### **Requirement 2)** - *After logging in, the application shall allow players to (1) create a puzzle phrase, (2) solve a random puzzle, (3) create a tournament, (4) join or continue a tournament, and (5) view the puzzle statistics.*


1. `create a puzzle phrase - createPuzzle Association between Player and Puzzle is added(This handle is of type `**`createdPuzzleList:GUID[0..*])`**`. Each Player can solve zero or many Puzzles and to reflect that(cardinality is set to 1 - 0..*). `**`createPuzzle(phrase,maxWrongGuesses): GUID`**` in Player Class achives this task.`

2. `solve a random puzzle - solvePuzzle Association between Player and Puzzle is added. Each Player can solve zero or many Puzzles while a puzzle can be solved by zero or many Players(Cardinality is 0..* to 0..*). `**`solveRandomPuzzle()`**` Operation in Player class achieves this operation and `**`solvedPuzzleList:HashMap<GUID,Integer>`**` in Player Class keeps track of the solved Puzzles with respective Prize values for each spefific Player`

3. `create a tournament - createtournament Association between Player and Tournament is added(This handle is of type `**`createdTournamentList:GUID[0..*])`**`. Each Player can create zero or many Tournaments(cardinality is one to 0 or many). `**`createTournament(name,GUID[1..5]):boolean`**` operation in Player Class achive this Operation`

4. `join or continue a tournament - incompleteTournament Association between Player and Puzzle is added(This handle is of type `**`incompleteTournamentList:GUID[0..*]`**`). Each Player may have zero or many Puzzles incomplete and to reflect that proper cardinality is set. `**`continueTournament()`**` Operation in Player Class achieves this Task.`

* **`GUIDs are used to identify each Puzzle and each Tournament uniquely`**


---


#### **Requirement 3)** - *The application shall maintain an underlying database to save persistent information across runs (e.g., players, puzzles, statistics, tournament information).*

* `Database is not specifically represented in the UML Diagram as it's used only to store and Retirieve Information. But Utilities Association Class represents player list which depicts the abstract behaviour of the atabse`

---



#### **Requirement 4)** - *When creating a new player, a user will: Enter the player’s first name. Enter the player’s last name. Enter the player’s desired username.Enter the player’s email.Submit the information.Either receive a confirmation that the information is saved and return to the menu or receive an error if the username is already taken on that device and be offered the option to pick a different username. A player cannot be edited or deleted after a successful save.*

* `Player Class consists of state variables: `**`firstName:String`**`, `**`lastName:String`**`, `**`userName:String`**` and `**`email:String`**`. Utilities Association class provides the list of all the players system has(`**`playerList`**`) which is used to determine if the userName User provides is already exist or not. `**`createNewPlayer(firstName,lastName,userName,email): boolean`**` and `**`logIn(userName) : boolean`**` Operations in PlayGame Class achieve these tasks.`

---



#### **Requirement 5)** - *To create a puzzle, the player will:Enter a phrase. Enter the maximum number of allowed wrong guesses a user can make before losing the game, between 0 and 10. Save and view the returned unique identifier for the puzzle. The puzzle may not be further edited after this point.*

* `I added a `**`phrase:String`**` and `**`maxWrongGuesses: int = 1..9`**` attributes into the Puzzle Class of UML Class Diagram to represent these constraints. Further, I created an association between Player and Puzzle(Type of which is `**`createdPuzzleList:Map<Player,GUID>`**`) with the cardinality 1 to zero or many as a each Player can create as many Puzzles or choose not to create any Puzzle.`


---


#### **Requirement 6)** - *When a player starts solving a puzzle, whether selected randomly or belonging to a tournament, the game will:Display the puzzle phrase, where (1) all non-alphabetic characters (e.g., numbers or punctuation) are shown, and (2) regular letters are replaced by blanks. The game should also display a list of all letters not yet chosen, the total prize, with an initial value of $0, and the remaining number of allowed wrong guesses, initialized to the maximum number of allowed wrong guesses chosen by the puzzle creator (see above).Allow the player to choose, at every turn, whether to guess a consonant, buy a vowel, or solve the puzzle.Guessing a consonant will show the player a randomly chosen prize value that is a multiple of $100 and is between $100 and $1000. If the guess is correct (i.e., the consonant is in the puzzle), all the occurrences of the consonant in the puzzle will be revealed, and the total prize will be increased by the prize value times the number of such occurrences.  Buying a vowel will cost $300 of the player’s total prize and will result in revealing all instances of that vowel in the puzzle.If a vowel or a consonant are guessed incorrectly (i.e., the guessed letter is not present in the puzzle), the remaining number of allowed wrong guesses is decremented. If the number goes below zero, the player gets a prize of $0 for that puzzle, and the game ends.If a player selects to solve the puzzle and is successful, he/she will score $1000 for each letter not yet revealed, and his/her total prize will be recorded and associated to that puzzle and player. Conversely, if a player tries to solve the puzzle and is unsuccessful, he/she gets a prize of $0 for that puzzle, and the game ends.*

* `solvePuzzle Association between Player and Puzzle(Type of which is `**`solvedPuzzleList:HashMap<GUID,Integer>`**`). Prize Class which represents the Price Calculation related Operations and once a puzzle solving is completed the final price value generated by the Prize Class updates the `**`prizeValue:int`**` attribute of the Puzzle Class. Cardinality between Prize Class and Puzzle Class is one to one as one Prize Class can be utilized by one Puzzle Class.`

* `createPuzzle Association (Typeof which is `**`createdPuzzleList:List<GUID>`**`) keeps track of created Puzzles by each Player`

* **`guessConsonent(consonent:char):boolean`**`, `**`buyVowel(vowel:char):boolean`**` and `**`solvePuzzle():boolean`**` operations in Player realize possible options in solving a puzzle`

* **`init()`**` operation inside Puzzle Class handles the constraints to hide letters and uncover all the non-alphabetic characters at the start of 'solving the Puzzle'.` 

* **`generateRandomPrize():int[100..1000]`**` inside Prize Class will generate the random prize for guessing a consonent operation while `**`isValidToBuyVowel():boolean`**` operation in the same class is used to check if the Player's current prize is more than ot equal to $300 so that the Player is allowed to execute buy a vowel operation. `**`updatePrize(prize)`**` inside solvePuzzle Association Class updates the prize value based on the random or set prize value,option Player selects and the number of characctes uncovers by the Player operation.`

* **`Assumption: Player is allowed to buy a vowel only if he/she has more than or equal to $300 by the time the Player chooses that option`**


---


#### **Requirement 7)** - *If a player interrupts a puzzle (e.g., by explicitly choosing to exit the game while solving a puzzle), the game must give the player the option to continue. If the player confirms that he/she wants to exit, he/she gets a prize of $0 for that puzzle, and the game ends.*

* **`interruptPuzzle()`**` operation provides inturrupt operation to the Player`

---



#### **Requirement 8)** - *When a player selects to solve a random puzzle, the game will not choose puzzles he/she has created or already successfully/unsuccessfully played.*

* `To achive this constrint we should keep track of the author of the puzzle and the solved puzzles by the Player. For which solvePuzzle Association (Which represents the attribute `**`createdPuzzleList:HashMap<Player,GUID>`**`) and createPuzzle Association(Which represents `**`createPuzzle:List<GUID>`**`)- here GUID represents the Puzzle identifier.`


---


#### **Requirement 9)** - *When a player selects a tournament for which he/she has already played some of the puzzles, the game will consider these puzzles already completed and preserve the prize the player won (including $0 for puzzles the player quit or did not successfully solve).*

* `To achive this constrint solvePuzzle Association(which represents `**`puzzlePrizeList:HashMap<GUID,Integer>`**`) in Player Class which keeps track of the Puzzles the Player has already played with respective prizes won. By using this attriute the Game can decide which Tournaments the Player can play and to retrieve/use the prize value if he selects a Tournement which has some Puzzles already played either randomly chosen or as part of a tounament.`

---

#### **Requirement 10)** - *To create a tournament, a player will:Select 1 to 5 puzzles from a list of puzzles that they have either created or already played.Enter a name for the tournament.Either receive a confirmation that the tournament has been created and return to the menu or receive an error if the tournament name is already taken and be offered the option to pick a different name.At this point, the tournament will be available for others to join.*


* `Tournament Class has `**`puzzleList: GUID[1..5]`**`, `**`name: String`**` attributes to fullfill this constraint. Player has createdTournament(Which Represents `**`createTournament(name,GUID[1..5]):boolean`**`), completedTournament(Which represents `**`completedTournament:HashMap<GUID,Prize>`**`) Associations to Tournament Class. In addition as depicted in Requirement 8 Player has associations with Puzzle Class to keep track of played and created Puzzles. By using these attributes the Game calculates which Puzzles the Player has already played or created and in turn the can use to create a new Tournament.`

* **`createTournament(name,GUID[1..5]):boolean`**` operation in the Player Class returns a boolean value based on the success of the opearation.`

* `Associations between Player and Tournament createTournament,completedTournament and incompleteTournament has cardinality 1 to 0 or many as each Player can have zero or many created tournaments, zero or many completed tournaments, zero or many incomplete tournaments`
 
* **`GUIDs are generated by the time Tournaments are created and used to uniquely identify them`**


---


#### **Requirement 11)** - *To play a tournament, a player can select whether to join a new tournament or continue a tournament he/she has already joined.If the player opts for joining a new tournament, the game will show the player a list of tournaments that are currently available for him/her to join (i.e., all puzzles not created and not yet played by the player all tournaments that contain (1) no puzzles created by the player and (2) at least one puzzle not yet played by the player). When the player chooses a tournament in the list, the game will display the first puzzle in the tournament.If the player opts for continuing a tournament they have already joined, the game will show the player a list of tournaments they are currently playing that still have puzzles not completed by the player. When the player chooses a tournament in the list, the game will display the first unsolved puzzle in that tournament.After a player completes the last puzzle in a tournament, the tournament ends (for that player), and the game stores the overall tournament prize of the player, which is the sum of the player’s total prizes in all the puzzles in the tournament.*


* `Tournament Class has operations joinTournament(), continueTournament() operations to fullfill these constraints.` 

* `As described in Requirement 8 and Requirement 10 Player has associations to Puzzle Class to keep track of the Puzzles Player created and solved. Using these associations Game can determine which Tournaments the Player can join. Furthermore the Game can retrieve GUIDs of all the Puzzles from the database.` 

* `Furthermore the incompleteTournament Association(which represents `**`incompleteTournament:GUID[0..*]`**`) between Tournament Class and Player Class is used to determine the available Tournaments if the Player selects to play incomplete Games. `**`firstUnplayedPuzzle: GUID`**` attribute in Tournament Class helps to throw the first unsolved Puzzle at the Player while using solvedPuzzle association(solvedPuzzle:HashMap<GUID,Integer>) can be used to detrmine the prizes of already played Puzzles contains in the intended Tournament.`

* `To terminate a Tournament incomplete, endTournament() operation in Tournament Class is used.` 

* `Once a Tournament ends `**`calculateTotalPrize(puzzleList):int`**` Operation in Tournament Class add prizes of all the Puzzles in the Tournament to update the `**`tournamentPrizeList: Map<Tournament,Integer>`**` of the Player which Maps the completed Tournments to Prizes achieved.`


---



#### **Requirement 12)** - *When a player opts to view the puzzle statistics, the game will show four pieces of information: The list of puzzles completed by that player with, for each puzzle, the prize the player won (including $0 for puzzles he/she quit or did not successfully solve). The list of tournaments completed by that player with, for each tournament, the prize the player won. The complete list of puzzles with, for each puzzle, (1) the number of players who played it and (2) the top prize won by a player for that puzzle, together with the username of that player. The complete list of tournaments with, for each tournament, (1) the number of players who completed the tournament and (2) the top prize won by a player for that tournament, together with the username of that player.*


* `Player's attributes puzzlePrizeList:Map<GUID,Integer> and tournamentPrizeList: Map<Tournament,Integer> can be used to generate statisctics for the Tournaments and Puzzles the Player has played. generatePuzzleStatistics()`

* **`generateTournamentStatistics()`**`, `**`generatePlayerStatistics()`**` and `**`generatePlayerStatistics()`**` operations are used to generate statistics.` 

* `On the Player Class `**`viewStatistics(puzzlePrizeList,tournamentPrizeList)`**` method is added to call statistic Operations. Each Puzzle and Tournament has attributes `**`numberOfPlayersPlayed:int`**`, `**`topPrize:int`**`  and `**`topPlayerUsername: String`**` which are used to generate statistics.`



---

#### **Requirement 13)** - *The User Interface (UI) shall be intuitive and responsive.*

* `UI Classes and Operations are not indicated as they are intuitive and responsive.`



---


#### **Requirement 14)** - *The performance of the game should be such that players does not experience any considerable lag between their actions and the response of the game.*

`As the performance of the Game is important I propose some design specifications,`

* `Speed Data Retrieval - For instance, I have HashMaps in each Player to keep track of solved Puzzles with Prize and solved Tournaments with Prize. This way we can retrieve the information we need with O(1) amortized time when calculating statistics for each Player while ensuring high cohesion.`

* `To achieve high cache/memory performance the design minimizes data redundancy - Same information is not stored multiple times in different ways. This way memory can keep many different types of data minimizing memory read/writes to the Disk drives while increasing percentage of cache hits.`

* `Database is configured such that to minimize joins when read information. Use indexes to improve read/write performance.`



---




## Design Considerations
* `In this design attributes relavant to each class are kept seperately rather than usig as shared to enhance `**`cohesion`**
* `Only few Associations are created to minimize data redundancy and to minimize `**`coupling`**
* `This design is `**`Scalable`**` and can be deployed on multiple machines later.`



---

## Author
* Sahan De Silva

---










.
