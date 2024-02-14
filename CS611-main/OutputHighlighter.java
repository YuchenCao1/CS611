/**
 * While there is an {@link OutputColor} class to indicate what colors
 * can be used, this class is the actual part where the highlighting
 * is done, hence the name "OutputHighlighter".
 */
public final class OutputHighlighter {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[48;2;255;255;255m";

    public static String red(String message) {
        return ANSI_RED_BACKGROUND + message + ANSI_RESET;
    }

    public static String green(String message) {
        return ANSI_GREEN_BACKGROUND + message + ANSI_RESET;
    }

    public static String yellow(String message) {
        return ANSI_YELLOW_BACKGROUND + message + ANSI_RESET;
    }

    public static String blue(String message) {
        return ANSI_BLUE_BACKGROUND + message + ANSI_RESET;
    }

    public static String purple(String message) {
        return ANSI_PURPLE_BACKGROUND + message + ANSI_RESET;
    }

    public static String cyan(String message) {
        return ANSI_CYAN_BACKGROUND + message + ANSI_RESET;
    }

    public static String white(String message) {
        return ANSI_WHITE_BACKGROUND + message + ANSI_RESET;
    }

    public static String withHighlight(OutputColor color, String message) {
        switch (color) {
            case RED:
                return red(message);
            case GREEN:
                return green(message);
            case YELLOW:
                return yellow(message);
            case BLUE:
                return blue(message);
            case PURPLE:
                return purple(message);
            case CYAN:
                return cyan(message);
            case WHITE:
                return white(message);
            default:
                return message;
        }
    }
}
