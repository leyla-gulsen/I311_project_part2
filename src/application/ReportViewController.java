package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import java.util.List;


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
	
    public TextField getMostPopularField() {
        return mostPopularField;
    }
	

	public void setInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}

	public void updateOrdersFilledReport() {
	    Report report = new Report();
	    String fastestFulfillmentTime = report.ordersFilledReport();
	    orderFilledField.setText(fastestFulfillmentTime);
	}
	
    public void updateMostPopularThneed() {
        Report report = new Report();
        String mostPopularThneed = report.mostPopularThneed();
        mostPopularField.setText(mostPopularThneed);
    }
    
    public void updateLeastPopularThneed() {
        Report report = new Report();
        String leastPopularThneed = report.leastPopularReport();
        leastPopularField.setText(leastPopularThneed);
    }
    
    public void updateTopCustomerReport() {
        Report report = new Report();
        String topCustomer = report.topCustomerReport();
        topCustomerField.setText(topCustomer);
    }
    
}