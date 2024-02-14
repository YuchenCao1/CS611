/**
 * Used to denote battles that occur with
 * one group versus another group.
 */
public interface Battle1v1 {
    /**
     * Method is used to determine whether the battle
     * has finished
     */
     public boolean isBattleDone();

    /**
     * Method used to show the current battle screen
     */
    public void displayBattle();

    /**
     * Method to begin the battle
     */
    public void start();

    /**
     * Method to end the battle
     */
    public void end();

    /**
     * Method for an action to occur during the battle
     */
    public void doAction();
}
