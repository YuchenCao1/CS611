import java.util.Scanner;

public class TicTacToe extends BoardGame implements Start_Game{// Play TicTacToe
    private char choice;
    private int id_of_player_in_team;
    private int id_of_player_in_team1;
    private int id_of_player_in_team2;
    private String user_enter;
    private int each_player;
    private int[] this_move = new int[2];
    private boolean if_this_move_valuable;
    private int now_round;
    private int[] result = new int[2];

    public int Get_Boardsize() throws Exception{// Get boardsize for Tic Tac Toe game
        do {
            System.out.println("\nHow many rows and columns do you want for Tic Tac Toe Game?");
            System.out.println("Only enter a number between 3 and 6, as the board is a square");

            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1){
                System.out.println("\n\nPlease enter only a number from 3 to 6!");
            }

            else {
                choice = user_enter.charAt(0);
                this.boardsize = Character.getNumericValue(choice);
                if (this.boardsize >= 3 && this.boardsize <= 6) {
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number from 3 to 6!");
                }
            }
        }while(true);
        return this.boardsize;
    }

    public int Get_Number_of_Teams() throws Exception {// Get number of teams for Tic Tac Toe game
        do {
            System.out.print("\nHow many teams do you want for TicTacToe?\n");
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
        return this.number_of_teams;
    }

    public int Get_Number_of_Players_in_Team() throws Exception {// Get number of players in a team for Tic Tac Toe game
        do {
            System.out.print("\nHow many players do you want in a team for TicTacToe?\n");
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
        return this.number_of_players_in_a_team;
    }

    public int Get_Number_of_Involved_Player() throws Exception {// Get number of players in a team for Tic Tac Toe game

        return this.number_of_players_in_a_team;
    }

    public int Get_Number_of_Players() throws Exception {// Get number of players for Tic Tac Toe game
        do {
            System.out.print("\nHow many players do you want for TicTacToe?\n");
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
        return this.number_of_players;
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


    public void Start() throws Exception{// Start a Tic Tac Toe game
        System.out.println("\nWelcome to Tic Tac Toe Game");

        Board now_board = new Board(0);
        this.boardsize = Get_Boardsize();
        now_board.Reset_Board_Size(this.boardsize);

        Piece now_pieces = new Piece(now_board.board_size);

        Player now_players = new Player();
        Team now_teams = new Team();

        Get_user_Answer get_answer = new Get_user_Answer();

        System.out.println("Do you want to have a team competition?(y/n)");
        if_team = get_answer.Get_y_or_n_Answer();


        now_board.PrintBoard(now_pieces.pieces);

        if(if_team){
            this.number_of_teams = Get_Number_of_Teams();
            now_teams.number_of_teams = this.number_of_teams;

            this.number_of_players_in_a_team = Get_Number_of_Players_in_Team();
            now_teams.number_of_players_in_a_team = this.number_of_players_in_a_team;

            now_teams.Reset_Team_Member_Scores();
        }
        else{
            this.number_of_players = Get_Number_of_Players();
            now_players.number_of_players = this.number_of_players;

            now_players.Reset_Players_Scores();
        }

        Round round = new Round();
        round.ResetRound();

        Rules rule = new Rules();

        do{
            if_gameover = false;
            if(if_team){
                this.id_of_player_in_team1 = Get_Number_of_Participating_Player(1, now_teams.number_of_players_in_a_team);
                this.id_of_player_in_team2 = Get_Number_of_Participating_Player(2, now_teams.number_of_players_in_a_team);

                now_board.PrintBoard(now_pieces.pieces);
            }
            do{
                round.Next_Round();
                do{
                    this.this_move = round.Next_Round_Move(now_board.board_size);
                    this.if_this_move_valuable = rule.If_Move_Valid(now_pieces.pieces,this_move[0],this_move[1]);
                    if(this.if_this_move_valuable){
                        System.out.println("Your move is ("+this_move[0]+","+this_move[1]+")");
                        this.now_round = round.Get_Round();
                        if (now_round % 2 == 1){
                            now_pieces.pieces = now_pieces.Update_Pieces(this_move[0]-1,this_move[1]-1,1);
                        }
                        else{
                            now_pieces.pieces = now_pieces.Update_Pieces(this_move[0]-1,this_move[1]-1,2);
                        }
                    }
                    else{
                        System.out.println("This position already has a piece\nPlease choose a different position");
                    }
                }while(!this.if_this_move_valuable);

                System.out.println("\n\n\tRound "+this.now_round);
                now_board.PrintBoard(now_pieces.pieces);

                result = rule.TicTacToeIf_Over(now_pieces.pieces,now_board.board_size);
                if(result[0] == 1){
                    if_gameover = true;
                    winner_id = result[1];
                    if(winner_id == 0){
                        System.out.println("\nDraw!!!\n\n");
                    }
                    else if(winner_id == 1){
                        System.out.println("\nPlayer 1 wins!!!\n\n");
                        if(if_team){
                            now_teams.Record_Score(1,this.id_of_player_in_team1);
                        }
                        else{
                            now_players.Record_Score(this.winner_id);
                        }
                    }
                    else if(winner_id ==2){
                        System.out.println("\nPlayer 2 wins!!!\n\n");
                        if(if_team){
                            now_teams.Record_Score(2,this.id_of_player_in_team2);
                        }
                        else{
                            now_players.Record_Score(this.winner_id);
                        }
                    }
                }
            }while(!if_gameover);
            Menu menu = new Menu();
            if_continue = menu.SubMenu();
            if(if_continue){
                now_pieces.Reset_Pieces(now_board.board_size);
                round.ResetRound();
            }
        }while(if_continue);

        System.out.println("\nGame over! The result is:");
        if(if_team){
            System.out.println("\nTeam 1: " + now_teams.Get_Team_Score(1)+" points!");
            for(each_player = 0; each_player < now_teams.number_of_players_in_a_team; each_player++) {
                System.out.println("Player" + (each_player+1) + ": " + now_teams.Get_Score(1,each_player+1)+" points!");
            }

            System.out.println("\nTeam 2: " + now_teams.Get_Team_Score(2)+" points!");
            for(each_player = 0; each_player < now_teams.number_of_players_in_a_team; each_player++) {
                System.out.println("Player" + (each_player+1) + ": " + now_teams.Get_Score(2,each_player+1)+" points!");
            }
        }
        else{
            for(each_player = 0; each_player < now_players.number_of_players; each_player++) {
                System.out.println("Player" + (each_player+1) + ": " + now_players.Get_Score(each_player+1)+" points!");
            }
        }
    }
}
