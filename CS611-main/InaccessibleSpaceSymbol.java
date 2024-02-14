public class InaccessibleSpaceSymbol implements Legendable{
    private final char symbol;
    private final String legendDescription;
    private final OutputColor outputColor;

    public InaccessibleSpaceSymbol(){
        symbol = '█';
        legendDescription = "Inaccessible Space";
        outputColor = OutputColor.WHITE;
    }

    @Override
    public String getLegendDescription() {
        return legendDescription;
    }

    /**
     * @return
     */
    @Override
    public String getLegendKey() {
        return toString();
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
        return String.valueOf(OutputColorer.withColor(outputColor, String.valueOf(symbol)));
    }
}
