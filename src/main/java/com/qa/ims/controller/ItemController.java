package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.services.ItemServices;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {
	
	public static final Logger LOGGER = Logger.getLogger(ItemController.class);
	
	private CrudServices<Item> itemService;
	
	public ItemController(CrudServices<Item> itemServices) {
		this.itemService = itemServices;
	}
	
	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<Item> readAll() {
		List<Item> items = itemService.readAll();
		for(Item item: items) {
			LOGGER.info(item.toString());
		}
		return items;
	}
	 
	@Override
	public Item create() {
		LOGGER.info("Please enter the item's name");
		String name = getInput();
		LOGGER.info("Please enter the item's price");
		double price = Double.valueOf(getInput());
		Item item = itemService.create(new Item(name,price));
		LOGGER.info("Item created");
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = Long.valueOf(getInput());
		LOGGER.info("Please enter a name");
		String name = getInput();
		LOGGER.info("Please enter the item's new price");
		double price = Double.valueOf(getInput());
		Item item = itemService.update(new Item(id, name,price));
		LOGGER.info("Item Updated");
		return item;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = Long.valueOf(getInput());
		itemService.delete(id);
		LOGGER.info("Item deleted");
	}
	
}
