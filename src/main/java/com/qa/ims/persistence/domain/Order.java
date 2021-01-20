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
		return "order_id=" + order_id + ", customer_id=" + customer_id + ", address=" + address;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}
	
	

	
	
	
	
	

}
