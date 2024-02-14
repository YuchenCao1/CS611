/**
 * This class serves to be the object that
 * a Board Game's Board can include: each
 * element in the Board is a Cell.
 */
public class Cell<T extends Symbolable> implements Occupiable{
    private T occupier;
    private Character symbol;

    public Cell() {
        occupier = null;
        symbol = null;
    }

    public void setOccupier(T occupier) {
        this.occupier = occupier;
    }

    public T getOccupier() {
        return this.occupier;
    }

    @Override
    public void setOccupier(Object newOccupier) {
        if (newOccupier instanceof Symbolable)
            setOccupier((T) newOccupier);
        else{
            throw new IllegalArgumentException("occupier in cell being set must be Symbolable");
        }
    }

    public Character getSymbol(){
        return symbol;
    }

    public void setSymbol(Character symbol){
        this.symbol = symbol;
    }

    public boolean isOccupied() {
        return occupier != null;
    }

    @Override
    public String toString(){
        if (symbol != null){
            return OutputColorer.withColor(occupier.getOutputColor(), String.valueOf(symbol));
        }
        else if (occupier != null) {
            return occupier.symbolOutput();
        }
        return " ";
    }
}
