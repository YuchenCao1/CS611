import java.util.LinkedList;
import java.util.Queue;

/**
 * A RolePlayGame (RPG) game is a game where player(s) fight against monsters
 * and level up, either through fighting or doing tasks given
 * from NPCs.
 */
public abstract class RolePlayGame<T extends GameMap> implements Game {
    protected Queue<Team> teams;
    protected Queue<Player> players;
    protected int minNumPlayers;
    protected int maxNumPlayers;
    protected T map;

    public Queue<Player> getPlayers(){
        return players;
    }

    public Queue<Team> getTeams(){
        return teams;
    }

    /**
     * This method is to configure the RPG's
     * appropriate {@link GameMap}
     */
    public abstract void setupMap();

    /**
     * Method to move a player by a certain
     * direction on the RPG Game map.
     */
    protected abstract <S extends Symbolable> void walk(final S object, final Direction direction);

    /**
     * Method for a move to be made in the game.
     */

    /**
     * Method initializes the shops content that
     * would show in an RPG game
     */
    protected abstract void setupShops();

    /**
     * Method displays the appropriate shop the
     * player(s) have found.
     */
    protected abstract void displayMarket(Market market);

    /**
     * Method displays an inventory of a given
     * player/team
     */
    protected abstract void displayInventory(Inventory inventory);

    protected void setupPlayers(final int minNumPlayers, final int maxNumPlayers){
        Queue<Player> players = new LinkedList<>();
        // Prompt for number of players IF there isn't a
        final int numPlayers;
        if (minNumPlayers != maxNumPlayers){
            numPlayers = UserInteractor.getIntegerInput("Enter the number of players", minNumPlayers, maxNumPlayers);
        }
        else {
            numPlayers = maxNumPlayers;
            System.out.println("This game requires "+numPlayers+" players.");
        }

        boolean isDoneWithSelectingPlayers = false;
        final boolean playersHaveBeenMadeBefore = !Database.PLAYERS.isEmpty();

        while (players.size() < numPlayers || players.size() < minNumPlayers) {
            // First prompt user to reuse players in database
            if (playersHaveBeenMadeBefore && !isDoneWithSelectingPlayers) {
                String wantToAddUsedPlayers = UserInteractor.getYesNoInput("You have created some players already. Would you like to add them?");
                if (wantToAddUsedPlayers.equals("y")){
                    // Display player selection
                    // Second condition: if all players are selected from the Database, nothing else can get added
                    while (!isDoneWithSelectingPlayers && players.size() < Database.PLAYERS.size()){
                        Player selectedPlayer = Menu.displayPlayerSelectionPage();
                        while (players.contains(selectedPlayer)){
                            System.out.println("Invalid selection: player "+selectedPlayer+" has already been selected.");
                            selectedPlayer = Menu.displayPlayerSelectionPage();
                        }
                        players.add(selectedPlayer);
                        if (players.size() < numPlayers && players.size() < Database.PLAYERS.size()) {
                            String doneWithSelectionResponse = UserInteractor.getYesNoInput(OutputColorer.purple("Are you done selecting players?"));
                            if (doneWithSelectionResponse.equals("y")){
                                isDoneWithSelectingPlayers = true;
                            }
                        }
                        else {
                            isDoneWithSelectingPlayers = true;
                        }
                    }

                    System.out.println("Selected players have been added.");
                }
                else{
                    isDoneWithSelectingPlayers = true;
                }
            }
            else {
                // Then we will have user input new players IF selecting did not reach threshold
                Player createdPlayer = Menu.displayPlayerCreationPage();
                players.add(createdPlayer);
            }
        }

        System.out.println("All "+numPlayers+" player(s) have been added.");
        this.players = players;
    }
}
