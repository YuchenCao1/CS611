/**
 * This interface is to display any product
 * that can be sold or has a price to it.
 */
public interface Purchasable {
    /**
     * Retrieve the price for the sellable item
     */
    public double getPrice();

    /**
     * Specifies standards for being able to purchase the product
     */
    public <T extends MoneyHolder> boolean canPersonPurchase(T person);
}
