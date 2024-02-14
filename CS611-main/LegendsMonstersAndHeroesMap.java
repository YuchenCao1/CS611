import java.util.HashMap;
import java.util.Map;

public class LegendsMonstersAndHeroesMap extends GameMap {
    public LegendsMonstersAndHeroesMap(final int numRowSpacesToMove, final int numColSpacesToMove){
        spacingFactor = 2;
        numRows = numRowSpacesToMove+2; // Adding boarders to sides of map for beauty
        numCols = numColSpacesToMove+2;   // Adding boarders to sides of map for beauty

        generateValidMap();

        // The map also respectively needs its legend
        legend = new HashMap<>();

        addLegendItem(new MarketSpaceSymbol(new LegendsMonstersAndHeroesMarket(null)));
        addLegendItem(new InaccessibleSpaceSymbol());
    }

    private void generateValidMap(){
        System.out.println("Generating a valid map...");

        do {
            map = new Cell[numRows][numCols];

            // Adding vertical borders to map
            for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
                VerticalWallSymbol verticalBorder = new VerticalWallSymbol();
                setCell(rowIndex, 0, verticalBorder);
                setCell(rowIndex, numCols - 1, verticalBorder);
            }

            // Adding horizontal borders to map
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                HorizontalWallSymbol horizontalBorder = new HorizontalWallSymbol();
                setCell(0, colIndex, horizontalBorder);
                setCell(numRows - 1, colIndex, horizontalBorder);
            }

            // Adding random spaces as instructed
            generateRandomSpaces();

        } while (!isValidMapArrangement(map));
    }

    /**
     * The map requires three types of spaces/symbols:
     * - Common Spaces
     * - Market Spaces
     * - Inaccessible Spaces
     * These were instructed to be a 5-3-2 ratio split
     */
    private void generateRandomSpaces(){
        final double probabilityOfMarketSymbol = 0.1; // Not a fan of the 3/10 ratio
        final double probabilityOfInaccessibleSymbol = 0.2;
        //Ignore first and last elements since they are aesthetic borders
        for (int rowIndex = 1; rowIndex<numRows-1; rowIndex++){
            for (int colIndex = 1; colIndex<numCols-1; colIndex++){
                double randomProbability = Math.random();
                if (randomProbability <= probabilityOfInaccessibleSymbol){
                    InaccessibleSpaceSymbol symbol = new InaccessibleSpaceSymbol();
                    setCell(rowIndex, colIndex, symbol);
                    getCell(rowIndex, colIndex).setSymbol(symbol.getSymbol());
                }
                else if (randomProbability <= probabilityOfInaccessibleSymbol + probabilityOfMarketSymbol){
                    final String marketName = String.valueOf(rowIndex*colIndex); //some number
                    MarketSpaceSymbol marketSpaceSymbol = new MarketSpaceSymbol(new LegendsMonstersAndHeroesMarket(marketName));
                    setCell(rowIndex, colIndex, marketSpaceSymbol);
                    getCell(rowIndex, colIndex).setSymbol(marketSpaceSymbol.getSymbol());
                }
                //Ignore other 50% since it would be an empty space
            }
        }
    }

    /**
     * Method checks whether the proposed map is valid.
     * This is done by checking if inaccessible spaces chain
     * to hit at least 2 borders, which limits the player from
     * exploring certain regions
     * @return boolean
     */
    private boolean isValidMapArrangement(final Cell<?>[][] proposedMap){
        for (int rowIndex = 1; rowIndex < proposedMap.length - 1; rowIndex++){
            for (int colIndex = 1; colIndex < proposedMap[rowIndex].length - 1; colIndex++){
                if (proposedMap[rowIndex][colIndex] != null
                        && proposedMap[rowIndex][colIndex].getOccupier() instanceof InaccessibleSpaceSymbol){
                    boolean[][] checkedSquares = new boolean[proposedMap.length][proposedMap[rowIndex].length];
                    //We will check up, down, left, right, and diagonals since that also blocks player
                    if (numberOfBordersInaccessibleSpaceTouches(proposedMap, checkedSquares, rowIndex, colIndex) >= 2){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private int numberOfBordersInaccessibleSpaceTouches(
            final Cell<?>[][] proposedMap,
            boolean[][] checkedSquares, final int rowIndex, final int colIndex){

        checkedSquares[rowIndex][colIndex] = true;

        // If not we check for inaccessible chain. Note if all chains been checked then code ends
        // NOTE: no need to check boundaries since non borders are inside rectangle map
        int totalWallsTouched = 0;

        if (!checkedSquares[rowIndex+1][colIndex] && proposedMap[rowIndex+1][colIndex] != null
                && proposedMap[rowIndex+1][colIndex].getOccupier()
                instanceof InaccessibleSpaceSymbol){
            checkedSquares[rowIndex+1][colIndex] = true;
            totalWallsTouched += numberOfBordersInaccessibleSpaceTouches(proposedMap, checkedSquares, rowIndex+1, colIndex);
        }
        if (!checkedSquares[rowIndex-1][colIndex] && proposedMap[rowIndex-1][colIndex] != null
                && proposedMap[rowIndex-1][colIndex].getOccupier()
                instanceof InaccessibleSpaceSymbol){
            checkedSquares[rowIndex-1][colIndex] = true;
            totalWallsTouched += numberOfBordersInaccessibleSpaceTouches(proposedMap, checkedSquares, rowIndex-1, colIndex);
        }
        if (!checkedSquares[rowIndex][colIndex+1] && proposedMap[rowIndex][colIndex+1] != null
                && proposedMap[rowIndex][colIndex+1].getOccupier()
                instanceof InaccessibleSpaceSymbol){
            checkedSquares[rowIndex][colIndex+1] = true;
            totalWallsTouched += numberOfBordersInaccessibleSpaceTouches(proposedMap, checkedSquares, rowIndex, colIndex+1);
        }
        if (!checkedSquares[rowIndex][colIndex-1] && proposedMap[rowIndex][colIndex-1] != null
                && proposedMap[rowIndex][colIndex-1].getOccupier()
                instanceof InaccessibleSpaceSymbol){
            checkedSquares[rowIndex][colIndex-1] = true;
            totalWallsTouched += numberOfBordersInaccessibleSpaceTouches(proposedMap, checkedSquares, rowIndex, colIndex-1);
        }
        if (!checkedSquares[rowIndex+1][colIndex+1] && proposedMap[rowIndex+1][colIndex+1] != null
                && proposedMap[rowIndex+1][colIndex+1].getOccupier()
                instanceof InaccessibleSpaceSymbol){
            checkedSquares[rowIndex+1][colIndex+1] = true;
            totalWallsTouched += numberOfBordersInaccessibleSpaceTouches(proposedMap, checkedSquares, rowIndex+1, colIndex+1);
        }
        if (!checkedSquares[rowIndex+1][colIndex-1] && proposedMap[rowIndex+1][colIndex-1] != null
                && proposedMap[rowIndex+1][colIndex-1].getOccupier()
                instanceof InaccessibleSpaceSymbol){
            checkedSquares[rowIndex+1][colIndex-1] = true;
            totalWallsTouched += numberOfBordersInaccessibleSpaceTouches(proposedMap, checkedSquares, rowIndex+1, colIndex-1);
        }
        if (!checkedSquares[rowIndex-1][colIndex+1] && proposedMap[rowIndex-1][colIndex+1] != null
                && proposedMap[rowIndex-1][colIndex+1].getOccupier()
                instanceof InaccessibleSpaceSymbol){
            checkedSquares[rowIndex-1][colIndex+1] = true;
            totalWallsTouched += numberOfBordersInaccessibleSpaceTouches(proposedMap, checkedSquares, rowIndex-1, colIndex+1);
        }
        if (!checkedSquares[rowIndex-1][colIndex-1] && proposedMap[rowIndex-1][colIndex-1] != null
                && proposedMap[rowIndex-1][colIndex-1].getOccupier()
                instanceof InaccessibleSpaceSymbol){
            checkedSquares[rowIndex-1][colIndex-1] = true;
            totalWallsTouched += numberOfBordersInaccessibleSpaceTouches(proposedMap, checkedSquares, rowIndex-1, colIndex-1);
        }

        // If it is a border
        if (rowIndex == 1 || rowIndex == proposedMap.length - 2
                || colIndex == 1 || colIndex == proposedMap[rowIndex].length - 2 ){
            return 1 + totalWallsTouched;
        }

        return  totalWallsTouched;
    }

    public boolean isMarketCell(final int rowIndex, final int colIndex){
        return map[rowIndex][colIndex] != null
                &&
                ((map[rowIndex][colIndex].getSymbol() != null
                    && map[rowIndex][colIndex].getSymbol() == new MarketSpaceSymbol(null).getSymbol())
                    || map[rowIndex][colIndex].getOccupier() instanceof MarketSpaceSymbol
                );
    }

    public int getNumRows(){
        return numRows;
    }

    public int getNumCols(){
        return numCols;
    }

    @Override
    public String toString(){
        /*
        Format will be
        ■■■■■■■
        █ ... █
        ■■■■■■■
        Legend:
        +-----+
        | ... |
        +-----+
        So no spacing is inside as smooth movement shall be displayed.
         */
        return super.toString();
    }
}
