package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;

public class OrderDaoMysql {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;
	
	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}
	
	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("order_id");
		Long customer_id = resultSet.getLong("customer_id");
		String address = resultSet.getString("address");
		return new Order(order_id, customer_id, address);
	}
	
	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public List<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders");) {
			ArrayList<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(orderFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order create(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into orders(customer_id, address) values('" + order.getCustomer_id()
					+ "','" + order.getAddress() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders where order_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orderline where order_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	public void deleteItem(Order order, Item item) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();){
				statement.executeQuery("delete from orderline where order_id = '"+
				order.getOrder_id()+"' and item_id = '" + item.getItem_id() + "' limit 1"); 
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
	
	public void deleteItem(Long order_id, Long item_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();){
				statement.executeQuery("delete from orderline where order_id = '"+
				order_id+"' and item_id = '" + item_id + "' limit 1"); 
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	public double calculate(Long order_id) {
		List<Item> items = readItems(order_id);
		double total = 0;
		for(Item item: items) {
			total+= item.getPrice();
		}
		return total;
	}
	
	public Item itemFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("item_id");
		String name = resultSet.getString("item_name");
		double price = resultSet.getDouble("price");
		return new Item(id,name,price);
	}
	
	public List<Item> readItems(Long order_id) {
		ArrayList<Long> itemIds = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orderline where order_id = '"+
				order_id+"'");) {
			while (resultSet.next()) {
				itemIds.add(resultSet.getLong("item_id"));
			}
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		ArrayList<Item> items = new ArrayList<>();
		for(int i = 0; i < itemIds.size(); i++) {
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from items where item_id = '"+
					itemIds.get(i)+"'");) {
				while (resultSet.next()) {
					items.add(itemFromResultSet(resultSet));
				}
			} catch (SQLException e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		}
		return items;
	}
	
	public Item addItem(Long order_id, Long item_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
				ResultSet resultSet = statement.executeQuery("insert into orderline(order_id, item_id) values('" + order_id
					+ "','" + item_id + "')");
				while (resultSet.next()) {
					return itemFromResultSet(resultSet);
				}
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public void addItem(Order order, Long item_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into orderline(order_id, item_id) values('" + order.getOrder_id()
					+ "','" + item_id + "')");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	public List<Item> retrieveAllItems() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();){
			ArrayList<Item> items = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery("select * from items"); 
			while(resultSet.next()) {
				items.add(itemFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

}
