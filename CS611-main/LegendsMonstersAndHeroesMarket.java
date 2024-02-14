import java.util.ArrayList;

public class LegendsMonstersAndHeroesMarket extends Market<RPGItem> {

    public LegendsMonstersAndHeroesMarket(final String name){
        this.name = name;
        products = new ArrayList<>();

        /*
         * Every Market in this game will only have 5 items, but they are all randomized
         * from where they come from
         */
        final int numberOfProductsToAdd = 5;
        ArrayList<RPGItem> possibleStoreItems = new ArrayList<>();
        possibleStoreItems.addAll(WeaponReader.readAll());
        possibleStoreItems.addAll(SpellReader.readAll());
        possibleStoreItems.addAll(PotionReader.readAll());
        possibleStoreItems.addAll(ArmorReader.readAll());
        for (int productAddedIndex = 0; productAddedIndex < numberOfProductsToAdd; productAddedIndex++){
            final int randomProductIndex = (int) (Math.random() * possibleStoreItems.size());
            RPGItem productToAdd = possibleStoreItems.get(randomProductIndex);

            // Now adjusting level to be something random.
            // NOTE: each store will have 1 level 1 item to make it fair
            final int randomLevel = 1 + (int) (Math.random() * 100);

            if (productAddedIndex != 0) {
                productToAdd.setLevel(randomLevel);
            }
            else {
                productToAdd.setLevel(1);
            }

            products.add(productToAdd);
        }
    }
    /**
     * Prints a welcome screen to the customer,
     * including products sold in the store.
     */
    public <U extends MoneyHolder> void displayToCustomer(final U customer){
        System.out.println(OutputColorer.green("Welcome to Market '"+name+"'!"));
        final String[] shopOptions = new String[]{"sell", "buy", "quit"};

        boolean wantsToLeave = false;

        //In the shop, the customer can either sell or buy items
        while (!wantsToLeave){
            final String customerAction = UserInteractor.getStringInput("Would you like to \"sell\" or \"buy\" an item, or \"quit\"?", shopOptions);
            //Sell action
            if (customerAction.equals("sell") && customer instanceof Hero){
                wantsToLeave = displaySellToCustomer(customer);
            }
            //Buy action
            else if (customerAction.equals("buy")){
                wantsToLeave = displayMenuToCustomer(customer);
            }
            //Quit action:
            else{
                wantsToLeave = true;
            }
        }
    }

    /**
     * Method displays sell operation
     * @return whether customer wants to leave
     */
    private <T extends MoneyHolder> boolean displaySellToCustomer(T customer){
        //Ensure the customer can actually sell something (e.g. they must have atleast 1 weapon)
        int customerItemCount = 0;
        int customerWeaponCount = 0;
        final Inventory customerInventory = ((Hero) customer).getInventory();
        for (Packable item : customerInventory.getItems()){
            customerItemCount++;
            if (item instanceof Weapon)
                customerWeaponCount++;
        }
        final Weapon equippedWeapon = ((Hero) customer).equippedWeapon;
        final Armor equippedArmor = ((Hero) customer).equippedArmor;

        if (equippedWeapon != null) customerWeaponCount++;

        if (customerItemCount == 1 && customerWeaponCount == 1){
            System.out.println(OutputColorer.red("You cannot sell your only weapon."));
            return false;
        }

        //Allowing the customer to choose what to buy
        //NOTE: Market is full of RPG items which are Sellable
        ArrayList<RPGItem> sellableItems = ((Hero) customer).getInventory().getItemsOfType(RPGItem.class);
        //We know the equipped weapon and armor are sellable so we will add them to this list
        if (equippedWeapon != null) sellableItems.add(equippedWeapon);
        if (equippedArmor  != null) sellableItems.add(equippedArmor);

        String[] validActionsToDo = new String[sellableItems.size() + 1];
        //Including quit option for customer
        validActionsToDo[validActionsToDo.length - 1] = "quit";
        int sellableItemIndex = 0;
        for (RPGItem item : sellableItems){
            validActionsToDo[sellableItemIndex] = String.valueOf(++sellableItemIndex);
            System.out.println(sellableItemIndex+") "+item.name);
            System.out.println(item.getItemContent());
            System.out.println(OutputColorer.yellow("Sellable value: "+item.getSellableValue()));
        }
        final String selectedAction = UserInteractor.getStringInput("Select which item you'd like to sell, or 'quit'", validActionsToDo);

        if (selectedAction.equals("quit")) return true; //quitting
        final int selectedItemToSellIndex = Integer.parseInt(selectedAction);

        final RPGItem selectedItem = sellableItems.get(selectedItemToSellIndex - 1);
        //Ensure the selected item to sell wasn't the only weapon hero had!
        if (selectedItem instanceof Weapon && customerWeaponCount == 1){
            System.out.println(OutputColorer.red("You cannot sell your only weapon."));
            return false;
        }

        //NOTE: if selected item is an equipped item, we shall add it to the customer's inventory to get removed
        if (selectedItem == equippedWeapon){
            customerInventory.addItem(equippedWeapon, 1);
            ((Hero) customer).equippedWeapon = null;
        }
        else if (selectedItem == equippedArmor){
            customerInventory.addItem(equippedArmor, 1);
            ((Hero) customer).equippedArmor = null;
        }
        handleProductReturnFromCustomer(selectedItem, customer, customerInventory);
        return false;
    }

    /**
     * Method to display the items to purchase
     * in the shop
     * @return whether customer doesn't want to shop anymore
     */
    private <U extends MoneyHolder> boolean displayMenuToCustomer(U customer){
        System.out.println(OutputColorer.green("The following items are sold here:\n"));

        int itemCount = 0;
        for (RPGItem product : products) {
            System.out.println(++itemCount + ") " + product.getItemName() + " (" + product.getClass().getName() + ")");
            System.out.println(product.getItemContent());
            System.out.println(OutputColorer.yellow("Cost: ") + product.getPrice() + "\n");
        }
        String[] validUserResponses = new String[products.size() + 1]; //Adding the quit option.

        for (int itemNumber = 1; itemNumber <= products.size(); itemNumber++) {
            validUserResponses[itemNumber - 1] = String.valueOf(itemNumber);
        }

        final String quit = "quit";
        validUserResponses[validUserResponses.length - 1] = quit;

        System.out.println("You currently have " + OutputColorer.yellow(String.valueOf(customer.getWalletBalance())) + " gold.");
        final String customerInput = UserInteractor.getStringInput("Select a product by its number to purchase or 'quit'", validUserResponses);

        if (customerInput.equals(quit)) {
            return true;
        } else {
            RPGItem productInterested = products.get(Integer.parseInt(customerInput) - 1);
            if (customer instanceof InventoryHolder) {
                final boolean wasProductSold = sellProductToPerson(productInterested, customer, ((InventoryHolder) customer).getInventory());
                if (wasProductSold) {
                    System.out.println(OutputColorer.yellow("Updated Market '" + name + "' Products:"));
                    return false;
                }
            } else {
                throw new IllegalArgumentException("RPG Player " + customer + " does not have an inventory, so the store cannot sell it to them.");
            }
        }
        return false;
    }

    @Override
    protected void replaceProduct(Purchasable product) {
        //Ensuring it is an RPG game item since this game takes RPG game items in the store
        if (!(product instanceof RPGItem)){
            throw new IllegalArgumentException("Provided product "+product+" is not an RPG item which is used in the "+this.getClass().getName()+" class");
        }
        //We will replace the product with something of the same type but a higher level
        final int productCurrentLevel = ((RPGItem) product).getLevel();
        final int productNewLevel = productCurrentLevel + (int) (10 * Math.random());

        ArrayList<? extends RPGItem> possibleItemsToReplaceWith;
        if (product instanceof Weapon){
            possibleItemsToReplaceWith = WeaponReader.readAll();
        }
        else if (product instanceof Spell){
            possibleItemsToReplaceWith = SpellReader.readAll();
        }
        else if (product instanceof Armor){
            possibleItemsToReplaceWith = ArmorReader.readAll();
        }
        else if (product instanceof Potion){ //Just replace anything else with Potion
            possibleItemsToReplaceWith = PotionReader.readAll();
        }
        else {
            throw new IllegalArgumentException("Unknown RPG item product type here: "+product.getClass().getTypeName());
        }

        final int randomIndex = (int)(Math.random() * possibleItemsToReplaceWith.size());
        final RPGItem randomProductToAdd = possibleItemsToReplaceWith.get(randomIndex);
        randomProductToAdd.setLevel(productNewLevel);
        products.add(randomProductToAdd);
    }
}
