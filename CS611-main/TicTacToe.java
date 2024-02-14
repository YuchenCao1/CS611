import java.util.Queue;

public class TicTacToe extends BoardGame<RectangleBoard>{
    protected int streakSizeToWin;

    /*
    Following fields are used for storing current move.
    This is stored to check quicker if a winning move
    is made instead of conducting a complete traversal
    of the board.
    */
    protected int currentMoveRow;
    protected int currentMoveCol;

    public TicTacToe(){
        System.out.println("Welcome to " + this + "!");
        System.out.println(description());

        teamsRequired = false;
        teamsAllowed = true;
        setupBoard();
        setupStreakSizeToWin();
        setupMinNumPlayers();
        setupMaxNumPlayers();
        setupPlayers(minNumPlayers, maxNumPlayers);
        /*
        A player could have selected a number smaller than
        the arbitrary maxPlayers provided
        */
        final int newMaxPlayers = players.size();
        setupTeams(minNumPlayers, newMaxPlayers, null, players);

        makeSureReady();
    }

    @Override
    public String description(){
        return "Tic Tac Toe is a game that requires players or teams to acquire 3 in a row, whether vertically, horizontally, or diagonally";
    }

    @Override
    public boolean isLegalMove(final int rowNum, final int colNum) {
        return board.getCell(rowNum, colNum) == null;
    }

    @Override
    public void setupBoard() {
        // Setting up board details from user input
        final int boardNumRows = UserInteractor.getIntegerInput("Enter the number of rows for the "+this+" board", 3);
        final int boardNumCols = UserInteractor.getIntegerInput("Enter the number of cols for the "+this+" board", 3);
        board = new RectangleBoard(boardNumRows, boardNumCols);
    }

    public void setupStreakSizeToWin(){
        final int defaultStreakSizeToWin = 3;
        final int streakSizeToWinForLargerBoards = 4;

        if (board.getNumRows() > 3 || board.getNumCols() > 3) {
            streakSizeToWin = streakSizeToWinForLargerBoards;
            System.out.println("Given that the board has "+board.getNumRows()+" rows and "+board.getNumCols()+" columns, the winning streak size is now "+streakSizeToWin);
        }
        else{
            streakSizeToWin = defaultStreakSizeToWin;
        }
    }

    @Override
    public void setupMinNumPlayers() {
        minNumPlayers = 2;
    }

    @Override
    public void setupMaxNumPlayers() {
        maxNumPlayers = Math.max(2, board.getNumRows() * board.getNumCols() / 9); // Something reasonable
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
            if (!isLegalMove(currentMoveRow, currentMoveCol)){
                System.out.println("The space "+OutputColorer.red(String.valueOf(moveIndex))+ " is already taken.");
            }
        } while (!isLegalMove(currentMoveRow, currentMoveCol));

        if (teams != null){
            board.setCell(currentMoveRow, currentMoveCol, getCurrentTeam());
        }
        else{
            board.setCell(currentMoveRow, currentMoveCol, getCurrentPlayer());
        }
    }

    @Override
    public void start() {
        boolean isGameDone = false;
        while (!isGameDone) {
            doTurn();

            if (isWinningMove(currentMoveRow, currentMoveCol, board) || areNoMovesLeft(board)) {
                isGameDone = true;
            } else {
                assignNextCurrentPlayer();
            }
        }

        end();
    }

    /*
    Method will conclude the game by declaring winner and
    adjusting player stats. If user permits to play the
    game again, the same settings e.g. players and teams
    will be made.
     */
    @Override
    public void end() {
        System.out.println(board);

        final boolean thereIsAWinner = isWinningMove(currentMoveRow, currentMoveCol, board);
        if (thereIsAWinner) {
            if (teams != null) {
                final Team winningTeam = getCurrentTeam();
                System.out.println("Team " + winningTeam + " has won!");

                for (final Player player : winningTeam.getPlayers()) {
                    player.getGameRecord().addWin();
                }

                //Removing the winning team to add loss for other teams
                teams.poll();
                final Queue<Team> losingTeams = teams;
                for (final Team losingTeam : losingTeams) {
                    for (final Player losingPlayer : losingTeam.getPlayers()) {
                        losingPlayer.getGameRecord().addLoss();
                    }
                }

                //Adding winning team back in
                teams.add(winningTeam);
            } else {
                final Player winningPlayer = getCurrentPlayer();
                System.out.println("Player " + winningPlayer + " has won!");

                winningPlayer.getGameRecord().addWin();

                players.poll();

                for (final Player losingPlayer : players) {
                    losingPlayer.getGameRecord().addLoss();
                }

                // Adding player back
                players.add(winningPlayer);
            }
        }
        else {
            // A draw occurs, regardless of teams or no teams setting
            System.out.println("This "+this+" game has ended in a draw!");
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
        board.clear();
    }
    protected boolean isWinningMove(final int moveRow, final int moveCol, RectangleBoard board){
        /* This function will check if the move that was just made
         * was a winning move. It will return true if it was a winning
         * move, and false otherwise.
         */
        final Cell<?> cellToInvestigate = board.getCell(moveRow, moveCol);

        if (cellToInvestigate == null || cellToInvestigate.getOccupier() == null){
            /*
             * This case is to see whether the move that was made was perhaps
             * some empty case.
             */
            return false;
        }

        final char symbolToCheckFor = cellToInvestigate.getOccupier().getSymbol();

        int numInARow = 1;
        int row = moveRow;
        int col = moveCol;

        // Check horizontal
        while (col > 0 && board.getCell(row, col - 1) != null && board.getCell(row, col - 1).getOccupier() != null && board.getCell(row, col - 1).getOccupier().getSymbol() == symbolToCheckFor){
            numInARow++;
            col--;
        }

        col = moveCol;
        while (col < board.getNumCols() - 1 && board.getCell(row, col + 1) != null && board.getCell(row, col + 1).getOccupier() != null && board.getCell(row, col + 1).getOccupier().getSymbol() == symbolToCheckFor){
            numInARow++;
            col++;
        }

        if (numInARow >= streakSizeToWin){
            return true;
        }

        // Check vertical
        numInARow = 1;
        row = moveRow;
        col = moveCol;

        while (row > 0 && board.getCell(row - 1, col) != null && board.getCell(row - 1, col).getOccupier() != null && board.getCell(row - 1, col).getOccupier().getSymbol() == symbolToCheckFor){
            numInARow++;
            row--;
        }

        row = moveRow;
        while (row < board.getNumRows() - 1 && board.getCell(row + 1, col) != null && board.getCell(row + 1, col).getOccupier() != null && board.getCell(row + 1, col).getOccupier().getSymbol() == symbolToCheckFor){
            numInARow++;
            row++;
        }

        if (numInARow >= streakSizeToWin){
            return true;
        }

        // Check diagonal
        numInARow = 1;

        row = moveRow;
        col = moveCol;
        while (row > 0 && col > 0 && board.getCell(row - 1, col - 1) != null && board.getCell(row - 1, col - 1).getOccupier() != null && board.getCell(row - 1, col - 1).getOccupier().getSymbol() == symbolToCheckFor){
            numInARow++;
            row--;
            col--;
        }

        row = moveRow;
        col = moveCol;
        while (row < board.getNumRows() - 1 && col < board.getNumCols() - 1 && board.getCell(row + 1, col + 1) != null && board.getCell(row + 1, col + 1).getOccupier() != null && board.getCell(row + 1, col + 1).getOccupier().getSymbol() == symbolToCheckFor){
            numInARow++;
            row++;
            col++;
        }

        if (numInARow >= streakSizeToWin){
            return true;
        }

        // Check other diagonal
        numInARow = 1;

        row = moveRow;
        col = moveCol;
        while (row > 0 && col < board.getNumCols() - 1 && board.getCell(row - 1, col + 1) != null && board.getCell(row - 1, col + 1).getOccupier() != null && board.getCell(row - 1, col + 1).getOccupier().getSymbol() == symbolToCheckFor){
            numInARow++;
            row--;
            col++;
        }

        row = moveRow;
        col = moveCol;
        while (row < board.getNumRows() - 1 && col > 0 && board.getCell(row + 1, col - 1) != null && board.getCell(row + 1, col - 1).getOccupier() != null  && board.getCell(row + 1, col - 1).getOccupier().getSymbol() == symbolToCheckFor){
            numInARow++;
            row++;
            col--;
        }

        return numInARow >= streakSizeToWin;
    }

    public boolean areNoMovesLeft(RectangleBoard board){
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
        if (teams == null) {
            return players.peek();
        }
        else {
            return teams.peek().getPlayers().peek();
        }
    }

    public Team getCurrentTeam(){ return teams.peek(); }

    @Override
    public void assignNextCurrentPlayer() {
        if (teams != null) {
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
        else {
            final Player currentPlayer = players.poll();
            players.offer(currentPlayer);
        }
    }

    @Override
    public String toString(){
        return "TicTacToe";
    }
}
