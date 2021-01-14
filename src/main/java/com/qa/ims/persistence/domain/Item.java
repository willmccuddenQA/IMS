package com.qa.ims.persistence.domain;

public class Item {
	private Long item_id;
	private String name;
	private double price;
	
	public Item(Long item_id, String name, double price) {
		this.item_id = item_id;
		this.name = name;
		this.price = price;
	}
	
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", name=" + name + "]";
	}
}
