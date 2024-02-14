import java.util.Scanner;

public class TicTacToe extends BoardGame implements Get_Board_Size{// Play TicTacToe
    private char choice;
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
            System.out.println("Only enter a number between 3 and 6");

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

    public void Start() throws Exception{// Start a Tic Tac Toe game
        System.out.println("\nWelcome to Tic Tac Toe Game");

        Board now_board = new Board(0, 0);
        System.out.println("\nHow many rows do you want for Tic Tac Toe Game?");
        this.board_length = Get_Boardsize();
        System.out.println("\nHow many columns do you want for Tic Tac Toe Game?");
        this.board_width = Get_Boardsize();
        now_board.Reset_Board_Size(this.board_length, this.board_width);

        Piece now_pieces = new Piece(now_board.board_length, now_board.board_width);

        Player now_players = new Player();
        Team now_teams = new Team();

        Get_user_Answer get_answer = new Get_user_Answer();

        System.out.println("Do you want to have a team competition?(y/n)");
        if_team = get_answer.Get_y_or_n_Answer();

        now_board.PrintBoard(now_pieces.pieces);

        if(if_team){
            System.out.print("\nHow many teams do you want for TicTacToe?\n");
            now_teams.Get_Number_of_Teams();

            System.out.print("\nHow many players do you want in a team for TicTacToe?\n");
            now_teams.Get_Number_of_Players_in_Team();

            now_teams.Reset_Team_Member_Scores();
        }
        else{
            System.out.print("\nHow many players do you want for TicTacToe?\n");
            now_players.Get_Number_of_Players();
            now_players.Reset_Players_Scores();
        }

        Round round = new Round();
        round.ResetRound();

        Rules rule = new Rules();

        do{
            if_gameover = false;
            if(if_team){
                this.id_of_player_in_team1 = now_players.Get_Number_of_Participating_Player(1, now_teams.number_of_players_in_a_team);
                this.id_of_player_in_team2 = now_players.Get_Number_of_Participating_Player(2, now_teams.number_of_players_in_a_team);

                now_board.PrintBoard(now_pieces.pieces);
            }
            do{
                round.Next_Round();
                do{
                    this.this_move = round.Next_Round_Move(now_board.board_length, now_board.board_width);
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

                result = rule.TicTacToeIf_Over(now_pieces.pieces,now_board.board_length, now_board.board_width);
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
                now_pieces.Reset_Pieces(now_board.board_length, now_board.board_width);
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
