package com.stockbrokerfrommars.server.util.db;

import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stockbrokerfrommars.server.service.StockService;
import com.stockbrokerfrommars.server.service.WatchingStockService;



public class DatabaseUtil {
	private static ResourceBundle resource = ResourceBundle.getBundle("com.stockbrokerfrommars.server.config.db");
	private static ApplicationContext context;

	public static StockService getStockService() {
		context = new ClassPathXmlApplicationContext(resource.getString("jdbc.xml"));
		return (StockService) context.getBean("stockService");
	}
	
	
	public static WatchingStockService getWatchingStockService(){
		context= new ClassPathXmlApplicationContext(resource.getString("jdbc.xml"));
		return (WatchingStockService)context.getBean("watchingStockService");
	}
	
	
}
