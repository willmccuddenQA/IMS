package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	
	public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	
	private CrudServices<Order> orderService;
	
	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}
	
	Long getInputLong() {
		return Utils.getInputLong();
	}
	
	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for(Order order: orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter a customer_id");
		Long customer_id = getInputLong();
		LOGGER.info("Please enter the address");
		String address = getInput();
		Order order = orderService.create(new Order(customer_id, address));
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public Order update() {
		// add item to order so adding an orderline is needed
		return null;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = Long.valueOf(getInput());
		orderService.delete(id);
		LOGGER.info("Order deleted");
	}

}
