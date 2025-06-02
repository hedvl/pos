package pos.test.se.kth.iv1350.pos.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.model.SaleDTO;
import se.kth.iv1350.pos.model.ItemDTO;

/**
 * Tests the Controller class.
 */
public class ControllerTest {
    private Controller controller;

    /**
     * Sets up controller and starts a new sale.
     */
    @BeforeEach
    void setUp() {
        controller = new Controller();
        controller.startSale();
    }

    /**
     * Tests that the total cost is 0.0 before any items are added.
     */
    @Test
    void testStartOfSaleTotalCost() {
        double totalCost = controller.getTotalCost();
        assertEquals(0.00, totalCost, 0.001, "Total cost should be 0.00 before any items have been added.");
    }

    /**
     * Tests that when a valid item ID is entered, a valid ItemDTO is returned.
     */
    @Test
    void testEnterValidItemID() {
        ItemDTO item = controller.enterItemID("abc123");
        assertNotNull(item, "Returned item should not be null.");
        assertEquals("abc123", item.getItemID(), "Item ID should match the input.");
    }

    /**
     * Tests that when an invalid item ID is entered, null is returned.
     */
    @Test
    void testEnterInvalidItemID() {
        ItemDTO item = controller.enterItemID("invalid999");
        assertNull(item, "Returned item should be null for an invalid ID.");
    }

    /**
     * Tests that SaleDTO is correctly returned with the added item.
     */
    @Test
    void testGetSaleInfoReturnsCorrectDTO() {
        controller.enterItemID("abc123");
        SaleDTO saleDTO = controller.getSaleDetails();
        assertNotNull(saleDTO, "SaleDTO should not be null.");
        assertTrue(saleDTO.getSoldItems().containsKey("abc123"), "SaleDTO should contain the added item.");
    }
}
