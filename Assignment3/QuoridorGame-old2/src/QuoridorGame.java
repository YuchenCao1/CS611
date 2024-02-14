import java.util.Scanner;

public class QuoridorGame extends BoardGame implements Get_Board_Size {
    private char choice;
    private String user_enter;
    private int id_of_player_in_team1;
    private int id_of_player_in_team2;
    private int each_player;
    private int[] this_move = new int[2];
    private int wall_number = 10;
    private int round_choice;
    private char this_movement;
    private int[] this_placement = new int[8];
    private boolean if_this_movement_valuable;
    private boolean if_this_placement_valuable;
    private int[] piece_position = new int[2];
    private int now_round;
    private int[] result = new int[2];

    private char[][] board;  // Chessboard
    private char[][] horizontalWalls;  // Horizontal walls
    private char[][] verticalWalls;  // Vertical walls
    private int[] player1Position;
    private int[] player2Position;
    private int wallsRemainingPlayer1;
    private int wallsRemainingPlayer2;

    public int Get_Boardsize() throws Exception {// Get boardsize for Quoridor game
        System.out.println("\n\nPlease enter a number from 9 to 11!(Strongly recommend 9)");
        do {
            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1) {
                System.out.println("\n\nOnly a number from 9 to 11!");
                System.out.println("(Strongly recommend 9 since chessboard is too large to display conveniently)");
            } else {
                choice = user_enter.charAt(0);
                this.boardsize = Character.getNumericValue(choice);
                if (this.boardsize >= 9 && this.boardsize <= 11) {
                    break;
                } else {
                    System.out.println("\n\nOnly a number from 9 to 11!");
                    System.out.println("(Strongly recommend 9 since chessboard is too large to display conveniently)");
                }
            }
        } while (true);
        return this.boardsize;
    }


    public void Start() throws Exception{

        System.out.println("\nWelcome to Quoridor Game");

        Board now_board = new Board(0, 0);
        System.out.println("\nHow many rows do you want for Quoridor Game?");
        this.board_length = Get_Boardsize();
        System.out.println("\nHow many columns do you want for Quoridor Game?");
        this.board_width = Get_Boardsize();
        now_board.Reset_Board_Size(this.board_length, this.board_width);

        Piece now_pieces = new Piece(now_board.board_length, now_board.board_width);
        now_pieces.Reset_QuoridorGame_Pieces(now_board.board_length, now_board.board_width);

        Wall now_walls = new Wall(now_board.board_length, now_board.board_width);

        Player now_players = new Player();
        Team now_teams = new Team();

        Get_user_Answer get_answer = new Get_user_Answer();

        System.out.println("Do you want to have a team competition?(y/n)");
        if_team = get_answer.Get_y_or_n_Answer();

        int [] hhh = new int[8];
        hhh[0] = 3;
        hhh[1] = 4;
        hhh[2] = 4;
        hhh[3] = 4;
        hhh[4] = 3;
        hhh[5] = 5;
        hhh[6] = 4;
        hhh[7] = 5;
        now_walls.Add_Walls(hhh);
        hhh[0] = 8;
        hhh[1] = 3;
        hhh[2] = 8;
        hhh[3] = 4;
        hhh[4] = 7;
        hhh[5] = 3;
        hhh[6] = 7;
        hhh[7] = 4;
        now_walls.Add_Walls(hhh);
        now_board.Print_Quoridor_Board(now_pieces.pieces, now_walls.walls);

        if(if_team){
            System.out.print("\nHow many teams do you want for Quoridor game?\n");
            now_teams.Get_Number_of_Teams();

            System.out.print("\nHow many players do you want in a team for Quoridor game?\n");
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

                now_board.Print_Quoridor_Board(now_pieces.pieces, now_walls.walls);
            }
            do{
                round.Next_Round();
                this.now_round = round.Get_Round();
                this.piece_position = now_pieces.Get_Piece_Position(now_board.board_length, now_board.board_width, now_pieces.pieces, this.now_round);
                if_this_movement_valuable = false;
                if_this_placement_valuable = false;
                do{
                    this.round_choice = round.Next_Round_Choice(now_walls.walls_number);
                    if(round_choice == 1) {
                        this.this_movement = round.Next_Round_Movement();
                        this.if_this_movement_valuable = rule.If_Movement_Valid(now_board.board_length, now_board.board_width, now_pieces.pieces, now_walls.walls, this.piece_position, this.this_movement);
                        if (this.if_this_movement_valuable) {
                            System.out.println("Your choice is to move your piece: " + this.this_movement);
                            now_pieces.pieces = now_pieces.Move_Pieces(this.piece_position, this.this_movement);
                        }
                    }
                    else if(round_choice == 2) {
                        this.this_placement = round.Next_Round_Placement(now_board.board_length,now_board.board_width);
                        this.if_this_placement_valuable = rule.If_Placement_Valid(now_board.board_length, now_board.board_width, now_walls.walls, this_placement);
                        if (this.if_this_placement_valuable) {
                            System.out.println("Your choice is to add a wall");
                            now_walls.walls = now_walls.Add_Walls(this.this_placement);
                            now_walls.walls_number -= 1;
                        }
                    }
                }while(!this.if_this_movement_valuable && !this.if_this_placement_valuable);

                System.out.println("\n\n\tRound "+this.now_round);
                now_board.Print_Quoridor_Board(now_pieces.pieces, now_walls.walls);

                this.piece_position = now_pieces.Get_Piece_Position(now_board.board_length, now_board.board_width, now_pieces.pieces, this.now_round);
                result = rule.QuoridorIf_Over(piece_position, now_board.board_length, this.now_round);

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
                now_pieces.Reset_QuoridorGame_Pieces(now_board.board_length, now_board.board_width);
                now_walls.Reset_Walls(now_board.board_length, now_board.board_width);
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
