//TODO: maybe have this class be the new RectangleBoard
public class QuoridorBoard extends Board {
    private final int numRows;
    private final int numCols;

    public QuoridorBoard(final int numRows, final int numCols){
        this.numRows = 2*numRows+1;
        this.numCols = 2*numCols+1;

        board = new Cell[2*numRows+1][2*numCols+1];

        //Adding + borders to board
        for (int plusToAddRowIndex = 0; plusToAddRowIndex<this.numRows; plusToAddRowIndex+=2){
            for (int plusToAddColIndex = 0; plusToAddColIndex<this.numCols; plusToAddColIndex+=2){
                PlusSymbol plusSymbol = new PlusSymbol();
                setCell(plusToAddRowIndex, plusToAddColIndex, plusSymbol);
            }
        }

        //Adding - borders to board
        for (int dashToAddRowIndex = 0; dashToAddRowIndex<this.numRows; dashToAddRowIndex+=2){
            for (int dashToAddColIndex = 1; dashToAddColIndex<this.numCols; dashToAddColIndex+=2){
                DashSymbol dashSymbol = new DashSymbol();
                setCell(dashToAddRowIndex, dashToAddColIndex, dashSymbol);
            }
        }

        //Adding | boarders to board
        for (int barToAddRowIndex = 1; barToAddRowIndex<this.numRows; barToAddRowIndex+=2){
            for (int barToAddColIndex = 0; barToAddColIndex<this.numCols; barToAddColIndex+=2){
                BarSymbol barSymbol = new BarSymbol();
                setCell(barToAddRowIndex, barToAddColIndex, barSymbol);
            }
        }
    }

    //Method is used to check if a wall move is valid through DFS
    public QuoridorBoard copy(){
        QuoridorBoard copyBoard = new QuoridorBoard(getNumRowsWithoutBorders(), getNumColsWithoutBorders());

        for (int rowIndex = 0; rowIndex<getNumRows(); rowIndex++){
            for (int colIndex = 0; colIndex<getNumCols(); colIndex++){
                copyBoard.setCell(rowIndex, colIndex, getCell(rowIndex, colIndex));
            }
        }

        return copyBoard;
    }

    public int getNumRows(){
        return numRows;
    }

    public int getNumCols(){
        return numCols;
    }

    public int getNumRowsWithoutBorders(){ return (numRows-1)/2; }

    public int getNumColsWithoutBorders(){ return (numCols-1)/2; }

    @Override
    public String toString() {
        /*
        Format will be:
        +--+--+--+
        |1 |2 |3 |
        +--#######
        and will depend on how many rows and columns there are,
        as we could have numbers like |93289 |  X  |, etc.
        */

        StringBuilder result = new StringBuilder();

        /*
        First, compute the number of digits in the largest number
        since the number of digits will be associated with number of
        spaces
        */
        final int maxNum = getNumRowsWithoutBorders() * getNumColsWithoutBorders();
        final int numDigits = (int) Math.log10(maxNum) + 1;

        final int maxSpaces = numDigits + 1; // Number of spaces between |
        final int numSpacesLeftSymbol = Math.min(1, numDigits / 2); //Number of spaces between  | and cell value
        final int numSpacesRightSymbol = maxSpaces - numSpacesLeftSymbol - 1; // Number of spaces between cell value and |

        //Number to output in the empty cell for the player to see
        int indexNumber = 1;

        // Now adding row content with Cell
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                Cell<?> currentCell = getCell(rowIndex, colIndex);

                if (currentCell != null) {
                    //Border content, including walls that will be placed in borders.
                    if (rowIndex % 2 == 0 && colIndex % 2 == 1){
                        //Spacing out the cell so numbers can be put inside
                        for (int numCopies = 0; numCopies<maxSpaces; numCopies++){
                            result.append(currentCell);
                        }
                    }
                    else if ((rowIndex % 2 == 0 && colIndex % 2 == 0) || (rowIndex % 2 == 1 && colIndex % 2 == 0)){
                        result.append(currentCell);
                    }
                    //Non border content
                    else {
                        /*
                        Add cell content, ensuring it is evenly spaced out
                        e.g. |  W  |
                         */
                        for (int numSpaces = 0; numSpaces < numSpacesLeftSymbol; numSpaces++) {
                            result.append(" ");
                        }

                        result.append(getCell(rowIndex, colIndex));

                        for (int numSpaces = 0; numSpaces < numSpacesRightSymbol; numSpaces++) {
                            result.append(" ");
                        }

                        indexNumber++;
                    }
                } else {
                    /*
                    Add index for player to see
                    e.g. "|139 |"
                     */
                    int indexNumDigits = (int) Math.log10(indexNumber) + 1;

                    result.append(indexNumber++);

                    for (int numSpaces = 0; numSpaces < maxSpaces - indexNumDigits; numSpaces++) {
                        result.append(" ");
                    }
                }
            }
            result.append("\n");
        }

        return result.toString();
    }
}
