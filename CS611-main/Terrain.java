public abstract class Terrain implements Legendable, Occupiable {
    protected final OutputColor highlight;
    protected final char symbol;
    protected Hero occupier;

    public Terrain(final OutputColor terrainColor, final char terrainSymbol){
        highlight = terrainColor;
        symbol = terrainSymbol;
        occupier = null;
    }
    public Object getOccupier() {
        return occupier;
    }

    @Override
    public void setOccupier(Object occupier) {
        if (occupier instanceof Hero) {
            this.occupier = (Hero) occupier;
        }
        else if (occupier == null){
            this.occupier = null;
        }
        else {
            throw new IllegalArgumentException("Cannot set occupier for Terrain: requires Hero, not "+occupier);
        }
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public OutputColor getOutputColor() {
        return highlight;
    }

    @Override
    public OutputColor getHighlightColor() {
        return highlight;
    }

    @Override
    public String symbolOutput() {
        //If there is an occupier, we will highlight their symbol as WHITE but keep bkg terrain color
        if (occupier == null)
            return Legendable.super.symbolOutput();
        else{
            return OutputColorer.white(
                    OutputHighlighter.withHighlight(highlight, String.valueOf(occupier.getSymbol()))
            );
        }
    }
}
