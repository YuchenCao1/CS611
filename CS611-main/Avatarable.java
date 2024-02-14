/**
 * This interface is to denote characters
 * that can be printed in an avatar-like format
 */
public interface Avatarable extends Symbolable {

    /**
     * Method returns a String of length 1 representing
     * the avatar's head
     */
    public default String getHead(){
        return String.valueOf(getSymbol());
    }

    /**
     * Method returns a String of length 3 representing
     * the avatar's body
     */
    public String getBody();

    /**
     * Method returns a String of length 3 representing
     * the avatar's legs
     */
    public String getLegs();

    public default String getAvatar(){
        StringBuilder avatar = new StringBuilder();
        avatar.append(OutputColorer.withColor(getOutputColor(), " "+getHead()+" ")).append("\n");
        avatar.append(OutputColorer.withColor(getOutputColor(), getBody())).append("\n");
        avatar.append(OutputColorer.withColor(getOutputColor(), getLegs()));

        return avatar.toString();
    }
}
