# Design Document

**Author**: Team11

## 1 Design Considerations

Below are some issues that need to be kept in mind while completing this project. Note, everything in this document is subject to change and will updated as the project evolves. 

### 1.1 Assumptions

* This application is Android only. No other platforms will be supported.
* The application is meant to be run from a single device (i.e. no cloud saving)
* Multiple users can use the same device.

### 1.2 Constraints

* The UI must responsive. 
* The UI should be understandable.
* The application should use a reasonable percentage of system resources (memory, battery, etc.)

### 1.3 System Environment

* To run this application, the user's device will have a minimum OS requirement of Jelly Bean (4.1 onward)
* To run this application, the user's device will require a device with a minimum of 5 MB

## 2 Architectural Design

The following two diagrams give high level overviews of the system's components. Both follow standard UML conventions. 

### 2.1 Component Diagram

![Component Diagram](../Design-Team/Resources/component-diagram.png)

### 2.2 Deployment Diagram

![Deployment Diagram](../Design-Team/Resources/deployment-diagram.png)

## 3 Low-Level Design

This section gives a lower-level look into the system's structure. More diagrams will be added as the components become more clearly implemented. 

### 3.1 Class Diagram

![Class Diagram](../Design-Team/design-team.png)

## 4 User Interface Design

Here are some rough drafts of the GUI for the 'Wheel of Fortune' Team11 application. The following images are by no means final and are subject to change. 

##### Login and Main Menu
Create Player | Player Login | GuessIt Main Menu
:------------:|:------------:|:-----------------:
<img src="../Design-Team/Resources/Beta-Mockups/login.png" width="180"> | <img src="../Design-Team/Resources/Beta-Mockups/create_player.png" width="180"> | <img src="../Design-Team/Resources/Beta-Mockups/play_guessit.png" width="180">

##### Puzzles
Create Puzzle | Play Puzzle | Win Puzzle
:------------:|:-----------:|:-----------:
<img src="../Design-Team/Resources/Beta-Mockups/create_puzzle.png" width="180"> | <img src="../Design-Team/Resources/Beta-Mockups/play_puzzle.png" width="180"> | <img src="../Design-Team/Resources/Beta-Mockups/won_puzzle.png" width="180">

##### Tournaments
Create Tournament | Play Tournament | Continue Tournament | Win Tournament
:----------------:|:---------------:|:-------------------:|:---------------:
<img src="../Design-Team/Resources/Beta-Mockups/create_tournament.png" width="180"> | <img src="../Design-Team/Resources/Beta-Mockups/play_tournament.png" width="180"> |<img src="../Design-Team/Resources/Beta-Mockups/select_continue_tournament.png" width="180"> | <img src="../Design-Team/Resources/Beta-Mockups/won_tournament.png" width="180">

##### Statistics
Puzzle Statistics for Player | Puzzle Statistics for Puzzle | Tournament Statistics for Player | Tournament Statistics for Tournament
:-------------------------------:|:------------------------------------:|:------------------------------------:|:------------------------------------:
<img src="../Design-Team/Resources/Beta-Mockups/player_puzzle_stats.png" width="180"> | <img src="../Design-Team/Resources/Beta-Mockups/puzzle_stats.png" width="180"> | <img src="../Design-Team/Resources/Beta-Mockups/player_tournament_stats.png" width="180"> | <img src="../Design-Team/Resources/Beta-Mockups/tournament_stats.png" width="180">
