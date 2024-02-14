import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is used to simply interact with the user
 * and retrieve data information. Moreover, validation
 * logic to ensure valid user input is done.
 */
public final class UserInteractor {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Method ensures user input is an integer and will prompt
     * until an integer is inputted.
     */
    public static int getIntegerInput(final String prompt){
        boolean validInput = false;
        int userInput = 0;

        while (!validInput){
            System.out.println(OutputColorer.blue(prompt));
            if (scanner.hasNextInt()){
                userInput = scanner.nextInt();
                validInput = true;
            } else {
                String invalidInput = scanner.next();
                System.out.println(OutputColorer.blue("Invalid input ")+OutputColorer.red(invalidInput)+OutputColorer.blue(". Please enter an integer."));
            }
        }

        return userInput;
    }

    /**
     * Method ensures user input is a valid integer that is
     * gte min_num and will prompt until a valid integer is
     * inputted.
     */
    public static int getIntegerInput(final String prompt, final int minNum){
        int userInput;

        do {
            userInput = getIntegerInput(prompt+" (atleast "+minNum+")");
            if (userInput < minNum) {
                System.out.println(OutputColorer.blue("The inputted number ")+OutputColorer.red(String.valueOf(userInput))+OutputColorer.blue(" is not at least "+minNum+"."));
            }
        } while (userInput < minNum);

        return userInput;
    }

    /**
     * Method ensures user input is atleast the min_num and
     * at most max_num and will prompt until a valid integer
     * is inputted.
     */
    public static int getIntegerInput(final String prompt, final int minNum, final int maxNum){
        int userInput;

        do {
            userInput = getIntegerInput(prompt+" (between "+minNum+" and "+maxNum+")");
            if (userInput < minNum || userInput > maxNum) {
                System.out.println(OutputColorer.blue("The inputted number ")+OutputColorer.red(String.valueOf(userInput))+OutputColorer.blue(" is not between "+minNum+" and "+maxNum+"."));
            }
        } while (userInput < minNum || userInput > maxNum);

        return userInput;
    }

    /**
     * Method ensures user input is a string with a restricted
     * minimum string length and maximum string length
     */
    public static String getStringInput(final String prompt, final int minStringLength, final int maxStringLength) {
        String userInput;

        do {
            System.out.println(OutputColorer.blue(prompt));
            userInput = scanner.next().trim();

            if (userInput.length() < minStringLength || userInput.length() > maxStringLength) {
                System.out.println(OutputColorer.blue("Invalid input. The inputted string ")+OutputColorer.red(userInput)+OutputColorer.blue(" is not between " + minStringLength + " and " + maxStringLength + " characters."));
            }
        } while (userInput.length() < minStringLength || userInput.length() > maxStringLength);

        return userInput;
    }

    /**
     * Method ensures user string input is valid by
     * checking if the input is in the given valid
     * responses.
     */
    public static String getStringInput(final String prompt, final String[] validResponses) {
        String userInput;
        boolean validInput;

        do {
            System.out.println(OutputColorer.blue(prompt));
            userInput = scanner.next().trim();

            validInput = false;
            for (String validResponse : validResponses){
                if (validResponse.equals(userInput) || validResponse.compareToIgnoreCase(userInput) == 0){
                    validInput = true;
                    break;
                }
            }
            if (!validInput) {
                System.out.println(OutputColorer.blue("Invalid input. The inputted string ") + OutputColorer.red(userInput) + OutputColorer.blue(" is not " + Arrays.toString(validResponses)));
            }
        } while (!validInput);

        return userInput;
    }

    public static String getYesNoInput(final String prompt){
        String[] choices = {"y", "n"};
        return getStringInput(prompt+" (y/n)", choices);
    }

    /**
     * Method ensures user inputs a valid symbol (character)
     * such that it is given from {@link validOptions}
     */
    public static char getSymbolInput(final String prompt, char[] validOptions){
        char userInput;
        boolean validInput;

        do {
            userInput = getSymbolInput(prompt);
            validInput = false;
            for (char validOption : validOptions){
                if (userInput == validOption){
                    validInput = true;
                    break;
                }
            }
            if (!validInput){
                System.out.println(OutputColorer.blue("Invalid input: input ")+OutputColorer.red(String.valueOf(userInput))+OutputColorer.blue(" must be either "+String.valueOf(validOptions)));
            }
        } while (!validInput);

        return userInput;
    }

    public static char getUniqueSymbolInput(final String prompt){
        boolean validInput = false;
        char userInput;

        do {
            userInput = getSymbolInput(prompt);

            validInput = true;

            for (Player player : Database.PLAYERS){
                if (player.getSymbol() == userInput){
                    validInput = false;
                    System.out.println(OutputColorer.blue("Input ")+OutputColorer.red(String.valueOf(userInput))+OutputColorer.blue(" is already used by another player."));
                    break;
                }
            }
            for (Team team : Database.TEAMS){
                if (team.getSymbol() == userInput){
                    validInput = false;
                    System.out.println(OutputColorer.blue("Input ")+OutputColorer.red(String.valueOf(userInput))+OutputColorer.blue(" is already used by another team."));
                    break;
                }
            }
        } while (!validInput);

        return userInput;
    };

    private static char getSymbolInput(final String prompt) {
        char userInput;
        boolean validInput = false;

        do {
            System.out.println(OutputColorer.blue(prompt));
            userInput = scanner.next().charAt(0);

            validInput = true;
            if (!Character.isAlphabetic(userInput)) {
                System.out.println(OutputColorer.blue("Invalid input ")+OutputColorer.red(String.valueOf(userInput))+OutputColorer.blue(". Please enter an alphabetic character."));
                validInput = false;
            }
        } while (!validInput);

        return userInput;
    }

    public static <T extends Enum<T>> T getEnumInput(Class<T> enumType, String prompt) {
        T[] validInputs = enumType.getEnumConstants();
        String[] validInputStrings = Arrays.stream(validInputs)
                .map(Enum::toString)
                .map(String::toLowerCase)
                .toArray(String[]::new);

        String stringInput = getStringInput(prompt + " " + Arrays.toString(validInputStrings), validInputStrings);

        return Enum.valueOf(enumType, stringInput.toUpperCase());
    }
}
