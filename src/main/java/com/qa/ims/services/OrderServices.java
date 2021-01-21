package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.OrderDaoMysql;
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
	
	public void deleteItem(Long order_id, Long item_id) {
		orderDao.deleteItem(order_id, item_id);
	}
	
	public double calculate(Long order_id) {
		return orderDao.calculate(order_id);
	}
	
	public List<Item> readItems(Long order_id){
		return orderDao.readItems(order_id);
	}
	
	public void addItem(Long order_id, Long item_id) {
		orderDao.addItem(order_id, item_id);
	}
	
	public void addItem(Order order, Long item_id) {
		orderDao.addItem(order, item_id);
	}

	public void delete(Long id) {
		orderDao.delete(id);
	}
	
	public List<Item> retrieveAllItems() {
		return orderDao.retrieveAllItems();
	}

}
