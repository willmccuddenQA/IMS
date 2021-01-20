package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	private Order order;
	private Order other;
	
	@Before
	public void setUp() {
		order = new Order(1L, 1L, "House");
		other = new Order(1L, 1L, "House");
	}
	
	@Test
	public void settersTest() {
		assertNotNull(order.getOrder_id());
		assertNotNull(order.getCustomer_id());
		assertNotNull(order.getAddress());
		
		order.setOrder_id(null);
		assertNull(order.getOrder_id());
		order.setCustomer_id(null);
		assertNull(order.getCustomer_id());
		order.setAddress(null);
		assertNull(order.getAddress());
	}
	
	@Test
	public void toStringTest() {
		String toString = "order_id=1, customer_id=1, address=House";
		assertEquals(toString, order.toString());
	}
	
	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}
	
	@Test
	public void orderIDNullButOtherNameNotNull() {
		order.setOrder_id(null);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void orderIDNotEqual() {
		other.setOrder_id(2L);
		assertFalse(order.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		order.setOrder_id(null);
		other.setOrder_id(null);
		assertTrue(order.equals(other));
	}
	
	@Test
	public void checkConstuctorWithoutID() {
		Order another = new Order(4L,"Green House");
		assertEquals(another.getCustomer_id(),4L,0);
		assertEquals(another.getAddress(),"Green House");
	}
	
}