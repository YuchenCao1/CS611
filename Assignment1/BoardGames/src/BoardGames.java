import java.util.*;

interface Start_Board_Game {
    void Start(int selection) throws Exception;
}

class Menu {  //Create menu
    private String user_enter;
    private boolean exit_or_not = false;
    private char choice;
    private boolean if_continue;
    private boolean exit_or_continue;

    public void MainMenu() throws Exception { //Create the main menu

        do {
            System.out.println("\n===============BoardGames===============");
            System.out.println("\t1. Start a new Tic Tac Toe game");
            System.out.println("\t2. Start a new Order and Chaos game");
            System.out.println("\t3. Exit");
            System.out.print("Please enter your choice(1-3)\n");

            Scanner sc = new Scanner(System.in);
            String user_enter = sc.next();
            if (user_enter.length() != 1) {
                System.out.println("Please enter only one character!");
            } else {
                choice = user_enter.charAt(0);
                switch (choice) {
                    case '1':
                        Start_Board_Game new_TicTacToe_game = new Play_BoardGames();
                        new_TicTacToe_game.Start(1);
                        break;
                    case '2':
                        Start_Board_Game new_OrderandChaos_game = new Play_BoardGames();
                        new_OrderandChaos_game.Start(2);
                        break;
                    case '3':
                        Exit();
                        exit_or_not = true;
                        break;
                    default:
                        System.out.println("Please enter a valid choice!");
                        break;
                }
            }
        } while (!exit_or_not);
    }

    public void Exit() throws Exception {  //Exit the board game

        System.out.println("Are you sure to exit(y/n)?");
        do {
            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1) {
                System.out.println("Please enter only one character!");
            } else {
                choice = user_enter.charAt(0);
                if (choice == 'y') {
                    exit_or_not = true;
                    break;
                } else if (choice == 'n') {
                    MainMenu();
                    break;
                } else {
                    System.out.println("Please enter y or n!");
                }
            }
        } while (true);
    }

    public boolean SubMenu() throws Exception { //Create the main menu
        do {
            System.out.println("1. Continue");
            System.out.println("2. Exit");
            System.out.print("Please enter your choice(1-2)\n");

            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1){
                System.out.println("\n\nPlease enter only one character!");
            }
            else {
                choice = user_enter.charAt(0);
                switch (choice){
                    case '1':
                        exit_or_continue = true;
                        if_continue = true;
                        break;
                    case '2':
                        exit_or_continue = true;
                        if_continue = false;
                        break;
                    default:
                        System.out.println("Please enter a valid choice!");
                        break;
                }
            }
        }while (!exit_or_continue);
        return if_continue;
    }
}

class Play_BoardGames implements Start_Board_Game{// Play the boardgames
    private char choice;
    private boolean if_team = false;
    private int boardsize;
    private int number_of_players;
    private int number_of_teams;
    private int number_of_players_in_a_team;
    private int id_of_player_in_team1;
    private int id_of_player_in_team2;
    private boolean if_continue = true;
    private String user_enter;
    private int each_player;
    private boolean exit_or_continue = false;
    private int[] this_move = new int[2];
    private int this_move_piece;
    private boolean if_this_move_valuable;
    private int now_round;
    private boolean if_gameover = false;
    private int[] result = new int[2];
    private int winner_id;

    public void Start(int selection) throws Exception{
        if(selection == 1){
            Start_TicTacToe();
        }
        else if(selection == 2){
            Start_OrderandChaos();
        }
    }
    public void Start_TicTacToe() throws Exception{// Start a Tic Tac Toe game
        System.out.println("\nWelcome to Tic Tac Toe Game");

        Board now_board = new Board(0);

        Piece now_pieces = new Piece();

        Player now_players = new Player();
        Team now_teams = new Team();

        System.out.println("Do you want to have a team competition?(y/n)");
        do {
            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1){
                System.out.println("Please enter only one character!");
            }
            else {
                choice = user_enter.charAt(0);
                if (choice == 'y') {
                    if_team = true;
                    break;
                }
                else if (choice == 'n') {
                    break;
                }
                else{
                    System.out.println("Please enter y or n!");
                }
            }
        }while(true);

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
                    now_board.Reset_Board_Size(this.boardsize);
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number from 3 to 6!");
                }
            }
        }while(true);

        now_board.PrintBoard(now_pieces.pieces);

        if(if_team){
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
                        now_teams.number_of_teams = this.number_of_teams;
                        break;
                    }
                    else{
                        System.out.println("\n\nFor this game, please only enter 2");
                    }
                }
            }while(true);

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
                        now_teams.number_of_players_in_a_team = this.number_of_players_in_a_team;
                        break;
                    }
                    else{
                        System.out.println("\n\nPlease enter 1-3");
                    }
                }
            }while(true);

            now_teams.Reset_Team_Member_Scores();
        }
        else{
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
                        now_players.number_of_players = this.number_of_players;
                        break;
                    }
                    else{
                        System.out.println("\n\nFor this game, please only enter 2");
                    }
                }
            }while(true);

            now_players.Reset_Players_Scores();
        }

        Round round = new Round();
        round.ResetRound();

        Rules rule = new Rules();

        do{
            if_gameover = false;
            if(if_team){
                System.out.println("\nWhich player from Team 1 is participating in this match?");
                System.out.println("Please enter a number less than "+ (now_teams.number_of_players_in_a_team+1)+ " and more than 0");

                do {
                    Scanner sc = new Scanner(System.in);
                    user_enter = sc.next();
                    if (user_enter.length() != 1){
                        System.out.println("\n\nPlease enter a number less than "+ (now_teams.number_of_players_in_a_team+1)+ " and more than 0");
                    }

                    else {
                        choice = user_enter.charAt(0);
                        this.id_of_player_in_team1 = Character.getNumericValue(choice);
                        if (id_of_player_in_team1 <= now_teams.number_of_players_in_a_team && id_of_player_in_team1 > 0) {
                            break;
                        } else {
                            System.out.println("\n\nPlease enter a number less than "+ (now_teams.number_of_players_in_a_team+1) + " and more than 0");
                        }
                    }
                }while(true);

                System.out.println("\nWhich player from Team 2 is participating in this match?");
                System.out.println("Please enter a number less than "+ (now_teams.number_of_players_in_a_team+1)+ " and more than 0");

                do {
                    Scanner sc = new Scanner(System.in);
                    user_enter = sc.next();
                    if (user_enter.length() != 1){
                        System.out.println("\n\nPlease enter a number less than "+ (now_teams.number_of_players_in_a_team+1));
                    }

                    else {
                        choice = user_enter.charAt(0);
                        this.id_of_player_in_team2 = Character.getNumericValue(choice);
                        if (id_of_player_in_team2 <= now_teams.number_of_players_in_a_team && id_of_player_in_team2 > 0) {
                            break;
                        } else {
                            System.out.println("\n\nPlease enter a number less tha "+ (now_teams.number_of_players_in_a_team+1));
                        }
                    }
                }while(true);

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
                now_pieces.Reset_Pieces();
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

    public void Start_OrderandChaos() throws Exception{// Start a Tic Tac Toe game
        System.out.println("\nWelcome to Order and Chaos Game");
        System.out.println("Player 1 is the attacker, and Player 2 is the defender");

        Board now_board = new Board(0);
        now_board.Reset_Board_Size(6);

        Piece now_pieces = new Piece();

        Player now_players = new Player();

        now_board.PrintBoard(now_pieces.pieces);

        do {
            System.out.print("\nHow many players do you want for Order and Chaos?\n");
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
                    now_players.number_of_players = this.number_of_players;
                    break;
                }
                else{
                    System.out.println("\n\nFor this game, please only enter 2");
                }
            }
        }while(true);

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
                now_pieces.Reset_Pieces();
                round.ResetRound();
            }
        }while(if_continue);
        System.out.println("\nGame over! The result is:");
        for(each_player = 0; each_player < now_players.number_of_players;each_player++) {
            System.out.println("Player" + (each_player+1) + ": " + now_players.Get_Score(each_player+1)+" points!");
        }
    }
}

class Board{ //Create the board
    public int board_size;
    private int row;
    private int column;
    public Board(int board_size){
        this.board_size = board_size;
    }
    public void Reset_Board_Size(int board_size) throws Exception{//Reset size of board
        this.board_size = board_size;
    }
    public void PrintBoard(int[][] pieces) throws Exception{//Print the board
        for (column = 0; column < board_size-1; column++) {
            if (pieces[0][column] == 0){
                System.out.printf("\t|");
            }
            else if(pieces[0][column] == 1){
                System.out.printf("O");
                System.out.printf("\t|");
            }
            else if(pieces[0][column] == 2){
                System.out.printf("X");
                System.out.printf("\t|");
            }
        }
        if (pieces[0][board_size-1] == 0){
            System.out.printf("\n");
        }
        else if(pieces[0][board_size-1] == 1){
            System.out.printf("O\n");
        }
        else if(pieces[0][board_size-1] == 2){
            System.out.printf("X\n");
        }
        for (row = 1; row < board_size; row++){
            for (column = 0; column < board_size; column++) {
                System.out.printf("--------");
            }
            System.out.printf("\n");
            for (column = 0; column < board_size-1; column++) {
                if (pieces[row][column] == 0){
                    System.out.printf("\t|");
                }
                else if(pieces[row][column] == 1){
                    System.out.printf("O");
                    System.out.printf("\t|");
                }
                else if(pieces[row][column] == 2){
                    System.out.printf("X");
                    System.out.printf("\t|");
                }
            }
            if (pieces[row][board_size-1] == 0){
                System.out.printf("\n");
            }
            else if(pieces[row][board_size-1] == 1){
                System.out.printf("O\n");
            }
            else if(pieces[row][board_size-1] == 2){
                System.out.printf("X\n");
            }
        }

    }
}

class Piece{//Create pieces
    public int[][] pieces = new int[6][6];
    private int row;
    private int column;
    public Piece(){
        for (row=0; row<=5; row++){
            for (column=0; column<=5; column++){
                pieces[row][column] = 0;
            }
        }
    }

    public void Reset_Pieces() throws Exception{//Reset pieces
        for (row=0; row<=5; row++){
            for (column=0; column<=5; column++){
                pieces[row][column] = 0;
            }
        }
    }

    public int[][] Update_Pieces(int row, int column, int piece_type) throws Exception{//Update piece(to record each move)
        pieces[row][column] = piece_type;
        return this.pieces;
    }
}

class Rules{//Create Tic Tac Toe rules
    private int is_over;
    private int[] result = new int[2];
    public int winner_id;
    private int row;
    private int column;
    private int next;
    private int first_piece;
    private boolean still_possible_over = true;
    public boolean If_Move_Valid(int[][] pieces, int this_move_row, int this_move_column) throws Exception{//Check if the player's move is valid
        if(pieces[this_move_row-1][this_move_column-1] == 0) {
            return true;
        }
        else{
            return false;
        }
    }
    public int[] TicTacToeIf_Over(int[][] pieces, int board_size) throws Exception{//Check if the Tic Tac Toe game is over

        //Check the horizontal arrangement of pieces
        column = 0;
        for (row = 0; row < board_size; row++) {
            still_possible_over = true;
            first_piece = pieces[row][column];
            if(first_piece == 0){
                still_possible_over = false;
            }
            next = 1;
            while(still_possible_over && next < board_size){
                if (pieces[row][column + next] != first_piece) {
                    still_possible_over = false;
                }
                next++;
            }
            if(still_possible_over){
                this.is_over = 1;
                this.result[0] = this.is_over;
                this.result[1] = first_piece;
                return result;
            }
        }

        //Check the vertical arrangement of pieces
        row = 0;
        for (column = 0; column < board_size; column++) {
            still_possible_over = true;
            first_piece = pieces[row][column];
            if(first_piece == 0){
                still_possible_over = false;
            }
            next = 1;
            while(still_possible_over && next < board_size){
                if (pieces[row + next][column] != first_piece) {
                    still_possible_over = false;
                }
                next++;
            }
            if(still_possible_over){
                this.is_over = 1;
                this.result[0] = this.is_over;
                this.result[1] = first_piece;
                return result;
            }
        }

        //Check the diagonal arrangement of pieces
        row = 0;
        column = 0;
        still_possible_over = true;
        first_piece = pieces[row][column];
        if(first_piece == 0){
            still_possible_over = false;
        }
        next = 1;
        while(still_possible_over && next < board_size){
            if (pieces[row + next][column + next] != first_piece) {
                still_possible_over = false;
            }
            next++;
        }
        if(still_possible_over){
            this.is_over = 1;
            this.result[0] = this.is_over;
            this.result[1] = first_piece;
            return result;
        }

        //Also check the diagonal arrangement of pieces
        row = 0;
        column = board_size - 1;
        still_possible_over = true;
        first_piece = pieces[row][column];
        if(first_piece == 0){
            still_possible_over = false;
        }
        next = 1;
        while(still_possible_over && next < board_size){
            if (pieces[row + next][column - next] != first_piece) {
                still_possible_over = false;
            }
            next++;
        }
        if(still_possible_over){
            this.is_over = 1;
            this.result[0] = this.is_over;
            this.result[1] = first_piece;
            return result;
        }

        //Check if the chessboard is full
        this.is_over = 1;
        for (row = 0; row < board_size; row++) {
            for (column = 0; column < board_size; column++) {
                //System.out.println("pieces["+row+"]["+column+"] is "+pieces[row][column]);
                if (pieces[row][column] == 0) {
                    this.is_over = 0;
                    this.result[0] = this.is_over;
                    return this.result;
                }
            }
        }
        this.result[0] = this.is_over;
        this.result[1] = 0;
        return this.result;
    }

    public int[] OrderandChaosIf_Over(int[][] pieces) throws Exception{//Check if the Tic Tac Toe game is over
        //Check the horizontal arrangement of pieces
        for (row = 0; row < 6; row++){
            for(column = 0; column < 2; column++){
                if (pieces[row][column] == 1 && pieces[row][column+1] == 1 && pieces[row][column+2] == 1 && pieces[row][column+3] == 1 && pieces[row][column+4] == 1){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
                if (pieces[row][column] == 2 && pieces[row][column+1] == 2 && pieces[row][column+2] == 2 && pieces[row][column+3] == 2 && pieces[row][column+4] == 2){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
            }
        }

        //Check the vertical arrangement of pieces
        for (column = 0; column < 6; column++){
            for(row = 0; row < 2; row++){
                if (pieces[row][column] == 1 && pieces[row+1][column] == 1 && pieces[row+2][column] == 1 && pieces[row+3][column] == 1 && pieces[row+4][column] == 1){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
                if (pieces[row][column] == 2 && pieces[row+1][column] == 2 && pieces[row+2][column] == 2 && pieces[row+3][column] == 2 && pieces[row+4][column] == 2){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
            }
        }

        //Check the diagonal arrangement of pieces
        for (row = 0; row < 2; row++){
            for(column = 0; column < 2; column++){
                if (pieces[row][column] == 1 && pieces[row+1][column+1] == 1 && pieces[row+2][column+2] == 1 && pieces[row+3][column+3] == 1 && pieces[row+4][column+4] == 1){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
                if (pieces[row][column] == 2 && pieces[row+1][column+1] == 2 && pieces[row+2][column+2] == 2 && pieces[row+3][column+3] == 2 && pieces[row+4][column+4] == 2){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
            }
        }

        //Also check the diagonal arrangement of pieces
        for (row = 0; row < 2; row++){
            for(column = 5; column > 3; column--){
                if (pieces[row][column] == 1 && pieces[row+1][column-1] == 1 && pieces[row+2][column-2] == 1 && pieces[row+3][column-3] == 1 && pieces[row+4][column-4] == 1){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
                if (pieces[row][column] == 2 && pieces[row+1][column-1] == 2 && pieces[row+2][column-2] == 2 && pieces[row+3][column-3] == 2 && pieces[row+4][column-4] == 2){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
            }
        }

        //Check if the chessboard is full
        this.is_over = 1;
        for (row = 0; row < 6; row++) {
            for (column = 0; column < 6; column++) {
                //System.out.println("pieces["+row+"]["+column+"] is "+pieces[row][column]);
                if (pieces[row][column] == 0) {
                    this.is_over = 0;
                    this.result[0] = this.is_over;
                    return this.result;
                }
            }
        }
        this.result[0] = this.is_over;
        if(this.is_over == 1){
            this.result[1] = 2;
        }
        return this.result;
    }
}

class Team{//Create players
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

class Player{//Create players
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

class Round{//Create round
    private char choice;
    private String user_enter;
    public int now_round;
    private int[] this_move = new int[2];
    private int this_move_row;
    private int this_move_column;
    private int piece_type;
    public void ResetRound() throws Exception{//Reset round
        now_round = 0;
    }
    public void Next_Round() throws Exception{//Begin next round
        now_round++;
    }
    public int Get_Round() throws Exception{//Get the number of current round
        return now_round;
    }
    public int[] Next_Round_Move(int board_size) throws Exception{//Let the player give his/her next move
        if (now_round % 2 == 1){
            System.out.print("\nPlayer 1, please enter the position you want\n");
        }
        else{
            System.out.print("\nPlayer 2, please enter the position you want\n");
        }

        System.out.print("Please enter row(a number from 1 to "+board_size+"):\n");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1){
                System.out.println("\n\nPlease enter only a number from 1 to "+board_size);
            }

            else {
                this.choice = user_enter.charAt(0);
                this.this_move_row = Character.getNumericValue(choice);
                if (this_move_row <= board_size && this_move_row > 0) {
                    this_move[0] = this_move_row;
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number from 1 to "+board_size);
                }
            }
        }while(true);

        System.out.print("Please enter column(a number from 1 to "+board_size+"):\n");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1){
                System.out.println("\n\nPlease enter only a number from 1 to "+board_size);
            }

            else {
                this.choice = user_enter.charAt(0);
                this.this_move_column = Character.getNumericValue(choice);
                if (this_move_column <= board_size && this_move_column > 0) {
                    this_move[1] = this_move_column;
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number from 1 to "+board_size);
                }
            }
        }while(true);

        return this.this_move;
    }

    public int Next_Round_Piece() throws Exception{//Let the player give his/her choice of pieces
        System.out.println("\nPlease enter the type of piece you want");
        System.out.println("(choose 'O', enter 1; choose 'X', enter 2)");
        System.out.println("Please enter your choice(1 or 2)");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1){
                System.out.println("\n\nPlease enter only a number 1 or 2");
            }

            else {
                this.choice = user_enter.charAt(0);
                this.piece_type = Character.getNumericValue(choice);
                if (piece_type == 1 || piece_type == 2) {
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number 1 or 2");
                }
            }
        }while(true);

        return piece_type;
    }
}

public class BoardGames {
    public static void main(String[] args) throws Exception{
        System.out.println("Welcome to BoardGames!!!");
        Menu menu_first = new Menu();
        menu_first.MainMenu();
        System.out.println("\nSee you next time!!!");
    }
}