import java.util.Scanner;

public class Order_and_Chaos extends BoardGame implements Start_Game{// Play the Order and Chaos
    private char choice;
    private String user_enter;
    private int each_player;
    private int[] this_move = new int[2];
    private int this_move_piece;
    private boolean if_this_move_valuable;
    private int now_round;
    private int[] result = new int[2];

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

    public void Start() throws Exception{// Start a Tic Tac Toe game
        System.out.println("\nWelcome to Order and Chaos Game");
        System.out.println("Player 1 is the attacker, and Player 2 is the defender");

        Board now_board = new Board(0);
        now_board.Reset_Board_Size(6);

        Piece now_pieces = new Piece(now_board.board_size);

        Player now_players = new Player();

        now_board.PrintBoard(now_pieces.pieces);

        this.number_of_players = Get_Number_of_Players();

        now_players.Reset_Players_Scores();

        Round round = new Round();
        round.ResetRound();

        Rules rule = new Rules();

        do{
            if_gameover = false;
            do{
                round.Next_Round();
                do{
                    this.this_move = round.Next_Round_Move(now_board.board_size);
                    this.if_this_move_valuable = rule.If_Move_Valid(now_pieces.pieces,this_move[0],this_move[1]);
                    this.this_move_piece = round.Next_Round_Piece();
                    if(this.if_this_move_valuable){
                        System.out.println("Your move is ("+this_move[0]+","+this_move[1]+")");
                        if(this_move_piece == 1){
                            System.out.println("Your choice of piece is 'O'");
                            now_pieces.pieces = now_pieces.Update_Pieces(this_move[0]-1,this_move[1]-1,1);
                        }
                        else if(this_move_piece == 2){
                            System.out.println("Your choice of piece is 'X'");
                            now_pieces.pieces = now_pieces.Update_Pieces(this_move[0]-1,this_move[1]-1,2);
                        }
                    }
                    else{
                        System.out.println("This position already has a piece\nPlease choose a different position");
                    }
                }while(!this.if_this_move_valuable);

                this.now_round = round.Get_Round();
                System.out.println("\n\n\tRound "+this.now_round);
                now_board.PrintBoard(now_pieces.pieces);

                result = rule.OrderandChaosIf_Over(now_pieces.pieces);
                if(result[0] == 1){
                    if_gameover = true;
                    winner_id = result[1];
                    if(winner_id == 1){
                        System.out.println("\nPlayer 1 wins!!!\n\n");
                        now_players.Record_Score(this.winner_id);
                    }
                    else if(winner_id ==2){
                        System.out.println("\nPlayer 2 wins!!!\n\n");
                        now_players.Record_Score(this.winner_id);
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
        for(each_player = 0; each_player < now_players.number_of_players;each_player++) {
            System.out.println("Player" + (each_player+1) + ": " + now_players.Get_Score(each_player+1)+" points!");
        }
    }
}
