
public class Player{//Create players
    public int[] players = new int[10];
    public int number_of_players;
    private int player_id;

    public void Reset_Players_Scores() throws Exception{//Reset players
        System.out.println("Number of players is:"+this.number_of_players);
        for (player_id = 0; player_id < number_of_players; player_id++){
            this.players[player_id] = 0;
        }
    }
    public void Record_Score(int winner_id) throws Exception{//Record the result of Tic Tac Toe game
        this.players[winner_id] += 1;
    }

    public int Get_Score(int player_id) throws Exception{//Get one player's score
        return players[player_id];
    }
}