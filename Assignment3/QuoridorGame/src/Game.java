import java.util.Scanner;

abstract class Game{// Play TicTacToe
    public int number_of_players;
    public int number_of_teams;
    public int number_of_players_in_a_team;
    public boolean if_gameover = false;

    abstract void Start() throws Exception;
}
