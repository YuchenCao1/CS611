import java.util.Arrays;
import java.util.Map;

public abstract class GameMap {
    protected Cell<?>[][] map;
    protected Map<String, String> legend;
    protected int numRows;
    protected int numCols;
    protected int spacingFactor; //number of spaces between each cell to output

    /**
     * Method retrieves a cell's content given
     * a row index and a column index
     */
    public Cell<?> getCell(final int rowIndex, final int colIndex){ return map[rowIndex][colIndex]; }

    /**
     * Method sets a {@link GameMap}'s {@link Cell} value to a
     * generic {@link Cell<T>} given a generic T {@link T value}
     */
    public <T extends Symbolable> void setCell(final int rowIndex, final int colIndex, final T value){
        Cell<T> newCell = new Cell<T>();
        newCell.setOccupier(value);
        map[rowIndex][colIndex] = newCell;
    }

    /**
     * Method sets a {@link GameMap}'s {@link Cell} value to a
     * passed in generic {@link Cell<T>} of generic type T.
     */
    public <T extends Symbolable> void setCell(final int rowIndex, final int colIndex, final Cell<T> value){
        map[rowIndex][colIndex] = value;
    }

    /**
     * Method sets an element to a cell to null
     */
    public void clearCell(final int rowIndex, final int colIndex){
        map[rowIndex][colIndex] = null;
    }

    /**
     * Method to remove the occupier from a cell but keep the cell content
     */
    public void clearCellOccupier(final int rowIndex, final int colIndex){
        Cell<?> cellInQuestion = getCell(rowIndex, colIndex);
        if (cellInQuestion == null){
            return;
        }

        //TODO: maybe while loop instead also this would be ambiguous
        if (cellInQuestion.getOccupier() instanceof Occupiable){
            ((Occupiable) cellInQuestion.getOccupier()).setOccupier(null);
        }
        else {
            clearCell(rowIndex, colIndex);
        }
    }

    public void clear(){
        for (Cell<?>[] cells : map) {
            Arrays.fill(cells, null);
        }
    }

    /**
     * Method locates a given {@link Symbolable} object
     * @return an int array consisting of the following:
     * {rowIndex, colIndex, depthLevel}
     * or null if it does not exist;
     */
    public <T extends Symbolable> int[] find(final T itemToLocate){
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++){
            for (int colIndex = 0; colIndex < numCols; colIndex++){
                if (getCell(rowIndex, colIndex) != null) {
                    Object itemFound = getCell(rowIndex, colIndex);
                    int depthLevel = 0;
                    while (itemFound instanceof Occupiable){
                        itemFound = ((Occupiable)itemFound).getOccupier();
                        if (itemFound == itemToLocate){
                            return new int[]{rowIndex, colIndex, depthLevel};
                        }
                        depthLevel++;
                    }
                }
            }
        }

        return null;
    }
    public <T extends Legendable> void addLegendItem(T legendableItem){
        String key = legendableItem.getLegendKey();
        String value = legendableItem.getLegendDescription();

        legend.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        //Adding the map
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++){
            for (int colIndex = 0; colIndex < numCols; colIndex++){
                Cell<?> cell = getCell(rowIndex, colIndex);
                if (cell != null){
                    result.append(cell);
                    //Adding extra space between cells

                    //Rows with borders get border added spacing factor # of times, unless it is on the edge
                    if ((rowIndex == 0 || rowIndex == numRows - 1) && colIndex != numCols - 1){
                        for (int numRepeat = 0; numRepeat < spacingFactor - 1; numRepeat++)
                            result.append(cell);
                    }
                    else {
                        for (int numSpace = 0; numSpace < spacingFactor - 1; numSpace++)
                            result.append(" ");
                    }
                }
                else{
                    for (int numSpace = 0; numSpace < spacingFactor; numSpace++)
                        result.append(" ");
                }
            }

            result.append('\n');
        }

        // Adding the legend
        int legendRowBorderLength = spacingFactor*(numCols - 2) + spacingFactor - 1; //Ignoring | borders
        for (final Map.Entry<String, String> entry : legend.entrySet()){
            final String key = entry.getKey();
            final String val = entry.getValue();
            legendRowBorderLength = Math.max(legendRowBorderLength, key.length()+val.length()+1);
        }
        final StringBuilder legendRowBorder = new StringBuilder();
        legendRowBorder.append('+');
        for (int colIndex = 0; colIndex < legendRowBorderLength; colIndex++){
            legendRowBorder.append('-');
        }
        legendRowBorder.append("+\n");

        result.append(legendRowBorder);

        for (final Map.Entry<String, String> entry: legend.entrySet()){
            final String key = entry.getKey();
            final String val = entry.getValue();
            final String addedLegend = key+" "+val+"\n";
            result.append(addedLegend);
        }

        result.append(legendRowBorder);

        return result.toString();
    }
}
