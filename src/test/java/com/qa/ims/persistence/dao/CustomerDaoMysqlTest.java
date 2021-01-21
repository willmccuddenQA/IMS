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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Customer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoMysqlTest {
	
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
			statement.executeUpdate("delete from customers");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
	
	@Test
	public void constuctorTest() {
		CustomerDaoMysql customerDao = new CustomerDaoMysql("user","password");
		assertEquals(customerDao.getJdbcConnectionUrl(),"jdbc:mysql://localhost:3306/ims");
		assertEquals(customerDao.getPassword(),"password");
		assertEquals(customerDao.getUsername(),"user");
	}
	
	@Test
	public void customerDaoGettersTest() {
		CustomerDaoMysql customerDao = new CustomerDaoMysql(jdbcConnectionUrl2,username,password);
		assertEquals(username,customerDao.getUsername());
		assertEquals(password,customerDao.getPassword());
		assertEquals(jdbcConnectionUrl2,customerDao.getJdbcConnectionUrl());
	}
	
	
	@Test
	public void customerDaoSettersTest() {
		CustomerDaoMysql customerDao = new CustomerDaoMysql(username,password,jdbcConnectionUrl2);
		customerDao.setJdbcConnectionUrl("jdbc:mysql://localhost://3305/ims_test");
		customerDao.setPassword("pass");
		customerDao.setUsername("user");
		assertEquals("jdbc:mysql://localhost://3305/ims_test", customerDao.getJdbcConnectionUrl());
		assertEquals("user",customerDao.getUsername());
		assertEquals("pass",customerDao.getPassword());
	}

	@Test
	public void customerDaoReadTest() {
		CustomerDaoMysql customerDao = new CustomerDaoMysql(jdbcConnectionUrl2,username,password);
		Customer customer1 = new Customer("Will", "Williams");
		Customer customer2 = new Customer("John", "Smith");
		Customer customer3 = new Customer("Dave", "Evans");
		Customer savedCustomer1 = new Customer(3L,"Will", "Williams");
		Customer savedCustomer2 = new Customer(4L,"John", "Smith");
		Customer savedCustomer3 = new Customer(5L,"Dave", "Evans");
		customerDao.create(customer1);
		customerDao.create(customer2);
		customerDao.create(customer3);
		List<Customer> customers = customerDao.readAll();
		assertEquals(savedCustomer1,customers.get(0));
		assertEquals(savedCustomer2,customers.get(1));
		assertEquals(savedCustomer3,customers.get(2));
	}
	
	@Test
	public void customerDaoCreateTest() {
		CustomerDaoMysql customerDao = new CustomerDaoMysql(jdbcConnectionUrl2,username,password);
		String first_name = "Will";
		String second_name = "McCudden";
		Customer customer = new Customer(first_name, second_name);
		Customer savedCustomer = new Customer(1L,first_name, second_name);
		customer = customerDao.create(customer);
		assertEquals(savedCustomer,customer);
	}
	
	@Test
	public void customerDaoDeleteTest() {
		CustomerDaoMysql customerDao = new CustomerDaoMysql(jdbcConnectionUrl2,username,password);
		Customer customer = new Customer("Tom","Smith");
		customerDao.create(customer);
		customerDao.delete(2L);
		List<Customer> customers = new ArrayList<>();
		assertEquals(customers,customerDao.readAll());
	}
	
	@Test
	public void customerDaoUpdateTest() {
		CustomerDaoMysql customerDao = new CustomerDaoMysql(jdbcConnectionUrl2,username,password);
		Customer customer = new Customer("James","Jackson");
		customerDao.create(customer);
		Customer updatedCustomer = new Customer(6L,"James","Jones");
		assertEquals(customerDao.update(updatedCustomer),updatedCustomer);
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
