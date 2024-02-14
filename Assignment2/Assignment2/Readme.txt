Yuchen Cao's Readme.txt

# CS611-Assignment 2
## Tic Tac Toe - Take II
---------------------------------------------------------------------------
Yuchen Cao
caoyc@bu.edu
U51424608

## Files
---------------------------------------------------------------------------

There are 16 files: Board.java, BoardGame.java, Game.java, Get_Board_Size.java, Get_user_Answer.java, Menu.java, Order_and_Chaos.java, Piece.java, Play_BoardGames.java, Player.java, Round.java, Rules.java, Print_Board.java, Super_TicTacToe.java, Team.java, TicTacToe.java. Each file contains a class or interface.

1. Board.java: There is a class called Board which implements an interface called Print_Board.
This class is used for generating the game board, resetting the game board, setting the board size and outputting the board.

2. BoardGame.java: There is an abstract class called BoardGame which extends from class Game.
Each board game can extend from this class, it has some basic objects and a function used to start a board game.

3. Game.java: There is an abstract class called Game.
Each game can extend from this class, it has some basic objects and a function used to start a game.

4.Get_Board_Size.java: There is an interface called Get_Board_Size.
The TicTacToe and Super_TicTacToe classes can implement it to use Get_Boardsize() function to get the size of the board for game.

5. Get_user_Answer.java: There is a class called Get_user_Answer.
This class is used to get the answer or choice from user.

6. Menu.java: There is a class called Menu.
This class is used for generating a menu for user's selection: play Tic-Tac-Toe, play Order and Chaos or exit. It contains two kinds of menu: main menu and submenu. 

7. Order_and_Chaos.java: There is a class called Order_and_Chaos which extends from abstract class BoardGame.
This class is used for start an Order_and_Chaos game.

8. Piece.java: There is a class called Board.
This class is used for generating the game board, resetting the game board, setting the board size and outputting the board.

9. Play_BoardGames.java: There is a class called Play_BoardGames.
It's the main class of the program. It is the class where the program starts.

10. Player.java: There is a class called Player.
This class is used for generating the players, resetting the score of each player and recording the score of each player.

11. Round.java: There is a class called Round.
This class is used for generating the rounds, resetting the round counter, recording the number of rounds and getting the actions of each user in each round.

12. Rules.java: There is a class called Rules.
This class is used for generating the rule of each board game, checking whether the player's move is valid and checking whether the game is over at the end of each round.

13. Print_Board.java: There is an interface called Print_Board.
The board class can implement it to use PrintBoard() function to print a board for game.

14. Super_TicTacToe.java: There is a class called Super_TicTacToe which extends from abstract class BoardGame.
This class is used for start a Super_TicTacToe game.

15. Team.java: There is a class called Team.
This class is used for generating the teams, setting the size of team, resetting the score of each player in each team, recording the score of each player in each team and calculating the score of each team.

16. TicTacToe.java: There is a class called TicTacToe which extends from abstract class BoardGame.
This class is used for start a TicTacToe game.


## Notes
---------------------------------------------------------------------------
1. Considering that every board game needs to start the game, I designed an interface for starting the game.
2. I use many functions to implement a feature of the program, not just one function. 
3. I use inheritance class in my program.
4. A loop was implemented wherever user input was required. This ensures that the user is prompted to re-enter their input in case of an error, until the enter value is right, thereby ensuring the accuracy of user's input.
5. I designed a "Team" class, enabling the game to have the option of team-based play.
6. I created what I believe to be a fantastic board and pieces. Since the board is big and scalable, it's hard to build a board. While I didn't use GUI, the board is distinguishable, which enhances the playability of the game.


## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "Super-TicTacToe" after unzipping the files.
2. Run the "BoardGames.java" in "Super-TicTacToe" folder by following steps:
javac *.java -d bin
java -cp ./bin Play_BoardGames


## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

Output:
Welcome to BoardGames!!!

===============BoardGames===============
	1. Start a new Tic Tac Toe game
	2. Start a new Order and Chaos game
	3. Start a new Super Tic Tac Toe game
	4. Exit
Please enter your choice(1-4)
Input:
3
Output:

Welcome to Super Tic Tac Toe Game

How many rows do you want for main board of Super Tic Tac Toe Game?
Only enter a number between 3 and 6.(Strongly recommend 3)
(Strongly recommend 3 since chessboard is too large to display conveniently)
Input:
3
Output:

How many columns do you want for main board of Super Tic Tac Toe Game?
Only enter a number between 3 and 6.(Strongly recommend 3)
(Strongly recommend 3 since chessboard is too large to display conveniently)
Input:
3
Output:
How many rows do you want for sub board of Super Tic Tac Toe Game?
Only enter a number between 3 and 6.(Strongly recommend 3)
(Strongly recommend 3 since chessboard is too large to display conveniently)
Input:
3
Output:

How many columns do you want for sub board of Super Tic Tac Toe Game?
Only enter a number between 3 and 6.(Strongly recommend 3)
(Strongly recommend 3 since chessboard is too large to display conveniently)
Input:
3
Output:
Do you want to have a team competition?(y/n)
Input:
y
Output:
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
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
1
Output:

Which player from Team 2 is participating in this match?
Please enter a number less than 4 and more than 0
Input:
2
Output:
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
	|	|
------------------------
	|	|
------------------------
	|	|

Player 1, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Your choice of the big board is (2, 2)

Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Your choice of the big board is (2, 2)
Your move of the small board is (2, 2)

Round 1
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
	|	|
------------------------
	|	|
------------------------
	|	|

Player 2, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Your choice of the big board is (2, 2)

Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:

Your choice of the big board is (2, 2)
Your move of the small board is (1, 2)

Round 2
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||		|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
	|	|
------------------------
	|	|
------------------------
	|	|

Player 1, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Player 1, please enter the position you want
Please enter row(a number from 1 to 3): 
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
This position of small board already has a piece
Please choose a different position

Player 1, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Your choice of the big board is (2, 2)
Your move of the small board is (1, 1)

Round 3
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
	|	|
------------------------
	|	|
------------------------
	|	|

Player 2, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:

Your choice of the big board is (1, 1)
Your move of the small board is (2, 2)

Round 4
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
	|	|
------------------------
	|	|
------------------------
	|	|

Player 1, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
3
Output:

Your choice of the big board is (2, 2)
Your move of the small board is (3, 3)

In the (2, 2) small board. Player 1 wins!!!


Round 5
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
==========================================================================================
	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|O	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
	|	|
------------------------
	|O	|
------------------------
	|	|

Player 2, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
This position of big board already has a piece
Please choose a different position

Player 2, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
3
Output:

Your choice of the big board is (1, 1)
Your move of the small board is (3, 3)

Round 6
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|	||		|	|
==========================================================================================
	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|O	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
	|	|
------------------------
	|O	|
------------------------
	|	|

Player 1, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:

Your choice of the big board is (1, 2)
Your move of the small board is (2, 2)

Round 7
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|	||		|	|
==========================================================================================
	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|O	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
	|	|
------------------------
	|O	|
------------------------
	|	|

Player 2, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:

Your choice of the big board is (1, 1)
Your move of the small board is (1, 1)

In the (1, 1) small board. Player 2 wins!!!



Round 8
X	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|	||		|	|
==========================================================================================
	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|O	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
X	|	|
------------------------
	|O	|
------------------------
	|	|

Player 1, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:

Your choice of the big board is (1, 2)
Your move of the small board is (1, 1)

Round 9
X	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|	||		|	|
==========================================================================================
	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|O	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
X	|	|
------------------------
	|O	|
------------------------
	|	|

Player 2, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:

Your choice of the big board is (2, 1)
Your move of the small board is (2, 2)

Round 10
X	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|	||		|	|
==========================================================================================
	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|O	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
X	|	|
------------------------
	|O	|
------------------------
	|	|

Player 1, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
3
Output:

Your choice of the big board is (1, 2)
Your move of the small board is (3, 3)

In the (1, 2) small board. Player 1 wins!!!


Round 11
X	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|O	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
X	|O	|
------------------------
	|O	|
------------------------
	|	|

Player 2, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:

Your choice of the big board is (2, 1)
Your move of the small board is (1, 1)

Round 12
X	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
X	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|O	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
X	|O	|
------------------------
	|O	|
------------------------
	|	|

Player 1, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:

Your choice of the big board is (3, 2)
Your move of the small board is (2, 2)

Round 13
X	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
X	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|O	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
X	|O	|
------------------------
	|O	|
------------------------
	|	|

Player 2, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
3
Output:

Your choice of the big board is (2, 1)
Your move of the small board is (3, 3)

In the (2, 1) small board. Player 2 wins!!!


Round 14
X	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
X	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
	|	|	||		|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
X	|O	|
------------------------
X	|O	|
------------------------
	|	|

Player 1, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
1
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:

Your choice of the big board is (3, 2)
Your move of the small board is (1, 1)

Round 15
X	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
X	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
X	|O	|
------------------------
X	|O	|
------------------------
	|	|

Player 2, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
1
Output:
Player 2, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
2
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:

Your choice of the big board is (3, 1)
Your move of the small board is (2, 2)

Round 16
X	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
X	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|	||		|	|

Now the main board is:
X	|O	|
------------------------
X	|O	|
------------------------
	|	|

Player 1, please choose a position of big board you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
2
Output:
Player 1, please enter the position you want
Please enter row(a number from 1 to 3):
Input:
3
Output:
Please enter column(a number from 1 to 3):
Input:
3
Output:

Your choice of the big board is (3, 2)
Your move of the small board is (3, 3)

In the (3, 2) small board. Player 1 wins!!!


Round 17
X	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
X	|	|	||	O	|X	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|X	||		|	|O	||		|	|
==========================================================================================
	|	|	||	O	|	|	||		|	|
------------------------||	------------------------||	------------------------
	|X	|	||		|O	|	||		|	|
------------------------||	------------------------||	------------------------
	|	|	||		|	|O	||		|	|

Now the main board is:
X	|O	|
------------------------
X	|O	|
------------------------
	|O	|

Player 1 wins!!!


Do you want to continue?(y/n)
Input:
n
Output:

Game over! The result is:

Team 1: 1 points!
Player1: 1 points!
Player2: 0 points!
Player3: 0 points!

Team 2: 0 points!
Player1: 0 points!
Player2: 0 points!
Player3: 0 points!

===============BoardGames===============
	1. Start a new Tic Tac Toe game
	2. Start a new Order and Chaos game
	3. Start a new Super Tic Tac Toe game
	4. Exit
Please enter your choice(1-4)
Input:
4
Output:
Are you sure to exit(y/n)?
Input:
y
Output:

See you next time!!!

