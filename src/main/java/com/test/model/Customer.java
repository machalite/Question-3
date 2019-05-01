package com.test.model;


/**
 * Model class for customer
 * @author Jonathan Huang
 *
 */
public class Customer {
	private String name;
	private boolean employee=false;
	private boolean affiliate=false;
	private boolean loyalCustomer=false;
	
	public boolean isEmployee() {
		return employee;
	}
	public boolean isAffiliate() {
		return affiliate;
	}
	public boolean isLoyalCustomer() {
		return loyalCustomer;
	}
	
	public Customer(String name, boolean employee, boolean affiliate, boolean loyalCustomer) {
		super();
		this.name = name;
		this.employee = employee;
		this.affiliate = affiliate;
		this.loyalCustomer = loyalCustomer;
	}
	
	
}
