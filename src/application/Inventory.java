package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<Thneed, Integer> currentInventory;
    private Map<String, String> scheduledReplacements;
    private List<Shipment> incomingShipments;
    private static final String SHIPMENT_FILEPATH = "shipment.txt";

    public Inventory() {
        this.currentInventory = new HashMap<>();
        this.scheduledReplacements = new HashMap<>();
        this.incomingShipments = new ArrayList<>();
    }

    public void initialize() {
    	loadInventory();
        updateInventory(null);
    }

    // Method to update inventory based on a shipment
    @SuppressWarnings("unlikely-arg-type")
	public void updateInventory(Shipment shipment) {
        int shipNum = shipment != null ? shipment.getShipnum() : -1;
        Date shipDate = shipment != null ? shipment.getShipdate() : null;
        HashMap<Thneed, Integer> shipmentList = shipment != null ? shipment.getShipmentList() : new HashMap<>();

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

        // Add the shipment to the incoming shipments list
        if (shipment != null) {
            incomingShipments.add(shipment);
        }
    }
    
    public void loadInventory() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(SHIPMENT_FILEPATH))) {
            @SuppressWarnings("unchecked")
			List<Shipment> loadedShipments = (List<Shipment>) input.readObject();
            incomingShipments.addAll(loadedShipments);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveInventory() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(SHIPMENT_FILEPATH))) {
            output.writeObject(incomingShipments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scheduleReplacement(String itemName, String estimatedDate) {
        scheduledReplacements.put(itemName, estimatedDate);
    }

    public Map<Thneed, Integer> getCurrentInventory() {
        return currentInventory;
    }

    public Map<String, String> getScheduledReplacements() {
        return scheduledReplacements;
    }

    public List<Shipment> getIncomingShipments() {
        return incomingShipments;
    }

	public int getNextShipnum() {
		int shipNumber = 0;
        while (shipNumber > 0) {
            shipNumber++;
        }
        return shipNumber;
	}
}
