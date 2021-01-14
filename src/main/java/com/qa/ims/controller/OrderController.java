package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.services.OrderServices;
import com.qa.ims.utils.Utils;

public class OrderController{
	
	public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	
	private OrderServices orderService;
	
	public OrderController(OrderServices orderService) {
		this.orderService = orderService;
	}
	
	Long getInputLong() {
		return Utils.getInputLong();
	}
	
	String getInput() {
		return Utils.getInput();
	}
	//READ
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for(Order order: orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}
	
	//CREATE
	public Order create() {
		LOGGER.info("Please enter a customer_id");
		Long customer_id = getInputLong();
		LOGGER.info("Please enter the address");
		String address = getInput();
		Order order = orderService.create(new Order(customer_id, address));
		LOGGER.info("What items would you like to add?");
		List<Item> items = orderService.retrieveAllItems();
		for(Item item : items) {
			LOGGER.info(item.toString());
		}
		while(true) {
			LOGGER.info("Use the item's id to select it and enter 0 when done");
			Long item_id = Long.getLong(getInput());
			if(item_id == 0) {
				break;
			}
			orderService.addItems(order, item_id);
		}
		LOGGER.info("Order created");
		return order;
	}
	
	// DELETEITEM
	public void deleteItem() {
		
	}
	
	// CALCULATE
	public double calculate() {
		return 0;
		
	}
	// READITEMS
	public List<Item> readItems() {
		return null;
		
	}
	
	//ADD
	public Order addItems() {
		return null;
	}
	
	
	//DELETE
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = Long.valueOf(getInput());
		orderService.delete(id);
		LOGGER.info("Order deleted");
	}

}
