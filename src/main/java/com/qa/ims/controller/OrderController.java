package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.OrderServices;
import com.qa.ims.utils.Utils;

public class OrderController{
	
	public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	
	private OrderServices orderService;
	
	public OrderController(OrderServices orderService) {
		this.orderService = orderService;
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
		LOGGER.info("Please enter a customer id");
		Long customer_id = Long.valueOf(getInput());
		System.out.println(customer_id);
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
			Long item_id = Long.valueOf(getInput());
			if(item_id == 0) {
				break;
			}
			orderService.addItem(order, item_id);
		}
		LOGGER.info("Order created");
		return order;
	}
	
	// DELETEITEM
	public void deleteItem() {
		LOGGER.info("Please enter an order id");
		Long order_id = Long.valueOf(getInput());
		LOGGER.info("What item would you like to delete?");
		List<Item> items = orderService.retrieveAllItems();
		for(Item item : items) {
			LOGGER.info(item.toString());
		}
		LOGGER.info("Use the item's id to select it");
		Long item_id = Long.valueOf(getInput());
		orderService.deleteItem(order_id, item_id);
		LOGGER.info("Item deleted");
	}
	
	// CALCULATE
	public double calculate() {
		LOGGER.info("Please enter an order id:");
		Long order_id = Long.valueOf(getInput());
		double cost = orderService.calculate(order_id);
		LOGGER.info("This order costs: " + cost);
		return cost;
	}
	
	// READITEMS
	public List<Item> readItems() {
		LOGGER.info("Please enter an order id:");
		Long order_id = Long.valueOf(getInput());
		List<Item> items = orderService.readItems(order_id);
		for(Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}
	
	//ADD
	public Item addItem() {
		LOGGER.info("Please enter and order id:");
		Long order_id = Long.valueOf(getInput());
		LOGGER.info("What item would you like to add?");
		List<Item> items = orderService.retrieveAllItems();
		for(Item item : items) {
			LOGGER.info(item.toString());
		}
		LOGGER.info("Use the item's id to select it");
		Long item_id = Long.valueOf(getInput());
		return orderService.addItem(order_id, item_id);
	}
	
	
	//DELETE
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = Long.valueOf(getInput());
		orderService.delete(id);
		LOGGER.info("Order deleted");
	}

}
