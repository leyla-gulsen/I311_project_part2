package application;

import java.util.Date;
import java.util.HashMap;


/**
 * A Thneed represents a single product to be ordered in the Thneed order system.
 * Thneeds were introduced in Dr. Seuss books. They are highly versatile 
 * knitted fabrics that have a size and color.
 */
public class Shipment {

	private int shipnum;
	private HashMap<Thneed, Integer> shipmentList;
	private Date shipdate;
	/**
	 * Constructs a new Thneed
	 * @param size the size of the Thneed ("small", "medium", or "large")
	 * @param color the color of the Thneed
	 * Shipment class calls Thneed - hashmap pairs Thneed with quantity like a dictionary. Shipment class is similar to Order
	 */
	public Shipment(int shipnum, HashMap<Thneed, Integer> shipmentList, Date shipdate) {
		super();
		this.shipnum = shipnum;
		this.shipmentList = shipmentList;
		this.shipdate = new Date();
	}
	
	/** Getters **/
	public int getShipnum() {
		return shipnum;
	}
	
	public HashMap<Thneed, Integer> getShipmentList() {
		return shipmentList;
	}
	
	public Date getShipdate() {
		return shipdate;
	}
	
}