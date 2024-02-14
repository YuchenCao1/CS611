import java.util.List;
import java.util.ArrayList;

public class Inventory {
    private final List<Packable> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public List<? extends Packable> getItems(){
        return items;
    }

    public <T extends Packable> void addItem(T item, int quantity) {
        for (int numAdded = 1; numAdded <= quantity; numAdded++){
            items.add(item);
        }
    }

    public <T extends Packable> void removeItem(T item, final int quantity){
        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++){
            if (items.get(itemIndex) == item){
                items.remove(itemIndex--);
            }
            if (quantity == 0){
                break;
            }
        }
    }

    public <T extends Packable> void removeItem(Class<T> itemClass, final int quantity) {
        int numberRemoved = 0;

        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++){
            if (itemClass.isInstance(items.get(itemIndex))){
                if (numberRemoved == quantity){
                    break;
                }
                items.remove(items.get(itemIndex));
                numberRemoved++;
                itemIndex--;
            }
        }
    }

    public <T extends Packable> Integer getItemCount(Class<T> itemClass){
        int itemCount = 0;

        for (Packable item : items){
            if (itemClass.isInstance(item)){
                itemCount++;
            }
        }

        return itemCount;
    }

    /**
     * Method allows to get objects of any type.
     * Note that by definition all items in the
     * inventory itself are of Packable, so it is
     * implied the object itself extends Packable,
     * BUT this doesn't mean the class being passed in
     * extends Packable itself (this distinction is
     * important)
     * @return items of given class type.
     */
    public <T> ArrayList<T> getItemsOfType(Class<T> itemClass) {
        ArrayList<T> result = new ArrayList<>();

        for (Object item : items) {
            if (itemClass.isInstance(item)) {
                result.add(itemClass.cast(item));
            }
        }

        return result;
    }

    public void empty(){
        items.clear();
    }

    /**
     * Method outputs item by quantity.
     */
    public String toString() {
        final ArrayList<Packable> itemsFound = new ArrayList<>();
        StringBuilder result = new StringBuilder("Inventory:\n");
        for (Packable item : items) {
            if (item == null) continue;
            if (!itemsFound.contains(item)) {
                final int itemCount = getItemCount(item.getClass());
                //Cool feature: we'll avoid printing the same item multiple times IFF content is not empty :)
                if (itemCount > 1 && item.getItemContent().isEmpty()) {
                    result.append(" - ").append(item.getItemName()).append(" (").append(itemCount).append(")\n");
                } else {
                    result.append(" - ").append(item.getItemName()).append("\n");
                }
                //If item has content to display, display that too:
                final String itemContent = item.getItemContent();
                if (!itemContent.isEmpty()){
                    result.append(indentString(itemContent)).append("\n");
                }
                itemsFound.add(item);

                // Check if the item itself also has an inventory
                if (item instanceof InventoryHolder) {
                    Inventory subInventory = ((InventoryHolder) item).getInventory();
                    result.append(indentString(item.getItemName()+"'s "+subInventory.toString()));
                }
            }
        }

        return result.toString();
    }

    private String indentString(String str) {
        StringBuilder indentedString = new StringBuilder();
        String[] lines = str.split("\n");
        final String indent = "   ";
        for (String line : lines) {
            indentedString.append(indent).append(indent).append(line).append("\n");
        }
        return indentedString.toString();
    }
}
