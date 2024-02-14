import java.util.Scanner;

public class Super_TicTacToe extends BoardGame implements Get_Board_Size{// Play Super-TicTacToe
    private boolean if_rectangle;
    private char choice;
    private int id_of_player_in_team1;
    private int id_of_player_in_team2;
    private int main_board_length;
    private int main_board_width;
    private int sub_board_length;
    private int sub_board_width;
    private int number_of_sub_board;
    private int number_of_sub_pieces;
    private int number_of_sub_gameover;
    private String user_enter;
    private int each_player;
    private int[] main_this_move = new int[2];
    private int[] sub_this_move = new int[2];
    private boolean if_this_move_valuable;
    private boolean if_this_choice_valuable;
    private int now_round;
    private int[] result = new int[2];
    private boolean[] if_small_board_gameover;

    public int Get_Boardsize() throws Exception{// Get boardsize for Tic Tac Toe game
        do {
            System.out.println("Only enter a number between 3 and 6.(Strongly recommend 3)");
            System.out.println("(Strongly recommend 3 since chessboard is too large to display conveniently)");

            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1){
                System.out.println("\n\nPlease enter only a number from 3 to 6!(Strongly recommend 3)");
                System.out.println("(Strongly recommend 3 since chessboard is too large to display conveniently)");
            }

            else {
                choice = user_enter.charAt(0);
                this.boardsize = Character.getNumericValue(choice);
                if (this.boardsize >= 3 && this.boardsize <= 6) {
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number from 3 to 6!(Strongly recommend 3)");
                    System.out.println("(Strongly recommend 3 since chessboard is too large to display conveniently)");
                }
            }
        }while(true);
        return this.boardsize;
    }


    public void Start() throws Exception{// Start a Super Tic Tac Toe game.
        System.out.println("\nWelcome to Super Tic Tac Toe Game");

        //Set up a new board as the main board and get the board size of it.
        Board now_main_board = new Board(0,0);
        System.out.println("\nHow many rows do you want for main board of Super Tic Tac Toe Game?");
        this.main_board_length = Get_Boardsize();
        System.out.println("\nHow many columns do you want for main board of Super Tic Tac Toe Game?");
        this.main_board_width = Get_Boardsize();
        now_main_board.Reset_Board_Size(this.main_board_length, this.main_board_width);

        Piece now_main_pieces = new Piece(now_main_board.board_length, now_main_board.board_width);

        //After getting the board size of main board, we can build each small board which contains board, piece, sign of if this small game over, etc.
        Board[] now_sub_boards = new Board[now_main_board.board_length * now_main_board.board_width];
        System.out.println("\nHow many rows do you want for sub board of Super Tic Tac Toe Game?");
        this.sub_board_length = Get_Boardsize();
        System.out.println("\nHow many columns do you want for sub board of Super Tic Tac Toe Game?");
        this.sub_board_width = Get_Boardsize();

        for(number_of_sub_board = 0; number_of_sub_board < now_main_board.board_length * now_main_board.board_width; number_of_sub_board++){
            Board sub_board = new Board(this.sub_board_length, this.sub_board_width);
            now_sub_boards[number_of_sub_board] = sub_board;
        }

        Piece[] now_sub_pieces = new Piece[now_main_board.board_length * now_main_board.board_width];
        for(number_of_sub_pieces = 0; number_of_sub_pieces < now_main_board.board_length * now_main_board.board_width; number_of_sub_pieces++){
            Piece sub_piece = new Piece(this.sub_board_length, this.sub_board_width);
            now_sub_pieces[number_of_sub_pieces] = sub_piece;
        }

        if_small_board_gameover = new boolean[now_main_board.board_length * now_main_board.board_width];
        for(number_of_sub_gameover = 0; number_of_sub_gameover < now_main_board.board_length * now_main_board.board_width; number_of_sub_gameover++){
            if_small_board_gameover[number_of_sub_gameover] = false;
        }

        //Set up the players and teams
        Player now_players = new Player();
        Team now_teams = new Team();

        //Get the answer of whether user wants a team competition from user.
        Get_user_Answer get_answer = new Get_user_Answer();
        System.out.println("Do you want to have a team competition?(y/n)");
        if_team = get_answer.Get_y_or_n_Answer();

        /* I used these data to test my Print_SuperTicTacToe_Board function.
        now_sub_pieces[0].pieces[0][2] = 1;
        now_sub_pieces[0].pieces[1][1] = 1;
        now_sub_pieces[0].pieces[1][0] = 2;
        now_sub_pieces[1].pieces[2][2] = 1;
        now_sub_pieces[1].pieces[2][1] = 1;
        now_sub_pieces[1].pieces[2][0] = 2;
        now_sub_pieces[2].pieces[2][0] = 1;
        now_sub_pieces[3].pieces[2][2] = 1;
        now_sub_pieces[3].pieces[1][1] = 1;
        now_sub_pieces[4].pieces[2][0] = 2;
        now_sub_pieces[4].pieces[2][2] = 1;
        now_sub_pieces[4].pieces[1][1] = 1;
        now_sub_pieces[4].pieces[2][1] = 2;
        now_sub_pieces[5].pieces[0][0] = 1;
        now_sub_pieces[5].pieces[1][0] = 2;
        now_sub_pieces[5].pieces[1][2] = 1;
        now_sub_pieces[6].pieces[0][0] = 1;
        now_sub_pieces[6].pieces[1][0] = 2;
        now_sub_pieces[7].pieces[1][2] = 1;
        now_sub_pieces[8].pieces[2][1] = 2;
        now_sub_pieces[8].pieces[1][1] = 1;
        now_sub_pieces[8].pieces[2][0] = 1;
        now_sub_pieces[8].pieces[0][1] = 2;
        now_sub_pieces[8].pieces[0][2] = 1;
        now_sub_pieces[8].pieces[0][0] = 1;
        */

        //Print the Board and extra print the main board to help the user understand the situation.
        now_main_board.Print_SuperTicTacToe_Board(main_board_length, main_board_width, sub_board_length, sub_board_width, now_sub_pieces);
        System.out.println("\n");
        System.out.println("Now the main board is:");
        now_main_board.PrintBoard(now_main_pieces.pieces);

        //If the user wants a team competition, get the numbers of teams and players in a team.
        if(if_team){
            System.out.print("\nHow many teams do you want for Super-TicTacToe?\n");
            now_teams.Get_Number_of_Teams();

            System.out.print("\nHow many players do you want in a team for Super-TicTacToe?\n");
            now_teams.Get_Number_of_Players_in_Team();

            now_teams.Reset_Team_Member_Scores();
        }
        else{
            System.out.print("\nHow many players do you want for Super-TicTacToe?\n");
            now_players.Get_Number_of_Players();
            now_players.Reset_Players_Scores();
        }

        //Set up a new round.
        Round round = new Round();
        round.ResetRound();

        //Set up new rules.
        Rules rule = new Rules();

        //Start the game now!
        do{
            //Assume the whole Super TicTacToe game is not over, which means the user still wants to play.
            if_gameover = false;

            //Get the number of player participating the game.
            if(if_team){
                this.id_of_player_in_team1 = now_players.Get_Number_of_Participating_Player(1, now_teams.number_of_players_in_a_team);
                this.id_of_player_in_team2 = now_players.Get_Number_of_Participating_Player(2, now_teams.number_of_players_in_a_team);

                now_main_board.Print_SuperTicTacToe_Board(main_board_length, main_board_width, sub_board_length, sub_board_width, now_sub_pieces);
                System.out.println("\n");
                System.out.println("Now the main board is:");
                now_main_board.PrintBoard(now_main_pieces.pieces);
            }
            do{//Begin a new Super TicTacToe game.
                //Start a new round.
                round.Next_Round();
                do{
                    //Get the player's next move and check if it's valid
                    this.main_this_move = round.Next_Round_Board_Choose(now_main_board.board_length, now_main_board.board_width);
                    this.if_this_choice_valuable = rule.If_Choice_Valid(now_main_pieces.pieces, main_this_move[0], main_this_move[1]);
                    if(this.if_this_choice_valuable) {
                        System.out.println("Your choice of the big board is (" + main_this_move[0] + ", " + main_this_move[1] + ")");
                        this.sub_this_move = round.Next_Round_Move(now_sub_boards[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1].board_length, now_sub_boards[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1].board_width);
                        this.if_this_move_valuable = rule.If_Move_Valid(now_sub_pieces[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1].pieces, sub_this_move[0], sub_this_move[1]);
                        if (this.if_this_move_valuable) {
                            //Record this move(Put the piece on the small board).
                            System.out.println("\nYour choice of the big board is (" + main_this_move[0] + ", " + main_this_move[1] + ")");
                            System.out.println("Your move of the small board is (" + sub_this_move[0] + ", " + sub_this_move[1] + ")");
                            this.now_round = round.Get_Round();
                            if (now_round % 2 == 1) {
                                now_sub_pieces[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1].pieces = now_sub_pieces[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1].Update_Pieces(sub_this_move[0] - 1, sub_this_move[1] - 1, 1);
                            }
                            else {
                                now_sub_pieces[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1].pieces = now_sub_pieces[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1].Update_Pieces(sub_this_move[0] - 1, sub_this_move[1] - 1, 2);
                            }

                            //Check if the TTT game on this small board is over
                            result = rule.TicTacToeIf_Over(now_sub_pieces[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1].pieces, now_sub_boards[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1].board_length, now_sub_boards[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1].board_width);
                            if(result[0] == 1){//If the TTT game on this small board is over, put a piece on the cell of the corresponding main board.
                                if_small_board_gameover[(main_this_move[0]-1) * now_main_board.board_width + main_this_move[1] - 1] = true;
                                winner_id = result[1];
                                if(winner_id == 0){//If the game on small board is draw, record it by put a no meaning piece on it.
                                    System.out.println("\nIn the ("+ main_this_move[0] +", "+main_this_move[1] +") small board. Draw!!!");
                                    now_main_pieces.pieces = now_main_pieces.Update_Pieces(main_this_move[0]-1, main_this_move[1]-1, 3);
                                }
                                else if(winner_id == 1){//If Player 1 wins the game on small board, record it by put a 'O' piece on it.
                                    System.out.println("\nIn the ("+ main_this_move[0] +", "+main_this_move[1] +") small board. Player 1 wins!!!\n");
                                    now_main_pieces.pieces = now_main_pieces.Update_Pieces(main_this_move[0]-1, main_this_move[1]-1, 1);
                                }
                                else if(winner_id ==2){//If Player 2 wins the game on small board, record it by put a 'X' piece on it.
                                    System.out.println("\nIn the ("+ main_this_move[0] +", "+main_this_move[1] +") small board. Player 2 wins!!!\n");
                                    now_main_pieces.pieces = now_main_pieces.Update_Pieces(main_this_move[0]-1, main_this_move[1]-1, 2);
                                }
                            }
                        }
                        else {
                            System.out.println("This position of small board already has a piece\nPlease choose a different position");
                        }
                    }
                    else{
                        System.out.println("This position of big board already has a piece\nPlease choose a different position");
                    }
                }while(!this.if_this_choice_valuable || !this.if_this_move_valuable);

                //Print the board.
                System.out.println("\nRound "+this.now_round);
                now_main_board.Print_SuperTicTacToe_Board(main_board_length, main_board_width, sub_board_length, sub_board_width, now_sub_pieces);
                System.out.println("\n");
                System.out.println("Now the main board is:");
                now_main_board.PrintBoard(now_main_pieces.pieces);

                //Check if the Super TTT game is over.
                result = rule.TicTacToeIf_Over(now_main_pieces.pieces,now_main_board.board_length, now_main_board.board_width);
                if(result[0] == 1){//The Super TTT game is over, record the result.
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
            //Get the answer of whether continue from the user.
            Menu menu = new Menu();
            if_continue = menu.SubMenu();
            if(if_continue){
                now_main_pieces.Reset_Pieces(now_main_board.board_length, now_main_board.board_width);
                for(number_of_sub_board = 0; number_of_sub_board < now_main_board.board_length * now_main_board.board_width; number_of_sub_board++){
                    now_sub_pieces[number_of_sub_board].Reset_Pieces(now_sub_boards[number_of_sub_board].board_length, now_sub_boards[number_of_sub_board].board_width);
                }
                round.ResetRound();
            }
        }while(if_continue);

        //The whole game is over. Show the results.
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
