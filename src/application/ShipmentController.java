package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ShipmentController {

    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private RadioButton largeRadioButton;

    @FXML
    private RadioButton redRadioButton;

    @FXML
    private RadioButton blueRadioButton;

    @FXML
    private RadioButton greenRadioButton;

    @FXML
    private RadioButton orangeRadioButton;

    @FXML
    private RadioButton yellowRadioButton;

    @FXML
    private RadioButton purpleRadioButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label errorLabel1;

    @FXML
    private Button addToOrderButton;

    @FXML
    private ListView<String> orderList;

    @FXML
    private Label errorLabel2;

    @FXML
    private TextField quantityField;

    // List to store shipments
    private List<Shipment> shipmentList = new ArrayList<>();

    private Inventory inventory;

    @FXML
    private ToggleGroup sizeGroup;

    @FXML
    private ToggleGroup colorGroup;
    
    
    private InventoryViewController inventoryViewController;
    
    public void setInventoryController(InventoryViewController inventoryViewController) {
        this.inventory = inventoryViewController.getInventory();
        this.inventoryViewController = inventoryViewController;
    }



    @FXML
    private void onConfirmButtonClick(ActionEvent event) {
        writeOrderListToFile("Shipment.txt");
        
        inventoryViewController.updateIncomingShipmentTextArea();

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
    
    private void writeOrderListToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (String orderInfo : orderList.getItems()) {
                writer.write(orderInfo);
                writer.newLine(); 
            }
            System.out.println("Data written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onAddToOrderButtonClick(ActionEvent event) {
        String selectedItem = getTitleForSelectedRadioButton();
        String quantity = quantityField.getText();

        // Validate input
        if (selectedItem == null || quantity.isEmpty()) {
            errorLabel2.setText("Please select item and enter quantity.");
            return;
        }

        // Get the color and size based on the selected item
        Thneed thneed = getThneedForSelectedItem(selectedItem);
        if (thneed != null) {
            int shipmentQuantity = Integer.parseInt(quantity);

            // Create a new shipment
            HashMap<Thneed, Integer> shipmentItem = new HashMap<>();
            shipmentItem.put(thneed, shipmentQuantity);
            
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR, 2);
            Date deliveryDate = calendar.getTime();
            
            Shipment shipment = new Shipment(inventory.getNextShipnum(), shipmentItem, deliveryDate);

            // Add the shipment to the list
            shipmentList.add(shipment);

            String orderInfo = thneed.getSize() + " " + thneed.getColor() + " Thneed  (" + shipmentQuantity + ") - Est. Delivery Date: " + deliveryDate;
            orderList.getItems().add(orderInfo);

            // Clear the quantity field
            quantityField.clear();
            
            System.out.println("Adding shipment. Calling updateIncomingShipmentTextArea...");
            inventoryViewController.displayShipmentDataFromFile("Shipment.txt");

        }
    }

    @FXML
    private String getTitleForSelectedRadioButton() {
        RadioButton selectedRadioButton = (RadioButton) sizeGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            return selectedRadioButton.getText();
        }

        selectedRadioButton = (RadioButton) colorGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            return selectedRadioButton.getText();
        }

        return null;
    }

    @FXML
    private Thneed getThneedForSelectedItem(String selectedItem) {
        RadioButton selectedRadioButton = (RadioButton) sizeGroup.getSelectedToggle();
        String size = selectedRadioButton != null ? selectedRadioButton.getText() : "";

        selectedRadioButton = (RadioButton) colorGroup.getSelectedToggle();
        String color = selectedRadioButton != null ? selectedRadioButton.getText() : "";

        // Check if both size and color are selected
        if (!size.isEmpty() && !color.isEmpty()) {
            return new Thneed(size, color);
        }

        // Return a default Thneed if size or color is not selected
        return new Thneed("defaultSize", "defaultColor");
    }


}
