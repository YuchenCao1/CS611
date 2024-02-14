import java.util.*;

public class Quoridor extends BoardGame<QuoridorBoard> {
    private int currentPawnMoveRow;
    private int currentPawnMoveCol;
    private int currentWallMoveRow;
    private int currentWallMoveCol;
    private int currentMoveType;
    private Direction currentWallDirection;

    private int numPawns;
    /*
     * The following are pawns that start in a given location. For example,
     * the South Pawn is a pawn that started south. Its objective is to
     * get to the top of the board.
     */
    private Team northPawn;
    private Team southPawn;
    private Team eastPawn;
    private Team westPawn;
    private int wallSize;

    public Quoridor(){
        System.out.println("Welcome to "+this+"!");
        System.out.println(description());

        setupBoard();

        teamsAllowed = true;
        /*
         * Forcing players to either go into 2-4 teams since
         * this game limits number of pawns to move. This is
         * because the objective is to get one's pawn to the
         * other side, so since this is a rectangle, the board
         * has 4 sides.
         */
        teamsRequired = true;
        setupNumPawns();

        setupMinNumPlayers();
        setupMaxNumPlayers();
        setupPlayers(minNumPlayers, maxNumPlayers);

        System.out.println("Now you will have to setup the teams for each pawn.");
        setupTeams(numPawns, numPawns, null, players);

        setupPawns();
        setupWallInventory();

        makeSureReady();
    }

    /**
     * This method initializes the wall inventory for each team. This will
     * be created based on the number of pawns.
     */
    private void setupWallInventory(){
        // Something reasonable
        final int numberOfWallsPerTeam = (20/numPawns)*(board.getNumRowsWithoutBorders()*board.getNumColsWithoutBorders()/81);
        // Something reasonable
        wallSize = 2* (board.getNumRowsWithoutBorders() * board.getNumColsWithoutBorders()) / 81;
        System.out.println("Each team will have "+numberOfWallsPerTeam+" walls per team.");
        System.out.println("The wall size that will be placed down will be a size of "+wallSize+"x1.");
        for (Team team : teams){
            //Ensuring each wall is a different address in the heap
            final int wallCount = team.getInventory().getItemCount(WallSymbol.class);
            //Clear up any walls already stored
            if (wallCount > 0){
                team.getInventory().removeItem(WallSymbol.class, wallCount);
            }

            team.getInventory().addItem(new WallSymbol(), numberOfWallsPerTeam);
        }
    }

    private void setupPawns(){
        //Assigning provided teams to pawns
        northPawn = teams.poll();
        southPawn = teams.poll();
        /*
         * NOTE: poll won't error for empty queue, so we can safely retrieve pawns
         * without worrying if the number of pawns assigned is less than 4.
         */
        eastPawn = teams.poll();
        westPawn = teams.poll();

        teams.add(northPawn);
        teams.add(southPawn);

        System.out.println("NORTH PAWN will be "+northPawn);
        System.out.println("SOUTH PAWN will be "+southPawn);

        if (eastPawn != null){
            teams.add(eastPawn);
            System.out.println("EAST PAWN will be "+eastPawn);
        }
        if (westPawn != null){
            teams.add(westPawn);
            System.out.println("WEST PAWN will be "+westPawn);
        }

        final int northPawnStartingRow = 1;
        final int southPawnStartingRow = board.getNumRows() - 2;

        int northAndSouthPawnStartingCol = (board.getNumCols()-1)/2;
        /*
         * An even position based on the board configuration is a Bar Symbol,
         * so we need to ensure the column number is odd
         */
        if (northAndSouthPawnStartingCol % 2 == 0){
            northAndSouthPawnStartingCol--;
        }

        board.setCell(northPawnStartingRow, northAndSouthPawnStartingCol, northPawn);
        board.setCell(southPawnStartingRow, northAndSouthPawnStartingCol, southPawn);

        if (eastPawn != null){
            final int eastPawnStartingCol = 1;

            int eastAndWestPawnStartingRow = (board.getNumRows()-1)/2;
            //Similar to the north and south, have to ensure number is odd
            if (eastAndWestPawnStartingRow % 2 == 0){
                eastAndWestPawnStartingRow--;
            }

            board.setCell(eastAndWestPawnStartingRow, eastPawnStartingCol, eastPawn);
            if (westPawn != null){
                final int westPawnStartingCol = board.getNumCols() - 2;

                board.setCell(eastAndWestPawnStartingRow, westPawnStartingCol, westPawn);
            }
        }
    }

    private boolean wouldWallIntersectOthers(final int wallRow, final int wallCol, final Direction direction){
        int wallCornerRow = 0;
        int wallCornerCol = 0;
        int wallRowIncrement = 0;
        int wallColIncrement = 0;

        switch (direction){
            case UP:
                wallCornerRow = wallRow - 1;
                wallCornerCol = wallCol - 1;
                wallColIncrement = 1;
                break;
            case DOWN:
                wallCornerRow = wallRow + 1;
                wallCornerCol = wallCol - 1;
                wallColIncrement = 1;
                break;
            case LEFT:
                wallCornerRow = wallRow - 1;
                wallCornerCol = wallCol - 1;
                wallRowIncrement = 1;
                break;
            case RIGHT:
                wallCornerRow = wallRow - 1;
                wallCornerCol = wallCol + 1;
                wallRowIncrement = 1;
                break;
        }

        //Ensuring wall isn't going to be too big it goes off the board
        if (wallCornerRow + wallRowIncrement*wallSize*2 >= board.getNumRows() || wallCornerCol + wallColIncrement*wallSize*2 >= board.getNumCols()){
            return false;
        }

        //Ensuring wall doesn't cross another wall
        int numAdjacentWalls = 0;

        for (int cellCheckerIndex = 1, cellRowIndex = wallCornerRow+wallRowIncrement,
             cellColIndex = wallCornerCol+wallColIncrement; cellCheckerIndex < 2*wallSize-1;
             cellCheckerIndex++, cellRowIndex+=wallRowIncrement, cellColIndex+=wallColIncrement){
            /*
             * If we find a wall cell, we need to make sure this one isn't an edge of a wall since edges can touch.
             * This can be done by checking one cell above, below, left, and right and checking if it is a wall.
             */
            if (board.getCell(cellRowIndex, cellColIndex) != null && board.getCell(cellRowIndex, cellColIndex).getOccupier() instanceof WallSymbol){
                if (cellRowIndex+1 < board.getNumRows()
                        && board.getCell(cellRowIndex+1, cellColIndex) != null
                        && board.getCell(cellRowIndex+1, cellColIndex).getOccupier() instanceof WallSymbol){
                    if (direction == Direction.LEFT || direction == Direction.RIGHT){
                        return false;
                    }
                    numAdjacentWalls++;
                }
                if (cellRowIndex-1 >= 0
                        && board.getCell(cellRowIndex-1, cellColIndex) != null
                        && board.getCell(cellRowIndex-1, cellColIndex).getOccupier() instanceof WallSymbol){
                    if (direction == Direction.LEFT || direction == Direction.RIGHT){
                        return false;
                    }
                    numAdjacentWalls++;
                }
                if (cellColIndex+1 < board.getNumCols()
                        && board.getCell(cellRowIndex, cellColIndex+1) != null
                        && board.getCell(cellRowIndex, cellColIndex+1).getOccupier() instanceof WallSymbol){
                    if (direction == Direction.UP || direction == Direction.DOWN){
                        return false;
                    }
                    numAdjacentWalls++;
                }
                if (cellColIndex-1 >= 0
                        && board.getCell(cellRowIndex, cellColIndex-1) != null
                        && board.getCell(cellRowIndex, cellColIndex-1).getOccupier() instanceof WallSymbol){
                    if (direction == Direction.UP || direction == Direction.DOWN){
                        return false;
                    }
                    numAdjacentWalls++;
                }
                if (numAdjacentWalls >= wallSize){
                    return false;
                }
            }
        }

        currentWallMoveRow = wallRow;
        currentWallMoveCol = wallCol;
        currentWallDirection = direction;
        return true;
    }

    /**
     * Method determines if wall placement will block any pawn from winning.
     * This method uses DFS to achieve this.
     */
    private boolean doesWallMoveBlockPawn(final int wallRow, final int wallCol, final Direction direction) {
        QuoridorBoard copyBoard = board.copy();
        boolean[][] visited = new boolean[board.getNumRows()][board.getNumCols()];
        Map<String, Boolean> memo = new HashMap<>();

        // Simulate placing the wall on the copy board
        placeWall(wallRow, wallCol, direction, copyBoard);

        // Iterate over each pawn and check if it can still reach the goal
        for (Team pawn : teams) {
            int[] pawnPosition = copyBoard.find(pawn);
            if (pawnPosition != null) {
                int row = pawnPosition[0];
                int col = pawnPosition[1];
                if (!canPawnReachGoal(copyBoard, visited, memo, pawn, row, col)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean canPawnReachGoal(QuoridorBoard testBoard, boolean[][] visited, Map<String, Boolean> memo, Team pawn, final int rowIndex, final int colIndex){
        String state = rowIndex + "," + colIndex;
        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        if (isWinningMove(rowIndex, colIndex, pawn, testBoard)){
            memo.put(state, true);
            return true;
        }
        visited[rowIndex][colIndex] = true;

        //Checking all 4 cell neighbors
        for (Direction direction : Direction.values()) {
            int[] pawnMovePosition = isPawnMoveLegal(pawn, direction, testBoard);
            if (pawnMovePosition != null  && !visited[pawnMovePosition[0]][pawnMovePosition[1]]) {
                final int newRow = pawnMovePosition[0];
                final int newCol = pawnMovePosition[1];
                visited[newRow][newCol] = true;
                // Moving pawn
                Cell<?> emptyCell = null;
                testBoard.setCell(rowIndex, colIndex, emptyCell);
                testBoard.setCell(newRow, newCol, pawn);

                if (canPawnReachGoal(testBoard, visited, memo, pawn, newRow, newCol)) {
                    // Reverting the move
                    testBoard.setCell(newRow, newCol, emptyCell);
                    testBoard.setCell(rowIndex, colIndex, pawn);
                    visited[newRow][newCol] = false;
                    memo.put(state, true);
                    return true;
                }

                // Reverting the move
                testBoard.setCell(newRow, newCol, emptyCell);
                testBoard.setCell(rowIndex, colIndex, pawn);
                //visited[newRow][newCol] = false;
            }
        }
        visited[rowIndex][colIndex] = false;
        memo.put(state, false);
        return false;
    }
    private void placeWall(final int wallRow, final int wallCol, final Direction direction, final QuoridorBoard board){
        int wallCornerRow = 0;
        int wallCornerCol = 0;
        int wallRowIncrement = 0;
        int wallColIncrement = 0;
        WallSymbol symbolToPlace = null;

        switch (direction){
            case UP:
                wallCornerRow = wallRow - 1;
                wallCornerCol = wallCol - 1;
                wallColIncrement = 1;
                symbolToPlace = new HorizontalWallSymbol();
                break;
            case DOWN:
                wallCornerRow = wallRow + 1;
                wallCornerCol = wallCol - 1;
                wallColIncrement = 1;
                symbolToPlace = new HorizontalWallSymbol();
                break;
            case LEFT:
                wallCornerRow = wallRow - 1;
                wallCornerCol = wallCol - 1;
                wallRowIncrement = 1;
                symbolToPlace = new VerticalWallSymbol();
                break;
            case RIGHT:
                wallCornerRow = wallRow - 1;
                wallCornerCol = wallCol + 1;
                wallRowIncrement = 1;
                symbolToPlace = new VerticalWallSymbol();
                break;
        }

        for (int cellCheckerIndex = 0, cellRowIndex = wallCornerRow,
             cellColIndex = wallCornerCol; cellCheckerIndex < 2*wallSize+1 && cellRowIndex < board.getNumRows() && cellColIndex < board.getNumCols();
             cellCheckerIndex++, cellRowIndex+=wallRowIncrement, cellColIndex+=wallColIncrement){
            board.setCell(cellRowIndex, cellColIndex, symbolToPlace);
        }
    }

    private boolean isWallMoveLegal(final int wallRow, final int wallCol, final Direction direction){
        return wouldWallIntersectOthers(wallRow, wallCol, direction) && !doesWallMoveBlockPawn(wallRow, wallCol, direction);
    }

    private int[] isPawnMoveLegal(final Team currentPawn, final Direction direction, final QuoridorBoard board){
        final int[] pawnPosition = board.find(currentPawn);
        final int rowIndex = pawnPosition[0];
        final int colIndex = pawnPosition[1];

        //Since borders are included in the board, we need to check up by a multiple of 2 cells
        int rowMoveIncrement = 0;
        int colMoveIncrement = 0;
        switch (direction){
            case LEFT:
                colMoveIncrement = -2;
                break;
            case RIGHT:
                colMoveIncrement = 2;
                break;
            case UP:
                rowMoveIncrement = -2;
                break;
            case DOWN:
                rowMoveIncrement = 2;
                break;
        }

        int newMoveRowIndex = rowIndex;
        int newMoveColIndex = colIndex;

        boolean isSpaceOccupiedByAPawn;
        boolean isThereAWallBetween;

        do{
            newMoveRowIndex+=rowMoveIncrement;
            newMoveColIndex+=colMoveIncrement;

            final boolean rowIndexExists = newMoveRowIndex >= 0 && newMoveRowIndex < board.getNumRows();
            final boolean colIndexExists = newMoveColIndex >= 0 && newMoveColIndex < board.getNumCols();
            if (!rowIndexExists || !colIndexExists)
                return null;

            switch (direction){
                case UP:
                    isThereAWallBetween = board.getCell(newMoveRowIndex+1, newMoveColIndex) != null && board.getCell(newMoveRowIndex+1, newMoveColIndex).getOccupier() instanceof WallSymbol;
                    break;
                case DOWN:
                    isThereAWallBetween = board.getCell(newMoveRowIndex-1, newMoveColIndex) != null && board.getCell(newMoveRowIndex-1, newMoveColIndex).getOccupier() instanceof WallSymbol;
                    break;
                case LEFT:
                    isThereAWallBetween = board.getCell(newMoveRowIndex, newMoveColIndex+1) != null && board.getCell(newMoveRowIndex, newMoveColIndex+1).getOccupier() instanceof WallSymbol;
                    break;
                case RIGHT:
                    isThereAWallBetween = board.getCell(newMoveRowIndex, newMoveColIndex-1) != null && board.getCell(newMoveRowIndex, newMoveColIndex-1).getOccupier() instanceof WallSymbol;
                    break;
                default:
                    isThereAWallBetween = false;
            }

            if (isThereAWallBetween)
                return null;

            isSpaceOccupiedByAPawn = board.getCell(newMoveRowIndex, newMoveColIndex) != null && board.getCell(newMoveRowIndex, newMoveColIndex).getOccupier() instanceof Team;
        } while (isSpaceOccupiedByAPawn);

        currentPawnMoveRow = newMoveRowIndex;
        currentPawnMoveCol = newMoveColIndex;

        return new int[]{newMoveRowIndex, newMoveColIndex};
    }

    private boolean isWinningMove(final int moveRow, final int moveCol, Team pawn, final QuoridorBoard board){
        if (pawn == null) {
            pawn = (Team) board.getCell(moveRow, moveCol).getOccupier();
        }

        if (pawn == northPawn && moveRow == board.getNumRows() - 2){
            return true;
        }
        else if (pawn == southPawn && moveRow == 1){
            return true;
        }
        else if (pawn == eastPawn && moveCol == board.getNumCols() - 2){
            return true;
        }
        else return pawn == westPawn && moveCol == 1;
    }

    @Override
    public boolean isLegalMove(int rowNum, int colNum) {
        return false;
    }

    @Override
    public void setupBoard() {
        // Setting up board details from user input
        final int boardNumRows = UserInteractor.getIntegerInput("Enter the number of rows for the "+this+" board", 9);
        final int boardNumCols = UserInteractor.getIntegerInput("Enter the number of cols for the "+this+" board", 9);
        board = new QuoridorBoard(boardNumRows, boardNumCols);
    }

    private void setupNumPawns(){
        numPawns = UserInteractor.getIntegerInput("Enter the number of pawns", 2, 4);
    }

    @Override
    public void setupMinNumPlayers() {
        //Minimum number of players have to match number of pawns.
        minNumPlayers = numPawns;
    }

    @Override
    public void setupMaxNumPlayers() {
        //Some reasonable number before game gets annoying.
        maxNumPlayers = numPawns*2;
    }

    @Override
    public void doTurn() {
        System.out.println("It is " + getCurrentPlayer() + "'s from " + getCurrentTeam() + " team's turn!");
        System.out.println(board);

        final boolean canPlaceWalls = getCurrentTeam().getInventory().getItemCount(WallSymbol.class) > 0;

        if (canPlaceWalls){
            currentMoveType = UserInteractor.getIntegerInput("You can do multiple actions. What would you like to do?\n1: Move pawn\n2: Place a wall ("+getCurrentTeam().getInventory().getItemCount(WallSymbol.class)+" left)", 1, 2);
        }
        else {
            currentMoveType = 1;
        }

        //Move Type = 1: Moving Pawn
        boolean isPawnMoveLegal;
        Direction pawnMoveDirection;

        if (currentMoveType == 1){
            final Team currentPawn = getCurrentTeam();
            do {
                pawnMoveDirection = UserInteractor.getEnumInput(Direction.class, "What direction would you like to move your pawn?");
                isPawnMoveLegal = isPawnMoveLegal(currentPawn, pawnMoveDirection, board) != null;
                if (!isPawnMoveLegal){
                    System.out.println("The direction "+OutputColorer.red(pawnMoveDirection.toString())+" cannot be made.");
                }
            } while (!isPawnMoveLegal);

            //Move piece e.g. remove old location and replace with a new one
            int[] pawnLocation = board.find(currentPawn);
            final Cell<?> emptyCell = null;
            board.setCell(pawnLocation[0], pawnLocation[1], emptyCell);
            board.setCell(currentPawnMoveRow, currentPawnMoveCol, getCurrentTeam());

            System.out.println("Team "+getCurrentTeam()+" has "+getCurrentTeam().getInventory().getItemCount(WallSymbol.class)+" walls left to place.");
        }

        //Move type 2: Placing a wall
        else if (currentMoveType == 2){
            boolean wallMoveIsLegal;
            do {
                wallMoveIsLegal = true;
                final int minBoardIndexToSelect = 1;
                final int maxBoardIndexToSelect = board.getNumRowsWithoutBorders() * board.getNumColsWithoutBorders();
                final int moveIndex = UserInteractor.getIntegerInput("Enter an index as displayed by the board to place down the wall", minBoardIndexToSelect, maxBoardIndexToSelect);
                currentWallMoveRow = 2*((moveIndex - 1) / board.getNumColsWithoutBorders())+1;
                currentWallMoveCol = 2*((moveIndex - 1) % board.getNumColsWithoutBorders())+1;
                final Direction wallDirection = UserInteractor.getEnumInput(Direction.class, "Which side of the cell "+moveIndex+" do you want to place the wall?");
                if (!isWallMoveLegal(currentWallMoveRow, currentWallMoveCol, wallDirection)){
                    System.out.println("Wall placement is invalid: check if a wall is already there or if the proposed wall placement would go off\nthe board. Be sure to also check if the wall blocks other player(s) from having a winning path.");
                    wallMoveIsLegal = false;
                }
            } while (!wallMoveIsLegal);

            placeWall(currentWallMoveRow, currentWallMoveCol, currentWallDirection, board);
            getCurrentTeam().getInventory().removeItem(WallSymbol.class, 1);
        }
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

    @Override
    public Player getCurrentPlayer() {
        return teams.peek().getPlayers().peek();
    }

    @Override
    public Team getCurrentTeam(){ return teams.peek(); }

    @Override
    public String description() {
        return "The objective of Quoridor is to be the first player to reach the opposite side of the game board."+"\n"+
                "Each player starts with a pawn and a set of wooden fences. On their turn, a player can either " + "\n"+
                "move their pawn to an adjacent square or place one of their fences on the board to block their "+"\n"+
                "opponent's path. The fences are placed between the squares and can be used strategically to create obstacles"+"\n"+
                "and impede the progress of the other player(s)."+"\n\n"+
                "The game requires careful planning, as players must decide whether to advance their own pawn or block their opponents." +"\n"+
                "The first player to reach the row opposite to their starting position wins the game.";
    }

    @Override
    public void start() {
        boolean isGameDone = false;
        while (!isGameDone) {
            doTurn();

            /*
            * Theoretically there exists no draw in this game so only winning move
            * needs to be checked.
            *
            * We check if currentMoveType is 1 because here, only moving the pawn
            * can win the game.
            */
            if (currentMoveType == 1 && isWinningMove(currentPawnMoveRow, currentPawnMoveCol, getCurrentTeam(), board)) {
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

        for (final Player player : players){
            System.out.println(player+"'s record is now "+player.getGameRecord());
        }

        promptToReplay();
    }

    @Override
    public void reset() {
        board = new QuoridorBoard(board.getNumRowsWithoutBorders(), board.getNumColsWithoutBorders());
        setupPawns();
        setupWallInventory();
    }

    @Override
    public String toString(){
        return "Quoridor";
    }
}
