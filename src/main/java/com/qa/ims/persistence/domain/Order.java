package com.qa.ims.persistence.domain;
import java.text.SimpleDateFormat;

public class Order {
	
	private int order_id;
	private int customer_id;
	private String address;
	
	public Order(int order_id, int customer_id, String address) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.address = address;
	}

	public Order(int customer_id, String address) {
		super();
		this.customer_id = customer_id;
		this.address = address;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	

}
