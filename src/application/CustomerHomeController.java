package application;

import java.io.BufferedReader;

import java.util.Calendar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Home page controller
 */
public class CustomerHomeController implements Initializable {
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private Label userNameLabel;
	
	@FXML
	private Label userIdLabel;
	
	@FXML
	private Label displayedCustomerLabel;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private Button placeNewOrderButton;
	
	@FXML
	private Button updateOrderButton;
	
	@FXML
	private Button fillOrderButton;
	
	@FXML
	private Button logoutButton;
	
	@FXML
	private TextArea orderDisplayArea;
	
	@FXML
	private ListView<String> orderHistoryList;
	
	private Stage stage;
	private Customer user;
	private CustomerLoginController customerLoginController;
	private EditOrdersController editOrdersController;
	private ArrayList<Order> orders = new ArrayList<>();
	private Order displayedOrder = null;
	
	/**
	 * Sets the current user
	 * @param c the customer currently signed in to the system
	 */
	public void setCustomer(Customer c) {
		user = c;
		for (Order o : orders) {
			if (o.getCustomer().equals(user)) 
				user.getOrderList().add(o);
		}
	}
	
	/**
	 * Displays the current user's name
	 * @param name the user's name
	 */
	public void setNameField(String name) {
		userNameLabel.setText(name);
	}
	
	/**
	 * Displays the current user's ID
	 * @param id the user's ID
	 */
	public void setIdField(String id) {
		userIdLabel.setText(userIdLabel.getText() + id);
	}
	
	/**
	 * Sets a controller for the login page
	 * @param c the login page controller
	 */
	public void setCallingController(CustomerLoginController c) {
		customerLoginController = c;
	}
	
	/**
	 * Set a controller for the edit orders page
	 * @param c the edit orders page controller
	 */
	public void setCallingController(EditOrdersController c) {
		editOrdersController = c;
	}
	
	/**
	 * Saves and adds the given order to the system
	 * @param o the order to be added
	 */
	public void addOrder(Order o) {
		user.getOrderList().add(o);
		orders.add(o);
		orderHistoryList.getItems().add(o.toString());
		saveOrders();
		displayOrder(o);
	}
	
	/**
	 * Saves and updates the given order in the system
	 * @param oldOrder the order to be updated
	 * @param newOrder the newly updated order
	 */
	private void updateOrder(Order oldOrder, Order newOrder) {
		newOrder.setDateFilled(null);
		user.getOrderList().set(user.getOrderList().indexOf(oldOrder), newOrder);
		orders.set(orders.indexOf(oldOrder), newOrder);
		orderHistoryList.getItems().set(orderHistoryList.getItems().indexOf(oldOrder.toString()), newOrder.toString());
		saveOrders();
		displayOrder(newOrder);
	}
	
	/**
	 * Displays the given order on the page's top text panel
	 * @param o the order to be displayed
	 */
	private void displayOrder(Order o) {
//		I got this calendar code from javatutorialspoint
		Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.WEEK_OF_YEAR, 2);
	    System.out.println("Updated Date = " + calendar.getTime());
		String s = String.format("\n\nOrder #%03d\n\n", o.getOrderNumber());
		displayedCustomerLabel.setText("Customer: " + o.getCustomer().getName());
		displayedCustomerLabel.setVisible(true);
		for(Map.Entry<Thneed, Integer> entry : o.getThneedList().entrySet()) {
			String thneed = entry.getKey().getSize() + " " + entry.getKey().getColor() + " thneed";
			s += String.format("%02d %-25.25s\n", entry.getValue(), thneed);
		}
		s += "\nOrdered: " + o.getDateOrdered() + "\n";
		if (o.getDateFilled() == null)
			s += "Status: Not filled\n Projected fill date: " + calendar.getTime();
		else
			s += "Status: Filled on " + o.getDateFilled();
		orderDisplayArea.setText(s);
		displayedOrder = o;
	}
	
	/**
	 * Loads orders from an order data file
	 */
	private void loadOrders() {
		File file = new File(CustomerLoginController.ORDERS_FILEPATH);
		try {
			file.createNewFile();
			BufferedReader br = new BufferedReader(new FileReader(CustomerLoginController.ORDERS_FILEPATH));
			if (br.readLine() != null) {
				FileInputStream fis = new FileInputStream(CustomerLoginController.ORDERS_FILEPATH);
				ObjectInputStream input = new ObjectInputStream(fis);
				orders = (ArrayList<Order>) input.readObject();
				fis.close();
				input.close();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Order o : orders) {
			orderHistoryList.getItems().add(o.toString()); 
		}
	}
	
	/**
	 * Saves all orders to an order data file
	 */
	private void saveOrders() {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(CustomerLoginController.ORDERS_FILEPATH));
			output.writeObject(orders);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Logs the current user out and returns to the login page
	 * @param event on-click event for "Logout" button
	 */
	@FXML
	private void onLogoutButtonClick(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomerLogin.fxml"));
		AnchorPane root;
		
		try {
			root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			customerLoginController = (CustomerLoginController) loader.getController();
			customerLoginController.setCallingController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		customerLoginController.setErrorVisibility(false);
		stage.show();
		pane.getScene().getWindow().hide();
	}
	
	/**
	 * Initializes and displays a new window where the user can place a new order
	 * @param event on-click event for "Place New Order" button
	 */
	@FXML
	private void onPlaceNewOrderButtonClick(ActionEvent event) {
		errorLabel.setVisible(false);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditOrders.fxml"));
		AnchorPane root;
		
		try {
			root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Place New Order");
			editOrdersController = (EditOrdersController) loader.getController();
			editOrdersController.setCallingController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		editOrdersController.setTitle("Place New Order");
		editOrdersController.setConfirmButtonText("Place Order");
		editOrdersController.setErrorVisibility(false);
		editOrdersController.setOrderNumber(orders.size() + 1);
		editOrdersController.setCustomer(user);
		stage.showAndWait();
		if (editOrdersController.getOrder() != null)
			addOrder(editOrdersController.getOrder());
	}
	
	/**
	 * Initializes and displays a new window where the user can update the selected order
	 * @param event on-click event for "Update Order" button
	 */
	@FXML
	private void onUpdateOrderButtonClick(ActionEvent event) {
		if (displayedOrder != null) {
			errorLabel.setVisible(false);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditOrders.fxml"));
			AnchorPane root;
			
			try {
				root = (AnchorPane) loader.load();
				Scene scene = new Scene(root);
				stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Update Order");
				editOrdersController = (EditOrdersController) loader.getController();
				editOrdersController.setCallingController(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			editOrdersController.setErrorVisibility(false);
			editOrdersController.setTitle(String.format("Update Order #%03d", displayedOrder.getOrderNumber()));
			editOrdersController.setConfirmButtonText("Update Order");
			editOrdersController.setErrorVisibility(false);
			editOrdersController.setOrderNumber(displayedOrder.getOrderNumber());
			editOrdersController.setCustomer(user);
			stage.showAndWait();
			if (editOrdersController.getOrder() != null)
				updateOrder(displayedOrder, editOrdersController.getOrder());
		} else
			errorLabel.setVisible(true);
	}
	
	/**
	 * Fills the currently displayed order
	 * @param event on-click event for "Fill Order" button
	 */
	@FXML
	private void onFillOrderButtonClick(ActionEvent event) {
		if (displayedOrder != null) {
			errorLabel.setVisible(false);
			Order o = orders.get(orders.indexOf(displayedOrder));
			o.setDateFilled(new Date());
			orders.set(orders.indexOf(displayedOrder), o);
			saveOrders();
			displayOrder(o);
		} else
			errorLabel.setVisible(true);
	}
	
	/**
	 * Displays an alert window containing information about the given customer
	 * @param event on-click event for the underlined customer label of the currently displayed order
	 */
	@FXML
	private void onCustomerNameLabelClick(MouseEvent event) {
		if (displayedOrder != null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			Customer c = displayedOrder.getCustomer();
			alert.setTitle("Customer Info");
			alert.setHeaderText(CustomerLoginController.formatName(displayedOrder.getCustomer().getName()));
			String context = String.format("Customer ID: #%03d", c.getCustomerId());
			context += "\nAddress: " + c.getAddress();
			context += "\nPhone Number: " + c.getPhoneNumber();
			context += "\nNumber of Orders: " + c.getOrderList().size();
			alert.setContentText(context);
			alert.showAndWait();
		}
	}
	
	/**
	 * Initializes the home page
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Hide error messages
		errorLabel.setVisible(false);
		
		// Create ListView item handler
		orderHistoryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String ref, String value) {
		    	for (Order o : orders) {
		    		String[] orderData = value.split(" ");
		    		int orderNumber = Integer.parseInt(orderData[1].substring(1, orderData[1].length() - 1));		
		    		if (o.getOrderNumber() == orderNumber)
		    			displayOrder(o);
		    	}
		    }
		});
		
		// Load order history
		loadOrders(); 
	}
}
