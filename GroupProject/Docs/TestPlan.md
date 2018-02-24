# Test Plan

*This is the template for your test plan. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: Robert Dale Johnson III

## 1 Testing Strategy

### 1.1 Overall strategy

*This section should provide details about your unit-, integration-, system-, and regression-testing strategies. In particular, it should discuss which activities you will perform as part of your testing process, and who will perform such activities.*

### 1.2 Test Selection

*Here you should discuss how you are going to select your test cases, that is, which black-box and/or white-box techniques you will use. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

### 1.3 Adequacy Criterion

*Define how you are going to assess the quality of your test cases. Typically, this involves some form of functional or structural coverage. If you plan to use different techniques at different testing levels (e.g., unit and system), you should clarify that.*

### 1.4 Bug Tracking

*Describe how bugs and enhancement requests will be tracked.*
Bugs and enhancement requests will be handled using GitHub Issues for the project ([Issues][076fe412]). We will track any issues found in the app or app enhancement needs here.

### 1.5 Technology

*Describe any testing technology you intend to use or build (e.g., JUnit, Selenium).*
Currently the only planned testing technologies with be JUnit. If the project proves to need additional UI testing Espresso tests will be created.

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
