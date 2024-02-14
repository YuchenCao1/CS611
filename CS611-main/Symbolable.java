/* Symbolable is an interface that represents
anything that can be outputted as a symbol in
the terminal. This can be a player's symbol,
or even just placing an object in a Board
Game's board cell.
 */
public interface Symbolable {
    public char getSymbol();
    public OutputColor getOutputColor();
    public default OutputColor getHighlightColor(){
        return null;
    }

    public default String symbolOutput(){
        if (getHighlightColor() == null){
            return OutputColorer.withColor(getOutputColor(), String.valueOf(getSymbol()));
        }
        return OutputColorer.withColor(getOutputColor(), OutputHighlighter.withHighlight(getHighlightColor(), String.valueOf(getSymbol())));
    }
}
