import java.util.ArrayList;
import java.util.List;

public class RectangleBoard extends Board {
    private final int numRows;
    private final int numCols;

    public RectangleBoard(final int numRows, final int numCols){
        this.numRows = numRows;
        this.numCols = numCols;
        board = new Cell[numRows][numCols];
    }

    public int getNumRows(){ return numRows; }

    public int getNumCols(){ return numCols; }
    @Override
    public String toString(){
        /*
        Format will be:
        +---+---+---+
        | 1 | 2 | 3 |
        +---+---+---+
        and will depend on how many rows and columns there are,
        as we could have numbers like |93289 |  X  |, etc.
        */

        StringBuilder result = new StringBuilder();

        /*
        First, compute the number of digits in the largest number
        since the number of digits will be associated with number of
        spaces
        */
        int maxNum = this.numRows * this.numCols;
        int numDigits = (int) Math.log10(maxNum) + 1;

        int maxSpaces = numDigits + 1; // Number of spaces between |
        int numSpacesLeftSymbol = Math.min(1, numDigits/2); //Number of spaces between  | and symbol
        int numSpacesRightSymbol = maxSpaces - numSpacesLeftSymbol - 1; // Number of spaces between symbol and |

        // Define the row border
        StringBuilder rowFrame = new StringBuilder("+");
        for (int colIndex = 0; colIndex < numCols; colIndex++){
            for (int numSpaces = 0; numSpaces < maxSpaces; numSpaces++){
                rowFrame.append("-");
            }
            rowFrame.append("+");
        }
        rowFrame.append("\n");

        // Now adding row content with Cell
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++){
            //Add row border
            result.append(rowFrame);

            // Add content
            result.append("|");
            for (int colIndex = 0; colIndex < numCols; colIndex++){
                if (this.board[rowIndex][colIndex] != null){
                    /*
                    Add cell content, ensuring it is evenly spaced out
                    e.g. |  W  |
                     */
                    for (int numSpaces = 0; numSpaces < numSpacesLeftSymbol; numSpaces++){
                        result.append(" ");
                    }

                    result.append(this.board[rowIndex][colIndex]);

                    for (int numSpaces = 0; numSpaces < numSpacesRightSymbol; numSpaces++){
                        result.append(" ");
                    }
                }
                else {
                    /*
                    Add index for player to see
                    e.g. "|139 |"
                     */
                    int index = rowIndex * numCols + colIndex + 1;
                    int indexNumDigits = (int) Math.log10(index) + 1;

                    result.append(index);

                    for (int numSpaces = 0; numSpaces < maxSpaces - indexNumDigits; numSpaces++){
                        result.append(" ");
                    }
                }

                result.append("|");
            }
            result.append("\n");
        }

        //Add final row border
        result.append(rowFrame);

        return result.toString();
    }
}
