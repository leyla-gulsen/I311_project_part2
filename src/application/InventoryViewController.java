package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

public class InventoryViewController {
	
	protected static final String SHIPMENT_FILEPATH = "Shipment.txt";
	@FXML
	private Button deliveryUpdateButton;
	@FXML
	private ListView<String> currentInventoryListView;
	@FXML
	private TextField deliveryDateField;
	@FXML
	private Button generateReportButton;
	@FXML
	private ListView<String> incomingShipmentListView;
	@FXML
	private TextField quantityTextField;
	
	private ArrayList<Shipment> shipment = new ArrayList<>();
	private InventoryViewController inventoryViewController;
	private Inventory inventory;
	

    public InventoryViewController() {
        this.inventory = new Inventory();
        loadRealData();
        saveInventory();
        loadInventory();
    }
    
    private void loadRealData() {
        // Assuming you have a method to fetch real data, here's a simplified example:
        List<Thneed> realThneeds = fetchDataFromExternalSource();

        // Create a shipment with real Thneeds and quantities
        HashMap<Thneed, Integer> shipmentList = new HashMap<>();
        for (Thneed thneed : realThneeds) {
            shipmentList.put(thneed, getRandomQuantity()); // You need to define getRandomQuantity()
        }

        Shipment realShipment = new Shipment(getNextShipnum(), shipmentList, new Date());

        // Update the inventory with the real shipment
        inventory.updateInventory(realShipment);
    }
    
    private List<Thneed> fetchDataFromExternalSource() {
        // Simulate fetching real Thneed data from an external source (e.g., a database or API)
        List<Thneed> thneeds = new ArrayList<>();
        thneeds.add(new Thneed("Small", "Red"));
        thneeds.add(new Thneed("Medium", "Blue"));
        // Add more Thneeds based on your real data source

        return thneeds;
    }

    private int getNextShipnum() {
        // Simulate getting the next shipment number from your real data source
        // You can implement logic to fetch the next available shipnum
        return 1; // For simplicity, returning a constant value
    }

    private int getRandomQuantity() {
        // Simulate generating a random quantity for each Thneed
        return new Random().nextInt(20) + 1; // Generates a random quantity between 1 and 20
    }
    
    
	private void loadInventory() {
		File file = new File(SHIPMENT_FILEPATH);
		try {
			file.createNewFile();
			BufferedReader br = new BufferedReader(new FileReader(SHIPMENT_FILEPATH));
			if (br.readLine() != null) {
				FileInputStream fis = new FileInputStream(SHIPMENT_FILEPATH);
				ObjectInputStream input = new ObjectInputStream(fis);
				shipment = (ArrayList<Shipment>) input.readObject();
				fis.close();
				input.close();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveInventory() {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(InventoryViewController.SHIPMENT_FILEPATH));
			output.writeObject(shipment);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    // Event handler for the "Update Delivery" button
    @FXML
    public void deliveryUpdateButtonClick(ActionEvent event) {
        String itemName = deliveryDateField.getText();
        String quantity = quantityTextField.getText();

        Thneed thneed = new Thneed(itemName, "defaultColor");
        HashMap<Thneed, Integer> shipmentList = new HashMap<>();
        shipmentList.put(thneed, Integer.parseInt(quantity));

        Shipment shipment = new Shipment(1, shipmentList, new Date());

        // Update the inventory with the received shipment
        inventory.updateInventory(shipment);

        // Update the UI to reflect the changes in the current inventory
        updateCurrentInventoryListView();
    }

    // Event handler for the "Generate Report" button
    @FXML
    public void generateReportButtonClick(ActionEvent event) {
        // Load the ReportView.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReportView.fxml"));
        AnchorPane dialogRoot;

        try {
            dialogRoot = (AnchorPane) loader.load();
            Scene dialogScene = new Scene(dialogRoot);
            Stage dialogStage = new Stage();
            dialogStage.setScene(dialogScene);
            ReportViewController dialogController = loader.getController();
            dialogController.setInventory(inventory);
            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update the current inventory ListView in the UI
    private void updateCurrentInventoryListView() {
        // Clear the existing items in the ListView
        currentInventoryListView.getItems().clear();

//        // Get the current inventory data from the Inventory class
        for (Entry<Thneed, Integer> entry : inventory.getCurrentInventory().entrySet()) {
            // Format the entry and add it to the ListView
        	String itemInfo = entry.getKey().getSize() + "-" + entry.getKey().getColor() +
                    ": " + entry.getValue() + " items";
            currentInventoryListView.getItems().add(itemInfo);
        }
    }
}