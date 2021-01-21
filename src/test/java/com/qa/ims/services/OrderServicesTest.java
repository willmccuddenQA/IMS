package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;

@RunWith(MockitoJUnitRunner.class)
public class OrderServicesTest {
	
	@Mock
	private OrderDaoMysql orderDao;
	
	@InjectMocks
	private OrderServices orderServices;
	
	@Test
	public void orderServicesReadAll() {
		orderServices.readAll();
		Mockito.verify(orderDao,Mockito.times(1)).readAll();
	}
	
	@Test
	public void orderServicesCreate() {
		Order order = new Order(1L, "House");
		orderServices.create(order);
		Mockito.verify(orderDao,Mockito.times(1)).create(order);
	}
	
	
	@Test 
	public void orderServicesDeleteItem() {
		orderServices.deleteItem(1L,1L );
		Mockito.verify(orderDao,Mockito.times(1)).deleteItem(1L, 1L);
	}
	
	@Test
	public void orderServicesCalculate() {
		orderServices.calculate(1L);
		Mockito.verify(orderDao,Mockito.times(1)).calculate(1L);
	}
	
	@Test
	public void orderServicesReadItems() {
		orderServices.readItems(1L);
		Mockito.verify(orderDao,Mockito.times(1)).readItems(1L);
	}
	
	@Test
	public void orderServicesAddItem() {
		orderServices.addItem(1L, 1L);
		Mockito.verify(orderDao,Mockito.times(1)).addItem(1L, 1L);
	}
	
	@Test 
	public void orderServicesAddItem2() {
		Order order = new Order(1L, "House");
		orderServices.addItem(order,1L);
		Mockito.verify(orderDao,Mockito.times(1)).addItem(order, 1L);
	}
	
	@Test
	public void orderServicesDelete() {
		orderServices.delete(1L);
		Mockito.verify(orderDao,Mockito.times(1)).delete(1L);
	}
	
	@Test
	public void retrieveAllItems() {
		orderServices.retrieveAllItems();
		Mockito.verify(orderDao,Mockito.times(1)).retrieveAllItems();
	}
}
