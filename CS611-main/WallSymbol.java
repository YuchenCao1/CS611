public class WallSymbol implements Symbolable, Packable {
    protected final char symbol;
    protected final OutputColor outputColor;

    public WallSymbol(final char symbol){
        this.symbol = symbol;
        outputColor = OutputColor.WHITE;
    }

    public WallSymbol(){
        this('#');
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public OutputColor getOutputColor() {
        return outputColor;
    }

    @Override
    public String getItemName(){
        return "Wall";
    }

    @Override
    public String toString(){
        return OutputColorer.withColor(outputColor, String.valueOf(symbol));
    }
}
