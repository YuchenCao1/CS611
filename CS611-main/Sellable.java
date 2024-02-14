/**
 * This interface is to indicate products that
 * you can sell to a market itself.
 */
public interface Sellable {
    /**
     * Method gets the item product value
     * to sell to the store
     */
    public double getSellableValue();

    /**
     * Method to sell the item to the market
     */
    public <T extends MoneyHolder> void sellToMarket(Market<?> market, T personSelling);
}
