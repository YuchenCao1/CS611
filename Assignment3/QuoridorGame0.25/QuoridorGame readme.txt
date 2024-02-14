Readme.txt

# CS611-Assignment 3
## Quoridor game
---------------------------------------------------------------------------
Yuchen Cao			Boqing Wang
caoyc@bu.edu		bqwang@bu.edu
U51424608			U77494171

## Files
---------------------------------------------------------------------------

There are 18 files included in the src folder: Board.java, BoardGame.java, Game.java, Get_Board_Size.java, Get_user_Answer.java, Menu.java, Order_and_Chaos.java, Piece.java, Play_BoardGames.java, Player.java, Round.java, Rules.java, Print_Board.java, Super_TicTacToe.java, Team.java, TicTacToe.java, QuoridorGame.java and Wall.java. Each file contains a class or interface.

1. Board.java: Includes a class named Board which implements an interface called Print_Board.
This class is used for generating the game board, resetting the game board, setting the board size and outputting the board.

2. BoardGame.java: Includes an abstract class named BoardGame which extends from class Game.
Each board game can extend from this class, it has some basic objects and a function used to start a board game.

3. Game.java: Includes an abstract class named Game.
Each game can extend from this class, it has some basic objects and a function used to start a game.

4.Get_Board_Size.java: Includes an interface named Get_Board_Size.
The TicTacToe and Super_TicTacToe classes can implement it to use Get_Boardsize() function to get the size of the board for game.

5. Get_user_Answer.java: Includes a class named Get_user_Answer.
This class is used to get the answer or choice from user.

6. Menu.java: Includes a class named Menu.
This class is used for generating a menu for user's selection: play Tic-Tac-Toe, play Order and Chaos or exit. It contains two kinds of menu: main menu and submenu. 

7. Order_and_Chaos.java: Includes a class named Order_and_Chaos which extends from abstract class BoardGame.
This class is used for starting an Order_and_Chaos game.

8. Piece.java: Includes a class named Board.
This class is used for generating the game board, resetting the game board, setting the board size and outputting the board.

9. Play_BoardGames.java: Includes a class named Play_BoardGames.
It's the main class of the program. It is the class where the program starts.

10. Player.java: Includes a class named Player.
This class is used for generating the players, resetting the score of each player and recording the score of each player.

11. Round.java: Includes a class named Round.
This class is used for generating the rounds, resetting the round counter, recording the number of rounds and getting the actions of each user in each round.

12. Rules.java: Includes a class named Rules.
This class is used for generating the rule of each board game, checking whether the player's move is valid and checking whether the game is over at the end of each round.

13. Print_Board.java: Includes an interface named Print_Board.
The board class can implement it to use PrintBoard() function to print a board for game.

14. Super_TicTacToe.java: Includes a class named Super_TicTacToe which extends from abstract class BoardGame.
This class is used for starting a Super_TicTacToe game.

15. Team.java: Includes a class named Team.
This class is used for generating the teams, setting the size of team, resetting the score of each player in each team, recording the score of each player in each team and calculating the score of each team.

16. TicTacToe.java: Includes a class named TicTacToe which extends from abstract class BoardGame.
This class is used for starting a TicTacToe game.

17.QuoridorGame.java: Includes a class named QuoridorGame that extends the rules from the base BoardGame.

18.Wall.java: Includes a class called named that will allow the creation of a wall in the game.



## Notes
---------------------------------------------------------------------------
1. Considering that every board game needs to start the game, we designed an interface for starting the game.
2. We use many functions to implement a feature of the program, not just one function. 
3. We use inheritance class in our program.
4. A loop was implemented wherever user input was required. This ensures that the user is prompted to re-enter their input in case of an error, until the enter value is correct, thereby ensuring the accuracy of user's input.
5. We designed a "Team" class, enabling the game to have the option of team-based play.
6. We created what we believe to be a fantastic board and pieces. Since the board is big and scalable, it's hard to build a board. While we didn't use GUI, the board is distinguishable, which enhances the playability of the game.
7. We designed the program to have the piece able to jump over the other players piece while being blocked.


## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "QuoridorGame" after unzipping the files.
2. Run the "BoardGames.java" in "Super-TicTacToe" folder by following steps:
javac *.java -d bin
java -cp ./bin Play_BoardGames


## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

Welcome to BoardGames!!!

===============BoardGames===============
        1. Start a new Tic Tac Toe game
        2. Start a new Order and Chaos game
        3. Start a new Super Tic Tac Toe game
        4. Start a new Quoridor game
        5. Exit
Please enter your choice(1-5)
4

Welcome to Quoridor Game

How many rows do you want the Quoridor Game to have?
Please enter a number from 7 to 9!(Strongly recommend 9)
4


Only a number from 7 to 9!
(Strongly recommend 9 since chessboard is too large to display conveniently)
9

How many columns do you want the Quoridor Game to have?
Please enter a number from 7 to 9!(Strongly recommend 9)
9
Do you want to have a team competition?(y/n)
n
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |O      |       |       |       |

How many players will be playing?
For this game, only enter 2
2
Number of players is:2


1. Move your piece
2. Place a new wall (you only have10walls)
Please enter your choice(1 or 2)
1
Player 1, please enter your movement W/A/S/D (up/left/down/right)
W

Your movement is W
Your piece_position is: 8, 4
Your choice is to move your piece: W


        Round 1
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |O      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have10walls)
Please enter your choice(1 or 2)
2
Please enter the wall placement as an 8 digit number
64746575
Your choice is to add a wall


        Round 2
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |O      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 1, please enter your movement W/A/S/D (up/left/down/right)
W

Your movement is W
Your piece_position is: 7, 4
Can't move over the wall!


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 1, please enter your movement W/A/S/D (up/left/down/right)
A

Your movement is A
Your piece_position is: 7, 4
Your choice is to move your piece: A


        Round 3
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 2, please enter your movement W/A/S/D (up/left/down/right)
S

Your movement is S
Your piece_position is: 0, 4
Your choice is to move your piece: S


        Round 4
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 1, please enter your movement W/A/S/D (up/left/down/right)
W

Your movement is W
Your piece_position is: 7, 3
Your choice is to move your piece: W


        Round 5
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 2, please enter your movement W/A/S/D (up/left/down/right)
S

Your movement is S
Your piece_position is: 1, 4
Your choice is to move your piece: S


        Round 6
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 1, please enter your movement W/A/S/D (up/left/down/right)
W

Your movement is W
Your piece_position is: 6, 3
Your choice is to move your piece: W


        Round 7
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 2, please enter your movement W/A/S/D (up/left/down/right)
S

Your movement is S
Your piece_position is: 2, 4
Your choice is to move your piece: S


        Round 8
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 1, please enter your movement W/A/S/D (up/left/down/right)
W

Your movement is W
Your piece_position is: 5, 3
Your choice is to move your piece: W


        Round 9
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 2, please enter your movement W/A/S/D (up/left/down/right)
S

Your movement is S
Your piece_position is: 3, 4
Your choice is to move your piece: S


        Round 10
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 1, please enter your movement W/A/S/D (up/left/down/right)
W

Your movement is W
Your piece_position is: 4, 3
Your choice is to move your piece: W


        Round 11
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 2, please enter your movement W/A/S/D (up/left/down/right)
S

Your movement is S
Your piece_position is: 4, 4
Your choice is to move your piece: S


        Round 12
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 1, please enter your movement W/A/S/D (up/left/down/right)
W

Your movement is W
Your piece_position is: 3, 3
Your choice is to move your piece: W


        Round 13
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |X      |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 2, please enter your movement W/A/S/D (up/left/down/right)
A

Your movement is A
Your piece_position is: 5, 4
Your choice is to move your piece: A


        Round 14
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |X      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 1, please enter your movement W/A/S/D (up/left/down/right)
W

Your movement is W
Your piece_position is: 2, 3
Your choice is to move your piece: W


        Round 15
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |X      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 2, please enter your movement W/A/S/D (up/left/down/right)
S

Your movement is S
Your piece_position is: 5, 3
Your choice is to move your piece: S


        Round 16
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |X      |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |


1. Move your piece
2. Place a new wall (you only have9walls)
Please enter your choice(1 or 2)
1
Player 1, please enter your movement W/A/S/D (up/left/down/right)
W

Your movement is W
Your piece_position is: 1, 3
Your choice is to move your piece: W


        Round 17
        |       |       |O      |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |X      |       |       |       |       |
--------------------------------================------------------------
        |       |       |       |       |       |       |       |
------------------------------------------------------------------------
        |       |       |       |       |       |       |       |

Player 1 wins!!!


Do you want to continue?(y/n)
n

Game over! The result is:
Player1: 1 points!
Player2: 0 points!
