package com.qa.ims.persistence.domain;
import java.text.SimpleDateFormat;

public class Order {
	
	private Long order_id;
	private Long customer_id;
	private String address;
	
	public Order(Long order_id, Long customer_id, String address) {
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.address = address;
	}

	public Order(Long customer_id, String address) {
		this.customer_id = customer_id;
		this.address = address;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", customer_id=" + customer_id + ", address=" + address + "]";
	}
	
	

	
	
	
	
	

}
