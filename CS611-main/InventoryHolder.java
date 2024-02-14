/**
 * This interface is used to denote which classes do
 * have an inventory, which can then be used to output
 * the inventory neatly.
 */
public interface InventoryHolder {
    public Inventory getInventory();
}
