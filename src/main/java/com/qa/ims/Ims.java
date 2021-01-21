package com.qa.ims;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderActions;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.dao.ItemDaoMysql;

import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.services.CustomerServices;
import com.qa.ims.services.OrderServices;
import com.qa.ims.services.ItemServices;
import com.qa.ims.utils.Utils;

public class Ims {

	public static final Logger LOGGER = Logger.getLogger(Ims.class);

	public void imsSystem() {
		LOGGER.info("What is your username");
		String username = Utils.getInput();
		LOGGER.info("What is your password");
		String password = Utils.getInput();

		init(username, password);
		boolean loop = true;
		boolean loop2 = true;
		while (loop) {
			Domain domain = chooseDomain();
			if (domain == Domain.ORDER) {
				while (loop2) {
					LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");
					OrderActions.printActions();
					OrderActions action = OrderActions.getAction();
					OrderController orderController = new OrderController(
							new OrderServices(new OrderDaoMysql(username, password)));
					doOrderAction(orderController, action);
					if(action == OrderActions.RETURN) {
						loop2 = false;
					}
					System.out.println();
				}
				loop2 = true;
			}
			
			else if(domain == Domain.STOP){
				LOGGER.info("Closing application");
				loop = false;
			}

			else {
				while (loop2) {
					LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");
					Action.printActions();
					Action action = Action.getAction();
					switch (domain) {
					case CUSTOMER:
						CustomerController customerController = new CustomerController(
								new CustomerServices(new CustomerDaoMysql(username, password)));
						doAction(customerController, action);
						if(action == Action.RETURN) {
							loop2 = false;
						}
						break;
					case ITEM:
						ItemController itemController = new ItemController(
								new ItemServices(new ItemDaoMysql(username, password)));
						doAction(itemController, action);
						if(action == Action.RETURN) {
							loop2 = false;
						}
						break;
					default:
						LOGGER.info("default");
						break;
					}
					System.out.println();
				}
				loop2 = true;
			} 
		}

	}
	
	public Domain chooseDomain() {
		LOGGER.info("Which entity would you like to use?");
		Domain.printDomains();
		return Domain.getDomain();
	}
	
	public void doOrderAction(OrderController orderController, OrderActions action) {
		switch (action) {
		case ADD:
			orderController.addItem();
			break;
		case DELETEITEM:
			orderController.deleteItem();
			break;
		case CALCULATE:
			orderController.calculate();
			break;
		case DELETE:
			orderController.delete();
			break;
		case READITEMS:
			orderController.readItems();
			break;
		case CREATE:
			orderController.create();
			break;
		case READ:
			orderController.readAll();
			break;
		case RETURN:
			break;
		default:
			LOGGER.info("Default");
			break;
		}
	}

	public boolean doAction(CrudController<?> crudController, Action action) {
		switch (action) {
		case CREATE:
			crudController.create();
			return true;
		case READ:
			crudController.readAll();
			return true;
		case UPDATE:
			crudController.update();
			return true;
		case DELETE:
			crudController.delete();
			return true;
		case RETURN:
			return false;
		default:
			LOGGER.info("Default");
			return false;
		}
	}

	/**
	 * To initialise the database schema. DatabaseConnectionUrl will default to
	 * localhost.
	 * 
	 * @param username
	 * @param password
	 */
	public void init(String username, String password) {
		init("jdbc:mysql://localhost:3306/", username, password, "src/main/resources/sql-schema.sql");
	}
 
	public String readFile(String fileLocation) {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(fileLocation));) {
			String string;
			while ((string = br.readLine()) != null) {
				stringBuilder.append(string);
				stringBuilder.append("\r\n");
			}
		} catch (IOException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);
			}
			LOGGER.error(e.getMessage());
		}
		return stringBuilder.toString();
	}

	/**
	 * To initialise the database with the schema needed to run the application
	 */
	public void init(String jdbcConnectionUrl, String username, String password, String fileLocation) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				BufferedReader br = new BufferedReader(new FileReader(fileLocation));) {
			String string;
			while ((string = br.readLine()) != null) {
				try (Statement statement = connection.createStatement();) {
					statement.executeUpdate(string);
				}
			}
		} catch (SQLException | IOException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);
			}
			System.out.println("Here");
			LOGGER.error(e.getMessage());
		}
	}

}
