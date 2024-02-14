import java.util.List;
import java.util.ArrayList;

/*
This class is used to store data that would be convenient to have.
For example, as it would be nice to have to not input the same
player information everytime, we can instead store all the players
into a static object.
 */
public final class Database {
    public final static List<Player> PLAYERS = new ArrayList<Player>();
    public final static List<Team> TEAMS = new ArrayList<Team>();
}
