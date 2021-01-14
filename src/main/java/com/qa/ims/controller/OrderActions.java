package com.qa.ims.controller;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum OrderActions {
	
	ADD("Add item to order"), DELETEITEM("Delete item from order"), CALCULATE("Calculate order cost"),
	DELETE("Removes an order from the database"), READITEMS("Read items from order"), 
	CREATE("Add new order to database"), READ("Read all orders from database"), RETURN("return to domain selection");
	
	public static final Logger LOGGER = Logger.getLogger(Action.class);
	
	private String description;
	
	private OrderActions() {
		
	}
	
	OrderActions(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return this.name() + ": " + this.description;
	}
	
	public static void printActions() {
		for(Action action : Action.values()) {
			LOGGER.info(action.getDescription());
		}
	}
	
	public static OrderActions getAction() {
		OrderActions action;
		while(true) {
			try {
				action = OrderActions.valueOf(Utils.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e){
				LOGGER.error("Invalid selection please try again");
			}
		}
		return action;
	}
}
