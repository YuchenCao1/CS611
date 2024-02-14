public class Player implements Symbolable, Avatarable, InventoryHolder {
    private final char symbol;
    private final OutputColor color;
    private final String name;
    private GameRecord gameRecord;
    private Inventory inventory;

    public Player(final String name, final char symbol){
        this.name = name;
        this.symbol = symbol;
        color = OutputColor.YELLOW;
        gameRecord = new GameRecord();
        inventory = new Inventory();
    }

    public Player(final String name, final char symbol, final OutputColor color){
        this.name = name;
        this.symbol = symbol;
        this.color = color;
        gameRecord = new GameRecord();
        inventory = new Inventory();
    }

    public char getSymbol(){ return symbol; }

    @Override
    public OutputColor getOutputColor(){ return color; }

    public String getName(){ return name; }

    public GameRecord getGameRecord(){ return gameRecord; }

    public Inventory getInventory(){ return inventory; }

    @Override
    public String toString(){ return name+" ("+symbol+")"; }

    @Override
    public String getBody() {
        return "-|-";
    }

    /**
     * Method returns a String of length 3 representing
     * the avatar's legs
     */
    @Override
    public String getLegs() {
        return "/ \\";
    }
}

