public class PlusSymbol implements Symbolable {
    private final char symbol;
    private final OutputColor outputColor;

    public PlusSymbol(){
        symbol = '+';
        outputColor = OutputColor.WHITE;
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
    public String toString(){
        return OutputColorer.withColor(outputColor, String.valueOf(symbol));
    }
}
