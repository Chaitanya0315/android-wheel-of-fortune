# Design Information
1.When starting the application, a user may choose to either create a new player or log in.  For simplicity in designing the application, you should consider the application to run locally; that is, only players on the same device will be able to share information (e.g., puzzles, tournaments, scores). In addition, you do not need to consider authentication or security requirements; that is, a (unique) username will be sufficient for logging in.
- I did not consider this requirement because the database will only persist data already present in the design. Also, as specified in the assignment specs, the GUI classes doesn't have to be represented.

2.After logging in, the application shall allow players to (1) create a puzzle phrase, (2) solve a random puzzle, (3) create a tournament, (4) join or continue a tournament, and (5) view the puzzle statistics.
- I did not consider this requirement because the GUI classes doesn't have to be represented.

3.The application shall maintain an underlying database to save persistent information across runs (e.g., players, puzzles, statistics, tournament information).
- I did not consider this requirement because the database will only persist data already present in the design.

4.When creating a new player, a user will: Enter the player’s first name; Enter the player’s last name; Enter the player’s desired username; Enter the player’s email; Submit the information. Either receive a confirmation that the information is saved and return to the menu or receive an error if the username is already taken on that device and be offered the option to pick a different username. A player cannot be edited or deleted after a successful save.
- To realize this requirement, I added to the design a class User with attributes Username (String), FirstName (String), LastName (String) and Email (String). Check if the username has already been used when submitting doesn't require any representation in the UML because it only includes GUI classes and using database already present in the design.

5.When creating a puzzle, the player will: Enter a phrase; Enter the maximum number of allowed wrong guesses a user can make before losing the game, between 0 and 10; Save and view the returned unique identifier for the puzzle. The puzzle may not be further edited after this point.
- To realize this requirement, I added to the design a class Puzzle with attributes Phrase (String), MaxGuess (Int), PlayedBy (String List) and CreatedBy (String), Display (String). The neccessity of PlayedBy, CreatedBy and Display will be explained in later requirements. Save and view the returned unique identifier doesn't require any representation in the UML because it only includes GUI classes and adding data to the database already present in the design.

6a.When a player starts solving a puzzle, whether selected randomly or belonging to a tournament, the game will display the puzzle phrase, where (1) all non-alphabetic characters (e.g., numbers or punctuation) are shown, and (2) regular letters are replaced by blanks. The game should also display a list of all letters not yet chosen, the total prize, with an initial value of $0, and the remaining number of allowed wrong guesses, initialized to the maximum number of allowed wrong guesses chosen by the puzzle creator (see above).
- To realize this requirement, I added an operation called Encode to class Puzzle which takes in a string and return an encoded string ready for play. 
- For each play, I added to the design a class SolvePuzzle with attributes TotalPrice (Int), LetterLeft (String), Puzzle (Int), and User (String).  TotalPrice, LetterLeft and GuessLeft are updated as the game proceeds. I used the identifier attribute to identify the puzzle. I used Username attribute for User to identify player.
- I added a private attribute Display to represent the string to be displayed as characters are being correctly guessed. I also added SetDisplay and GetDisplay as setter and getter functions to access and modify the private attribute. 
- Remaining number of guesses doesn't needs to be represented because it can be derived from the MaxGuess attribute of the Puzzle class.

6b(i).Allow the player to choose, at every turn, whether to guess a consonant, buy a vowel, or solve the puzzle.
Guessing a consonant will show the player a randomly chosen prize value that is a multiple of $100 and is between $100 and $1000. If the guess is correct (i.e., the consonant is in the puzzle), all the occurrences of the consonant in the puzzle will be revealed, and the total prize will be increased by the prize value times the number of such occurrences.  
- To realize this requirement, I added an operation called CheckExist to class Puzzle which takes in a char and return boolean indicating whether the char is in the puzzle string. 
- I added an operation called Reveal to SolvePuzzle class which takes in a char and update the Display attribute to show the chars that are correctly guessed. Nothing is returned.
- The TotalPrice attribute is updated as the game proceeds. However nothing new needs to be represented in the UML diagram. 

6b(ii).Buying a vowel will cost $300 of the player’s total prize and will result in revealing all instances of that vowel in the puzzle.
- Use method Reveal again to show the characters that have been guessed. Nothing is returned.
- The TotalPrice attribute is updated as the game proceeds. However nothing new needs to be represented in the UML diagram. 

6b(iii).If a vowel or a consonant are guessed incorrectly (i.e., the guessed letter is not present in the puzzle), the remaining number of allowed wrong guesses is decremented. If the number goes below zero, the player gets a prize of $0 for that puzzle, and the game ends.
- I did not consider this requirement because it is a terminating criteria. GuessLeft will be updated as the game proceeds. There is no representation for this requiremnt in the UML diagram.

6b(iv).If a player selects to solve the puzzle and is successful, he/she will score $1000 for each letter not yet revealed, and his/her total prize will be recorded and associated to that puzzle and player. Conversely, if a player tries to solve the puzzle and is unsuccessful, he/she gets a prize of $0 for that puzzle, and the game ends.
- I added a method called CorrectGuess to check if the player successfuly solve the puzzle. The method takes in a string and return boolean. The TotalPrice is updated per the requirement. There is no representation for this requiremnt in the UML diagram.

7.If a player interrupts a puzzle (e.g., by explicitly choosing to exit the game while solving a puzzle), the game must give the player the option to continue. If the player confirms that he/she wants to exit, he/she gets a prize of $0 for that puzzle, and the game ends.
- I did not consider this requirement because the it only contains information for the GUI class.

8.When a player selects to solve a random puzzle, the game will not chose puzzles he/she has created or already successfully/unsuccessfully played.
- To realize this requirement, I added attribute PlayedBy (String List) which stores the username of all the players that have played the puzzle. I also added an attribute CreatedBy (String) which records the author of the puzzle. When initiating a game, only the puzzles that meet the requirement will be provided.

8.When a player selects to solve a random puzzle, the game will not chose puzzles he/she has created or already successfully/unsuccessfully played.
- To realize this requirement, I added attribute PlayedBy (String List) which stores the username of all the players that have played the puzzle. I also added an attribute CreatedBy (String) which records the author of the puzzle. When initiating a game, only the puzzles that meet the requirement will be provided.

9.When a player selects a tournament for which he/she has already played some of the puzzles, the game will consider these puzzles already completed and preserve the prize the player won (including $0 for puzzles the player quit or did not successfully solve).
- I did not consider this requirement because it only contains info about identifying puzzles that are played by a certain user. When a user enters a tournament and finishes the puzzles, the PlayedBy attribute for the specific Puzzle will be updated.

10.Create a tournament. A player will select 1 to 5 puzzles from a list of puzzles that they have either created or already played; Enter a name for the tournament; Either receive a confirmation that the tournament has been created and return to the menu or receive an error if the tournament name is already taken and be offered the option to pick a different name.	At this point, the tournament will be available for others to join.
- For each play, I added to the design a class Tournament with attributes Puzzles (Int List) and Name (String).  Check if the name has already been used when submitting doesn't require any representation in the UML because it only includes GUI classes and uses database already present in the design.

11a. To play a tournament, a player can select whether to join a new tournament or continue a tournament he/she has already joined. If the player opts for joining a new tournament, the game will show the player a list of tournaments that are currently available for him/her to join (i.e., all tournaments that contain (1) no puzzles created by the player and (2) at least one puzzle not yet played by the player). When the player chooses a tournament in the list, the game will display the first puzzle in the tournament.
- Because this is a n-m relationship, for each play, I added to the design a class PlayTournament with attributes User (String), Tournament (String) and FinalPrice (Int). I used Name attribute to identify the tournament. I used Username attribute for User to identify player. FinalPrice is the sum of prices of all puzzles in the tournament achieved by the player.
- I did not consider requirement to check the available tournament for the specific user because we only need to query based on the existing attributes CreatedBy and PlayedBy for the Puzzle class. 

11b. If the player opts for continuing a tournament they have already joined, the game will show the player a list of tournaments they are currently playing that still have puzzles not completed by the player. When the player chooses a tournament in the list, the game will display the first unsolved puzzle in that tournament.
- I DIDN't create Tournaments(String List) attribute to the User class because this is duplicating information with the Users attribute in the Tournament class. And this can lead to inconsistency. We can realize the requirement to check what tournaments the user is currently playing with query using the Users attribute for Tournament.


11c. After a player completes the last puzzle in a tournament, the tournament ends (for that player), and the game stores the overall tournament prize of the player, which is the sum of the player’s total prizes in all the puzzles in the tournament. 
- I did not consider this requirement because it only includes operations on the database and attributes already establish previously. We only need to add to the FinalPrice attribute everytime a SolvePuzzle is completed. And display the FinalPrice in the end.

12a. When a player opts to view the puzzle statistics, the game will show: The list of puzzles completed by that player with, for each puzzle, the prize the player won (including $0 for puzzles he/she quit or did not successfully solve).
- I did not consider this requirement because it only includes operations on the database and attributes already establish previously. We only need to go through each SolvePuzzle instance and display the TotalPrice attribute for the ones that are completed by the specific user.

12b. The list of tournaments completed by that player with, for each tournament, the prize the player won.
- I did not consider this requirement because it only includes operations on the database and attributes already establish previously. We only need to go through each PlayTournament instance and display the FinalPrice attribute for the ones that are played by the specific user.

12c. The complete list of puzzles with, for each puzzle, (1) the number of players who played it and (2) the top prize won by a player for that puzzle, together with the username of that player.
- To realize this requirement, I added attribute BestScore and BestPlayer. These two attributes are updated everytime a new player completes the puzzle. The number of players who have played the puzzle can be obtained by calculating the length of the PlayedBy attribute.

12d. The complete list of tournaments with, for each tournament, (1) the number of players who completed the tournament and (2) the top prize won by a player for that tournament, together with the username of that player.
- To realize this requirement, I added attribute BestScore and BestPlayer. These two attributes are updated everytime a new player completes the tournament. The number of players who have played the tournament can be obtained by calculating the length of the CompleteBy attribute.

13.The User Interface (UI) shall be intuitive and responsive.
- Understood.

14.The performance of the game should be such that players does not experience any considerable lag between their actions and the response of the game.
- This requirement is realized by optimizing the design of the system, which I've demonstrated under each specific operation. However, we need to sacrafaci complexity for so that the system design doesn't present inconsistency problems. For example, I avoided saving a list of users that have played a specific puzzle, and also a list of puzzles a specific user had completed, although this will be faster since we have 1 less query to conduct.


































[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
