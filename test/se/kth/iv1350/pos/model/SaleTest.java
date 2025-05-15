package pos.test.se.kth.iv1350.pos.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.Sale;

/**
 * The tests belonging to the Sale class
 */
public class SaleTest {

    /**
     * Tests that adding an item to a sale works
     */
    @Test
    void testAddItemToSale() {
        Sale sale = new Sale();
        Item item = new Item("abc123", "BigWheel Oatmeal", "Healthy Oats", 29.90, 0.06, 1);
        sale.addItem(item);

        assertTrue(sale.getSoldItems().containsKey("abc123"));
        assertEquals(1, sale.getSoldItems().size());
    }

    /**
     * Tests that adding the same item twice increases its quantity rather than duplicating it
     */
    @Test
    void testAddSameItemTwiceIncreasesQuantity() {
        Sale sale = new Sale();
        Item item1 = new Item("abc123", "Oatmeal", "Healthy", 30.0, 0.1, 1);
        Item item2 = new Item("abc123", "Oatmeal", "Healthy", 30.0, 0.1, 1);

        sale.addItem(item1);
        sale.addItem(item2);

        assertEquals(1, sale.getSoldItems().size());
        assertEquals(2, sale.getSoldItems().get("abc123").getQuantity());
    }

    /**
     * Tests that the total price including VAT is calculated correctly after adding items
     */
    @Test
    void testGetTotalCost() {
        Sale sale = new Sale();
        Item item = new Item("xyz789", "Bread", "Whole grain", 20.0, 0.12, 2); // 20*2*1.12 = 44.8
        sale.addItem(item);

        assertEquals(44.8, sale.getTotalCost(), 0.01);
    }

    /**
     * Tests that the total VAT is calculated correctly after adding items
     */
    @Test
    void testGetTotalVat() {
        Sale sale = new Sale();
        Item item = new Item("xyz789", "Bread", "Whole grain", 20.0, 0.12, 2); // 20*2*0.12 = 4.8
        sale.addItem(item);

        assertEquals(4.8, sale.getTotalVat(), 0.01);
    }

    /**
     * Tests the calculateChange method when a payment is made
     */
    @Test
    void testCalculateChange() {
        Sale sale = new Sale();
        Item item = new Item("item1", "Milk", "1L", 15.0, 0.06, 1); // 15 * 1.06 = 15.9
        sale.addItem(item);

        double amountPaid = 20.0;
        double expectedChange = 20.0 - 15.9;

        assertEquals(expectedChange, sale.calculateChange(amountPaid), 0.01);
    }
}