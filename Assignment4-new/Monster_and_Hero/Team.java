import java.util.Scanner;

public class Team{//Create players
    private char choice;
    private String user_enter;
    public int[][] players = new int[10][10];
    public int number_of_teams;
    public int number_of_players_in_a_team;
    private int player_id;
    private int team_id;
    private int team_score;



    public void Get_Number_of_Teams() throws Exception {// Get number of teams for Tic Tac Toe game
        do {
            System.out.print("For this game, only enter 2\n");

            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1){
                System.out.println("\n\nFor this game, please only enter 2");
            }

            else {
                choice = user_enter.charAt(0);
                this.number_of_teams = Character.getNumericValue(choice);
                if (number_of_teams == 2) {
                    break;
                }
                else{
                    System.out.println("\n\nFor this game, please only enter 2");
                }
            }
        }while(true);
    }

    public void Get_Number_of_Players_in_Team() throws Exception {// Get number of players in a team for Tic Tac Toe game
        do {
            System.out.print("Please enter 1-3\n");

            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1){
                System.out.println("\n\nPlease enter 1-3");
            }

            else {
                choice = user_enter.charAt(0);
                this.number_of_players_in_a_team = Character.getNumericValue(choice);
                if (number_of_players_in_a_team <= 3 && number_of_players_in_a_team>=1) {
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter 1-3");
                }
            }
        }while(true);
    }
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