# Test Plan

**Author**: Team11

## 1 Testing Strategy

### 1.1 Overall strategy

Our testing strategy will be as follows:
* Classes should be developed in such a manner that unit tests CAN (and should) be written. This means striving for low dependency across classes and creating modular interfaces. Any classes that can be unit tested will be. Unit tests should be automatable.
* Collection classes or groups of classes that compose specific subsystems will be subject to integration tests. Integration tests will not be implemented for purely contrived subsystems. This means that we will primarily focust on testing subsystems that will exist in the 'Wheel of Fortune' application and no more. Integration tests should be automatable. 
* We have decided to neglect writing a full system test in an automative form. All full system level tests will be done in the manual QA testing process. This does not mean that these tests will not be recorded. Specific step by step instructions will be provided for testing specific use cases. 

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

Test cases are linked below.

| Purpose | Steps | Expected Results | Actual Results | Pass/Fail |
| ------ | ------ | ------ | ------ | ------ |
| Test if a non unique username can be added when a new user signs up| sign up for the first user with user name "XXX"; sign up for the second user with the same username "XXX" | Error message | --- | --- |
| Test if a non unique tournament name can be added when a new new tournament is being created| create a tournament with  name "XXX"; create the second tournament with the same name "XXX" | Error message | --- | --- |
| Test if login function works correctly| create a user with username "XXX"; login as the just just created | Display UI with correct options for player to choose from | --- | --- |
| Test if the system allows creating a player with missing user input | leave one of the required input box blank | error message and submit is non-clickable | --- | --- |
| Test if the system allows creating a puzzle with missing user input | leave one of the required input box blank | error message and submit is non-clickable | --- | --- |
| Test if the system allows creating a puzzle with negative or greater than 10 max allowable | input negative or numbers greater than 10 in the max allowable guesses input box | error message and submit is non-clickable | --- | --- |
| Test if the system allows creating a tournament with missing user input | leave one of the required input box blank | error message and submit is non-clickable | --- | --- |
| Test if the system will select a puzzle already played by the user| create 2 puzzles; complete one puzzle; select play random puzzle 10 times | the puzzle already played shall not be selected| --- | --- |
| Test if the system will select a puzzle already played by the user in a tournament| create a tournament with 5 puzzles and complete all of them; create 6th puzzle; select play random puzzle 10 times | the 6th puzzle shall always be selected| --- | --- |
| Test if the guess a consonant function acts correctly| select guess a consonant; make a guess | only consonant are allowed in the guess input; correct guess will increase the prize value by $100 * number of occurrences and reveal correct occurrences; wrong guess will deduct the remaining wrong guesses by 1| --- | --- |
| Test if the guess a vowel function acts correctly| select buy a vowel; make a guess | the total prize is deducted by $300 upon selection; correct guess will reveal the correct occurrences; wrong guess will deduct the remaining wrong guesses by 1| --- | --- |
| Test if solve the puzzle function acts correctly| select solve a puzzle; make a guess | all remaining blanks has to be filled; correct guess will increase the prize value by $1000 for each letter not yet revealed; wrong guess will receive $0 for the puzzle; ends the game| --- | --- |
| Test if the puzzle data has been added into the database| guess a puzzle correctly; select view stats in the main menu | the puzzle just played shall display the correct prize as expected| --- | --- |
| Test if the exit/continue function acts correctly| start a puzzle; select exit; select continue; select exit; select leave | confirmation window pops up when selecting exit for the first time; continue button available for user and takes user back to the game; return to the main menu when selecting exit again before clicking on the leave button on the confirmation window| --- | --- |
| Test if the database records the puzzle stats correctly if the player interrupt and exits the game| After last test click on view stats | puzzle shall be stored in the database as already being played by the user; prize for the puzzle just played shall be the prize shown when the player left the game| --- | --- |
| Test if joining an invalid tournament is possible| Create 5 puzzles and 1st tournament; logout and login as 2nd user; create 5 different puzzles and 2nd tournament; logout and log back in as the 1st user; select join a new tournament | tournament created by the 2nd user shall always be displayed| --- | --- |
| Test if joining an invalid tournament is possible| Create 5 puzzles and 3rd tournament; logout and login as 2nd user; complete the first tournament; select join random tournament again | the 3rd tournament shall always be displayed| --- | --- |
| Test if the correct stats have been added into the database after a tournament is completed | After the last test is completed; return to main menu and select view stats | stats for the 1st tournament shall be correctly displayed| --- | --- |
| Test if continue an tournament functions correctly| 2nd user starts 3rd tournament; quit after completing the 1st puzzle; select join an existing tournament; create the 4th tournament | the 3rd tournament shall always be displayed| --- | --- |
| Test if top prize player for each puzzle | create 3rd user; complete 1st tournament with better prize on 4 puzzles and total prize than 2nd user; return to main menu and display stats| puzzle 1-4 shall show 3rd user as top player and puzzle 5 should show 2nd user; tournament shall show 3rd user as the top player| --- | --- |

[076fe412]: https://github.gatech.edu/gt-omscs-se-2018spring/6300Spring18Team11/issues "GitHub Issues"
