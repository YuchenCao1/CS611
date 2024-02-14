import java.util.ArrayList;

/**
 * This class characterizes a general structure of
 * a Market.
 */
public abstract class Market<T extends Purchasable & Packable> {
    protected String name;
    protected ArrayList<T> products;

    public ArrayList<T> getProducts(){
        return products;
    }

    public void addProduct(T product){
        products.add(product);
    }

    /**
     * Method used to display the Market to the customer
     */
    public abstract <U extends MoneyHolder> void displayToCustomer(final U customer);

    /**
     * Simply selling a product. Note if there is the option
     * to replace the item in the store, this will be done
     * here.
     *
     * @Returns whether purchase was done
     */
    public  <U extends MoneyHolder> boolean sellProductToPerson(T product, U moneyHolder, Inventory inventory){
        // NOTE: There is no currency conversion implemented, TODO?
        if (product.canPersonPurchase(moneyHolder)){
            moneyHolder.setWalletBalance(moneyHolder.getWalletBalance() - product.getPrice());
            System.out.println(OutputColorer.green(moneyHolder+" has bought "+product+" for "+product.getPrice()));
            System.out.println(OutputColorer.green(moneyHolder+"'s new balance is "+moneyHolder.getWalletBalance()));

            //Replacing the product with respect to market's standards
            replaceProduct(product);

            //Add product to Inventory Holder
            inventory.addItem(product, 1);

            products.remove(product);
            return true;
        }
        else{
            System.out.println(OutputColorer.red(moneyHolder+" cannot purchase "+product));
            return false;
        }
    }

    /**
     * Replacing a product when item is sold. This can be done
     * to ensure market inventory is kept at a constant size.
     */
    protected abstract void replaceProduct(Purchasable product);


    /**
     * Handling returns from a customer (if the store takes
     * returns)
     */
    protected <U extends MoneyHolder> boolean handleProductReturnFromCustomer(T product, U customer, Inventory inventory){
        if (!(product instanceof Sellable)){
            System.out.println(OutputColorer.red("The product "+product+" cannot be returned."));
            return false;
        }
        else {
            final int sellableProductValue = (int)((Sellable) product).getSellableValue();
            customer.setWalletBalance(customer.getWalletBalance() + sellableProductValue);
            System.out.println(OutputColorer.green(customer+" has sold "+product+" for "+sellableProductValue));
            System.out.println(OutputColorer.green(customer+"'s new balance is "+customer.getWalletBalance()));

            //Remove product to Inventory Holder
            inventory.removeItem(product, 1);

            return true;
        }
    }
}
