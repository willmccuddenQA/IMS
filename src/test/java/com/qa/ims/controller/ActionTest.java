package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ActionTest {
	
	@Test
	public void getDescriptionsTestAction() {
		assertEquals(Action.CREATE.getDescription(),"CREATE: To save a new item into the database");
		assertEquals(Action.READ.getDescription(),"READ: To read an item from the database");
		assertEquals(Action.UPDATE.getDescription(),"UPDATE: To change an item already in the database");
		assertEquals(Action.DELETE.getDescription(),"DELETE: To remove an item from the database");
		assertEquals(Action.RETURN.getDescription(),"RETURN: To return to domain selection");
	}
	
	@Test
	public void getDescriptionsTestOrderAction() {
		assertEquals(OrderActions.ADD.getDescription(),"ADD: Add item to order");
		assertEquals(OrderActions.DELETEITEM.getDescription(),"DELETEITEM: Delete item from order");
		assertEquals(OrderActions.CALCULATE.getDescription(),"CALCULATE: Calculate order cost");
		assertEquals(OrderActions.DELETE.getDescription(),"DELETE: Removes an order from the database");
		assertEquals(OrderActions.READITEMS.getDescription(),"READITEMS: Read items from order");
		assertEquals(OrderActions.CREATE.getDescription(),"CREATE: Add new order to database");
		assertEquals(OrderActions.READ.getDescription(),"READ: Read all orders from database");
		assertEquals(OrderActions.RETURN.getDescription(),"RETURN: return to domain selection");
	}
}
