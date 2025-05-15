package pos.test.se.kth.iv1350.pos.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.pos.model.Item;

/**
 * The tests for the Item class.
 */
public class ItemTest {

    /**
     * Tests that when an item has been created, the getters return the correct values.
     */
    @Test
    void testItemCreation() {
        Item item = new Item("abc123", "BigWheel Oatmeal", "Healthy Oats", 29.90, 0.06, 10);
        assertEquals("abc123", item.getItemID());
        assertEquals("BigWheel Oatmeal", item.getName());
        assertEquals("Healthy Oats", item.getItemDescription());
        assertEquals(29.90, item.getPrice(), 0.001);
        assertEquals(0.06, item.getVat(), 0.001);
        assertEquals(10, item.getQuantity());
    }

    /**
     * Tests that the quantity increases correctly when calling increaseQuantity().
     */
    @Test
    void testIncreaseQuantity() {
        Item item = new Item("id1", "Banana", "Fresh", 10.0, 0.12, 1);
        item.increaseQuantity(2);
        assertEquals(3, item.getQuantity());
    }

    /**
     * Tests the total price including VAT for a given quantity.
     */
    @Test
    void testTotalPriceWithVat() {
        Item item = new Item("id1", "Banana", "Fresh", 10.0, 0.1, 2); // 10*2*1.1 = 22
        assertEquals(22.0, item.totalPriceWithVat(), 0.001);
    }

    /**
     * Tests the total VAT amount calculation.
     */
    @Test
    void testTotalVatAmount() {
        Item item = new Item("id1", "Banana", "Fresh", 10.0, 0.1, 2); // 10*2*0.1 = 2
        assertEquals(2.0, item.totalVatAmount(), 0.001);
    }

    /**
     * Tests behavior when increasing quantity with zero.
     */
    @Test
    void testIncreaseQuantityWithZero() {
        Item item = new Item("id1", "Orange", "Juicy", 5.0, 0.1, 2);
        item.increaseQuantity(0);
        assertEquals(2, item.getQuantity());
    }
}