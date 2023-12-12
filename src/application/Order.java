package application;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.HashMap;

/**
 * An Order represents a Customer's order in the Thneeds order system.
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int orderNumber;
	private HashMap<Thneed, Integer> thneedList;
	private Customer customer;
	private Date dateOrdered;
	private Date dateFilled;
	
//	added
	private Date dateProjected;
	
	
	/**
	 * Construct a new Order
	 * @param orderNumber a unique order ID
	 * @param thneedList a HashMap containing the Order's Thneeds and their respective quantities
	 * @param customer the Customer that placed the Order
	 */
	public Order(int orderNumber, HashMap<Thneed, Integer> thneedList, Customer customer) {
		super();
		this.orderNumber = orderNumber;
		this.thneedList = thneedList;
		this.customer = customer;
		this.dateOrdered = new Date();
		this.dateProjected = new Date();
	}
	
	/** Getters and Setters **/
	
	public int getOrderNumber() {
		return orderNumber;
	}
	
	public HashMap<Thneed, Integer> getThneedList() {
		return thneedList;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Date getDateOrdered() {
		return dateOrdered;
	}
	
	public void setDateOrdered(Date date) {
		this.dateOrdered = date;
	}
	
	public Date getDateFilled() {
		return dateFilled;
	}
	
	public void setDateFilled(Date dateFilled) {
		this.dateFilled = dateFilled;
	}
	
	public Date getDateProjected() {
		return dateProjected;
	}
	
	public void setDateProjected(Date dateProjected) {
		this.dateProjected = dateProjected;
	}
	
	/**
	 * Converts the Order to a string.
	 * @return a string representation of the Order
	 */
	@Override
	public String toString() {
		int size = 0;
		for (Integer i : thneedList.values())
			size += i;
		return String.format("Order #%03d: " + customer.getName() + " (" + size + " thneeds)", orderNumber);
	}
	
	public boolean isFulfilled() {
		return dateFilled != null;
	}

    public String getFulfillmentTime() {
        if (isFulfilled()) {
            long diffInMillies = Math.abs(dateFilled.getTime() - dateOrdered.getTime());

            // Calculate the differences in days, hours, minutes, and seconds
            long days = TimeUnit.MILLISECONDS.toDays(diffInMillies);
            long hours = TimeUnit.MILLISECONDS.toHours(diffInMillies) % 24;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies) % 60;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillies) % 60;

            // Format the string
            return String.format("%d Days, %d Hours, %d Minutes, %d Seconds", days, hours, minutes, seconds);
        } else {
            return "Order not fulfilled";
        }
    }
}
