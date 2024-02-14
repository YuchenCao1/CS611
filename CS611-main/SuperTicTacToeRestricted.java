public class SuperTicTacToeRestricted extends TicTacToe {
    /**
     * Restricted Super TicTacToe needs previous move row
     * to ensure user is playing in the right subgrid
     */
    private Integer previousMoveRow;

    /**
     * Restricted Super TicTacToe needs previous move row
     * to ensure user is playing in the right subgrid
     */
    private Integer previousMoveCol;
    private boolean wasPreviousMoveASubgridWin;

    /**
     * This field is used to simply display which player
     * has won which subgrid. This is used to make
     * the code more legible and avoid having to re-evaluate
     * which player won a subgrid.
     */
    private RectangleBoard overallWinningGrid;
    private SuperTicTacToeBoard superTicTacToeBoard;

    @Override
    public boolean isLegalMove(final int rowNum, final int colNum) {
        /*
         * Unrestricted implies a player has to move in the subgrid
         * that the previous player has put for the next player.
         * When a subgrid is won, all the cells will be replaced
         * so we don't need to worry about placing a symbol in the
         * already-won subgrid.
         */
        final boolean isSpaceEmpty = board.getCell(rowNum, colNum) == null;

        //Comparing previous subgrid position to current move subgrid index
        final boolean isMoveInAppropriateSubgrid = isMoveInAppropriateSubgrid(rowNum, colNum);

        return isSpaceEmpty && isMoveInAppropriateSubgrid;
    }

    private boolean isMoveInAppropriateSubgrid(final int rowNum, final int colNum) {
        // If a previous move hasn't been made, the player can place a piece in any subgrid
        if (previousMoveRow == null && previousMoveCol == null){
            return true;
        }

        /*
         * Moreover, if the previous move's indicates the next player to move to a subgrid that is
         * already full, then any subgrid is appropriate.
         */
        final int previousMoveSubgridRowPosition = previousMoveRow % superTicTacToeBoard.getSubgridNumRows();
        final int previousMoveSubgridColPosition = previousMoveCol % superTicTacToeBoard.getSubgridNumCols();

        //Check if subgrid already has winner or draw
        if (overallWinningGrid.getCell(previousMoveSubgridRowPosition, previousMoveSubgridColPosition) != null){
            return true;
        }

        // Otherwise, we make sure that the player's move's subgrid is related to the previous player's move
        final int currentMoveSubgridRowNumber = rowNum / superTicTacToeBoard.getNumSubgridsPerRow();
        final int currentMoveSubgridColNumber = colNum / superTicTacToeBoard.getNumSubgridsPerCol();

        return previousMoveSubgridColPosition == currentMoveSubgridColNumber
                && previousMoveSubgridRowPosition == currentMoveSubgridRowNumber;
    }

    @Override
    public void setupStreakSizeToWin(){
        final int defaultStreakSizeToWin = 3;
        final int streakSizeToWinForLargerBoards = 4;

        if (superTicTacToeBoard.getSubgridNumRows() > 3 || superTicTacToeBoard.getNumSubgridsPerCol() > 3) {
            streakSizeToWin = streakSizeToWinForLargerBoards;
            System.out.println("Given that the board has "+superTicTacToeBoard.getSubgridNumRows()+" rows and "+superTicTacToeBoard.getSubgridNumCols()+" columns for each subgrid, the winning streak size is now "+streakSizeToWin);
        }
        else{
            streakSizeToWin = defaultStreakSizeToWin;
        }
    }
    @Override
    public void doTurn() {
        if (teams != null) {
            System.out.println("It is " + getCurrentPlayer() + "'s from " + getCurrentTeam() + " team's turn!");
        }
        else{
            System.out.println("It is "+getCurrentPlayer()+"'s turn!");
        }
        System.out.println(board);

        do {
            final int minBoardIndexToSelect = 1;
            final int maxBoardIndexToSelect = board.getNumRows() * board.getNumCols();
            final int moveIndex = UserInteractor.getIntegerInput("Enter an index as displayed by the board", minBoardIndexToSelect, maxBoardIndexToSelect);
            currentMoveRow = (moveIndex - 1) / board.getNumCols();
            currentMoveCol = (moveIndex - 1) % board.getNumCols();
            if (!super.isLegalMove(currentMoveRow, currentMoveCol)){
                System.out.println("The space "+OutputColorer.red(String.valueOf(moveIndex))+ " is already taken.");
            }
            else if (!isMoveInAppropriateSubgrid(currentMoveRow, currentMoveCol)){
                System.out.println("The move "+OutputColorer.red(String.valueOf(moveIndex))+ " cannot be made since it is not placed in the appropriate subgrid!");
            }
        } while (!isLegalMove(currentMoveRow, currentMoveCol));

        if (teams != null){
            board.setCell(currentMoveRow, currentMoveCol, getCurrentTeam());
        }
        else{
            board.setCell(currentMoveRow, currentMoveCol, getCurrentPlayer());
        }

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
            addSubgridDrawToWinningBoard();
        }

        previousMoveRow = currentMoveRow;
        previousMoveCol = currentMoveCol;
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

    private void addSubgridDrawToWinningBoard(){
        final int overallWinningGridBoardRow = currentMoveRow / superTicTacToeBoard.getSubgridNumRows();
        final int overallWinningGridBoardCol = currentMoveCol / superTicTacToeBoard.getSubgridNumCols();
        overallWinningGrid.setCell(overallWinningGridBoardRow, overallWinningGridBoardCol, (Symbolable) null);
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
        overallWinningGrid = new RectangleBoard(boardSubgridNumRows, boardSubgridNumCols);
        superTicTacToeBoard = new SuperTicTacToeBoard(boardSubgridNumRows, boardSubgridNumCols);
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
        return "Restricted Super TicTacToe is a variation of TicTacToe where there are subgrids:\n"+
                "Each subgrid is a mini TicTacToe game and whoever wins the mini TicTacToe, would\n"+
                "win a single cell in the \"Super\" TicTacToe. In the restricted variation, players\n"+
                "can place their symbol only based on the location of where the previous player\n"+
                "placed their symbol. For example. If the previous player placed a symbol on the\n"+
                "top left in any subgrid, then the next player has to place their symbol in the top\n" +
                "left subgrid. Have fun!";
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
                            || areNoMovesLeft(overallWinningGrid)
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
        return "Restricted Super TicTacToe";
    }
}
