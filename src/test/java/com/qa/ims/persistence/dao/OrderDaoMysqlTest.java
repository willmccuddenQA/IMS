package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDaoMysqlTest {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);

	private static String jdbcConnectionUrl= "jdbc:mysql://34.89.52.122:3306/";
	private static String jdbcConnectionUrl2= "jdbc:mysql://34.89.52.122:3306/ims_test";
	private static String username = "root";
	private static String password = "root";
	
	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl,username, password,"src/test/resources/sql-schema.sql");
		
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl2, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO customers (first_name,last_name) VALUES ('Will','McCudden');");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl2, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders (customer_id,address) VALUES (1,'House');");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl2, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO items (item_name,price) VALUES ('Hat',5.99);");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
	
	@Test
	public void constuctorTest() {
		OrderDaoMysql orderDao = new OrderDaoMysql("user","password");
		assertEquals(orderDao.getJdbcConnectionUrl(),"jdbc:mysql://34.89.52.122:3306/ims");
		assertEquals(orderDao.getPassword(),"password");
		assertEquals(orderDao.getUsername(),"user");
	}
	
	@Test
	public void orderDaoGettersTest() {
		OrderDaoMysql orderDao = new OrderDaoMysql(jdbcConnectionUrl2,username,password);
		assertEquals(username,orderDao.getUsername());
		assertEquals(password,orderDao.getPassword());
		assertEquals(jdbcConnectionUrl2,orderDao.getJdbcConnectionUrl());
	}
	
	@Test
	public void orderDaoSettersTest() {
		OrderDaoMysql orderDao = new OrderDaoMysql(username,password,jdbcConnectionUrl2);
		orderDao.setJdbcConnectionUrl("jdbc:mysql://localhost://3305/ims_test");
		orderDao.setPassword("pass");
		orderDao.setUsername("user");
		assertEquals("jdbc:mysql://localhost://3305/ims_test", orderDao.getJdbcConnectionUrl());
		assertEquals("user",orderDao.getUsername());
		assertEquals("pass",orderDao.getPassword());
	}
	
	
	@Test 
	public void orderDaoCreateTest() {
		OrderDaoMysql orderDao = new OrderDaoMysql(jdbcConnectionUrl2,username,password);
		Order order = new Order(1L, "Second House");
		Order savedOrder = new Order(2L,1L, "Second House");
		orderDao.create(order);
		assertEquals(savedOrder,orderDao.readLatest());
		
	}
	
	@Test 
	public void orderDaoDeleteTest() {
		OrderDaoMysql orderDao = new OrderDaoMysql(jdbcConnectionUrl2,username,password);
		List<Order> savedOrders = new ArrayList<>(Arrays.asList(new Order(1L,1L,"House")));
		orderDao.delete(2L);
		assertEquals(savedOrders,orderDao.readAll());
	}
	
	@Test 
	public void orderDaoDeleteItemTest() {
		OrderDaoMysql orderDao = new OrderDaoMysql(jdbcConnectionUrl2,username,password);
		List<Item> items = new ArrayList<>(Arrays.asList(new Item(1L,"Hat",5.99)));
		orderDao.deleteItem(1L, 1L);
		assertEquals(items,orderDao.readItems(1L));
	}
	
	
	@Test 
	public void orderDaoCalculateTest() {
		OrderDaoMysql orderDao = new OrderDaoMysql(jdbcConnectionUrl2,username,password);
		assertEquals(orderDao.calculate(1L),11.98,0);
	}
	
	
	@Test 
	public void orderDaoAddItemTest() {
		OrderDaoMysql orderDao = new OrderDaoMysql(jdbcConnectionUrl2,username,password);
		Item savedItem = new Item(1L, "Hat", 5.99);
		orderDao.addItem(1L, 1L);
		List<Item> items = orderDao.readItems(1L);
		assertEquals(savedItem,items.get(0));
	}
	
	@Test 
	public void orderDaoAddItemTest2() {
		OrderDaoMysql orderDao = new OrderDaoMysql(jdbcConnectionUrl2,username,password);
		Item savedItem = new Item(1L, "Hat", 5.99);
		orderDao.addItem(new Order(1L,1L,"House"), 1L);
		List<Item> savedItems = new ArrayList<>();
		savedItems.add(savedItem);
		savedItems.add(savedItem);
		List<Item> items = orderDao.readItems(1L);
		assertEquals(savedItems,items);
	}
	
	@Test 
	public void orderDaoRetrieveAllItemsTest() {
		OrderDaoMysql orderDao = new OrderDaoMysql(jdbcConnectionUrl2,username,password);
		List<Item> savedOrders = new ArrayList<>(Arrays.asList(new Item(1L,"Hat",5.99)));
		assertEquals(savedOrders,orderDao.retrieveAllItems());
	}
	
	@Test
	public void testExceptions() {
		OrderDaoMysql orderDao = new OrderDaoMysql("",username,password);
		Order order = new Order(1L,"House");
		assertNull(orderDao.readLatest());
		assertEquals(new ArrayList<>(),orderDao.readAll());
		assertNull(orderDao.create(order));
		assertEquals(new ArrayList<>(),orderDao.readItems(1L));
		assertEquals(new ArrayList<>(),orderDao.retrieveAllItems());
	}
	
	@AfterClass
	public static void shutdown() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl2, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DROP SCHEMA ims_test");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
	
	
	
	
	
	
}
