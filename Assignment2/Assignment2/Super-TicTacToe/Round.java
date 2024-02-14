import java.util.Scanner;
public class Round{//Create round
    private char choice;
    private String user_enter;
    public int now_round;
    private int[] this_move = new int[2];
    private int[] big_board_choice = new int[2];
    private int this_move_row;
    private int this_move_column;
    private int this_choice_row;
    private int this_choice_column;
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
    public int[] Next_Round_Move(int board_length, int board_width) throws Exception{//Let the player give his/her next move
        if (now_round % 2 == 1){
            System.out.print("\nPlayer 1, please enter the position you want\n");
        }
        else{
            System.out.print("\nPlayer 2, please enter the position you want\n");
        }

        System.out.print("Please enter row(a number from 1 to "+board_length+"):\n");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1){
                System.out.println("\n\nPlease enter only a number from 1 to "+board_length);
            }

            else {
                this.choice = user_enter.charAt(0);
                this.this_move_row = Character.getNumericValue(choice);
                if (this_move_row <= board_length && this_move_row > 0) {
                    this_move[0] = this_move_row;
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number from 1 to "+board_length);
                }
            }
        }while(true);

        System.out.print("Please enter column(a number from 1 to "+board_width+"):\n");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1){
                System.out.println("\n\nPlease enter only a number from 1 to "+board_width);
            }

            else {
                this.choice = user_enter.charAt(0);
                this.this_move_column = Character.getNumericValue(choice);
                if (this_move_column <= board_width && this_move_column > 0) {
                    this_move[1] = this_move_column;
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number from 1 to "+board_width);
                }
            }
        }while(true);
        return this.this_move;
    }

    public int[] Next_Round_Board_Choose(int main_board_length, int main_board_width) throws Exception{//Let the player give his/her next move on main board
        if (now_round % 2 == 1){
            System.out.print("\nPlayer 1, please choose a position of big board you want\n");
        }
        else{
            System.out.print("\nPlayer 2, please choose a position of big board you want\n");
        }

        System.out.print("Please enter row(a number from 1 to "+main_board_length+"):\n");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1){
                System.out.println("\n\nPlease enter only a number from 1 to "+main_board_length);
            }

            else {
                this.choice = user_enter.charAt(0);
                this.this_choice_row = Character.getNumericValue(choice);
                if (this_choice_row <= main_board_length && this_choice_row > 0) {
                    big_board_choice[0] = this_choice_row;
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number from 1 to "+main_board_length);
                }
            }
        }while(true);

        System.out.print("Please enter column(a number from 1 to "+main_board_width+"):\n");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1){
                System.out.println("\n\nPlease enter only a number from 1 to "+main_board_width);
            }

            else {
                this.choice = user_enter.charAt(0);
                this.this_choice_column = Character.getNumericValue(choice);
                if (this_choice_column <= main_board_width && this_choice_column > 0) {
                    big_board_choice[1] = this_choice_column;
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number from 1 to "+main_board_width);
                }
            }
        }while(true);
        return this.big_board_choice;
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
