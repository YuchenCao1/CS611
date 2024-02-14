import java.util.Arrays;

public abstract class Board {
    protected Cell<?>[][] board;

    /**
     * Method retrieves a cell's content given
     * a row index and a column index
     */
    public Cell<?> getCell(final int rowIndex, final int colIndex){ return board[rowIndex][colIndex]; }

    /**
     * Method sets a {@link Board}'s {@link Cell} value to a
     * generic {@link Cell<T>} given a generic T {@link T value}
     */
    public <T extends Symbolable> void setCell(final int rowIndex, final int colIndex, final T value){
        Cell<T> newCell = new Cell<T>();
        newCell.setOccupier(value);
        board[rowIndex][colIndex] = newCell;
    }

    /**
     * Method sets a {@link Board}'s {@link Cell} value to a
     * passed in generic {@link Cell<T>} of generic type T.
     */
    public <T extends Symbolable> void setCell(final int rowIndex, final int colIndex, final Cell<T> value){
        board[rowIndex][colIndex] = value;
    }

    public void clear(){
        for (Cell<?>[] cells : board) {
            Arrays.fill(cells, null);
        }
    }

    public <T extends Symbolable> int[] find(final T value){
        for (int rowIndex = 0; rowIndex<board.length; rowIndex++){
            for (int colIndex = 0; colIndex<board[rowIndex].length; colIndex++){
                if (board[rowIndex][colIndex] != null && board[rowIndex][colIndex].getOccupier() == value){
                    return new int[]{rowIndex, colIndex};
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        //TODO: handle jagged layout and allow other boards to use this
        return "TODO";
    }
}
