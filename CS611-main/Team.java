import java.util.LinkedList;
import java.util.Queue;

public class Team implements Symbolable{
    private final String name;
    private final char symbol;
    private final OutputColor color;
    private Queue<Player> players;
    private Inventory inventory;

    public Team(final String name, final char symbol){
        this.name = name;
        this.symbol = symbol;
        color = OutputColor.YELLOW;
        players = new LinkedList<Player>();
        inventory = new Inventory();
    }

    public Team(final String name, final char symbol, final OutputColor color){
        this.name = name;
        this.symbol = symbol;
        this.color = color;
    }

    public void addPlayer(final Player player){
        players.add(player);
    }

    public Queue<Player> getPlayers(){
        return players;
    }

    public String getName(){
        return name;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public OutputColor getOutputColor(){
        return color;
    }

    public Inventory getInventory(){ return inventory; }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(name).append(" (").append(symbol).append("): ");
        result.append(String.valueOf(players));

        return result.toString();
    }
}
