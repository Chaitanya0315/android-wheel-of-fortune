# Test Plan

**Author**: Team11

## 1 Testing Strategy

### 1.1 Overall strategy

Our testing strategy will focus on three main areas: UI Testing, Instrument Testing and Unit Testing as described in detail below.

* Classes should be developed in such a manner that unit tests CAN (and should) be written. This means striving for low dependency across classes and creating modular interfaces. Any classes that can be unit tested will be. Unit tests should be automatable.
* Collection classes or groups of classes that compose specific subsystems will be subject to integration tests. Integration tests will not be implemented for purely contrived subsystems. This means that we will primarily focus on testing subsystems that will exist in the 'Wheel of Fortune' application and no more. Integration tests should be automatable.
* We have decided to neglect writing a full system test in an automative form. All full system level tests will be done in the manual QA testing process. This does not mean that these tests will not be recorded. Specific step by step instructions will be provided for testing specific use cases.
* UI tests are slow in general as we have to load the application on to an emulator but an automated UI test using tools such as Espresso, UI automator will enable us to identify errors that may have not captured through unit tests, system level tests and integration tests.

To some degree, all team members will be responsible for testing different aspects of the project. It is recommended that a different engineer be responsible for testing a newly authored code. This rule does not have to be strictly followed, but should be viewed as a best practice.

### 1.2 Test Selection

Both our unit tests and our integration tests will be written in the 'white' box format. In both cases the developer writing the test case should consider creating workflows (diagrams or otherwise) to visualize which code paths are followed and when. Upon understanding these various code paths, it is the developer's responsibility to get a reasonable amount of code coverage. 100% code coverage in automated testing is an admiral goal, but sometimes a non-functional one.

Our system level test will be done more from the black box perspective. There will be a listing of tests (i.e. use cases) that should be regularly checked with new builds of the project. Additionally the team will be responsible for creating new tests as the project develops. This means testing expected behavior of the application.  If, at any point, the tester discovers a particular workflow that does not produce an expected result (aka a bug), that workflow will be added to the use case listing.

### 1.3 Adequacy Criterion

To verify that the tests we create meet the minimum standard of quality for this application we will use a combination of both functional and structural test coverage. At the unit test level we will keep track of the structural coverage at the minimum. Additionally, the test writer may additionally write functional tests at the unit test level, if the test makes sense.

At the integration test level, we will focus primarily on the functional coverage of the subsystems. Since the unit tests will cover the structural coverage of the code, we do not need to create tests for that at this level. However, if the test writer decides structural coverage is needed at this level as well, adding tests in that domain is satisfactory.

### 1.4 Bug Tracking

Bugs and enhancement requests weree handled using GitHub Issues for the project ([Issues][076fe412]). We tracked all the issues found in the app or app enhancement needs here. The same procedure will be followed for the upcoming delivarables and releases of the Application. Issues we found so far and tracked with the help of 'github issue tracking' are as follows.

|                            Issue                           | Description                                                                                                                                                                                                                                                                                                                                              | Status |
|:----------------------------------------------------------:|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:------:|
| Return button not present                                  | Need a return button in 'create player' UI                                                                                                                                                                                                                                                                                                               | Closed |
| Tournament name should be unique                           | Tournament name should be unique according to requirement 10.C and we have missed it                                                                                                                                                                                                                                                                     | Closed |
| BuyVowel Should Always Cost $300                           | According to AC 6.b.ii buying a vowel should always cost $300. Currently, the application only subtracts $300 from the prize value when the guess is correct.  Reproduction: Launch app -> Login ->  Select Play Puzzle ->  Increase the score enough to have more than $300 ->  Buy a vowel that is incorrect - the prize value will not be decremented | Open   |
| Typo in getPlayableTournamentsForUser method               | Not a big issue. In TournamentManager.java in getPlayableTournamentsForUser there is a typo of one of the local variables.The name is ownedPuzsle instead of ownedPuzzle.Repo:Open TournamentManager.javaNavigate to getPlayableTournamentsForUserSee boolean ownedPuzsle                                                                                | Open   |
| Wrong Error Message when username is blank at registration | When a username is not provided by the user during registration, the error message is "Sorry, that user already exists". Should change to something like "Please enter username".Also, right now we can register as long as we enter username. The project spec implied but didn't say clearly that firstname, lastname, email are required fields.      | Open   |
| Create Tournament Activity Title Misspelled                | Reproduction: Launch appLogin -> Click Create Tournament -> Look at page title                                                                                                                                                                                                                                                                           | Open   |
| Puzzles are shown by their phrase | when puzzles are listed for solve tournament operation they are displayed by phrase itself. where we show the phrase to the player before start solving. I think we would need another string for puzzles as their title so that it could be used to let the player select which puzzle to play.Test steps: create player1 -> Create 5 puzzles and a Tournament by adding them -> logout -> create player 2 -> select play Tournament      | Open   |
| Manual Test - Tournament should consist of 1-5 puzzles                | Right now we can add more than 5 puzzles into a Tournament and according to the requirement 10.a Tournament can have 1-5 puzzles.Test steps: create a player1 -> create 6 puzzles -> create a Tournament and select all 6 puzzles for the tournament with a unique name -> See if it is accepted  | Open   |
| Minimum guesses allowed should be 1  | According to the Requirements minimum guesses allowed are between 0 and 10. The minimum should be 1 and maximum should be 9. Right now we can select 0 and 10 as maximum guesses. It's debatable whether 10 is allowed or not but the minimum should be 1. Test Steps: -> create player1 -> select create puzzle -> select max guesses - 0 allwed -> select 0 -> create player 2 -> select the puzzle create by player1 -> puzzle is displayed and doesn't let the player2 to make any guesses and terminates right away by displaying a message that player2 is lost | Open   |

* Beta Version, 2/1/2018
  * Return button at create player window missing.
  * Wrong error message when username is not provided at registration.
  * Buy vowel should always cost $300
  * Typo getPlayableTournamentsForUser
  * Typo in message when solved puzzle correctly at Play Puzzle

### 1.5 Technology

Currently, the only planned testing technology is JUnit. If the project proves to need additional UI testing Espresso tests will be implemented.

## 2 Test Cases

Our Test Plan consists of both manual and automated Testing. Test cases will be developed as follows for each UseCase.

### * Manual Test Cases

**Login UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail | Testing Technology |
| ------ | ------ | ------ | ------ | ------ | ------ |
| Test How the application behaves when a User signup with a username which already exist in the database| sign up for the first user with user name "XXX"; sign up for the second user with the same username "XXX" | Error message | Signed up successfully with message "Player Added". Error Message "Sorry, that user already Exists" displayed when signing up for the second time with the same username| Pass | Manual Test |
|Test if "DoesUserNameExist" method works correctly| create a user with username "hwang404"; test if method "DoesUserNameExist" returns true when passing in "hwang404" | true | true | Pass | Instrumented Unit Test |
|Test if signup as new user actually updates the database| create a user with username "hwang404"; test if method "getPlayerByUsername" returns null when passing in "hwang404" | not null | not null | Pass | Instrumented Unit Test |
|Test if login function works correctly end to end| create a user with username "XXX"; login as the one just created | Display UI with correct options for player to choose from | Display UI as required | Pass | Manual Test |
| Test if the system allows creating a player with missing user input | leave one of the required input box blank | error message and submit is non-clickable | We'll decide if this is required per project spec | --- | --- |
| Test if the system allows Login as a player with missing user input | leave the required username input box blank | error message and submit is non-clickable | display error message | logged in successfully | failed | Manual Test |

<br />
<br />
<br />

**Create Puzzle UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |  Testing Technology  |
| ------ | ------ | ------ | ------ | ------ | ------ |
| Test if the system allows creating a puzzle with missing user input | leave puzzle input box blank | error message and submit is non-clickable | error message | pass | Manual Test |
| Test if the system allows creating a puzzle with negative or greater than 9 allowable guesses | input negative or numbers greater than 9 in the max allowable guesses input box | only value between 0-10 is available | select from 0 to 10 | pass | Manual Test |

<br />
<br />
<br />

**Create Tournament UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |  Testing Technology  |
| ------ | ------ | ------ | ------ | ------ | ------ |
| Test if the system allows creating a tournament with missing user input | leave one of the required input box blank | error message and submit is non-clickable | --- | --- | ------ |
| Test if the system will select a puzzle already played by the user as a direct play| create 2 puzzles; complete one puzzle; select play random puzzle 10 times | the puzzle already played shall not be selected| --- | --- |
| Test if the system will select a puzzle already played by the user in a tournament| create a tournament with 5 puzzles and complete all of them; create 6th puzzle; select play random puzzle 10 times | the 6th puzzle shall always be selected| --- | --- |
| Test if the system will select a puzzle created by the User| create 6 puzzles where only one of them by the tester; select create Tournament; repeat the test for few times |Tester/User should not see the Puzzle he/she created in each iteration| --- | --- |
| Test How the the application behaves when a duplicate name is used for a Tournament| create a tournament with  name "XXX"; create the second tournament with the same name "XXX" | Error message | --- | --- |

<br />
<br />
<br />

**Play Puzzle UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail | Testing Technology |
| ------ | ------ | ------ | ------ | ------ | ------ |
| Test if the guess a consonant function acts correctly | select guess a consonant; make a guess | only consonant are allowed in the guess input; correct guess will increase the prize value by $100 * number of occurrences and reveal correct occurrences; wrong guess will deduct the remaining wrong guesses by 1| all required conditions met | pass | Manual Test |
| Test if the guess a vowel function acts correctly when the current balance is less than $300| select buy a vowel |A popup window will appear with the error message that the current balance is not enough to buy a vowel| buy a vowel option is greyed out | pass | Manual Test |
| Test if the guess a vowel function acts correctly when the current balance is greater than $300| select buy a vowel; make a guess | the total prize is deducted by $300 upon selection; correct guess will reveal the correct occurrences; wrong guess will deduct the remaining wrong guesses by 1| behave as required | pass | Manual Test |
| Test if solve the puzzle function acts correctly| select solve a puzzle; make a guess | all remaining blanks has to be filled; correct guess will increase the prize value by $1000 for each letter not yet revealed; wrong guess will receive $0 for the puzzle; ends the game| total prize is $0 | passed | Manual Test |
| Test if the exit/continue functions for Puzzles act correctly| start a puzzle; select exit; select continue; select exit; select leave | confirmation window pops up when selecting exit for the first time; continue button available for user and takes user back to the game; return to the main menu when selecting exit again before clicking on the leave button on the confirmation window| as required | pass | Manual Test |
| Test if an already played Puzzle is displayed to the User to play | Create multiple Puzzles fro the Tester's Player account; select solve Random Puzzle option | Tester should not see any Puzzle as a option to play| only valid puzzles selected | pass | Manual Test |

<br />
<br />
<br />


**Create Tournament UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if the system allows creating a tournament with missing user input | leave one of the required input box blank | error message and submit is non-clickable | When the name is not entered or the puzzles are not selected for the Tournament an error message: "Sorry the name cannot be empty and puzzles must be selected" is displayed to the user as expected | Passed |
| Test if the system will select a puzzle already played by the user in a tournament| create player1; create a tournament with 5 puzzles ;create the 6th standalone puzzle; Logout player1;create player2; complete the tournament completed by the player1; select play random puzzle 10 times | the 6th standalone puzzle create by player1 shall always be selected for player1| As expected only the 6th puzzle created by player1 is displayed as the only option for the player2 to play as a random puzzle | Passed |
| Test if the system will select a puzzle created by the User| create player1; create 5 puzzles from player1's account; logout player1; create player2; create a puzzle from player2's account; select create Tournament from player2's account; repeat the test for few times |Player2 should only see the Puzzle he/she created in each iteration as an option to add in a Tournament created| As expected puzzle created by player2 was the only displayed to the player2 in each iteration | Passed |
| Test How the the application behaves when a duplicate name is used for a Tournament| create a tournament with  name "XXX"; create the second tournament with the same name "XXX" | Error message | As expected an error message:"Name already exists!" is displayed to the User| Passed |
| Test if the Tournament can select more than 5 puzzles| create a player1; create 6 puzzles; create a Tournament and select all 6 puzzles for the tournament with a unique name | Error message | It was allowed which is not the expected case and logged an issue on Github issue tracker| Failed |

<br />
<br />
<br />

**Play Tournament UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if joining an invalid tournament is possible|Create 5 puzzles and 1st tournament; logout and login as 2nd user; create 5 different puzzles and 2nd tournament; logout and log back in as the 1st user; select join a new tournament ; Create 5 puzzles and 3rd tournament; logout and login as 2nd user; complete the first tournament; select join random tournament again; repeat join Tournament for 5 times | the 3rd tournament shall always be displayed in each iteration| As expected the third tournament is displayed in each iteration | Passed |
| Test the 'continue a tournament function'| create player1; Create 5 puzzles and 1st tournament; logout and login as 2nd player; create 5 different puzzles and 2nd tournament; logout and log back in as the 1st player; select join a new tournament ; Create 5 puzzles and 3rd tournament; logout and login as 2nd player; complete the first tournament; logout player2 and login player2; select join random tournament again; start 3rd tournament; quit after completing the 1st puzzle; logout player2 and login player2; select continue tournament | the 3rd tournament shall always be displayed to player2| As expected Tournament2 is displayed to player2 | Passed |

<br />
<br />
<br />

**Generate Statistics UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if the Correct statistical data is tracked by the System| create player1; create 5 puzzles and a Tournament; logout; create player2; select play tournament; Complete all the Puzzles in the Tournament; return to main menu and select view Player Puzzle Stats, Player Tournament Stats | stats for the tournament and puzzles should be displayed correctly for the player1| As expected correct statistical data is displayed by the Application for player one on 1-5 puzzles and the Tournament played | Passed |
| Test if the top prized player is correctly detemined for each puzzle | create player1; Create 5 puzzles and 1st tournament; logout and login as 2nd player; create 5 different puzzles and 2nd tournament; logout and log back in as the 1st player; Create 5 puzzles and 3rd tournament; logout and login as 2nd player; complete the first tournament; select join random tournament again where 2nd user starts 3rd tournament and quit after completing the 1st puzzle; create the 4th tournament ; logout and login from player1's account;select join an incomplete Tournament; complete all the Puzzles; create 3rd user; complete 1st tournament with better prize on first to fourth puzzles and total prize than 2nd user; return to main menu and display stats| puzzle 1-4 shall show 3rd user as top player and puzzle 5 should show 2nd user; tournament shall show 3rd user as the top player| As expected Puzzles 1-4 show 3rd user as the top player and olayer3 is the top player for puzzle5. Top player for Tournament1 is player3 | Passed |
| Test if the existing Puzzle Prizes are counted when they appear in a Tournament selected to be played | create Player 1, create 5 Puzzles(Puzzle1-Puzzle5) and a Tournament(Tournament1) which includes the 5 puzzles created; Logout and Login as Player 2; Select Solve Random puzzle and complete Puzzle1 created by player1; Then select Play Tournament and select the Tournament1 created by player1| The puzzle already completed should be displayed in the Tournament as complete with the earned Prize and it should not be allowed to play again as a part of the Tournament play| As expected Puzzle1 is displayed in Tournament1 as complete with the Prize earned and is disabled to play again | Passed |
| Test if the database records the Puzzle/Tournament stats correctly| create player1; create 5 Puzzles and a Tournament; create player2; select play Tournament; complete Puzzle1 and Puzzle2; quit the Tournament play; select Player Puzzle Stat and Player Tournament Stat| Puzzle1 and Puzzle2 shall be stored in the database as already being played by the user with respective prizes earned; Prize for the Tournament should not still be logged as it's still incompleted| As expected prize for the Puzzle1 and Puzzle2 is recorded correctlt by the Databse and the retrieval isdone correctly by the Application. Tournament prize is not logged as it's incomplete | Passed |

<br />
<br />
<br />

### * Automated Test Cases

Automated Instrumentation Test Cases can be found in AndroidTest directory.

**Create Tournament UseCase and Databse subsystem**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if the Tournament Created is saved properly in the Database | Create 5 puzzles and 1st tournament; retrieve tournament name from the saved tournament list with the help of the Tournament ID| Successful retrival of the saved Tournament into the Database | It successfullt retrieved the created tournament from the Database as expected |Passed |
| Test if the Tournaments completed are saved properly as completed ones in the database |create a Player: player1 ; create 5 Puzzles and a Tournament using them;Logout player1;create a Player: player2 ; Solve the Tournament created by player1 and complete it; Retrive the TournamentRecords saved for player2 and tournament solved|  It should return the Tournament solved by player2 and the size of returned list should be 1 |  It reteurned the Tournament solved by player2 and the size of returned list is 1 which was the expected result |  Passed |

<br />
<br />
<br />

**Solve tournament UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if the playable Tournaments are none when no other Player has created Tournaments but the Player intended to play the tournament has one tournament created. |create a Player; create 5 Puzzles and a Tournament using them; Then retrive the playable Tournaments by the player; | It should return an empty list | It returned an empty list as expected |Passed |
| Test if the Tournaments completed are not playable for the same Player |create a Player: player1 ; create 5 Puzzles and a Tournament using them;Logout player1;create a Player: player2 ; Solve the Tournament created by player1 and complete it; Retrive the Tournaments player2 can play further|  It should return an empty list of tournaments |  It returned an empty list of tournaments and it is the expected result | Passed |
| Test the boundary condition what tournament returns when Two Tournaments exist and only one of them created by Player intended to play a Tournament |create a Player: player1 ; create 5 Puzzles and a Tournament using them;Logout player1;create a Player: player2 ; create 5 Puzzles and a Tournament using them;Logout player2; Login player1; Retrive the playable Tournaments; Repeate this test for 10 times|  It should return the Tournament created by player1 in every iteration |  It returned the Tournament created by player1 in every iteration which was the expected result | Passed |
| Test if the Tournament is playable even when that has one or more Puzzles which have already been completed by the Player. |create a Player: player1 ; create 5 Puzzles and a Tournament using them;Logout player1;create a Player: player2 ; Solve a randomly selected Puzzle and complete it; Retrive the playable Tournaments;|  It should return the Tournament created by player1 and the size of returned list should be 1 |  It returned the Tournament created by player1 and the size of returned list was be 1 which was the expected result| Passed |


[076fe412]: https://github.gatech.edu/gt-omscs-se-2018spring/6300Spring18Team11/issues "GitHub Issues"
