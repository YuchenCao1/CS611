/**
 * This class is to represent the Nexus Space
 * in Legends of Valor
 */
public class Nexus extends MarketSpaceSymbol implements Legendable, Occupiable {
    protected final OutputColor highlight;
    protected Object occupier;
    public Nexus(OutputColor color){
        super(new LegendsMonstersAndHeroesMarket("Nexus"));
        symbol = 'N';
        outputColor = color;
        highlight = OutputColor.WHITE;
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
    public OutputColor getHighlightColor(){ return highlight; }

    @Override
    public String getLegendDescription() {
        return "Nexus";
    }

    @Override
    public Object getOccupier() {
        return occupier;
    }

    public void setOccupier(Object occupier){
        this.occupier = occupier;
    }

    @Override
    public String getLegendKey(){
        return super.symbolOutput();
    }

    @Override
    public String symbolOutput() {
        if (occupier != null) {
            Symbolable occupier = (Symbolable) this.occupier;
            return OutputColorer.withColor(outputColor, OutputHighlighter.withHighlight(highlight, String.valueOf(occupier.getSymbol())));
        }
        //I prefer the output to not display the N on the board itself so this will also be overriden
        return OutputColorer.withColor(outputColor, OutputHighlighter.withHighlight(highlight, " "));
    }
}
