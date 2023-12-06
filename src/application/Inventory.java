package application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Thneed, Integer> currentInventory;
    private Map<String, String> scheduledReplacements;

    public Inventory() {
        this.currentInventory = new HashMap<>();
        this.scheduledReplacements = new HashMap<>();
    }
    

    // Method to update inventory based on a shipment
    public void updateInventory(Shipment shipment) {
        int shipNum = shipment.getShipnum();
        Date shipDate = shipment.getShipdate();
        HashMap<Thneed, Integer> shipmentList = shipment.getShipmentList();

        System.out.println("Updating inventory for shipment #" + shipNum + " received on " + shipDate);

        for (Map.Entry<Thneed, Integer> entry : shipmentList.entrySet()) {
            Thneed thneed = entry.getKey();
            int quantity = entry.getValue();

            // Update current inventory with the new quantity
            currentInventory.put(thneed, quantity);

            // Check if there's a scheduled replacement for the item
            if (scheduledReplacements.containsKey(thneed)) {
                System.out.println("Item on backorder: " + thneed.getSize() + "-" + thneed.getColor());
                System.out.println("Estimated replacement date: " + scheduledReplacements.get(thneed));
            }
        }
    }
//    public void updateInventory(Shipment shipment) {
//        int shipNum = shipment.getShipnum();
//        Date shipDate = shipment.getShipdate();
//        HashMap<Thneed, Integer> shipmentList = shipment.getShipmentList();
//
//        System.out.println("Updating inventory for shipment #" + shipNum + " received on " + shipDate);
//
//        for (Map.Entry<Thneed, Integer> entry : shipmentList.entrySet()) {
//            Thneed thneed = entry.getKey();
//            int quantity = entry.getValue();
//
//            // Update current inventory with the new quantity
//            currentInventory.put(thneed.getSize() + "-" + thneed.getColor(), quantity);
//
//            // Check if there's a scheduled replacement for the item
//            if (scheduledReplacements.containsKey(thneed.getSize() + "-" + thneed.getColor())) {
//                System.out.println("Item on backorder: " + thneed.getSize() + "-" + thneed.getColor());
//                System.out.println("Estimated replacement date: " + scheduledReplacements.get(thneed.getSize() + "-" + thneed.getColor()));
//            }
//        }
//    }

    public void scheduleReplacement(String itemName, String estimatedDate) {
        scheduledReplacements.put(itemName, estimatedDate);
    }

    public Map<Thneed, Integer> getCurrentInventory() {
        return currentInventory;
    }

    public Map<String, String> getScheduledReplacements() {
        return scheduledReplacements;
    }
}