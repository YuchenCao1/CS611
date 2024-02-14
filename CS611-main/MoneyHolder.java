/**
 * Interface is to denote what Objects can hold money.
 */
public interface MoneyHolder {
    /**
     * Method retrieves the total money the moneyholder contains.
     * Note this logic currently
     * assumes that only a single currency total is used.
     */
    public double getWalletBalance();

    /**
     * Method sets the total wallet balance to the parameter passed in.
     */
    public void setWalletBalance(final double newBalance);
}
