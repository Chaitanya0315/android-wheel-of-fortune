# Use Case Model
**Author**: Team 11

## 1 Use Case Diagram

![Use Case Diagram](https://github.gatech.edu/gt-omscs-se-2018spring/6300Spring18Team11/blob/dev_GroupProjectD2/GroupProject/Docs/use_case_diagram.png)

## 2 Use Case Descriptions

**SignUp**

* Requirements: A user may choose to register as a new user when starting the Application.
* Pre-conditions: None.
* Post-conditions: The username must be unique, i.e. non-existent in the database.
* Scenarios: When creating a new player, the system will ask the user to input:
   * Enter the player’s first name.
   * Enter the player’s last name.
   * Enter the player’s desired username.
   * Enter the player’s email.
   * Submit the information.
   * Either receive a confirmation that the information is saved and return to the menu or receive an error if the username is already taken on that device and be offered the option to pick a different username.

**Login**

* Requirements: A user may choose to login when starting the Application.
* Pre-conditions: None.
* Post-conditions: The username must exist in the database.
* Scenarios: If the username doesn't exist in the database, an error message will pop up asking for a correct username. After logging in, the application will allow players to:
   * Create new puzzle.
   * Solve a random puzzle.
   * Create a tournament.
   * Join or continue a tournament.
   * View puzzle stats.

**Create Puzzle**

* Requirements: A user can create a new puzzle upon login.
* Pre-conditions: The user is logged in.
* Post-conditions: None.
* Scenarios: To create a puzzle, the system will ask the user to input a phrase and the maximum number of allowed wrong guesses.The system will save and return the unique identifier for the puzzle.

**Solve Puzzle**

* Requirements: A user can solve a new puzzle upon login.
* Pre-conditions: The user is logged in.
* Post-conditions: None.
* Scenarios: When the player is solving the puzzle, the system will:
   * Display the puzzle phrase with all non-alphabetic characters shown, and regular letters replaced with blanks. It should also display a list of all letters not yet chosen, the total prize, with an initial value of $0, and the remaining number of allowed wrong guesses.
   * Allow the player to choose, at every turn, whether to guess a consonant, buy a vowel, or solve the puzzle.
   * If guess a consonant:
     * Reveal all occurrences of consonant if the guess is correct; Increase the the total prize by the prize value times the number of occurrences.
     * If the guess is wrong, decrement the remaining number of guesses by one.
   * If buying a vowel:
     * Deduct $300 from the total price.
     * If the guess is correct, reveal all vowels.
     * If the guess is incorrect, decrement the remaining number of guesses by one.
   * If solving the puzzle:
     * Increase total price by $1000 times each letter not yet revealed if the word is solved.
     * Set total prize to $0 and end the game.  
   * If a player interrupts the puzzle:
     * Continue and return to game.
     * Finish game with total prize of $0.

**Create Tournament**

* Requirements: A user can create a new tournament upon login.
* Pre-conditions: The user is logged in.
* Post-conditions: None.
* Scenarios: To create a tournament:
   * The system will ask the user to input 1-5 puzzle ids, tournament name.The system will save and return the unique identifier for the puzzle.
   * Either receive a confirmation that the tournament has been created and return to the menu or receive an error if the tournament name is already taken and be offered the option to pick a different name.

**Play Tournament**

* Requirements: A user can play a new tournament or join an existing tournament.
* Pre-conditions: The user is logged in.
* Post-conditions: None.
* Scenarios: When the player is playing a tournament, the system will allow user to either play a new tournament or join an existing tournament
   * If play a list of new tournament available:
     * Display the available tournament to the user.
     * Display the first puzzle in the tournament after the user made the selection.
   * If join in an existing tournament:
     * Display a list of tournament that the player has already joined in.
     * Display the first unsolved puzzle.
   * When the last puzzle has been completed, display the tournament prize.

**View Stats**

* Requirements: A user can view statistics of the puzzle and tournament.
* Pre-conditions: The user is logged in.
* Post-conditions: None.
* Scenarios: When the player selects to view stats, four pieces of info are displayed:
  * The list of puzzles completed by that player with the prize the player won.
  * The list of tournaments completed by that player with the prize the player won.
  * The complete list of puzzles with the number of players, and the username of the top prize player.
  *  The complete list of tournament with the number of players, and the username of the top prize player.
