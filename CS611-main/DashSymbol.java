public class DashSymbol implements Symbolable {
    private final char symbol;
    private final OutputColor outputColor;

    public DashSymbol(){
        symbol = '-';
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
