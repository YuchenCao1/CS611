/**
 * This interface is to denote if a class can be placed into a legend.
 */
public interface Legendable extends Symbolable {
    public String getLegendDescription();
    public default String getLegendKey(){
        return symbolOutput();
    }
}
