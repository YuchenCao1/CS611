/**
 * Interface is used to denote people/things that contain
 * a level to them.
 */
public interface Levelable {
    /**
     * Retrieves the level value of a person/thing that is
     * levelable
     */
    public int getLevel();

    /**
     * Method to do operations when leveling up
     */
    public void levelUp();
}
