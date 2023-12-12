package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;



import javafx.scene.control.ListView;


public class InventoryViewController implements Initializable {


    @FXML
    private Button deliveryUpdateButton;

    @FXML
    private TextArea currentInventoryTextArea;

    @FXML
    private TextField deliveryDateField;

    @FXML
    private Button generateReportButton;

    @FXML
    private Button addShipmentButton;

    @FXML
    private TextArea incomingShipmentTextArea;

    @FXML
    private TextField quantityTextField;

    private Inventory inventory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    public InventoryViewController() {

        this.inventory = new Inventory();
        this.inventory.initialize();
        inventory.loadInventory();
        displayShipmentDataFromFile("Shipment.txt");
        updateIncomingShipmentTextArea();
        updateCurrentInventoryTextArea();

    }
    
    public void displayShipmentDataFromFile(String fileName) {
        updateIncomingShipmentTextArea();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            incomingShipmentTextArea.clear();

            String line;
            while ((line = reader.readLine()) != null) {
                incomingShipmentTextArea.appendText(line + "\n");
            }
            System.out.println("Shipment data loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void updateCurrentInventoryTextArea() {
        currentInventoryTextArea.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("Shipment.txt"))) {
            Map<String, Map<String, Integer>> inventoryByCategory = new HashMap<>();

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts based on the format
                String[] parts = line.split(" - Est. Delivery Date: ");

                // Check if the line has the expected format
                if (parts.length == 2) {
                    String thneedInfo = parts[0].trim();
                    int quantity = Integer.parseInt(thneedInfo.substring(thneedInfo.lastIndexOf("(") + 1, thneedInfo.lastIndexOf(")")).trim());
                    String[] thneedParts = thneedInfo.split(" ", 3);
                    String size = thneedParts[0].trim();
                    String color = thneedParts[1].trim();

                    // Group by size
                    inventoryByCategory.putIfAbsent(size, new HashMap<>());
                    Map<String, Integer> sizeMap = inventoryByCategory.get(size);
                    sizeMap.put(color, sizeMap.getOrDefault(color, 0) + quantity);
                }
            }

            // Display the inventory in the TextArea
            for (Entry<String, Map<String, Integer>> sizeEntry : inventoryByCategory.entrySet()) {
                String size = sizeEntry.getKey();
                Map<String, Integer> colorMap = sizeEntry.getValue();

                for (Entry<String, Integer> colorEntry : colorMap.entrySet()) {
                    String color = colorEntry.getKey();
                    int totalQuantity = colorEntry.getValue();

                    String itemInfo = size + " " + color + ": " + totalQuantity + " items\n";
                    currentInventoryTextArea.appendText(itemInfo);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void updateIncomingShipmentTextArea() {
        for (Shipment shipment : inventory.getIncomingShipments()) {
            incomingShipmentTextArea.appendText(shipment.toString() + "\n");
        }
    }
    @FXML
    public void deliveryUpdateButtonClick(ActionEvent event) {
        String itemName = deliveryDateField.getText();
        String quantity = quantityTextField.getText();

        Thneed thneed = new Thneed(itemName, "defaultColor");

        HashMap<Thneed, Integer> shipmentList = new HashMap<>();
        shipmentList.put(thneed, Integer.parseInt(quantity));
        Shipment shipment = new Shipment(inventory.getNextShipnum(), shipmentList, null);
        inventory.updateInventory(shipment);

        updateCurrentInventoryTextArea();
    }

    @FXML
    public void generateReportButtonClick(ActionEvent event) {
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

            dialogController.updateOrdersFilledReport();
            dialogController.updateMostPopularThneed();
            dialogController.updateLeastPopularThneed();
            dialogController.updateTopCustomerReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addShipmentButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Shipment.fxml"));
            AnchorPane root = loader.load();

            Stage shipmentStage = new Stage();
            shipmentStage.setTitle("Shipment Details");
            shipmentStage.setScene(new Scene(root));

            ShipmentController shipmentController = loader.getController();
            shipmentController.setInventoryController(this);

            shipmentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public Inventory getInventory() {
        // Return the inventory instance
        return this.inventory;
    }
}
