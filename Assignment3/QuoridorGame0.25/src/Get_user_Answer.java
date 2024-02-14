import java.util.Scanner;

public class Get_user_Answer {// Get answers from the user
    public boolean if_team = false;
    private char choice;
    private String user_enter;
    private boolean result;

    public boolean Get_y_or_n_Answer() throws Exception{// Get a y or n answer from the user
        do {
            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1){
                System.out.println("Please enter only one character!");
            }
            else {
                choice = user_enter.charAt(0);
                if (choice == 'y') {
                    result = true;
                    break;
                }
                else if (choice == 'n') {
                    result = false;
                    break;
                }
                else{
                    System.out.println("Please enter y or n!");
                }
            }
        }while(true);
        return result;
    }
}
