/**
 * This interface is to clearly define any Object
 * that is able to be "packed" in the Inventory
 */
public interface Packable {
    /**
     * Method is used to find out what the item's name (key) when
     * grabbing it from the {@link Inventory}'s items list.
     */
    public String getItemName();

    /**
     * Method is used to output the item's content when
     * printing out the {@link Inventory}. Note if the name
     * is self-explanatory, this class will simply just print
     * nothing.
     */
    public default String getItemContent(){
        return "";
    }
}
