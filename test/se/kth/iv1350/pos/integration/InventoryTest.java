package pos.test.se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.Inventory;
import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.Sale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventory;
    private Sale sale;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        sale = new Sale();

        Item item1 = inventory.fetchItem("abc123");
        Item item2 = inventory.fetchItem("def456");
        sale.addItem(item1);
        sale.addItem(item2);
    }

    /**
     * Tests that an item with quantity 1 is removed from inventory after sale.
     */
    @Test
    void testUpdateInventoryReducesStock() {
        Inventory inventory = new Inventory();
        Sale sale = new Sale();

        Item item = inventory.fetchItem("abc123");
        int originalQuantity = item.getQuantity();

        sale.addItem(item); // quantity will now go to zero

        inventory.updateInventory(sale);
        Map<String, Item> items = inventory.getItems();

        assertFalse(items.containsKey("abc123"), "Item should be removed from inventory when quantity reaches zero.");
    }

    /**
     * Verifies that an out-of-stock item is removed, while others remain.
     */
    @Test
    public void testUpdateInventoryRemovesOutOfStock() {
        inventory.updateInventory(sale);
        Map<String, Item> updatedItems = inventory.getItems();

        assertFalse(updatedItems.containsKey("abc123"), "abc123 should be removed after selling all stock.");
        assertTrue(updatedItems.containsKey("def456"), "def456 should remain in inventory.");
    }
}
