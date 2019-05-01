package com.test.model;

import java.util.List;


/**
 * Model class for purchase order
 * @author Jonathan Huang
 *
 */
public class PurchaseOrder {
	
	private Customer purchaser;
	private List<Item> listPurchase;
	
	public Customer getPurchaser() {
		return purchaser;
	}
	public List<Item> getListPurchase() {
		return listPurchase;
	}
	public PurchaseOrder(Customer purchaser, List<Item> listPurchase) {
		super();
		this.purchaser = purchaser;
		this.listPurchase = listPurchase;
	}
	
	
}
