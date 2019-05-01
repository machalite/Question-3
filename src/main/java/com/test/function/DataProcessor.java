package com.test.function;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.test.model.Customer;
import com.test.model.Item;
import com.test.model.PurchaseOrder;

/**
* Data Processor class contains functions used for net amount calculation
* 
* @author Jonathan Huang
*/
public class DataProcessor {
	
	private static final double EMPLOYEE_DISCOUNT = 0.3;
	private static final double AFFILIATE_DISCOUNT = 0.1;
	private static final double LOYAL_DISCOUNT = 0.05;
	private static final double NORMAL_DISCOUNT = 0;
	
	private static final long MULTIPLIER_DISCOUNT_THERSHOLD = 100;
	private static final long MULTIPLIER_DISCOUNT_AMOUNT = 5;
	
	/**
	 * Function to calculate net amount of a purchase order.
	 * Includes percentage discount and multiplier discount calculations.
	 * 
	 * @param order is PurchaseOrder entity to calculate the total amount
	 * @return the net amount after discounts
	 */
	public BigDecimal calculateNetAmount(PurchaseOrder order) {
		BigDecimal netAmount = BigDecimal.valueOf(0);
		Customer purchaser = order.getPurchaser();
		
		if(purchaser.isEmployee()) 
			netAmount = calculateTotalAmount(order.getListPurchase(), EMPLOYEE_DISCOUNT);
		else if(purchaser.isAffiliate())
			netAmount = calculateTotalAmount(order.getListPurchase(), AFFILIATE_DISCOUNT);
		else if(purchaser.isLoyalCustomer())
			netAmount = calculateTotalAmount(order.getListPurchase(), LOYAL_DISCOUNT);
		else
			netAmount = calculateTotalAmount(order.getListPurchase(), NORMAL_DISCOUNT);

		BigDecimal multiplierDiscount = calculateMultiplierDiscount(netAmount);
		netAmount = netAmount.subtract(multiplierDiscount);
		return netAmount;
	}
	
	/**
	 * Function to calculate total amount of list of items
	 * Excludes grocery item from percentage discount
	 * 
	 * @param listItem is list of Item entity to calculate
	 * @param discount is the percentage discount applied
	 * @return the total amount after 
	 */
	public BigDecimal calculateTotalAmount(List<Item> listItem, double discount) {
		BigDecimal amount = BigDecimal.valueOf(0);
		BigDecimal fraction = BigDecimal.valueOf(1-discount);
		
		for(Item item : listItem) {
			if(item.isNotGrocery()) {
				BigDecimal percentagePrice = item.getPrice().multiply(fraction);
				amount = amount.add(percentagePrice);				
			}else {
				amount = amount.add(item.getPrice());
			}
		}
		return amount;
	}
	
	/**
	 * Function to calculate multiplier discount amount from a bill
	 * 
	 * @param amount is the bill amount after percentage discount
	 * @return the discount amount
	 */
	public BigDecimal calculateMultiplierDiscount(BigDecimal amount) {
		BigDecimal multiplier = amount.divide(BigDecimal.valueOf(MULTIPLIER_DISCOUNT_THERSHOLD), 0, RoundingMode.DOWN);
		BigDecimal discount = BigDecimal.valueOf(MULTIPLIER_DISCOUNT_AMOUNT).multiply(multiplier);
		return discount;
	}
}
