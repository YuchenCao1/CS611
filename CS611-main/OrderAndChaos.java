import java.util.Queue;

public class OrderAndChaos extends BoardGame<RectangleBoard>{
    private final Team order;
    private final Team chaos;
    private int currentMoveRow;
    private int currentMoveCol;

    private final int STREAK_TO_WIN = 5;

    public OrderAndChaos(){
        System.out.println("Welcome to " + this + "!");
        System.out.println(description());

        teamsRequired = true;
        setupBoard();
        setupMinNumPlayers();
        setupMaxNumPlayers();
        setupPlayers(minNumPlayers, maxNumPlayers);
        //Order and Chaos can only have 2 teams
        setupTeams(2, 2, null, players);

        order = teams.poll();
        chaos = teams.poll();
        teams.add(order);
        teams.add(chaos);

        System.out.println("ORDER will be "+order);
        System.out.println("CHAOS will be "+chaos);

        makeSureReady();
    }

    public void setupMinNumPlayers(){
        minNumPlayers = 2;
    }

    public void setupMaxNumPlayers(){
        maxNumPlayers = 4;
    }

    @Override
    public String description(){
        return "Order and Chaos is a game that consists of two teams: \n" +
                "Order and Chaos. Order wants to get 5 in a row, but \n" +
                "Chaos wants to prevent Order from getting 5 in a row\n" +
                "at all costs. This is a variation of TicTacToe in the\n" +
                "way that both teams can placed down an X or an O";
    }

    @Override
    public boolean isLegalMove(final int rowNum, final int colNum) {
        return board.getCell(rowNum, colNum) != null;
    }

    private boolean isWinningMove(final int moveRow, final int moveCol){
        /* This function will check if the move that was just made
         * was a winning move. It will return true if it was a winning
         * move, and false otherwise.
         */
        final char symbolToCheckFor = board.getCell(moveRow, moveCol).getSymbol();

        int numInARow = 1;
        int row = moveRow;
        int col = moveCol;

        // Check horizontal
        while (col > 0 && board.getCell(row, col - 1) != null && board.getCell(row, col - 1).getSymbol() == symbolToCheckFor){
            numInARow++;
            col--;
        }

        col = moveCol;
        while (col < board.getNumCols() - 1 && board.getCell(row, col + 1) != null && board.getCell(row, col + 1).getSymbol() == symbolToCheckFor){
            numInARow++;
            col++;
        }

        if (numInARow >= STREAK_TO_WIN){
            return true;
        }

        // Check vertical
        numInARow = 1;
        row = moveRow;
        col = moveCol;

        while (row > 0 && board.getCell(row - 1, col) != null && board.getCell(row - 1, col).getSymbol() == symbolToCheckFor){
            numInARow++;
            row--;
        }

        row = moveRow;
        while (row < board.getNumRows() - 1 && board.getCell(row + 1, col) != null && board.getCell(row + 1, col).getSymbol() == symbolToCheckFor){
            numInARow++;
            row++;
        }

        if (numInARow >= STREAK_TO_WIN){
            return true;
        }

        // Check diagonal
        numInARow = 1;

        row = moveRow;
        col = moveCol;
        while (row > 0 && col > 0 && board.getCell(row - 1, col - 1) != null && board.getCell(row - 1, col - 1).getSymbol() == symbolToCheckFor){
            numInARow++;
            row--;
            col--;
        }

        row = moveRow;
        col = moveCol;
        while (row < board.getNumRows() - 1 && col < board.getNumCols() - 1 && board.getCell(row + 1, col + 1) != null && board.getCell(row + 1, col + 1).getSymbol() == symbolToCheckFor){
            numInARow++;
            row++;
            col++;
        }

        if (numInARow >= STREAK_TO_WIN){
            return true;
        }

        // Check other diagonal
        numInARow = 1;

        row = moveRow;
        col = moveCol;
        while (row > 0 && col < board.getNumCols() - 1 && board.getCell(row - 1, col + 1) != null && board.getCell(row - 1, col + 1).getSymbol() == symbolToCheckFor){
            numInARow++;
            row--;
            col++;
        }

        row = moveRow;
        col = moveCol;
        while (row < board.getNumRows() - 1 && col > 0 && board.getCell(row + 1, col - 1) != null && board.getCell(row + 1, col - 1).getSymbol() == symbolToCheckFor){
            numInARow++;
            row++;
            col--;
        }

        return numInARow >= STREAK_TO_WIN;
    }

    @Override
    public void setupBoard() {
        board = new RectangleBoard(6,6);
    }

    @Override
    public void doTurn() {
        if (getCurrentTeam() == order){
            System.out.println("ORDER's turn: "+getCurrentPlayer()+", make your move!");
        }
        else{
            System.out.println("CHAOS's turn: "+getCurrentPlayer()+", make your move!");
        }

        System.out.println(board);

        char symbolToPlace;
        do {
            final int minBoardIndexToSelect = 1;
            final int maxBoardIndexToSelect = board.getNumRows() * board.getNumCols();
            final int moveIndex = UserInteractor.getIntegerInput("Enter an index as displayed by the board", minBoardIndexToSelect, maxBoardIndexToSelect);
            currentMoveRow = (moveIndex - 1) / board.getNumCols();
            currentMoveCol = (moveIndex - 1) % board.getNumCols();
            final char[] validChars = {'X', 'O'};
            symbolToPlace = UserInteractor.getSymbolInput("Enter the letter you want to place down", validChars);

            if (isLegalMove(currentMoveRow, currentMoveCol)){
                System.out.println("The space "+OutputColorer.red(String.valueOf(moveIndex))+ " is already taken.");
            }
        } while (isLegalMove(currentMoveRow, currentMoveCol));

        board.setCell(currentMoveRow, currentMoveCol, getCurrentTeam());
        board.getCell(currentMoveRow, currentMoveCol).setSymbol(symbolToPlace);
    }


    @Override
    public void assignNextCurrentPlayer() {
        /*
        Get the currentTeam and currentPlayer from the front
        of the line.
        */
        final Team currentTeam = teams.poll();
        assert currentTeam != null;
        final Player currentPlayer = currentTeam.getPlayers().poll();

        /*
        Adding currentTeam and currentPlayer to the back of
        the line.
        */
        currentTeam.getPlayers().offer(currentPlayer);
        teams.offer(currentTeam);
    }

    private boolean areNoMovesLeft(){
        for (int rowIndex = 0; rowIndex < board.getNumRows(); rowIndex++){
            for (int colIndex = 0; colIndex < board.getNumCols(); colIndex++){
                if (board.getCell(rowIndex, colIndex) == null){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Player getCurrentPlayer() {
        return getCurrentTeam().getPlayers().peek();
    }

    @Override
    public Team getCurrentTeam(){
        return teams.peek();
    }

    @Override
    public void start() {
        boolean isGameDone = false;
        while (!isGameDone) {
            doTurn();

            if (isWinningMove(currentMoveRow, currentMoveCol) || areNoMovesLeft()) {
                isGameDone = true;
            } else {
                assignNextCurrentPlayer();
            }
        }

        end();
    }

    @Override
    public void end() {
        System.out.println(board);

        final boolean didOrderWin = isWinningMove(currentMoveRow, currentMoveCol);
        final boolean didChaosWin = !didOrderWin && areNoMovesLeft();
        Team winningTeam = null;
        Team losingTeam = null;

        if (didOrderWin) {
            winningTeam = order;
            losingTeam = chaos;

            System.out.println("Order ("+ order +") has won!");
        }
        else if (didChaosWin){
            winningTeam = chaos;
            losingTeam = order;

            System.out.println("Chaos ("+ chaos +") has won!");
        }
        if (didOrderWin || didChaosWin) {
            for (final Player player : winningTeam.getPlayers()) {
                player.getGameRecord().addWin();
            }

            for (final Player losingPlayer : losingTeam.getPlayers()) {
                losingPlayer.getGameRecord().addLoss();
            }
        }
        else {
            for (final Player player : players){
                player.getGameRecord().addDraw();
            }
        }

        for (final Player player : players){
            System.out.println(player+"'s record is now "+player.getGameRecord());
        }

        promptToReplay();
    }

    @Override
    public void reset() {
        // Clearing board
        board.clear();
    }

    @Override
    public String toString(){
        return "Order And Chaos";
    }
}
