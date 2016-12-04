package com.stockbrokerfrommars.server.util.db;

import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stockbrokerfrommars.server.service.InStockService;
import com.stockbrokerfrommars.server.service.OutStockService;
import com.stockbrokerfrommars.server.service.StockService;
import com.stockbrokerfrommars.server.service.TransactionService;
import com.stockbrokerfrommars.server.service.TxDetailService;
import com.stockbrokerfrommars.server.service.WatchingStockService;

public class DatabaseUtil {
	private static ResourceBundle resource = ResourceBundle.getBundle("com.stockbrokerfrommars.server.config.db");
	private static ApplicationContext context;

	public static StockService getStockService() {
		context = new ClassPathXmlApplicationContext(resource.getString("jdbc.xml"));
		return (StockService) context.getBean("stockService");
	}

	public static WatchingStockService getWatchingStockService() {
		context = new ClassPathXmlApplicationContext(resource.getString("jdbc.xml"));
		return (WatchingStockService) context.getBean("watchingStockService");
	}

	public static InStockService getInStockService() {
		context = new ClassPathXmlApplicationContext(resource.getString("jdbc.xml"));
		return (InStockService) context.getBean("inStockService");
	}

	public static OutStockService getOutStockService() {
		context = new ClassPathXmlApplicationContext(resource.getString("jdbc.xml"));
		return (OutStockService) context.getBean("outStockService");
	}

	public static TxDetailService getTxDetailService() {
		context = new ClassPathXmlApplicationContext(resource.getString("jdbc.xml"));
		return (TxDetailService) context.getBean("txDetailService");
	}

	public static TransactionService getTransactionService() {
		context = new ClassPathXmlApplicationContext(resource.getString("jdbc.xml"));
		return (TransactionService) context.getBean("transactionService");
	}
}
