package application;

import java.util.Date;


/**
 * A Thneed represents a single product to be ordered in the Thneed order system.
 * Thneeds were introduced in Dr. Seuss books. They are highly versatile 
 * knitted fabrics that have a size and color.
 */
public class Shipment {

	private int shipnum;
	private String size;
	private String color;
	private int quantity;
	private Date shipdate;
	/**
	 * Constructs a new Thneed
	 * @param size the size of the Thneed ("small", "medium", or "large")
	 * @param color the color of the Thneed
	 * Shipment class calls Thneed - hashmap pairs Thneed with quantity like a dictionary. Shipment class is similar to Order
	 */
	public Shipment(int shipnum, String size, String color, int quantity, Date shipdate) {
		super();
		this.size = size;
		this.color = color;
		this.quantity = quantity;
		this.shipdate = shipdate;
	}
	
	/** Getters **/
	public int getOrderNumber() {
		return orderNumber;
	}

	public String getSize() {
		return size;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Date getShipdate() {
		return shipdate;
	}
	
}