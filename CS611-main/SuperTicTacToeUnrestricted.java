public class SuperTicTacToeUnrestricted extends TicTacToe {

    /**
     * This field is used to simply display which player
     * has won which subgrid. This is used to make
     * the code more legible and avoid having to re-evaluate
     * which player won a subgrid.
     */
    private RectangleBoard overallWinningGrid;
    private SuperTicTacToeBoard superTicTacToeBoard;
    private int overallWinningGridStreakSizeToWin;

    @Override
    public boolean isLegalMove(final int rowNum, final int colNum) {
        /*
         * Unrestricted implies a player can move in any subgrid.
         * When a subgrid is won, all the cells will be replaced
         * so we don't need to worry about placing a symbol in the
         * already-won subgrid.
         */
        return super.isLegalMove(rowNum, colNum);
    }

    @Override
    public boolean isWinningMove(final int moveRow, final int moveCol, RectangleBoard board){
        final int temp = streakSizeToWin;

        if (board == overallWinningGrid)
            streakSizeToWin = overallWinningGridStreakSizeToWin;

        final boolean isWinningMove = super.isWinningMove(moveRow, moveCol, board);

        streakSizeToWin = temp;
        return isWinningMove;
    }

    @Override
    public void setupStreakSizeToWin(){
        final int defaultStreakSizeToWin = 3;
        final int streakSizeToWinForLargerBoards = 4;

        if (superTicTacToeBoard.getSubgridNumRows() > 3 || superTicTacToeBoard.getNumSubgridsPerCol() > 3) {
            streakSizeToWin = streakSizeToWinForLargerBoards;
            System.out.println("Given that the board has "+superTicTacToeBoard.getSubgridNumRows()+" rows and "+superTicTacToeBoard.getSubgridNumCols()+" columns for each subgrid, the winning streak size for each subgrid is now "+streakSizeToWin);
        }
        else{
            streakSizeToWin = defaultStreakSizeToWin;
        }
        if (overallWinningGrid.getNumRows() > 3 || overallWinningGrid.getNumCols() > 3){
            overallWinningGridStreakSizeToWin = streakSizeToWinForLargerBoards;
            System.out.println("Given that the number of subgrids in each row is "+overallWinningGrid.getNumRows()+" and "+overallWinningGrid.getNumCols()+" for each column, the winning streak for the board itself is now "+overallWinningGridStreakSizeToWin);
        }
        else{
            overallWinningGridStreakSizeToWin = defaultStreakSizeToWin;
        }
    }
    @Override
    public void doTurn() {
        super.doTurn();

        /*
         * In addition to normal TicTacToe for a turn, we now have to additionally check
         * whether a player/team has won a subgrid.
         */
        RectangleBoard currentMoveSubgrid = superTicTacToeBoard.getSubgrid(currentMoveRow, currentMoveCol);

        final int currentMoveSubgridRow = currentMoveRow % currentMoveSubgrid.getNumRows();
        final int currentMoveSubgridCol = currentMoveCol % currentMoveSubgrid.getNumCols();

        if (doesMoveWinSubgrid(currentMoveSubgridRow, currentMoveSubgridCol, currentMoveSubgrid)){
            Cell<?> subgridWinner = board.getCell(currentMoveRow, currentMoveCol);
            fillSubgridWithSubgridWinner(subgridWinner);
            addSubgridWinnerToWinningBoard(subgridWinner);
            System.out.println(subgridWinner.getOccupier()+" has won a subgrid!");
        }
        else if (areNoMovesLeft(currentMoveSubgrid)){
            System.out.println("The subgrid ended in a draw!");
        }
    }

    /**
     * This method will determine whether a player has won
     * a subgrid in TicTacToe
     */
    private boolean doesMoveWinSubgrid(final int rowNum, final int colNum, RectangleBoard subgrid){
        return super.isWinningMove(rowNum, colNum, subgrid);
    }

    private void addSubgridWinnerToWinningBoard(Cell<?> subgridWinner){
        final int overallWinningGridBoardRow = currentMoveRow / superTicTacToeBoard.getSubgridNumRows();
        final int overallWinningGridBoardCol = currentMoveCol / superTicTacToeBoard.getSubgridNumCols();
        overallWinningGrid.setCell(overallWinningGridBoardRow, overallWinningGridBoardCol, subgridWinner);
    }

    /**
     * This method is for aesthetics: whenever a player/team wins, the whole subgrid
     * will show who has won that subgrid!
     */
    private void fillSubgridWithSubgridWinner(Cell<?> subgridWinner){
        superTicTacToeBoard.setSubgrid(currentMoveRow, currentMoveCol, subgridWinner);
    }

    @Override
    public void setupBoard() {
        // Setting up board details from user input
        final int boardSubgridNumRows = UserInteractor.getIntegerInput("Enter the number of rows for each subgrid in the "+this+" board", 3);
        final int boardSubgridNumCols = UserInteractor.getIntegerInput("Enter the number of cols for each subgrid in the "+this+" board", 3);
        final int boardNumSubgridsPerRow = UserInteractor.getIntegerInput("Enter the number of subgrids in each row in the "+this+" board", 3);
        final int boardNumSubgridsPerCol = UserInteractor.getIntegerInput("Enter the number of subgrids in each col in the "+this+" board", 3);
        overallWinningGrid = new RectangleBoard(boardNumSubgridsPerCol, boardNumSubgridsPerRow);
        superTicTacToeBoard = new SuperTicTacToeBoard(boardSubgridNumRows, boardSubgridNumCols, boardNumSubgridsPerRow, boardNumSubgridsPerCol);
        board = superTicTacToeBoard;
    }

    @Override
    public void setupMaxNumPlayers(){
        maxNumPlayers = Math.max(2, superTicTacToeBoard.getSubgridNumRows() * superTicTacToeBoard.getSubgridNumCols() / 9); //something reasonable
    }

    @Override
    public void setupMinNumPlayers(){
        super.setupMinNumPlayers();
    }

    @Override
    public String description() {
        return "Unrestricted Super TicTacToe is a variation of TicTacToe where there are subgrids:\n"+
                "Each subgrid is a mini TicTacToe game and whoever wins the mini TicTacToe, would\n"+
                "win a single cell in the \"Super\" TicTacToe. In the unrestricted variation, players\n"+
                "Can place their symbol anywhere on the board, independent of where the previous player\n"+
                "placed their symbol. Have fun!";
    }

    @Override
    public void start() {
        boolean isGameDone = false;
        while (!isGameDone) {
            doTurn();

            final int overallWinningGridCurrentRow = currentMoveRow / superTicTacToeBoard.getSubgridNumRows();
            final int overallWinningGridCurrentCol = currentMoveCol / superTicTacToeBoard.getSubgridNumCols();

            if (
                    (overallWinningGrid.getCell(overallWinningGridCurrentRow, overallWinningGridCurrentCol) != null
                            && isWinningMove(overallWinningGridCurrentRow, overallWinningGridCurrentCol, overallWinningGrid))
                            || areNoMovesLeft(board)
            ) {
                isGameDone = true;
            } else {
                assignNextCurrentPlayer();
            }
        }

        end();
    }

    @Override
    public void reset(){
        board.clear();
        overallWinningGrid.clear();
    }

    @Override
    public String toString(){
        return "Unrestricted Super TicTacToe";
    }
}
