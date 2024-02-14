import java.util.Scanner;

abstract class BoardGame extends Game{// Play TicTacToe
    public boolean if_team = false;
    public int boardsize;
    public int board_length;
    public int board_width;
    public boolean if_continue = true;
    public int winner_id;

    public abstract void Start() throws Exception;
}
