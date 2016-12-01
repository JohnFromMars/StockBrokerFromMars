package com.stockbrokerfrommars.batch.job;

import java.util.ResourceBundle;
import java.util.logging.Logger;

public abstract class Job {

	protected static ResourceBundle resource;
	protected static Logger logger;

	public static void main(String[] args) {
		setResource();
		setLogger();

	}

	protected static void setLogger() {

	}

	protected static void setResource() {

	}

	public Job() {
		super();
	}

}