import java.util.Scanner;

public class Menu {  //Create menu
    private boolean exit_or_not = false;
    private char choice;
    private boolean if_continue;

    public void MainMenu() throws Exception { //Create the main menu

        do {
            System.out.println("\n===============BoardGames===============");
            System.out.println("\t1. Start a new Tic Tac Toe game");
            System.out.println("\t2. Start a new Order and Chaos game");
            System.out.println("\t3. Start a new Super Tic Tac Toe game");
            System.out.println("\t4. Start a new Quoridor game");
            System.out.println("\t5. Exit");
            System.out.print("Please enter your choice(1-5)\n");

            Scanner sc = new Scanner(System.in);
            String user_enter = sc.next();
            if (user_enter.length() != 1) {
                System.out.println("Please enter only one character!");
            } else {
                choice = user_enter.charAt(0);
                switch (choice) {
                    case '1':
                        Game new_TicTacToe_game = new TicTacToe();
                        new_TicTacToe_game.Start();
                        break;
                    case '2':
                        Game new_OrderandChaos_game = new Order_and_Chaos();
                        new_OrderandChaos_game.Start();
                        break;
                    case '3':
                        Game new_SuperTicTacToe_game = new Super_TicTacToe();
                        new_SuperTicTacToe_game.Start();
                        break;
                    case '4':
                        Game new_Quoridor_game = new QuoridorGame();
                        new_Quoridor_game.Start();
                        break;
                    case '5':
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
        Get_user_Answer get_answer = new Get_user_Answer();

        System.out.println("Are you sure to exit?(y/n)");
        exit_or_not = get_answer.Get_y_or_n_Answer();
        if(!exit_or_not){
            MainMenu();
        }
    }

    public boolean SubMenu() throws Exception { //Create the main menu
        Get_user_Answer get_answer = new Get_user_Answer();

        System.out.println("Do you want to continue?(y/n)");
        if_continue = get_answer.Get_y_or_n_Answer();

        return if_continue;
    }
}