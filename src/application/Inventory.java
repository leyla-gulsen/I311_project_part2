package application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> currentInventory;
    private Map<String, String> scheduledReplacements;

    public Inventory() {
        this.currentInventory = new HashMap<>();
        this.scheduledReplacements = new HashMap<>();
    }

    public void updateInventory(String itemName, int quantity) {
        currentInventory.put(itemName, quantity);

        if (scheduledReplacements.containsKey(itemName)) {
            System.out.println("Item on backorder: " + itemName);
            System.out.println("Estimated replacement date: " + scheduledReplacements.get(itemName));
        }
    }

    public void scheduleReplacement(String itemName, String estimatedDate) {
        scheduledReplacements.put(itemName, estimatedDate);
    }

    public Map<String, Integer> getCurrentInventory() {
        return currentInventory;
    }

    public Map<String, String> getScheduledReplacements() {
        return scheduledReplacements;
    }
}
