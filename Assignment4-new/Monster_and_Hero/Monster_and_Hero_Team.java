import java.util.Scanner;

public class Monster_and_Hero_Team {//Create players
    private char choice;
    private String user_enter;
    private int number_of_heroes_in_a_team;



    public void Get_Number_of_Heroes_in_Team() throws Exception {// Get number of players in a team for Tic Tac Toe game
        System.out.print("Please enter 1-3\n");
        do {
            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1) {
                System.out.println("\n\nPlease enter 1-3");
            } else {
                choice = user_enter.charAt(0);
                this.number_of_heroes_in_a_team = Character.getNumericValue(choice);
                if (number_of_heroes_in_a_team <= 3 && number_of_heroes_in_a_team >= 1) {
                    break;
                } else {
                    System.out.println("\n\nPlease enter 1-3");
                }
            }
        } while (true);
    }

    public int Get_Number_of_Heroes_in_a_Team() throws Exception{//Get one player's score
        return number_of_heroes_in_a_team;
    }
}