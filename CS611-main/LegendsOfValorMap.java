import java.util.HashMap;

public class LegendsOfValorMap extends GameMap {
    private final int numZones;
    private final int numRowsPerZone;
    protected final int numSpacesPerMovableArea;
    private final int numColPerZone;
    private final int numSpaceDividersInZone;
    protected final int numSquaresInColPerZone;

    public LegendsOfValorMap(){
        spacingFactor = 3;
        numZones = 3; //Given
        numRowsPerZone = 8; //Given
        numSpacesPerMovableArea = 2; //Given
        numSquaresInColPerZone = 2; //Given
        numColPerZone = numSquaresInColPerZone * numSpacesPerMovableArea; // excluding space divider
        numSpaceDividersInZone = numColPerZone / 2 - 1;
        numCols = numZones * (numColPerZone + numSpaceDividersInZone + 1)
                + 1; //Adding for left and right border

        numRows = numRowsPerZone*2 + 1; //Adding dividers and 1 for the top and bottom border

        map = new Cell[numRows][numCols];
        legend = new HashMap<>();

        setupGrid();
        setupNexuses();
        setupInaccessibleSpaces();
        setupSpecialSpaces();
        setupLegend();
    }

    private void setupLegend(){
        addLegendItem(new HeroNexus());
        addLegendItem(new MonsterNexus());
        addLegendItem(new Obstacle());
        addLegendItem(new Grass());
        addLegendItem(new Cave());
        addLegendItem(new Koulou());
        addLegendItem(new InaccessibleSpaceSymbol());

    }

    private void setupSpecialSpaces(){
        /*
         * This will be randomly placed with odds
         * I found interesting.
         */
        randomlyPlaceWithOdds(Obstacle.class, 0.1);
        randomlyPlaceWithOdds(Grass.class, 0.2);
        randomlyPlaceWithOdds(Cave.class, 0.2);
        randomlyPlaceWithOdds(Koulou.class, 0.2);
    }

    private void randomlyPlaceWithOdds(final Class<? extends Legendable> clazz, final double odds) {
        try {
            for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
                for (int colIndex = 0; colIndex < numCols; colIndex++) {
                    if (getCell(rowIndex, colIndex) == null && Math.random() < odds) {
                        setCell(rowIndex, colIndex, clazz.newInstance());
                    }
                }
            }
        }
        catch (Exception e){
            throw new IllegalArgumentException(
                    "Unable to instantiate or place object "+clazz.getName()+" into Legends of Valor map");
        }
    }

    private void setupInaccessibleSpaces(){
        /*
         * Although instructions say
         * "Inaccessible cells, which should exist as described
         * above,"
         * The above doesn't mention anything, so I will go with
         * random BUT ensuring valid placement.
         */
        final int heroRowIndexIncrement = 2 * 2; //Row dividers AND we place in every other row
        final int heroColIndexIncrement = numSpacesPerMovableArea + 1; //Hero & monster and divider
        final double oddsOfInaccessibleSpacePlaced = 0.5;
        for (int heroRowIndex = 3, monsterRowIndex = heroRowIndex; heroRowIndex < numRows - 2; heroRowIndex += heroRowIndexIncrement, monsterRowIndex += heroRowIndexIncrement){
            for (int heroColIndex = 1, monsterColIndex = heroColIndex+1; heroColIndex < numCols ; heroColIndex += heroColIndexIncrement, monsterColIndex += heroColIndexIncrement){
                final boolean placedDownSpace = Math.random() < oddsOfInaccessibleSpacePlaced;
                if (placedDownSpace){
                    setCell(heroRowIndex, heroColIndex, new InaccessibleSpaceSymbol());
                    //We will make it inaccessible for monsters too!
                    setCell(monsterRowIndex, monsterColIndex, new InaccessibleSpaceSymbol());
                    //Ensure we don't place it again as this would block path to nexus
                    heroColIndex += heroColIndexIncrement;
                    monsterColIndex += heroColIndexIncrement;
                }
            }
        }
    }

    public boolean isCellOccupiable(final int rowIndex, final int colIndex){
        Cell<?> cellInQuestion = getCell(rowIndex, colIndex);
        final boolean isCellEmpty = cellInQuestion == null;
        final boolean isEmptyTerrainCell = !isCellEmpty &&
                (
                        (cellInQuestion.getOccupier() instanceof Terrain
                        && ((Terrain) cellInQuestion.getOccupier()).getOccupier() == null)
                        || (cellInQuestion.getOccupier() instanceof Nexus
                        && ((Nexus) cellInQuestion.getOccupier()).getOccupier() == null)
                );

        return isCellEmpty || isEmptyTerrainCell;
    }

    private void setupNexuses(){
        //Hero nexuses are on top, monster nexuses are on the bottom
        for (int nexusColIndex = 0; nexusColIndex < numCols; nexusColIndex++){
            if (getCell(1, nexusColIndex) == null){
                setCell(1, nexusColIndex, new MonsterNexus());
            }
            if (getCell(numRows - 2, nexusColIndex) == null){
                setCell(numRows - 2, nexusColIndex, new HeroNexus());
            }
        }
    }

    private void setupGrid(){
        //Adding column space dividers
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++){
            for (int dividerColIndex = 1 + numSpacesPerMovableArea; dividerColIndex < numCols; dividerColIndex += numSpacesPerMovableArea + 1){
                setCell(rowIndex, dividerColIndex, new BarSymbol());
            }
        }

        //Adding row space dividers
        for (int rowIndex = 0; rowIndex < numRows; rowIndex += 2){
            for (int colIndex = 0; colIndex < numCols; colIndex ++){
                if (getCell(rowIndex, colIndex) != null && getCell(rowIndex, colIndex).getOccupier() instanceof BarSymbol){
                    setCell(rowIndex, colIndex, new PlusSymbol());
                }
                else{
                    setCell(rowIndex, colIndex, new DashSymbol());
                }
            }
        }

        //Adding borders between zones
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int zoneColIndex = 0; zoneColIndex < numCols; zoneColIndex += numColPerZone + numSpaceDividersInZone + 1) {
                setCell(rowIndex, zoneColIndex, new VerticalWallSymbol());
            }
        }

        //Adding borders
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++){
            setCell(rowIndex, 0, new VerticalWallSymbol());
            setCell(rowIndex, numCols - 1, new VerticalWallSymbol());
        }
        for (int colIndex = 0; colIndex < numCols; colIndex++){
            setCell(0, colIndex, new HorizontalWallSymbol());
            setCell(numRows - 1, colIndex, new HorizontalWallSymbol());
        }
    }

    @Override
    public String toString(){
        /*
        Format will be
        ■■■■■■■ ...
        █     | ...
        █ - - + ...
        █     | ...
        ■■■■■■■ ...
        Legend:
        +-----+
        | ... |
        +-----+
        So no spacing is inside as smooth movement shall be displayed.
         */
        return super.toString();
    }
}
