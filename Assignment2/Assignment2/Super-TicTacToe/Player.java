import java.util.Scanner;

public class Player{//Create players
    private char choice;
    private String user_enter;
    private int id_of_player_in_team;
    public int[] players = new int[10];
    public int number_of_players;
    private int player_id;


    public void Get_Number_of_Players() throws Exception {// Get number of players for Tic Tac Toe game
        do {
            System.out.print("For this game, only enter 2\n");

            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1){
                System.out.println("\n\nFor this game, please only enter 2");
            }

            else {
                choice = user_enter.charAt(0);
                this.number_of_players = Character.getNumericValue(choice);
                if (number_of_players == 2) {
                    break;
                }
                else{
                    System.out.println("\n\nFor this game, please only enter 2");
                }
            }
        }while(true);
    }

    public int Get_Number_of_Participating_Player(int team_id, int number_of_players_in_a_team) throws Exception {// Get number of player who participates in this Tic Tac Toe game
        System.out.println("\nWhich player from Team " + team_id + " is participating in this match?");
        System.out.println("Please enter a number less than "+ (number_of_players_in_a_team+1)+ " and more than 0");

        do {
            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1){
                System.out.println("\n\nPlease enter a number less than "+ (number_of_players_in_a_team+1)+ " and more than 0");
            }

            else {
                choice = user_enter.charAt(0);
                this.id_of_player_in_team = Character.getNumericValue(choice);
                if (id_of_player_in_team <= number_of_players_in_a_team && id_of_player_in_team > 0) {
                    break;
                } else {
                    System.out.println("\n\nPlease enter a number less than "+ (number_of_players_in_a_team+1) + " and more than 0");
                }
            }
        }while(true);
        return this.id_of_player_in_team;
    }
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