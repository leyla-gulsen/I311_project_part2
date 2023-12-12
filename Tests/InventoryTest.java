import application.Inventory;
import application.Thneed;
import application.Shipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void testUpdateInventory() {
        Thneed thneed = new Thneed("medium", "red");
        HashMap<Thneed, Integer> shipmentList = new HashMap<>();
        shipmentList.put(thneed, 10);
        Shipment shipment = new Shipment(1, shipmentList, null);

        inventory.updateInventory(shipment);

        assertTrue(inventory.getCurrentInventory().containsKey(thneed), "Inventory should contain the thneed after update");
        assertEquals(10L, (long) inventory.getCurrentInventory().get(thneed), "Inventory should have correct quantity of thneed");    }

    @Test
    void testScheduleReplacement() {
        String itemName = "Thneed-medium-red";
        String estimatedDate = "2023-12-31";
        inventory.scheduleReplacement(itemName, estimatedDate);

        assertTrue(inventory.getScheduledReplacements().containsKey(itemName), "Scheduled replacements should contain the item");
        assertEquals(estimatedDate, inventory.getScheduledReplacements().get(itemName), "Should have the correct estimated replacement date");
    }

}
