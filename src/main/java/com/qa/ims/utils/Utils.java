package com.qa.ims.utils;

import java.util.Scanner;

public class Utils {

	private final Scanner scanner;

	private Utils() {
		scanner = new Scanner(System.in);
	}

	private static Utils instance;

	public static Utils getInstance() {
		if (instance == null) {
			instance = new Utils();
		}
		return instance;
	}

	public String getInput() {
		return scanner.nextLine();
	}

}
