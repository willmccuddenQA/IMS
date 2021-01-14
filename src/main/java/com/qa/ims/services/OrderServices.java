package com.qa.ims.services;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;

public class OrderServices  {

	private OrderDaoMysql orderDao;
	
	public OrderServices(OrderDaoMysql orderDao) {
		this.orderDao = orderDao;
	}
	
	public List<Order> readAll() {
		return orderDao.readAll();
	}

	public Order create(Order order) {
		return orderDao.create(order);
	}

	public void deleteItem(Order order, Item item) {
		orderDao.deleteItem(order, item);
	}
	
	public double calculate(Order order) {
		return orderDao.calculate(order);
	}
	
	public List<Item> readItems(Order order){
		return orderDao.readItems(order);
	}
	
	public void addItems(Order order, Item item) {
		orderDao.addItems(order, item);
	}
	
	public void addItems(Order order, Long item_id) {
		orderDao.addItems(order, item_id);
	}

	public void delete(Long id) {
		orderDao.delete(id);
	}
	
	public List<Item> retrieveAllItems() {
		return orderDao.retrieveAllItems();
	}

}
