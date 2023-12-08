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

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

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
        this.inventory = new Inventory();
        this.inventory.initialize();
        inventory.loadInventory();
        updateIncomingShipmentTextArea();
        updateCurrentInventoryTextArea();
    }

    private void updateCurrentInventoryTextArea() {
        currentInventoryTextArea.clear();

        for (Entry<Thneed, Integer> entry : inventory.getCurrentInventory().entrySet()) {
            String itemInfo = entry.getKey().getSize() + "-" + entry.getKey().getColor() +
                    ": " + entry.getValue() + " items\n";
            currentInventoryTextArea.appendText(itemInfo);
        }
    }

    private void updateIncomingShipmentTextArea() {
        incomingShipmentTextArea.clear();

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
        updateIncomingShipmentTextArea();
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
