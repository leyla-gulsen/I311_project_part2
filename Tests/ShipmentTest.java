import application.Shipment;
import application.Thneed;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.HashMap;

class ShipmentTest {

    @Test
    void testShipmentConstructorAndGetters() {
        int shipnum = 1;
        Date shipdate = new Date();
        HashMap<Thneed, Integer> shipmentList = new HashMap<>();
        shipmentList.put(new Thneed("medium", "red"), 10);

        Shipment shipment = new Shipment(shipnum, shipmentList, shipdate);

        assertEquals(shipnum, shipment.getShipnum(), "Shipnum getter should return the correct ship number");
        assertEquals(shipmentList, shipment.getShipmentList(), "ShipmentList getter should return the correct shipment list");
        assertNotNull(shipment.getShipdate(), "Shipdate should not be null");
    }

    @Test
    void testShipmentToString() {
        HashMap<Thneed, Integer> shipmentList = new HashMap<>();
        shipmentList.put(new Thneed("medium", "red"), 10);
        Shipment shipment = new Shipment(1, shipmentList, new Date());

        String shipmentString = shipment.toString();
        assertTrue(shipmentString.contains("Shipment #1"), "toString should include the shipment number");
        assertTrue(shipmentString.contains("medium-red: 10 items"), "toString should include the thneed details and quantity");
    }

}
