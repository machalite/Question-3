package com.test.model;

import java.math.BigDecimal;


/**
 * Model class for item
 * @author Jonathan Huang
 *
 */
public class Item {
	private String name;
	private BigDecimal price=BigDecimal.valueOf(0);
	private boolean grocery=false;
	public BigDecimal getPrice() {
		return price;
	}
	public boolean isNotGrocery() {
		return !grocery;
	}
	public Item(String name, BigDecimal price, boolean grocery) {
		super();
		this.name = name;
		this.price = price;
		this.grocery = grocery;
	}
	
	
	
}
