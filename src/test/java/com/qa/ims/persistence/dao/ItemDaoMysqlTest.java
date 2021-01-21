package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;

public class ItemDaoMysqlTest {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);

	private static String jdbcConnectionUrl= "jdbc:mysql://localhost:3306/";
	private static String jdbcConnectionUrl2= "jdbc:mysql://localhost:3306/ims_test";
	private static String username = "root";
	private static String password = "root";
	
	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl,username, password,"src/test/resources/sql-schema.sql");
	}	
	
	@Before
	public void setup() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl2, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from items");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
	
	@Test
	public void constuctorTest() {
		ItemDaoMysql itemDao = new ItemDaoMysql("root","root");
		assertEquals(itemDao.getJdbcConnectionUrl(),"jdbc:mysql://localhost:3306/ims");
		assertEquals(itemDao.getPassword(),"root");
		assertEquals(itemDao.getUsername(),"root");
	}
	
	@Test
	public void itemDaoGettersTest() {
		CustomerDaoMysql customerDao = new CustomerDaoMysql(jdbcConnectionUrl2,username,password);
		assertEquals(username,customerDao.getUsername());
		assertEquals(password,customerDao.getPassword());
		assertEquals(jdbcConnectionUrl2,customerDao.getJdbcConnectionUrl());
	}
	
	
	@Test
	public void itemDaoSettersTest() {
		CustomerDaoMysql customerDao = new CustomerDaoMysql(username,password,jdbcConnectionUrl2);
		customerDao.setJdbcConnectionUrl("jdbc:mysql://localhost://3305/ims_test");
		customerDao.setPassword("pass");
		customerDao.setUsername("user");
		assertEquals("jdbc:mysql://localhost://3305/ims_test", customerDao.getJdbcConnectionUrl());
		assertEquals("user",customerDao.getUsername());
		assertEquals("pass",customerDao.getPassword());
	}
	
	@Test
	public void itemDaoReadTest() {
		ItemDaoMysql itemDao = new ItemDaoMysql(jdbcConnectionUrl2,username,password);
		Item item1 = new Item("Hat", 5.99);
		Item item2 = new Item("Pen", 0.99);
		Item item3 = new Item("Phone", 125.50);
		Item savedItem1 = new Item(3L,"Hat", 5.99);
		Item savedItem2 = new Item(4L,"Pen", 0.99);
		Item savedItem3 = new Item(5L,"Phone", 125.50);
		itemDao.create(item1);
		itemDao.create(item2);
		itemDao.create(item3);
		List<Item> items = itemDao.readAll();
		assertEquals(savedItem1,items.get(0));
		assertEquals(savedItem2,items.get(1));
		assertEquals(savedItem3,items.get(2));
	}
	
	@Test
	public void itemDaoCreateTest() {
		ItemDaoMysql itemDao = new ItemDaoMysql(jdbcConnectionUrl2,username,password);
		String name = "Hat";
		double price = 6.33;
		Item item = new Item(name, price);
		Item savedItem = new Item(1L,name, price);
		item = itemDao.create(item);
		assertEquals(savedItem,item);
	}
	
	@Test
	public void itemDaoDeleteTest() {
		ItemDaoMysql itemDao = new ItemDaoMysql(jdbcConnectionUrl2,username,password);
		Item item = new Item("Plates",12.50);
		itemDao.create(item);
		Item updatedItem = new Item(2L,"Plates",12.50);
		itemDao.delete(2L);
		List<Item> items = new ArrayList<>();
		assertEquals(items,itemDao.readAll());
	}
	
	@Test
	public void itemDaoUpdateTest() {
		ItemDaoMysql itemDao = new ItemDaoMysql(jdbcConnectionUrl2,username,password);
		Item item = new Item("Light bulb",5.60);
		itemDao.create(item);
		Item updatedItem = new Item(6L,"Light bulb",5.60);
		assertEquals(itemDao.update(updatedItem),updatedItem);
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
