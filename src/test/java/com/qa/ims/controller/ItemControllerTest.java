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
import com.qa.ims.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	@Mock
	private ItemServices itemServices;
	
	@Spy
	@InjectMocks
	private ItemController itemController;

	@Test
	public void readAllTest() {
		ItemController itemController = new ItemController(itemServices);
		List<Item> items = new ArrayList<>();
		items.add(new Item("Hat", 10.00));
		items.add(new Item("Pen", 1.49));
		items.add(new Item("TV", 59.99));
		Mockito.when(itemServices.readAll()).thenReturn(items);
		assertEquals(items, itemController.readAll());
	}

	@Test
	public void createTest() {
		String name = "Hat";
		String price = "2.33";
		Mockito.doReturn(name,price).when(itemController).getInput();
		Item item = new Item(name,2.33);
		Item savedItem = new Item(1L, name, 2.33);
		Mockito.when(itemServices.create(item)).thenReturn(savedItem);
		assertEquals(savedItem, itemController.create());
	}
	
	@Test
	public void updateTest() {
		String id = "1";
		String name = "Coat";
		String price = "49.99";
		Mockito.doReturn(id,name,price).when(itemController).getInput();
		Item item = new Item(1L, name, 49.99);
		Mockito.when(itemServices.update(item)).thenReturn(item);
		assertEquals(item,itemController.update());
	}
	
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(itemController).getInput();
		itemController.delete();
		Mockito.verify(itemServices, Mockito.times(1)).delete(1L);
	}
	
}
