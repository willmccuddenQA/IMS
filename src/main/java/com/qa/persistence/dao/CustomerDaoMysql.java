package com.qa.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.controller.CustomerController;
import com.qa.persistence.domain.Customer;
import com.qa.utils.Config;

public class CustomerDaoMysql implements Dao<Customer> {
	
	public static final Logger logger = Logger.getLogger(CustomerController.class);
	
	public List<Customer> readAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims", Config.username, Config.password)) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from customers");
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String firstName = resultSet.getString("first_name");
				String surname = resultSet.getString("surname");
				Customer customer = new Customer(id, firstName, surname);
				customers.add(customer);
			}
		} catch (Exception e) {
			
		}
		return customers;
	}

	public void create(Customer customer) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims", Config.username, Config.password)){
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into customers(first_name, surname) values('" + customer.getFirstName() + "','" + customer.getSurname()+"')");
		} catch (Exception e) {
			
		} 
	}

	public void update(long id, Customer customer) {

	}

	public void delete(Customer customer) {

	}




}
