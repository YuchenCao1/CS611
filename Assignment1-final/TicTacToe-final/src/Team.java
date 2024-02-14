
public class Team{//Create players
    public int[][] players = new int[10][10];
    public int number_of_teams;
    public int number_of_players_in_a_team;
    private int player_id;
    private int team_id;
    private int team_score;

    public void Reset_Team_Member_Scores() throws Exception{//Reset players
        System.out.println("Number of teams is:"+this.number_of_teams);
        System.out.println("Number of players in a team is:"+this.number_of_players_in_a_team);

        for(team_id = 0; team_id < number_of_teams; team_id++){
            for(player_id = 0; player_id < number_of_players_in_a_team; player_id++){
                this.players[team_id][player_id] = 0;
            }
        }
    }
    public void Record_Score(int winner_team_id, int winner_id) throws Exception{//Record the result of Tic Tac Toe game
        this.players[winner_team_id][winner_id] += 1;
    }

    public int Get_Score(int team_id, int player_id) throws Exception{//Get one player's score
        return players[team_id][player_id];
    }

    public int Get_Team_Score(int team_id) throws Exception{//Get one player's score
        team_score = 0;
        for(player_id = 1; player_id <= number_of_players_in_a_team; player_id++){
            team_score += Get_Score(team_id, player_id);
        }
        return team_score;
    }

}