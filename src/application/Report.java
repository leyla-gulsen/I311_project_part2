package application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/*
	 * Most Popular Thneed
	 */
	
	private String mostPopularThneed;
	
	public void popularReport(List<Order> orders) {
        HashMap<String, Integer> thneedOccurrences = new HashMap<>();

        // iterate through each order and count thneed occurrences
        for (Order order : orders) {
            for (Map.Entry<Thneed, Integer> entry : order.getThneedList().entrySet()) {
                Thneed thneed = entry.getKey();
                String thneedType = thneed.getSize() + " " + thneed.getColor() + " thneed";

                // increment
                thneedOccurrences.put(thneedType, thneedOccurrences.getOrDefault(thneedType, 0) + entry.getValue());
            }
        }

        // find the most popular thneed type
        String mostPopularThneed = null;
        int maxOccurrences = 0;

        for (Map.Entry<String, Integer> entry : thneedOccurrences.entrySet()) {
            if (entry.getValue() > maxOccurrences) {
                mostPopularThneed = entry.getKey();
                maxOccurrences = entry.getValue();
            }
        }

        // display the most popular thneed type
        if (mostPopularThneed != null) {
            this.mostPopularThneed = mostPopularThneed;
        } else {
            this.mostPopularThneed = "No orders placed yet.";
        }
    }
	
    public String getMostPopularThneed() {
        return mostPopularThneed;
    }
    
    /*
     * 
     */
	
	public void leastPopularReport(List<order> orders) {
		
	}
	
	public void topCustomerReport() {

	}
	
	public void backorderedItemsReport(List<order> orders) {
		
	}
}

/*
 * update generateReportButtonClick in InventoryViewController to call mostPopularThneed and generating reports
 */
