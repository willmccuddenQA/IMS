package com.qa.ims.persistence.domain;

public class Item {
	private Long item_id;
	private String name;
	
	public Item(Long item_id, String name) {
		this.item_id = item_id;
		this.name = name;
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
