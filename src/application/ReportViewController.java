package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

public class ReportViewController {
	@FXML
	private ListView backorderListView;
	@FXML
	private TextField orderFilledField;
	@FXML
	private TextField mostPopularField;
	@FXML
	private TextField leastPopularField;
	@FXML
	private TextField topCustomerField;
	@FXML
	private Button closeReportButton;

	// Event Listener on Button[#closeReportButton].onAction
	@FXML
	public void closeReportButtonClick(ActionEvent event) {
		// TODO Autogenerated
	}

	public void setInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}
}
