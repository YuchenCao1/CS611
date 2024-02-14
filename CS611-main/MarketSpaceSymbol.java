public class MarketSpaceSymbol implements Legendable{
    protected char symbol;
    protected final String legendDescription;
    protected OutputColor outputColor;
    protected final Market market;

    public MarketSpaceSymbol(Market market){
        symbol = '$';
        legendDescription = "Market";
        outputColor = OutputColor.GREEN;
        this.market = market;
    }

    public Market getMarket(){ return market; }

    @Override
    public String getLegendDescription() {
        return legendDescription;
    }

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
