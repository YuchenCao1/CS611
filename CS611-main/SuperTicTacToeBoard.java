public class SuperTicTacToeBoard extends RectangleBoard {
    private final int subgridNumRows;
    private final int subgridNumCols;
    private final int numSubgridsPerRow;
    private final int numSubgridsPerCol;

    /**
     * This is the constructor for the Restricted SuperTicTacToe.
     * We know this is restricted because this constructor doesn't
     * prompt for the number of subgrids --- this is predetermined
     * since in restricted where one places their symbol will determine
     * where the next player's move can be.
     *
     * @param subgridNumRows the number of rows for each sub TicTacToe
     * @param subgridNumCols the number of cols for each sub TicTacToe
     */
    public SuperTicTacToeBoard(int subgridNumRows, int subgridNumCols) {
        /* A subgrid is a smaller TicTacToe, as SuperTicTacToe consists
         * of multiple TicTacToe grids. To determine the overall board
         * size, it is important to remember that in super TicTacToe,
         * given an arbitrary number of rows (r) and columns  (c) for a sub grid,
         * we have to make r*c grids, since where the user places their piece
         * will affect which subgrid the next player has to place their piece.
         *
         * This is for the restricted version atleast, explained here:
         *
         * https://en.wikipedia.org/wiki/Ultimate_tic-tac-toe
         */
        super((int)Math.pow(subgridNumRows, 2), (int)Math.pow(subgridNumCols, 2));
        this.subgridNumRows = subgridNumRows;
        this.subgridNumCols = subgridNumCols;
        this.numSubgridsPerRow = subgridNumRows;
        this.numSubgridsPerCol = subgridNumCols;
    }

    /**
     * This is the constructor for the Unrestricted Super TicTacToe.
     * In the unrestricted version, the user can define the subgrid dimensions
     * and the number of subgrids. This is because in the unrestricted board,
     * the user can choose any box to place their symbol.
     *
     * @param subgridNumRows the number of rows for each sub TicTacToe
     * @param subgridNumCols the number of cols for each sub TicTacToe
     * @param numSubgridsPerRow the number of subgrids in each row
     * @param numSubgridsPerCol the number of subgrids in each column
     */
    public SuperTicTacToeBoard(int subgridNumRows, int subgridNumCols, int numSubgridsPerRow, int numSubgridsPerCol){
        super(subgridNumRows*numSubgridsPerCol, subgridNumCols*numSubgridsPerRow);
        this.subgridNumRows = subgridNumRows;
        this.subgridNumCols = subgridNumCols;
        this.numSubgridsPerRow = numSubgridsPerRow;
        this.numSubgridsPerCol = numSubgridsPerCol;
    }

    public int getSubgridNumRows(){
        return subgridNumRows;
    }

    public int getSubgridNumCols(){
        return subgridNumCols;
    }

    public int getNumSubgridsPerRow(){
        return numSubgridsPerRow;
    }

    public int getNumSubgridsPerCol(){
        return numSubgridsPerCol;
    }

    /**
     * This method will return the subgrid cells that a given row
     * and col are in. Essentially, it returns the whole subgrid
     * box.
     * @return Subgrid which is a {@link Board} type
     */
    public RectangleBoard getSubgrid(final int rowIndex, final int colIndex){
        //Getting top corner of subgrid box
        final int boardFirstRowIndexInSubgrid = Math.floorDiv(rowIndex, subgridNumRows) * subgridNumRows;
        final int boardFirstColIndexInSubgrid = Math.floorDiv(colIndex, subgridNumCols) * subgridNumCols;

        //Now getting the appropriate elements
        RectangleBoard subgrid = new RectangleBoard(subgridNumRows, subgridNumCols);
        for (int subgridRowIndex = 0; subgridRowIndex < subgrid.getNumRows(); subgridRowIndex++){
            for (int subgridColIndex = 0; subgridColIndex < subgrid.getNumCols(); subgridColIndex++){
                subgrid.setCell(subgridRowIndex, subgridColIndex,
                        getCell(
                                boardFirstRowIndexInSubgrid + subgridRowIndex,
                                boardFirstColIndexInSubgrid + subgridColIndex
                        )
                );
            }
        }

        return subgrid;
    }

    public void setSubgrid(final int rowIndex, final int colIndex, Cell<?> value){
        //Getting top corner of subgrid box
        final int boardFirstRowIndexInSubgrid = Math.floorDiv(rowIndex, subgridNumRows) * subgridNumRows;
        final int boardFirstColIndexInSubgrid = Math.floorDiv(colIndex, subgridNumCols) * subgridNumCols;

        for (int subgridRowIndex = 0; subgridRowIndex < subgridNumRows; subgridRowIndex++){
            for (int subgridColIndex = 0; subgridColIndex < subgridNumCols; subgridColIndex++){
                setCell(
                        boardFirstRowIndexInSubgrid + subgridRowIndex,
                        boardFirstColIndexInSubgrid + subgridColIndex,
                        value
                );
            }
        }
    }


    @Override
    public String toString(){
        /*
        Format will be:
        +--+--+--+    +--+--+--+
        |1 |2 |3 |    |4 |5 |6 |
        +--+--+--+    +--+--+--+

        +--+--+--+
        ...
        and will depend on how many rows and columns there are,
        as we could have numbers like |93289 |  X  |, etc.

        The key thing to do here is add a space between each subgrid
        so it is easier to see for the user. We will also add new lines
        so we can see the clear separation of subgrids.

        */
        String normalBoardToString = super.toString();
        final String normalBoardRowFrame = normalBoardToString.substring(0, normalBoardToString.indexOf('\n')+1);

        StringBuilder result = new StringBuilder(normalBoardToString);

        final int numPlusSignsToFindBeforeSpace = subgridNumCols+1;
        final int numVerticalBarsToFindBeforeSpace = numPlusSignsToFindBeforeSpace;
        final int numNewLinesToFindBeforeNewLine = 2*subgridNumRows+1;
        final String spacesToInsert = "    ";

        int plusCount = 0;
        int verticalBarCount = 0;
        int newLineCount = 0;
        int stringIndex = 0;

        while (stringIndex < result.length()-1) {
            final boolean nextToEndOfLine = result.charAt(stringIndex + 1) == '\n';
            if (result.charAt(stringIndex) == '+') {
                plusCount++;
                if (plusCount == numPlusSignsToFindBeforeSpace) {
                    if (!nextToEndOfLine) {
                        result.insert(stringIndex + 1, spacesToInsert+"+");
                    }
                    else {
                        newLineCount++;
                    }
                    plusCount = 0;
                    stringIndex++;
                }
            } else if (result.charAt(stringIndex) == '|') {
                verticalBarCount++;
                if (verticalBarCount == numVerticalBarsToFindBeforeSpace) {
                    if (!nextToEndOfLine) {
                        result.insert(stringIndex + 1, spacesToInsert+"|");
                    }
                    else {
                        newLineCount++;
                    }
                    verticalBarCount = 0;
                    stringIndex++;
                }
            } else if (newLineCount == numNewLinesToFindBeforeNewLine) {
                result.insert(stringIndex-1, '\n');
                result.insert(stringIndex, normalBoardRowFrame);
                newLineCount = 0;
                verticalBarCount = 0;
                plusCount = 0;
                stringIndex--;
            }

            stringIndex++;
        }

        return result.toString();
    }
}
