import java.util.ArrayList;

public class LegendsOfValor extends LegendsRolePlayGame<LegendsOfValorMap>{
    private ArrayList<Monster> monsters;
    private boolean playerWantsToQuit;
    private Hero currentHero;

    public LegendsOfValor(){
        description();

        minNumPlayers = 1;
        maxNumPlayers = 1;
        setupPlayers(minNumPlayers, maxNumPlayers);

        setupMap();

        minNumHeroes = 3;
        maxNumHeroes = 3;
        setupHeroes(minNumHeroes, maxNumHeroes);

        setupMonsters();

        makeSureReady();
    }


    private void getNextHeroToMove(){
        if (currentHero == null) {
            currentHero = heroes[0];
            return;
        }
        for (int heroIndex = 0; heroIndex < heroes.length; heroIndex++){
            if (heroes[heroIndex] == currentHero){
                currentHero = heroes[(heroIndex+1) % heroes.length];
                return;
            }
        }
    }

    private boolean isGameOver(){
        return playerWantsToQuit || didMonstersReachHeroNexus() || didHeroesReachMonsterNexus();
    }

    private boolean didHeroesReachMonsterNexus(){
        final int monsterNexusRow = 1;
        for (int mapColIndex = 0; mapColIndex < map.numCols; mapColIndex++){
            //Note: we know none of these cells are null since it is all either nexus or border
            Symbolable occupier = map.getCell(monsterNexusRow, mapColIndex).getOccupier();
            if (occupier instanceof MonsterNexus){
                if (((MonsterNexus) occupier).getOccupier() instanceof Hero){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean didMonstersReachHeroNexus(){
        final int heroNexusRow = map.numRows - 2;
        for (int mapColIndex = 0; mapColIndex < map.numCols; mapColIndex++){
            //Note: we know none of these cells are null since it is all either nexus or border
            Symbolable occupier = map.getCell(heroNexusRow, mapColIndex).getOccupier();
            if (occupier instanceof HeroNexus){
                if (((HeroNexus) occupier).getOccupier() instanceof Monster){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method initializes map with monsters in their appropriate nexus
     */
    public void setupMonsters(){
        monsters = new ArrayList<>();
        //Selecting initial monsters
        for (Hero hero : heroes) {
            ArrayList<Monster> possibleMonsters = MonsterReader.readAll();
            Monster monsterToAdd = possibleMonsters.get((int) (Math.random() * possibleMonsters.size()));
            //Ensure even match that monsters are the same level
            monsterToAdd.setLevel(hero.getLevel());
            monsters.add(monsterToAdd);
        }

        //Placing monster down into each monster nexus zone.
        final int monsterNexusRowIndex = 1;
        final int monsterNexusColIndexIncrement = (map.numSpacesPerMovableArea + 1)*map.numSquaresInColPerZone;
        for (int monsterNexusColIndex = 1+1, monsterIndex = 0;
             monsterNexusColIndex < map.numCols & monsterIndex < monsters.size();
             monsterNexusColIndex += monsterNexusColIndexIncrement, monsterIndex++){
            //We know from map setup these cells are MonsterNexus cells
            MonsterNexus nexusCell = (MonsterNexus) (map.getCell(monsterNexusRowIndex, monsterNexusColIndex).getOccupier());
            nexusCell.setOccupier(monsters.get(monsterIndex));
        }
    }

    @Override
    public void setupPlayers(final int minNumPlayers, final int maxNumPlayers){
        super.setupPlayers(minNumPlayers, maxNumPlayers);
        player = players.peek();
    }

    @Override
    public void setupHeroes(final int minNumHeroes, final int maxNumHeroes){
        super.setupHeroes(minNumHeroes, maxNumHeroes);

        //Placing hero down into each hero nexus zone.
        final int heroNexusRowIndex = map.numRows - 2;
        final int heroNexusColIndexIncrement = (map.numSpacesPerMovableArea + 1)*map.numSquaresInColPerZone;
        for (int heroNexusColIndex = 1, heroIndex = 0;
             heroNexusColIndex < map.numCols & heroIndex < heroes.length;
             heroNexusColIndex += heroNexusColIndexIncrement, heroIndex++){
            //We know from map setup these cells are HeroNexus cells
            HeroNexus nexusCell = (HeroNexus) (map.getCell(heroNexusRowIndex, heroNexusColIndex).getOccupier());
            System.out.println("Hero "+heroes[heroIndex]+" has been placed into lane "+(heroIndex+1));
            nexusCell.setOccupier(heroes[heroIndex]);
        }
    }
    /**
     * Method to output game description
     */
    @Override
    public String description() {
        return "Legends of Valor is a game that consists of Heroes and Monsters: you start with three selected\n"+
                "heroes in three respective lanes. The goal is to get to the monsters' base (nexus) before the monsters\n"+
                "get to your base. The game gets more interesting with a variety of terrain types and battling the \n"+
                "monsters! Have fun!";
    }

    @Override
    public void start() {

        while (!isGameOver()){
            getNextHeroToMove();
            System.out.println("It is Hero "+currentHero+"'s turn!");
            System.out.println(map);
            move();
            //TODO: add monster move
        }

        end();
    }

    private void move() {
        final int[] currentPosition = map.find(currentHero);
        final int currentRow = currentPosition[0], currentCol = currentPosition[1];

        final ArrayList<String> validKeyboardPresses = new ArrayList<String>() {{
                    add("w"); add("a"); add("s"); add("d"); add("i"); add("q"); add("t");
        }};

        final String marketKeypress = "m";
        final String attackKeypress = "x";
        final String obstclKeypress = "o";
        //Note: we know hero never disappears from the map so the current row and current col cell content
        //is never null
        if (map.getCell(currentRow, currentCol).getOccupier() instanceof MarketSpaceSymbol) {
            validKeyboardPresses.add(marketKeypress);
        }
        //TODO: check adjacent cell for obstacle and monster attacking AND equip stuff
        boolean moveSuccessful;
        String keyPressed;

        do {
            String prompt = "What would you like to do: move (wasd), see inventory (i), quit (q), teleport (t)";
            if (validKeyboardPresses.contains(marketKeypress)) {
                prompt += OutputColorer.green(", enter market ("+marketKeypress+")");
            }
            if (validKeyboardPresses.contains(attackKeypress)) {
                prompt += OutputColorer.red(", attack nearby monster ("+attackKeypress+")");
            }
            if (validKeyboardPresses.contains(obstclKeypress)) {
                prompt += OutputColorer.yellow(", remove nearby obstacle ("+obstclKeypress+")");
            }

            keyPressed = UserInteractor.getStringInput(prompt, validKeyboardPresses.toArray(new String[0])).toLowerCase();
            moveSuccessful = processMove(keyPressed, currentRow, currentCol);
            if (!moveSuccessful) {
                System.out.println("You cannot do that move");
            }
        } while (!moveSuccessful);

        performMove(currentRow, currentCol, keyPressed);
    }

    private boolean processMove(final String keyPressed, final int currentRow, final int currentCol) {
        final boolean moveSuccessful;
        int newRow, newCol;
        final int heroColMoveIncrement = map.numSpacesPerMovableArea + 1;
        final int heroRowMoveIncrement = 1 + 1;
        switch (keyPressed) {
            case "w":
                newRow = currentRow - heroRowMoveIncrement;
                newCol = currentCol;
                moveSuccessful = validateWalkMove(currentRow, currentCol, newRow, newCol);
                break;
            case "a":
                newRow = currentRow;
                newCol = currentCol - heroColMoveIncrement;
                moveSuccessful = validateWalkMove(currentRow, currentCol, newRow, newCol);
                break;
            case "s":
                newRow = currentRow + heroRowMoveIncrement;
                newCol = currentCol;
                moveSuccessful = validateWalkMove(currentRow, currentCol, newRow, newCol);
                break;
            case "d":
                newRow = currentRow;
                newCol = currentCol + heroColMoveIncrement;
                moveSuccessful = validateWalkMove(currentRow, currentCol, newRow, newCol);
                break;
                //TODO: validate teleport and obstacle removal since they have to make decision for these
            default:
                moveSuccessful = true;
                break;
        }

        return moveSuccessful;
    }

    private boolean validateWalkMove(final int currentRow, final int currentCol, final int newRow, final int newCol) {
        //TODO: ensure movement also based on monster location relative to new position
        final boolean isInGrid =  newRow >= 1 && newRow < map.numRows - 1 &&
                newCol >= 1 && newCol < map.numCols - 1;
        if (!isInGrid) return false;

        final boolean spaceIsOccupiable = map.isCellOccupiable(newRow, newCol);
        System.out.println("isSpaceOccu "+spaceIsOccupiable);
        if (!spaceIsOccupiable) return false;
        //Ensuring we do not cross over to another nexus or hit an inaccessible/obstacle symbol on the way
        //by traversing through each cell the hero would cross

        //Moving by row only
        if (newRow != currentRow) {
            final int rowIndexValidationIncrement = Math.abs(newRow - currentRow) / (newRow - currentRow);
            for (int rowIndexValidation = currentRow + rowIndexValidationIncrement; rowIndexValidation != newRow; rowIndexValidation+=rowIndexValidationIncrement){
                //Ignore dashes and vertical bars since these are for display
                final Cell<?> cell = map.getCell(rowIndexValidation, newCol);
                final boolean isDisplayBar = (cell != null
                        && (cell.getOccupier() instanceof DashSymbol
                        || cell.getOccupier() instanceof BarSymbol
                ));
                if (isDisplayBar) continue;
                if (!map.isCellOccupiable(rowIndexValidation, newCol)){
                    System.out.println("For pos "+rowIndexValidation+" and col "+currentCol+" not occu");

                    return false;
                }
            }
        }

        //Moving by col only
        else if (newCol != currentCol) {
            final int colIndexValidationIncrement = Math.abs(newCol - currentCol) / (newCol - currentCol);
            for (int colIndexValidation = currentCol + colIndexValidationIncrement; colIndexValidation != newCol; colIndexValidation+=colIndexValidationIncrement){
                //Ignore dashes and vertical bars since these are for display
                final Cell<?> cell = map.getCell(newRow, colIndexValidation);
                final boolean isDisplayBar = (cell != null
                        && (cell.getOccupier() instanceof DashSymbol
                        || cell.getOccupier() instanceof BarSymbol
                ));
                if (isDisplayBar) continue;
                if (!map.isCellOccupiable(newRow, colIndexValidation)){
                    System.out.println("For pos "+currentRow+" and col "+colIndexValidation+" not occu");

                    return false;
                }
            }
        }

        return true;
    }

    private void performMove(int currentRow, int currentCol, final String moveAction){
        switch (moveAction) {
            case "w":
                walk(currentHero, Direction.UP);
                break;
            case "a":
                walk(currentHero, Direction.LEFT);
                break;
            case "s":
                walk(currentHero, Direction.DOWN);
                break;
            case "d":
                walk(currentHero, Direction.RIGHT);
                break;
            case "i":
                displayInventory(player.getInventory());
                break;
            case "m":
                //M means the cell has to be a Nexus
                final LegendsMonstersAndHeroesMarket market =
                        (LegendsMonstersAndHeroesMarket)
                                ((HeroNexus) map.getCell(currentRow, currentCol).getOccupier()).getMarket();
                displayMarket(market);
                break;
            case "q":
                playerWantsToQuit = true;
                break;
        }
    }

    @Override
    public void end() {

    }

    /**
     * Method to clear game variables. This is
     * so the game can be played again with new settings
     */
    @Override
    public void reset() {

    }

    /**
     * This method is to configure the RPG's
     * appropriate {@link GameMap}
     */
    @Override
    public void setupMap() {
        map = new LegendsOfValorMap();
    }

    @Override
    protected <S extends Symbolable> void walk(S hero, Direction direction) {
        final int heroColMoveIncrement = map.numSpacesPerMovableArea + 1;
        final int heroRowMoveIncrement = 1 + 1;

        final int[] location = map.find(hero);
        final int currentRow = location[0], currentCol = location[1];

        map.clearCellOccupier(currentRow, currentCol);

        final int newRow, newCol;
        switch (direction) {
            case UP:
                newRow = currentRow - heroRowMoveIncrement;
                newCol = currentCol;
                break;
            case DOWN:
                newRow = currentRow + heroRowMoveIncrement;
                newCol = currentCol;
                break;
            case LEFT:
                newRow = currentRow;
                newCol = currentCol - heroColMoveIncrement;
                break;
            case RIGHT:
                newRow = currentRow;
                newCol = currentCol + heroColMoveIncrement;
                break;
            default:
                throw new IllegalArgumentException("Unhandled walk direction " + direction);
        }
        /*
         * NOTE: we do not need to check for any validation logic since that is already handled
         * before calling this method
         */

        if (map.getCell(newRow, newCol) == null){
            map.setCell(newRow, newCol, hero);
            return;
        }

        final boolean isNewAreaTerrain = (map.getCell(newRow, newCol).getOccupier() instanceof Terrain);
        final boolean isNewAreaNexus = (map.getCell(newRow, newCol).getOccupier() instanceof Nexus);

        if (isNewAreaTerrain){
            ((Terrain) map.getCell(newRow, newCol).getOccupier()).setOccupier(currentHero);
        }
        else if (isNewAreaNexus){
            ((Nexus) map.getCell(newRow, newCol).getOccupier()).setOccupier(hero);
        }
        //All cases should be covered here
    }

    /**
     * Method initializes the shops content that
     * would show in an RPG game
     */
    @Override
    protected void setupShops() {

    }

    @Override
    protected void displayMarket(Market market) {
        //We will ask which hero the player wants to enter the shop with
        System.out.println(currentHero+OutputColorer.green(" is entering market "+market.name+"!"));
        market.displayToCustomer(currentHero);
    }

    /**
     * Method displays an inventory of a given
     * player/team
     *
     * @param inventory
     */
    @Override
    protected void displayInventory(Inventory inventory) {
        System.out.println(player.getInventory());
        UserInteractor.getStringInput("Type ok when you are done looking", new String[]{"ok"});
    }
}
