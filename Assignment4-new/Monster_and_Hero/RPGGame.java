import java.util.Scanner;

abstract class RPGGame extends Game{// Play TicTacToe
    public boolean if_team = false;
    public int worldsize;
    public int world_length;
    public int world_width;
    public boolean if_continue = true;
    public int winner_id;

    public abstract void Start() throws Exception;
}
