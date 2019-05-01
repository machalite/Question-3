package com.test.testCase;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.test.function.DataProcessor;
import com.test.model.Customer;
import com.test.model.Item;
import com.test.model.PurchaseOrder;


/**
 * Unit tests for Data Processor class 
 * @author Jonathan Huang
 *
 */
public class Question3Test {
	
	private Customer normalCust = new Customer("Normal", false, false, false);
	
	private Customer employeeCust = new Customer("Employee", true, false, false);
	
	private Customer affiliateCust = new Customer("Affiliate", false, true, false);
	
	private Customer loyalCust = new Customer("Loyal", false, false, true);
	
	private Customer allCust = new Customer("Affiliate", true, true, true);
	
	private Customer loyalEmpCust = new Customer("Affiliate", true, false, true);
	
	private Customer loyalAffCust = new Customer("Affiliate", false, true, true);
	
	private Customer empAffCust = new Customer("Affiliate", true, true, false);
	
	private DataProcessor func = new DataProcessor();
	
	private Item item1 = new Item("Item1", BigDecimal.valueOf(25), false);
	private Item item2 = new Item("Item2", BigDecimal.valueOf(150), false);
	private Item item3 = new Item("Item3", BigDecimal.valueOf(200), false);
	private Item grocery1 = new Item("Grocery1", BigDecimal.valueOf(15), true);
	private Item grocery2 = new Item("Grocery2", BigDecimal.valueOf(50), true);
	private Item grocery3 = new Item("Grocery3", BigDecimal.valueOf(80), true);
	
	public List<Item> listItem;
	
	public PurchaseOrder createMixedOrder(Customer purchaser) {
		List<Item> listItem = new ArrayList<Item>();
		listItem.add(item1);
		listItem.add(grocery2);
		PurchaseOrder order = new PurchaseOrder(purchaser, listItem);
		return order;
	}
	
	public PurchaseOrder createMixedOrderWithDisc(Customer purchaser) {
		List<Item> listItem = new ArrayList<Item>();
		listItem.add(item1);
		listItem.add(item2);
		listItem.add(grocery1);
		listItem.add(grocery2);
		PurchaseOrder order = new PurchaseOrder(purchaser, listItem);
		return order;
	}
	
	public PurchaseOrder createOrderNoGrocery(Customer purchaser) {
		List<Item> listItem = new ArrayList<Item>();
		listItem.add(item1);
		listItem.add(item1);
		listItem.add(item1);
		PurchaseOrder order = new PurchaseOrder(purchaser, listItem);
		return order;
	}
	
	public PurchaseOrder createOrderNoGroceryWithDisc(Customer purchaser) {
		List<Item> listItem = new ArrayList<Item>();
		listItem.add(item3);
		PurchaseOrder order = new PurchaseOrder(purchaser, listItem);
		return order;
	}
	
	public PurchaseOrder createOrderGroceryOnly(Customer purchaser) {
		List<Item> listItem = new ArrayList<Item>();
		listItem.add(grocery3);
		PurchaseOrder order = new PurchaseOrder(purchaser, listItem);
		return order;
	}
	
	public PurchaseOrder createOrderGroceryOnlyWithDisc(Customer purchaser) {
		List<Item> listItem = new ArrayList<Item>();
		listItem.add(grocery1);
		listItem.add(grocery2);
		listItem.add(grocery3);
		PurchaseOrder order = new PurchaseOrder(purchaser, listItem);
		return order;
	}
	
	@Test
    public void testMixedOrder() {
		PurchaseOrder order = createMixedOrder(normalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(75), amount.setScale(0));
    }
	
	@Test
    public void testMixedOrderWithDisc() {
		PurchaseOrder order = createMixedOrderWithDisc(normalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(230), amount.setScale(0));
    }
	
	@Test
    public void testNoGroceryOrder() {
		PurchaseOrder order = createOrderNoGrocery(normalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(75), amount.setScale(0));
    }
	
	@Test
    public void testNoGroceryOrderWithDisc() {
		PurchaseOrder order = createOrderNoGroceryWithDisc(normalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(190), amount.setScale(0));
    }
	
	@Test
    public void testGroceryOnlyOrder() {
		PurchaseOrder order = createOrderGroceryOnly(normalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(80), amount.setScale(0));
    }
	
	@Test
    public void testGroceryOnlyOrderWithDisc() {
		PurchaseOrder order = createOrderGroceryOnlyWithDisc(normalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(140), amount.setScale(0));
    }
	
	@Test
    public void testMixedOrderEmployee() {
		PurchaseOrder order = createMixedOrder(employeeCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(67.5), amount);
    }
	
	@Test
    public void testMixedOrderWithDiscEmployee() {
		PurchaseOrder order = createMixedOrderWithDisc(employeeCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(182.5), amount);
    }
	
	@Test
    public void testNoGroceryOrderEmployee() {
		PurchaseOrder order = createOrderNoGrocery(employeeCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(52.5), amount);
    }
	
	@Test
    public void testNoGroceryOrderWithDiscEmployee() {
		PurchaseOrder order = createOrderNoGroceryWithDisc(employeeCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(135), amount.setScale(0));
    }
	
	@Test
    public void testGroceryOnlyOrderEmployee() {
		PurchaseOrder order = createOrderGroceryOnly(employeeCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(80), amount);
    }
	
	@Test
    public void testGroceryOnlyOrderWithDiscEmployee() {
		PurchaseOrder order = createOrderGroceryOnlyWithDisc(employeeCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(140), amount);
    }
	
	@Test
    public void testMixedOrderAffiliate() {
		PurchaseOrder order = createMixedOrder(affiliateCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(72.5), amount);
    }
	
	@Test
    public void testMixedOrderWithDiscAffiliate() {
		PurchaseOrder order = createMixedOrderWithDisc(affiliateCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(212.5), amount);
    }
	
	@Test
    public void testNoGroceryOrderAffiliate() {
		PurchaseOrder order = createOrderNoGrocery(affiliateCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(67.5), amount);
    }
	
	@Test
    public void testNoGroceryOrderWithDiscAffiliate() {
		PurchaseOrder order = createOrderNoGroceryWithDisc(affiliateCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(175), amount.setScale(0));
    }
	
	@Test
    public void testGroceryOnlyOrderAffiliate() {
		PurchaseOrder order = createOrderGroceryOnly(affiliateCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(80), amount.setScale(0));
    }
	
	@Test
    public void testGroceryOnlyOrderWithDiscAffiliate() {
		PurchaseOrder order = createOrderGroceryOnlyWithDisc(affiliateCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(140), amount.setScale(0));
    }
	
	@Test
    public void testMixedOrderLoyalCust() {
		PurchaseOrder order = createMixedOrder(loyalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(73.75), amount);
    }
	
	@Test
    public void testMixedOrderWithDiscLoyalCust() {
		PurchaseOrder order = createMixedOrderWithDisc(loyalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(221.25), amount);
    }
	
	@Test
    public void testNoGroceryOrderLoyalCust() {
		PurchaseOrder order = createOrderNoGrocery(loyalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(71.25), amount);
    }
	
	@Test
    public void testNoGroceryOrderWithDiscLoyalCust() {
		PurchaseOrder order = createOrderNoGroceryWithDisc(loyalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(185), amount.setScale(0));
    }
	
	@Test
    public void testGroceryOnlyOrderLoyalCust() {
		PurchaseOrder order = createOrderGroceryOnly(loyalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(80), amount.setScale(0));
    }
	
	@Test
    public void testGroceryOnlyOrderWithDiscLoyalCust() {
		PurchaseOrder order = createOrderGroceryOnlyWithDisc(loyalCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(140), amount.setScale(0));
    }

	@Test
    public void testAllCustomer() {
		PurchaseOrder order = createOrderNoGrocery(allCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(52.5), amount);
    }
	
	@Test
    public void testLoyalEmployeeCustomer() {
		PurchaseOrder order = createOrderNoGrocery(loyalEmpCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(52.5), amount);
    }
	
	@Test
    public void testLoyalAffiliateCustomer() {
		PurchaseOrder order = createOrderNoGrocery(loyalAffCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(67.5), amount);
    }
	
	@Test
    public void testEmployeeAffiliateCustomer() {
		PurchaseOrder order = createOrderNoGrocery(empAffCust);
		BigDecimal amount = func.calculateNetAmount(order);
		assertEquals(BigDecimal.valueOf(52.5), amount);
    }
}
