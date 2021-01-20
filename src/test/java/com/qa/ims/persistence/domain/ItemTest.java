package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	
	private Item item;
	private Item other;
	
	@Before
	public void setUp() {
		item = new Item(1L, "Hat", 3.99);
		other = new Item(1L, "Hat", 3.99);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(item.getItem_id());
		item.setItem_id(null);
		assertNull(item.getItem_id());
	}
	
	@Test
	public void settersTestName() {
		assertNotNull(item.getName());
		item.setName(null);
		assertNull(item.getName());
	}
	
	@Test(expected=NullPointerException.class)
	public void settersTestPrice() {
		assertNotNull(item.getPrice());
		item.setPrice(null);
		assertNull(item.getPrice());
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}
	
	@Test
	public void createitemWithId() {
		assertEquals(1L, item.getItem_id(), 0);
		assertEquals("Hat", item.getName());
		assertEquals(3.99, item.getPrice(),0.00);
	}
	
	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(item.equals(other));
	}
	
	@Test
	public void itemNameNullButOtherNameNotNull() {
		item.setName(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void itemNamesNotEqual() {
		other.setName("rhys");
		assertFalse(item.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		item.setName(null);
		other.setName(null);
		assertTrue(item.equals(other));
	}
	
	@Test
	public void nullId() {
		item.setItem_id(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		item.setItem_id(null);
		other.setItem_id(null);
		assertTrue(item.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setItem_id(2L);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullName() {
		item.setName(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullNameOnBoth() {
		item.setName(null);
		other.setName(null);
		assertTrue(item.equals(other));
	}
	
	@Test
	public void otherNameDifferent() {
		other.setName("thompson");
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullPrice() {
		item.setPrice(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void nullPriceOnBoth() {
		item.setPrice(null);
		other.setPrice(null);
		assertTrue(item.equals(other));
	}
	
	@Test
	public void otherPriceDifferent() {
		other.setPrice(1.99);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		Item item = new Item("Book", 11.50);
		assertNull(item.getItem_id());
		assertNotNull(item.getName());
		assertNotNull(item.getPrice());
	}
	
	
	@Test
	public void toStringTest() {
		String toString = "Item [item_id=1, name=Hat, price=3.99]";
		assertEquals(toString, item.toString());
	}
}