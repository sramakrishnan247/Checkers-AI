# Checkers-AI

## About
[Checkers](https://en.wikipedia.org/wiki/Draughts) playing agent implemented in Java. The agent uses the Minimax algorithm with [alpha-beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning). 

[![License MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## Motivation

English draughts or American checkers is a popular 2 player game played on an 8x8 checkerboard. AI witnessed one of its earliest success in game playing through checkers when Arthur Samuel, a pioneer in Computer gaming and AI created a self-learning checkers program. This project is motivatated by the history of game playing and AI.

### Features
* Interactive command prompt.
* Multiple difficulty levels
* Move generator function that takes a board configuration and generates all the available moves for the user to choose.
* Evaluation function that use the weighted count of Kings and Pawn
* Alpha beta search for agent moves.

### Requirements 🔧
* Java version 8 or higher.

### Installation 🔌
1. Press the **Fork** button (on the top right corner of the page) to save a copy of this project on your account.

2. Download the repository files (project) from the download section or clone this project by typing in the bash the following command:

       git clone https://github.com/sramakrishnan247/Checkers-AI
3. Import the project using Intellij Ide(or any other Ide or your favorite text editor)
4. Run the application from Game.java :D

### Checkers Rules
1. Follows standard game rules for piece movement and king promotion.
2. If a piece has the option to jump, then it must jump

### Checkerboard Numbering
![Board numbering](https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Draughts_Notation.svg/220px-Draughts_Notation.svg.png)

### Move notation
The prompt will list all the available moves in this format so that the user only needs to choose the required move.
Follows standard checkers notation as follows:
1. Piece movement denoted using "-" for single steps.(Eg 5-6 denotes moving a piece from square "5" to square "6")
2. Caputure is denoted using "x" (Eg. 14 x 23 denotes moving a piece from square "14" to square "23" after capturing the piece on square "18")


## Sample Gameplay:
```
Welcome!
Pick the difficulty!
1.Rookie
2.Intermediate
3.Pro
4.Grandmaster

Pick the difficulty(1-4)!
2

Player color: 2
AI color: 1
Player King: 4
AI King: 3
Light(Player) Pieces: 12,Dark(AI) Pieces: 12

Current Board
0 1 0 1 0 1 0 1 
1 0 1 0 1 0 1 0 
0 1 0 1 0 1 0 1 
0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 
2 0 2 0 2 0 2 0 
0 2 0 2 0 2 0 2 
2 0 2 0 2 0 2 0 

Available Moves: 
1: 21-17
2: 22-17
3: 22-18
4: 23-18
5: 23-19
6: 24-19
7: 24-20
Please choose move number:
2

Player Played last...
Light(Player) Pieces: 12,Dark(AI) Pieces: 12

AI thinking...

Heuristic val(AI is winning if +ve): 0.0
Source: 2,1,  Dest: 3,2 Jump: false
Skipped squares: 
Move notation: 9-14

AI PLAYED LAST...

0 1 0 1 0 1 0 1 
1 0 1 0 1 0 1 0 
0 0 0 1 0 1 0 1 
0 0 1 0 0 0 0 0 
0 2 0 0 0 0 0 0 
2 0 0 0 2 0 2 0 
0 2 0 2 0 2 0 2 
2 0 2 0 2 0 2 0 

Available Moves: 
1: 17-13
2: 23-18
3: 23-19
4: 24-19
5: 24-20
6: 25-22
7: 26-22
Please choose move number:
```
...

```
Player Played last...

Light(Player) Pieces: 1,Dark(AI) Pieces: 8

AI thinking...

Heuristic val(AI is winning if +ve): 9.0
Source: 5,6,  Dest: 7,4 Jump: true
Skipped squares: (6,5),
Move notation: 24x31

AI PLAYED LAST...
0 0 0 0 0 0 0 1 
0 0 0 0 0 0 1 0 
0 0 0 1 0 1 0 1 
0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 
0 0 1 0 0 0 0 0 
0 0 0 0 0 0 0 0 
0 0 3 0 3 0 0 0 

Light(Player) Pieces: 0,Dark(AI) Pieces: 8

Final Board Status
0 0 0 0 0 0 0 1 
0 0 0 0 0 0 1 0 
0 0 0 1 0 1 0 1 
0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 
0 0 1 0 0 0 0 0 
0 0 0 0 0 0 0 0 
0 0 3 0 3 0 0 0 

1
AI WINS!
```



## Thank You!
Please ⭐️ this repo and share it with others!

