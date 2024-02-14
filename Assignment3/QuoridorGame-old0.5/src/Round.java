import java.util.Scanner;
public class Round {//Create round
    private char choice;
    private int next_choice;
    private String user_enter;
    public int now_round;
    private int row;
    private int column;
    private int now_piece_row;
    private int now_piece_column;
    private int[] this_move = new int[2];
    private int[] this_placement = new int[8];
    private int[] big_board_choice = new int[2];
    private int this_move_row;
    private int this_move_column;
    private int this_choice_row;
    private int this_choice_column;
    private int piece_type;

    public void ResetRound() throws Exception {//Reset round
        now_round = 0;
    }

    public void Next_Round() throws Exception {//Begin next round
        now_round++;
    }

    public int Get_Round() throws Exception {//Get the number of current round
        return now_round;
    }

    public int[] Next_Round_Move(int board_length, int board_width) throws Exception {//Let the player give his/her next move
        if (now_round % 2 == 1) {
            System.out.print("\nPlayer 1, please enter the position you want\n");
        } else {
            System.out.print("\nPlayer 2, please enter the position you want\n");
        }

        System.out.print("Please enter row(a number from 1 to " + board_length + "):\n");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\n\nPlease enter only a number from 1 to " + board_length);
            } else {
                this.choice = user_enter.charAt(0);
                this.this_move_row = Character.getNumericValue(choice);
                if (this_move_row <= board_length && this_move_row > 0) {
                    this_move[0] = this_move_row;
                    break;
                } else {
                    System.out.println("\n\nPlease enter only a number from 1 to " + board_length);
                }
            }
        } while (true);

        System.out.print("Please enter column(a number from 1 to " + board_width + "):\n");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\n\nPlease enter only a number from 1 to " + board_width);
            } else {
                this.choice = user_enter.charAt(0);
                this.this_move_column = Character.getNumericValue(choice);
                if (this_move_column <= board_width && this_move_column > 0) {
                    this_move[1] = this_move_column;
                    break;
                } else {
                    System.out.println("\n\nPlease enter only a number from 1 to " + board_width);
                }
            }
        } while (true);
        return this.this_move;
    }

    public int[] Next_Round_Board_Choose(int main_board_length, int main_board_width) throws Exception {//Let the player give his/her next move on main board
        if (now_round % 2 == 1) {
            System.out.print("\nPlayer 1, please choose a position of big board you want\n");
        } else {
            System.out.print("\nPlayer 2, please choose a position of big board you want\n");
        }

        System.out.print("Please enter row(a number from 1 to " + main_board_length + "):\n");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\n\nPlease enter only a number from 1 to " + main_board_length);
            } else {
                this.choice = user_enter.charAt(0);
                this.this_choice_row = Character.getNumericValue(choice);
                if (this_choice_row <= main_board_length && this_choice_row > 0) {
                    big_board_choice[0] = this_choice_row;
                    break;
                } else {
                    System.out.println("\n\nPlease enter only a number from 1 to " + main_board_length);
                }
            }
        } while (true);

        System.out.print("Please enter column(a number from 1 to " + main_board_width + "):\n");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\n\nPlease enter only a number from 1 to " + main_board_width);
            } else {
                this.choice = user_enter.charAt(0);
                this.this_choice_column = Character.getNumericValue(choice);
                if (this_choice_column <= main_board_width && this_choice_column > 0) {
                    big_board_choice[1] = this_choice_column;
                    break;
                } else {
                    System.out.println("\n\nPlease enter only a number from 1 to " + main_board_width);
                }
            }
        } while (true);
        return this.big_board_choice;
    }

    public int Next_Round_Piece() throws Exception {//Let the player give his/her choice of pieces
        System.out.println("\nPlease enter the type of piece you want");
        System.out.println("(choose 'O', enter 1; choose 'X', enter 2)");
        System.out.println("Please enter your choice(1 or 2)");
        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\n\nPlease enter only a number 1 or 2");
            } else {
                this.choice = user_enter.charAt(0);
                this.piece_type = Character.getNumericValue(choice);
                if (piece_type == 1 || piece_type == 2) {
                    break;
                } else {
                    System.out.println("\n\nPlease enter only a number 1 or 2");
                }
            }
        } while (true);

        return piece_type;
    }

    public int Next_Round_Choice(int wall_number) throws Exception {//Let the player give their choice of next step
        if (wall_number > 0) {
            System.out.println("\n\n1. Move your piece");
            System.out.println("2. Place a new wall (you only have" + wall_number + "walls)");
            System.out.println("Please enter your choice(1 or 2)");
            do {
                Scanner sc = new Scanner(System.in);
                this.user_enter = sc.next();
                if (this.user_enter.length() != 1) {
                    System.out.println("\n\nPlease enter only a number 1 or 2");
                } else {
                    this.choice = user_enter.charAt(0);
                    this.next_choice = Character.getNumericValue(choice);
                    if (next_choice == 1 || next_choice == 2) {
                        break;
                    } else {
                        System.out.println("\n\nPlease enter only a number 1 or 2");
                    }
                }
            } while (true);
        } else {
            System.out.println("\nYou have no walls, you can only move your piece");
            next_choice = 1;
        }
        return next_choice;
    }

    public char Next_Round_Movement() throws Exception {//Let the player give their choice of next step
        if (now_round % 2 == 1) {
            System.out.println("Player 1, please enter your movement W/A/S/D (up/left/down/right)");
        } else {
            System.out.println("Player 2, please enter your movement W/A/S/D (up/left/down/right)");
        }

        do {
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\nPlease enter a valid movement W/A/S/D !");
            } else {
                this.choice = user_enter.charAt(0);
                switch (choice) {
                    case 'W':
                        System.out.println("\nYour movement is W");
                        return choice;
                    case 'A':
                        System.out.println("\nYour movement is A");
                        return choice;
                    case 'S':
                        System.out.println("\nYour movement is S");
                        return choice;
                    case 'D':
                        System.out.println("\nYour movement is D");
                        return choice;
                    default:
                        System.out.println("Please enter a valid movement W/A/S/D !");
                        break;
                }
            }
        } while (true);
    }

    public int[] Next_Round_Placement(int board_length, int board_width) throws Exception {//Let the player give their choice of next step
        //写一个函数，用于拿到用户给的放板子的坐标，这个返回的this_placement我设置成了一个长度为8的数组，可以用来放四个坐标
        //随便你和用户是怎么沟通和交流的，无论用户是以什么方式输入，最后只要保证：
        //(this_placement[0],this_placement[1])和(this_placement[2],this_placement[3])之间是要加的墙。
        //(this_placement[4],this_placement[5])和(this_placement[6],this_placement[7])之间也是要加的墙；
        //要用到什么变量也可以在这个类的最上面定义变量的地方新定义
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            System.out.println("Please enter the wall placement as an 8 digit number");

            input = scanner.nextLine();

            if (!input.matches("\\d{8}")) {
                System.out.println("Invalid input. Please enter exactly 8 digits.");
            }

        } while (!input.matches("\\d{8}"));

        for (int i = 0; i < 8; i++) {
            this_placement[i] = Character.getNumericValue(input.charAt(i));
        }

        return this_placement;
    }
}