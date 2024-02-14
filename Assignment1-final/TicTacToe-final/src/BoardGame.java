import java.util.Scanner;

public class BoardGame extends Game implements Start_Game{// Play TicTacToe
    public boolean if_team = false;
    public int boardsize;
    public boolean if_continue = true;
    public int winner_id;

    public void Start() throws Exception{// Start a Tic Tac Toe game
        System.out.println("\nWelcome to this BoardGame");

    }
}
