public abstract class RPGItem implements Purchasable, Sellable, Packable, Levelable {
    protected String name;
    protected double marketPrice;
    protected int currentLevel;

    protected RPGItem(final String name, final int initialLevel, final double initialCost){
        this.name = name;
        currentLevel = initialLevel;
        marketPrice = initialCost;
    }

    @Override
    public int getLevel(){
        return currentLevel;
    }

    @Override
    public double getPrice() {
        return marketPrice;
    }

    @Override
    public <T extends MoneyHolder> boolean canPersonPurchase(T person) {
        if (person instanceof Levelable) {
            final boolean doesPersonHaveEnoughMoney = person.getWalletBalance() >= marketPrice;
            final boolean doesPersonHaveValidLevel = ((Levelable) person).getLevel() >= currentLevel;
            return doesPersonHaveValidLevel && doesPersonHaveEnoughMoney;
        }
        return false;
    }

    @Override
    public double getSellableValue() {
        return (int)(marketPrice/2); //returning gives half the price
    }

    /**
     * NOTE: method does not adjust money to MoneyHolder.
     */
    @Override
    public <T extends MoneyHolder> void sellToMarket(Market<?> market, T personSelling) {
        //In RPG game, the item doesn't get sold back in shop,
        //but profits are taken.
        personSelling.setWalletBalance(personSelling.getWalletBalance() + getSellableValue());
    }

    @Override
    public String getItemName(){
        return name;
    }

    /**
     * Method adjusts the stats of the RPG item to be a given level item.
     * This is used when generating shop items
     */
    public abstract void setLevel(final int newLevel);

    @Override
    public void levelUp(){
        setLevel(currentLevel + 1);

        final String levelUpMessage = "Item "+name+" has leveled up to level "+currentLevel+"!";
        System.out.println(OutputColorer.yellow(levelUpMessage));
    }

    @Override
    public String toString(){
        return name;
    }
}
