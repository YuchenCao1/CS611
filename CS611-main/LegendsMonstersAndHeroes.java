import java.util.ArrayList;
import java.util.Arrays;

public class LegendsMonstersAndHeroes extends LegendsRolePlayGame<LegendsMonstersAndHeroesMap> {
    private MarketSpaceSymbol visitedMarket;
    private boolean isGameOver; // It is open-ended but we're instructed to have quit option and dead heroes -> no play
    private final double probabilityOfWalkingIntoBattle = 0.1;

    public LegendsMonstersAndHeroes(){
        System.out.println(description());

        setupMap();

        minNumPlayers = 1;
        maxNumPlayers = 1;
        setupPlayers(minNumPlayers, maxNumPlayers);

        minNumHeroes = 1;
        maxNumHeroes = 3;
        setupHeroes(minNumHeroes, maxNumHeroes);

        makeSureReady();
    }

    @Override
    public void setupPlayers(final int minNumPlayers, final int maxNumPlayers){
        super.setupPlayers(minNumPlayers, maxNumPlayers);

        player = players.peek();

        //Then place player on map
        for (int rowIndex = 0; rowIndex < map.getNumRows(); rowIndex++){
            for (int colIndex = 0; colIndex < map.getNumCols(); colIndex++){
                if (map.getCell(rowIndex, colIndex) == null){
                    map.setCell(rowIndex, colIndex, player);
                    return;
                }
            }
        }
        //Note we are guaranteed player placement so no nullity checks are needed.
    }

    @Override
    public void setupMap(){
        final int minMapSize = 8;
        final int maxMapSize = 20;
        final int numRows = UserInteractor.getIntegerInput("Enter the number of rows for the map", minMapSize, maxMapSize);
        final int numCols = UserInteractor.getIntegerInput("Enter the number of cols for the map", minMapSize, maxMapSize);
        map = new LegendsMonstersAndHeroesMap(numRows, numCols);
    }

    @Override
    protected <S extends Symbolable> void walk(S object, Direction direction) {
        assert object instanceof Player;
        Player player = (Player) object;
        final int[] currentPosition = map.find(player);
        final int currentRow = currentPosition[0], currentCol = currentPosition[1];
        final int newRow, newCol;

        switch (direction){
            case LEFT:
                newRow = currentRow;
                newCol = currentCol - 1;
                break;
            case RIGHT:
                newRow = currentRow;
                newCol = currentCol + 1;
                break;
            case UP:
                newRow = currentRow - 1;
                newCol = currentCol;
                break;
            default: //DOWN
                newRow = currentRow + 1;
                newCol = currentCol;
                break;
        }

        // Retaining market symbol when leaving
        if (map.isMarketCell(currentRow, currentCol)) {
            map.setCell(currentRow, currentCol, visitedMarket);
            map.getCell(currentRow, currentCol).setSymbol(visitedMarket.getSymbol());
        } else {
            map.clearCell(currentRow, currentCol);
        }

        // Retaining market symbol when entering
        if (map.isMarketCell(newRow, newCol)) {
            visitedMarket = (MarketSpaceSymbol) (map.getCell(newRow, newCol).getOccupier());
            map.setCell(newRow, newCol, player);
            map.getCell(newRow, newCol).setSymbol(visitedMarket.getSymbol()); //Looks cool to keep it as shop
        } else {
            map.setCell(newRow, newCol, player);
        }

        //BATTLES: in this game, battles occur by chance
        final double diceRoll = Math.random();
        if (diceRoll <= probabilityOfWalkingIntoBattle){
            System.out.println(OutputColorer.red("YOU'VE WALKED INTO BATTLE!"));
            LegendsMonstersAndHeroesBattle battle = new LegendsMonstersAndHeroesBattle(new ArrayList<Hero>(Arrays.asList(heroes)), player);
            battle.start();

            //Ensure heroes did not faint
            if (battle.didHeroesLose()){
                isGameOver = true;
            }
        }
    }

    private void move() {
        final int[] currentPosition = map.find(player);
        final int currentRow = currentPosition[0], currentCol = currentPosition[1];

        final String[] validKeyboardPresses;
        final boolean isInMarketCell = map.isMarketCell(currentRow, currentCol);

        if (isInMarketCell) {
            validKeyboardPresses = new String[]{"w", "a", "s", "d", "i", "q", "m"};
        } else {
            validKeyboardPresses = new String[]{"w", "a", "s", "d", "i", "q"};
        }

        boolean moveSuccessful;
        String keyPressed;

        do {
            String prompt = "What would you like to do: move (wasd), see inventory (i), quit (q)";
            if (isInMarketCell) {
                prompt += OutputColorer.green(", enter market (m)");
            }

            keyPressed = UserInteractor.getStringInput(prompt, validKeyboardPresses).toLowerCase();
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

        switch (keyPressed) {
            case "w":
                newRow = currentRow - 1;
                newCol = currentCol;
                moveSuccessful = validateWalkMove(newRow, newCol);
                break;
            case "a":
                newRow = currentRow;
                newCol = currentCol - 1;
                moveSuccessful = validateWalkMove(newRow, newCol);
                break;
            case "s":
                newRow = currentRow + 1;
                newCol = currentCol;
                moveSuccessful = validateWalkMove(newRow, newCol);
                break;
            case "d":
                newRow = currentRow;
                newCol = currentCol + 1;
                moveSuccessful = validateWalkMove(newRow, newCol);
                break;
            default:
                moveSuccessful = true;
                break;
        }

        return moveSuccessful;
    }

    private boolean validateWalkMove(Integer newRow, Integer newCol) {
        return newRow != null && newCol != null &&
                newRow >= 1 && newRow < map.getNumRows() - 1 &&
                newCol >= 1 && newCol < map.getNumCols() - 1 &&
                (map.getCell(newRow, newCol) == null ||
                        map.isMarketCell(newRow, newCol));
    }

    private void performMove(int currentRow, int currentCol, final String moveAction){
        switch (moveAction) {
            case "w":
                walk(player, Direction.UP);
                break;
            case "a":
                walk(player, Direction.LEFT);
                break;
            case "s":
                walk(player, Direction.DOWN);
                break;
            case "d":
                walk(player, Direction.RIGHT);
                break;
            case "i":
                displayInventory(player.getInventory());
                break;
            case "m":
                //M means the cell has to be a MarketSpace
                final LegendsMonstersAndHeroesMarket market =
                        (LegendsMonstersAndHeroesMarket)
                               visitedMarket.getMarket();
                displayMarket(market);
                break;
            case "q":
                isGameOver = true;
                break;
        }
    }

    @Override
    protected void setupShops() {

    }

    @Override
    protected void displayMarket(Market market) {
        //We will ask which hero the player wants to enter the shop with
        Hero heroThatEntersShop;
        int heroIndex = 0;
        for (Hero hero : heroes){
            System.out.println(++heroIndex+") "+hero);
        }
        final int heroIndexSelected = UserInteractor.getIntegerInput("Which hero do you want to use when shopping?", 1, heroIndex);
        heroThatEntersShop = heroes[heroIndexSelected - 1];
        market.displayToCustomer(heroThatEntersShop);
    }

    @Override
    protected void displayInventory(Inventory inventory) {
        //We will output the player's avatar with some neat stats and then their inventory
        StringBuilder result = new StringBuilder();
        result.append("+---+\n");
        result.append("| ").append(OutputColorer.withColor(player.getOutputColor(), player.getHead())).append(" | ").append(player).append("\n");
        result.append("|").append(OutputColorer.withColor(player.getOutputColor(), player.getBody())).append("| ").append(player.getGameRecord()).append("\n");
        result.append("|").append(OutputColorer.withColor(player.getOutputColor(), player.getLegs())).append("|\n");
        result.append("+---+\n\n");
        result.append(player).append("'s ");
        result.append(player.getInventory());
        System.out.println(result);

        UserInteractor.getStringInput("Type ok when you are done looking", new String[]{"ok"});
    }


    /**
     * Method to output game description
     */
    @Override
    public String description() {
        return "Legends: Monsters and Heroes is a single player Open-Ended Role-Playing Game. The player can select 1-3\n" +
                "heroes to use in battle, and these heroes would respectively fight 1-3 monsters when the player\n" +
                "encounters an area where monsters are. Fight to gain level and power and ultimately beat all the\n" +
                "monsters in the game!\n"+
                "Note this game has no end as it is open-ended, but there's no end to fighting --- it gets more fun!";
    }

    @Override
    public void start() {
        isGameOver = false;

        while (!isGameOver){
            System.out.println(map);
            move();
        }

        end();
    }

    @Override
    public void end() {
        System.out.println("Ending game...");
        /* NOTE: we will keep players' heroes so they don't lose their progress!
         * But, if ALL heroes are dead, what we could do is regenerate them, but
         * player gets a loss in their record :)
         */
        boolean allHeroesFainted = true;
        for (Hero hero : heroes){
            if (hero.getHealth().isAlive()){
                allHeroesFainted = false;
                break;
            }
        }
        if (allHeroesFainted){
            System.out.println("Unfortunately, all your heroes have fainted. " +
                    "Their health has been regenerated, but you receive a loss and lose some gold.");
            player.getGameRecord().addLoss();
            for (Hero hero : heroes){
                hero.getHealth().healByAmount(hero.getHealth().getMaximumHealth());
                hero.gold /= 2;
            }
            System.out.println("Player "+player+"'s record is now "+player.getGameRecord());
        }

        promptToReplay();
    }

    @Override
    public void reset() {

    }
}
