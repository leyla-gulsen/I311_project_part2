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
	
    public String mostPopularThneed() {
        List<Order> orders = getOrdersList(); 
        Map<String, Integer> colorCount = new HashMap<>();
        int maxCount = 0;
        String mostPopularColor = "No duplicate colors ordered";

        for (Order order : orders) {
            HashMap<Thneed, Integer> thneedMap = order.getThneedList(); 

            for (Map.Entry<Thneed, Integer> entry : thneedMap.entrySet()) {
                Thneed thneed = entry.getKey();
                int quantity = entry.getValue();
                String color = thneed.getColor();

                int count = colorCount.getOrDefault(color, 0) + quantity;
                colorCount.put(color, count);

                if (count > maxCount) {
                    maxCount = count;
                    mostPopularColor = color;
                }
            }
        }

        return (maxCount > 1) ? mostPopularColor : "No duplicate colors ordered";
    }
    
    
    public String leastPopularReport() {
        List<Order> orders = getOrdersList(); 
        Map<String, Integer> colorCount = new HashMap<>();
        int minCount = Integer.MAX_VALUE;
        String leastPopularColor = "All colors equally popular";

        for (Order order : orders) {
            HashMap<Thneed, Integer> thneedMap = order.getThneedList(); 

            for (Map.Entry<Thneed, Integer> entry : thneedMap.entrySet()) {
                Thneed thneed = entry.getKey();
                int quantity = entry.getValue();
                String color = thneed.getColor();

                int count = colorCount.getOrDefault(color, 0) + quantity;
                colorCount.put(color, count);

                if (colorCount.size() > 1 && count < minCount) {
                    minCount = count;
                    leastPopularColor = color;
                }
            }
        }

        return leastPopularColor;
    }
//	
//	public void topCustomerReport() {
//
//	}
//	
//	public void backorderedItemsReport(List<order> orders) {
//		
//	}
}
