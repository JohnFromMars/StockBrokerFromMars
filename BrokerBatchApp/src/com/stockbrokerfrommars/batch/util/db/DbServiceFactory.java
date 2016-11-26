package com.stockbrokerfrommars.batch.util.db;

import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stockbrokerfrommars.batch.service.StockService;

public class DbServiceFactory {
	private static ResourceBundle resource = ResourceBundle.getBundle("com.stockbrokerfrommars.batch.config.db");
	private static ApplicationContext context;

	public static StockService getStockService() {
		context = new ClassPathXmlApplicationContext(resource.getString("jdbc.xml"));
		return (StockService) context.getBean("stockService");
	}
}
