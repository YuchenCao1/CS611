import java.util.Queue;

public interface Game {
    /**
     * Method to output game description
     */
    public String description();

    /**
     * Method to start the game playing process
     */
    public void start();

    /**
     * Method to end the game playing process.
     */
    public void end();

    /**
    * Method to clear game variables. This is
    * so the game can be played again with new settings
    */
    public void reset();

    /**
     * Method to get all teams, used if the
     * provided game have teams allowed.
     */
    public Queue<Team> getTeams();

    /**
    * Method to get all players, used if the
    * provided game doesn't have teams allowed.
    */
    public Queue<Player> getPlayers();

    /**
     * Method to get input on what players will be in
     * the game.
     */
    public default Queue<Player> setupPlayers() {
        return null;
    }

    /**
     * Method to get input on what team pairings
     * will be. If game has no team option, then
     * one player will be assigned to a single
     * team.
     */
    public default Queue<Team> setupTeams() {
        return null;
    }


    /**
     * Fun feature added: if the game is done, we should also request to see if user
     * wants to play again
     */
    public default void promptToReplay(){
       String doesUserWantToReplay = UserInteractor.getYesNoInput("Would you like to play again with same settings?");
       if (doesUserWantToReplay.equals("y")){
           reset();
           start();
       }
       else{
           cleanup();
           // Bring back to home screen
           Menu.displayHomePage();
       }
    }

    /**
     * This method is really for ensuring the user understands what
     * assignments are made in a game or what settings they've put
     * in before starting. To ensure they read what is done, the
     * user can type "ready" once they've double-checked.
     */
    public default void makeSureReady(){
        UserInteractor.getIntegerInput("Before we start, we'd like to make sure you are ready. Once you are ready, type a very big number", 999);
    }

    /**
     * Method for cleaning up any game-related data
     */
    public default void cleanup(){
        // No need to store team data after a game
        Database.TEAMS.clear();
    }
}
