Yuchen Cao's Readme.txt

# CS611-Assignment 1
## Tic Tac Toe and other variants
---------------------------------------------------------------------------
Yuchen Cao
caoyc@bu.edu
U51424608

## Files
---------------------------------------------------------------------------

There is only one file called BoardGames.java.

BoardGames.java: There are 9 different classes and an interface in this file.

Interface:
Start_Board_Game: Used for start a board game.

Class:
1. BoardGames.class
It's the main class of the program. It is the class where the program starts.

2. Menu.class
This class is used to generate a menu for user's selection: play Tic-Tac-Toe, play Order and Chaos or exit. It contains two kinds of menu: main menu and submenu. 

3. Play_BoardGames.class
Contains two games, Tic-Tac-Toe and Order and Chaos, for starting a game.

4. Board.class
Used for generating the game board, resetting the game board, setting the board size and outputting the board.

5. Piece.class
Used for generating the pieces, resetting the positions of pieces and recording the positions of various chess pieces on the board.

6. Player.class
Used for generating the players, resetting the score of each player and recording the score of each player.

7. Team.class
Used for generating the teams, setting the size of team, resetting the score of each player in each team, recording the score of each player in each team and calculating the score of each team.

8. Round.class
Used for generating the rounds, resetting the round counter, recording the number of rounds and getting the actions of each user in each round.

9. Rules.class
Used for generating the rule of each board game, checking whether the player's move is valid and checking whether the game is over at the end of each round.


## Notes
---------------------------------------------------------------------------
1. Considering that every board game needs to start the game, I designed an interface for starting the game.
2. A loop was implemented wherever user input was required. This ensures that the user is prompted to re-enter their input in case of an error, until the enter value is right, thereby ensuring the accuracy of user's input.
3. I designed a "Team" class, enabling the game to have the option of team-based play (using Tic-Tac-Toe as an example).
4. I created what I believe to be a fantastic board and pieces. While I didn't use GUI, they are distinguishable, which enhances the playability of the game.


## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "BoardGames" after unzipping the files.
2. Open "BoardGames" folder and continue to navigate to the directory "src".
2. Run the "BoardGames.java" in "src" folder by following steps:
javac *.java -d bin
java -cp ./bin BoardGames


## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

Output:
Welcome to BoardGames!!!

===============BoardGames===============
	1. Start a new Tic Tac Toe game
	2. Start a new Order and Chaos game
	3. Exit
Please enter your choice(1-3)
Input:
1
Output:

Welcome to Tic Tac Toe Game
Do you want to have a team competition?(y/n)
Input:
y
Output:

How many rows and columns do you want for Tic Tac Toe Game?
Only enter a number between 3 and 6, as the board is a square
Input:
3
Output:
	|	|
------------------------
	|	|
------------------------
	|	|

How many teams do you want for TicTacToe?
For this game, only enter 2
Input:
2
Output:

How many players do you want in a team for TicTacToe?
Please enter 1-3 
Input:
3
Output:
Number of teams is:2
Number of players in a team is:3

Which player from Team 1 is participating in this match?
Please enter a number less than 4 and more than 0
Input:
7
Output:


Please enter a number less than 4 and more than 0
Input:
3
Output:

Which player from Team 2 is participating in this match?
Please enter a number less than 4 and more than 0
Input:
1
Output:
	|	|
------------------------
	|	|
------------------------
	|	|

Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Your move is (2,2)


	Round 1
	|	|
------------------------
	|O	|
------------------------
	|	|

Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
1  
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Your move is (1,2)


	Round 2
	|X	|
------------------------
	|O	|
------------------------
	|	|

Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Your move is (1,1)


	Round 3
O	|X	|
------------------------
	|O	|
------------------------
	|	|

Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
3
Output:
Your move is (3,3)


	Round 4
O	|X	|
------------------------
	|O	|
------------------------
	|	|X

Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
3
Output:
This position already has a piece
Please choose a different position

Player 1, please enter the position you want
Please enter row(a number from 1 to 3): 
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Your move is (3,1)


	Round 5
O	|X	|
------------------------
	|O	|
------------------------
O	|	|X

Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Your move is (2,1)


	Round 6
O	|X	|
------------------------
X	|O	|
------------------------
O	|	|X

Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
3
Output:
Your move is (1,3)


	Round 7
O	|X	|O
------------------------
X	|O	|
------------------------
O	|	|X

Player 1 wins!!!


1. Continue
2. Exit
Please enter your choice(1-2)
Input:
2
Output:

Game over! The result is:

Team 1: 1 points!
Player1: 0 points!
Player2: 0 points!
Player3: 1 points!

Team 2: 0 points!
Player1: 0 points!
Player2: 0 points!
Player3: 0 points!

===============BoardGames===============
	1. Start a new Tic Tac Toe game
	2. Start a new Order and Chaos game
	3. Exit
Please enter your choice(1-3)
Input:
3
Output:
Are you sure to exit(y/n)?
Input:
y
Output:

See you next time!!!

