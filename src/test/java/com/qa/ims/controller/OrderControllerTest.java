package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private OrderServices orderServices;
	
	@Spy
	@InjectMocks
	private OrderController orderController;
	
	@Test
	public void readAllTest() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, "House"));
		orders.add(new Order(2L, "Big House"));
		orders.add(new Order(3L, "Green House"));
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}
	
	@Test
	public void createTest() {
		String customer_id = "1";
		String address = "House";
		Mockito.doReturn(customer_id, address,"1","0").when(orderController).getInput();
		Order order = new Order(1L, address);
		Order savedOrder = new Order(1L, 1L, "House");
		Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
		assertEquals(savedOrder, orderController.create());
	}
	
	@Test
	public void deleteItemTest() {
		Mockito.doReturn("1","1").when(orderController).getInput();
		orderController.deleteItem();
		Mockito.verify(orderServices, Mockito.times(1)).deleteItem(1L,1L);
	}
	
	@Test 
	public void calculateTest() {
		String customer_id = "1";
		Mockito.doReturn(customer_id).when(orderController).getInput();
		Mockito.when(orderServices.calculate(1L)).thenReturn(10.00);
		assertEquals(orderController.calculate(),10.00,0.00);
	}
	
	@Test
	public void readItemsTest() {
		List<Item> items = new ArrayList<>();
		items.add(new Item("Pen",1.29));
		items.add(new Item("Hat",4.99));
		items.add(new Item("Shirt",7.85));
		Mockito.doReturn("1").when(orderController).getInput();
		Mockito.when(orderServices.readItems(1L)).thenReturn(items);
		assertEquals(items, orderController.readItems());
	}
	
	@Test
	public void addItem() {
//		Item savedItem = new Item(1L,"Coat",1.99);
//		Mockito.doReturn("1","1").when(orderController).getInput();
//		Mockito.when(orderServices.addItem(1L, 1L)).thenReturn(savedItem);
//		assertEquals(new Item(1L,"Coat",1.99), orderServices.addItem(1L, 1L));
		String order_id = "1";
		String item_id = "1";
		Mockito.doReturn(order_id,item_id).when(orderController).getInput();
		Item item = new Item("thing", 5.00);
		Item savedItem = new Item(1L, "thing", 5.00);
		Mockito.when(orderServices.addItem(1L,1L)).thenReturn(savedItem);
		assertEquals(savedItem, orderController.addItem());
	}
	
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}
}
