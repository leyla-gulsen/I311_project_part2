package application;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
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

/*
 * Orders Filled
 */

public class Report {
	
    private List<Order> getOrdersList() {
        List<Order> orders = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CustomerLoginController.ORDERS_FILEPATH))) {
            orders = (List<Order>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
	
    public String ordersFilledReport() {
        List<Order> orders = getOrdersList();
        String fastestFulfillmentTime = "No fulfilled orders";
        long shortestTimeInMillies = Long.MAX_VALUE;

        for (Order order : orders) {
            if (order.isFulfilled()) {
                long diffInMillies = Math.abs(order.getDateFilled().getTime() - order.getDateOrdered().getTime());
                if (diffInMillies < shortestTimeInMillies) {
                    shortestTimeInMillies = diffInMillies;
                    fastestFulfillmentTime = order.getFulfillmentTime(); 
                }
            }
        }

        return fastestFulfillmentTime;
    }
	
	/*
	 * Most Popular Thneed
	 */
	
//	private String mostPopularThneed;
//	
//	public void popularReport(List<Order> orders) {
//        HashMap<String, Integer> thneedOccurrences = new HashMap<>();
//
//        // iterate through each order and count thneed occurrences
//        for (Order order : orders) {
//            for (Map.Entry<Thneed, Integer> entry : order.getThneedList().entrySet()) {
//                Thneed thneed = entry.getKey();
//                String thneedType = thneed.getSize() + " " + thneed.getColor() + " thneed";
//
//                // increment
//                thneedOccurrences.put(thneedType, thneedOccurrences.getOrDefault(thneedType, 0) + entry.getValue());
//            }
//        }
//
//        // find the most popular thneed type
//        String mostPopularThneed = null;
//        int maxOccurrences = 0;
//
//        for (Map.Entry<String, Integer> entry : thneedOccurrences.entrySet()) {
//            if (entry.getValue() > maxOccurrences) {
//                mostPopularThneed = entry.getKey();
//                maxOccurrences = entry.getValue();
//            }
//        }
//
//        // display the most popular thneed type
//        if (mostPopularThneed != null) {
//            this.mostPopularThneed = mostPopularThneed;
//        } else {
//            this.mostPopularThneed = "No orders placed yet.";
//        }
//    }
//	
//    public String getMostPopularThneed() {
//        return mostPopularThneed;
//    }
//    
//    /*
//     * 
//     */
//	
//	public void leastPopularReport(List<order> orders) {
//		
//	}
//	
//	public void topCustomerReport() {
//
//	}
//	
//	public void backorderedItemsReport(List<order> orders) {
//		
//	}
}
