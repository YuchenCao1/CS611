/**
 * This interface is to define items that are consumable
 */
public interface Consumable {
    /**
     * Method to retrieve the number of uses
     */
    public int getNumberOfUsesLeft();

    /**
     * Method to use the item once.
     */
    public void consumeItem();
}
