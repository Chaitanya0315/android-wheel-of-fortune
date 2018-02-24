# Test Plan

**Author**: Team11

## 1 Testing Strategy

### 1.1 Overall strategy

Our testing strategy will focus on three main areas: UI Testing, Integaryions Testing and Unit Testing as described in detail below.

* Classes should be developed in such a manner that unit tests CAN (and should) be written. This means striving for low dependency across classes and creating modular interfaces. Any classes that can be unit tested will be. Unit tests should be automatable.
* Collection classes or groups of classes that compose specific subsystems will be subject to integration tests. Integration tests will not be implemented for purely contrived subsystems. This means that we will primarily focus on testing subsystems that will exist in the 'Wheel of Fortune' application and no more. Integration tests should be automatable. 
* We have decided to neglect writing a full system test in an automative form. All full system level tests will be done in the manual QA testing process. This does not mean that these tests will not be recorded. Specific step by step instructions will be provided for testing specific use cases. 
* UI tests are slow in general as we have to load the applocation on to an emulator but an automated UI tets using tools such as Espresso, UI automator will enable us to identify errors that may have not captured through unit tests, system level tests and integration tests.

To some degree, all team members will be responsible for testing different aspects of the project. It is recommended that a different engineer be responsible for testing a newly authored code. This rule does not have to be strictly followed, but should be viewed as a best practice. 

### 1.2 Test Selection

Both our unit tests and our integration tests will be written in the 'white' box format. In both cases the developer writing the test case should consider creating workflows (diagrams or otherwise) to visualize which code paths are followed and when. Upon understanding these various code paths, it is the developer's responsibility to get a reasonabls amout of code coverage. 100% code coverage in automated testing is an admiral goal, but sometimes a non-functional one. 

Our system level test will be done more from the black box perspective. There will be a listing of tests (i.e. use cases) that should be regularly checked with new builds of the project. Additionally the team will be responsible for creating new tests as the project develops. This means tesing expected behavior of the application.  If, at any point, the tester discovers a particular workflow that does not produce an expected result (aka a bug), that workflow will be added to the use case listing.

### 1.3 Adequacy Criterion

To verify that the tests we create meet the minimum standard of quality for this application we will use a combination of both functional and structural test coverage. At the unit test level we will keep track of the structual coverage at the minimum. Additionally, the test writer may additionally write functional tests at the unit test level, if the test makes sense. 

At the integration test level, we will focus primarily on the functional coverage of the subsystems. Since the unit tests will cover the structural coverage of the code, we do not need to create tests for that at this level. However, if the test writer decides structural coverage is needed at this level as well, adding tests in that domain is satisfactory. 

### 1.4 Bug Tracking

Bugs and enhancement requests will be handled using GitHub Issues for the project ([Issues][076fe412]). We will track any issues found in the app or app enhancement needs here.

### 1.5 Technology

Currently, the only planned testing technology is JUnit. If the project proves to need additional UI testing Espresso tests will be implemented.

## 2 Test Cases

Test cases will be developed as follows for each subsection.

**Login UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test How the application behaves when a User signsup with a usename which already exist in the databse| sign up for the first user with user name "XXX"; sign up for the second user with the same username "XXX" | Error message | --- | --- |
|Test if login function works correctly end to end| create a user with username "XXX"; login as the just just created | Display UI with correct options for player to choose from | --- | --- |
| Test if the system allows creating a player with missing user input | leave one of the required input box blank | error message and submit is non-clickable | --- | --- |
| Test if the system allows Login as a player with missing user input | leave the required username inputbox blank | error message and submit is non-clickable | --- | --- |

<br />
<br />
<br />

**Create Puzzle UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if the system allows creating a puzzle with missing user input | leave one of the required input box blank | error message and submit is non-clickable | --- | --- |
| Test if the system allows creating a puzzle with negative or greater than 9 allowable guesses | input negative or numbers greater than 9 in the max allowable guesses input box | error message and submit is non-clickable | --- | --- |

<br />
<br />
<br />

**Create Tournament UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if the system allows creating a tournament with missing user input | leave one of the required input box blank | error message and submit is non-clickable | --- | --- |
| Test if the system will select a puzzle already played by the user as a direct play| create 2 puzzles; complete one puzzle; select play random puzzle 10 times | the puzzle already played shall not be selected| --- | --- |
| Test if the system will select a puzzle already played by the user in a tournament| create a tournament with 5 puzzles and complete all of them; create 6th puzzle; select play random puzzle 10 times | the 6th puzzle shall always be selected| --- | --- |
| Test if the system will select a puzzle created by the User| create 6 puzzles where only one of them by the tester; select create Tournament; repeat the test for few times |Tester/User should not see the Puzzle he/she created in each iteration| --- | --- |
| Test How the the application behaves when a duplicate name is used for a Tournament| create a tournament with  name "XXX"; create the second tournament with the same name "XXX" | Error message | --- | --- |

<br />
<br />
<br />

**Solve Puzzle UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if the guess a consonant function acts correctly  when the current balance is greater than $300| select guess a consonant; make a guess | only consonant are allowed in the guess input; correct guess will increase the prize value by $100 * number of occurrences and reveal correct occurrences; wrong guess will deduct the remaining wrong guesses by 1| --- | --- |
| Test if the guess a consonant function acts correctly when the current balance is less than $300| select guess a consonan|A popup window will apprear with the error message that the current balance is not enough to guess a consonant| --- | --- |
| Test if the guess a vowel function acts correctly| select buy a vowel; make a guess | the total prize is deducted by $300 upon selection; correct guess will reveal the correct occurrences; wrong guess will deduct the remaining wrong guesses by 1| --- | --- |
| Test if solve the puzzle function acts correctly| select solve a puzzle; make a guess | all remaining blanks has to be filled; correct guess will increase the prize value by $1000 for each letter not yet revealed; wrong guess will receive $0 for the puzzle; ends the game| --- | --- |
| Test if the exit/continue functions for Puzzles act correctly| start a puzzle; select exit; select continue; select exit; select leave | confirmation window pops up when selecting exit for the first time; continue button available for user and takes user back to the game; return to the main menu when selecting exit again before clicking on the leave button on the confirmation window| --- | --- |
| Test if an already played Puzzle is displayed to the User to play | Create muliple Puzzles fro the Tester's Player account; select solve Random Puzzle option | Tester should not see any Puzzle as a option to play| --- | --- |

<br />
<br />
<br />

**Database Subsystem**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if the database records the puzzle stats correctly when the player interrupt and exits the game| After the last puzzle play click on view stats | puzzle shall be stored in the database as already being played by the user; prize for the puzzle just played shall be the prize shown when the player left the game| --- | --- |
|Test if the database records the puzzle stats correctly when the player solve the puzzle correctly| guess a puzzle correctly; select view stats in the main menu | the puzzle just played shall display the correct prize as expected| --- | --- |

<br />
<br />
<br />

**Play Tournament UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if joining an invalid tournament is possible - Senario 1| Create 5 puzzles and 1st tournament; logout and login as 2nd user; create 5 different puzzles and 2nd tournament; logout and log back in as the 1st user; select join a new tournament | tournament created by the 2nd user shall always be displayed| --- | --- |
| Test if joining an invalid tournament is possible - Senario 2|Create 5 puzzles and 1st tournament; logout and login as 2nd user; create 5 different puzzles and 2nd tournament; logout and log back in as the 1st user; select join a new tournament ; Create 5 puzzles and 3rd tournament; logout and login as 2nd user; complete the first tournament; select join random tournament again | the 3rd tournament shall always be displayed| --- | --- |
| Test the 'continue a tournament function'| Create 5 puzzles and 1st tournament; logout and login as 2nd user; create 5 different puzzles and 2nd tournament; logout and log back in as the 1st user; select join a new tournament ; Create 5 puzzles and 3rd tournament; logout and login as 2nd user; complete the first tournament; select join random tournament again; 2nd user starts 3rd tournament; quit after completing the 1st puzzle; select join an existing tournament; create the 4th tournament | the 3rd tournament shall always be displayed| --- | --- |

<br />
<br />
<br />

**Generate Statistics UseCase**

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if the top prize player is correctly detemined for each puzzle | Create 5 puzzles and 1st tournament; logout and login as 2nd user; create 5 different puzzles and 2nd tournament; logout and log back in as the 1st user; select join a new tournament ; Create 5 puzzles and 3rd tournament; logout and login as 2nd user; complete the first tournament; select join random tournament again; 2nd user starts 3rd tournament; quit after completing the 1st puzzle; select join an existing tournament; create the 4th tournament ; create 3rd user; complete 1st tournament with better prize on 4 puzzles and total prize than 2nd user; return to main menu and display stats| puzzle 1-4 shall show 3rd user as top player and puzzle 5 should show 2nd user; tournament shall show 3rd user as the top player| --- | --- |
| Test if the Correct statistical data is tracked by the System| Complete all the Puzzles in a Tournament; return to main menu and select view stats | stats for the tournament should be correctly displayed| --- | --- |
| Test if the existing Puzzle Prizes are counted when they appear in a Tournament selected to be played | Login as the Player 1, create 5 Puzzles and a Tournament which includes the 5 puzzles created; Logout and Login as Player 1; Select Solve Random puzzle and complete it; Then select Play Tournament; Play all the Puzzles to Complete the Tournament where the Puzzle already played should be completed with a Prize different from what earned when it was played alone; Selct Tournament's Prize Tab| Prize earned for the randome puzzle played is in the Tournament selected and should be counted into the tournament Prize even when that Puzzle is played again in the current Tournament| --- | --- |

[076fe412]: https://github.gatech.edu/gt-omscs-se-2018spring/6300Spring18Team11/issues "GitHub Issues"
