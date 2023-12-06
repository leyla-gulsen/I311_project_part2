package application;

/*
 * Report class
 * The program should also include a feature to generate reports that 
 * indicate how quickly orders are filled, which items are most/least popular, 
 * which items get back ordered, and which customers order the most product. 
 * The reports should essentially help the business plan their inventory and 
 * marketing better in the future based on the past programmer roles
 */

public class Report {
	
	public void ordersFilledReport(List<Order> orders) {
		
	}
	
	public void popularReport(List<order> orders) {
		
	}
	
	public void leastPopularReport(List<order> orders) {
		
	}
	
	public void topCustomerReport() {
	    if (!orders.isEmpty()) {
	        Customer topCustomer = null;
	        int maxOrders = 0;

	        for (int i = 0; i < orders.size(); i++) {
	            int orderCount = 0;
	            Customer currentCustomer = orders.get(i).getCustomer();

	            for (int j = 0; j < orders.size(); j++) {
	                if (orders.get(j).getCustomer().equals(currentCustomer)) {
	                    orderCount++;
	                }
	            }

	            if (orderCount > maxOrders) {
	                maxOrders = orderCount;
	                topCustomer = currentCustomer;
	            }
	        }

	        if (topCustomer != null) {
	            topCustomerField.setText(topCustomer.getName());
	        } else {
	            topCustomerField.setText("No orders placed yet.");
	        }
	    }
	}
	
	public void backorderedItemsReport(List<order> orders) {
		
	}
}
