/**
 * While there is an {@link OutputColor} class to indicate what colors
 * can be used, this class is the actual part where the coloring
 * is done, hence the name "OutputColorer".
 */
public final class OutputColorer {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";

    public static String red(String message){
        return ANSI_RED+message+ANSI_RESET;
    }

    public static String green(String message){
        return ANSI_GREEN+message+ANSI_RESET;
    }

    public static String yellow(String message){
        return ANSI_YELLOW+message+ANSI_RESET;
    }

    public static String blue(String message){
        return ANSI_BLUE+message+ANSI_RESET;
    }

    public static String purple(String message){
        return ANSI_PURPLE+message+ANSI_RESET;
    }

    public static String cyan(String message){
        return ANSI_CYAN+message+ANSI_RESET;
    }

    public static String white(String message){ return ANSI_RESET+message; }

    public static String withColor(OutputColor color, String message) {
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
