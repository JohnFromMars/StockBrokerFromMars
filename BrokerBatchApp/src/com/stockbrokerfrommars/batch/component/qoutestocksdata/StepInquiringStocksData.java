package com.stockbrokerfrommars.batch.component.qoutestocksdata;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.batchfrommars.component.BatchComponentII;
import com.batchfrommars.file.LogUtil;
import com.stockbrokerfrommars.batch.service.StockService;
import com.stockbrokerfrommars.batch.util.db.DbServiceFactory;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StepInquiringStocksData extends BatchComponentII {
	private static final String ERROR_MSG = ", stock inquiring fail stock id = ";
	private ResourceBundle resource = ResourceBundle.getBundle("com.stockbrokerfrommars.batch.config.job");
	private Logger logger = LogUtil.setLogger("StepInquiring", resource.getString("sit.qouteStockData.log"), Logger.getLogger("StepInquiring"));
	private StockService stockService = DbServiceFactory.getStockService();
	private ApplicationContext context;
	private int inputStockIdCount = 0;
	private int inquiringCount = 0;
	private int updateCount = 0;
	private int updateFailCount = 0;

	@Override
	protected LinkedList<String> excuteProcess(LinkedList<String> inputList) {
		LinkedList<String> failOuputList = new LinkedList<String>();
		String failOutput = null;
		
		if (inputList.get(INPUT_1) != null) {
			inputStockIdCount++;
			try {
				Stock stock = YahooFinance.get(inputList.get(INPUT_1) + ".tw");

				try {
					stockService.updateStock(stock);
					updateCount++;
					
				} catch (DataAccessException e) {
					logger.warning(e.getCause() +" "+ stock.toString());
					updateCount--;
					updateFailCount++;
					failOutput = inputList.get(INPUT_1);
				}

				System.out.println(stock);
				inquiringCount++;

			} catch (IOException e) {
				logger.warning(e.getClass() + ERROR_MSG + inputList.get(INPUT_1));
				e.printStackTrace();
				failOutput = inputList.get(INPUT_1);
			}
		}

		failOuputList.add(failOutput);
		return failOuputList;
	}

	@Override
	protected void onInit() {
		super.onInit();
		logger.info("------ Step Inquiring started ------");
		context = new ClassPathXmlApplicationContext("/com/stockbrokerfrommars/batch/config/batchapp.xml");
	}

	@Override
	protected void onFinish() {
		super.onFinish();
		logger.info("Input Stock Id Count   = " + inputStockIdCount);
		logger.info("Inquiring Success Count= " + inquiringCount);
		logger.info("Update success         = " + updateCount);
		logger.info("Update failed          = " + updateFailCount);
		logger.info("------ Step Inquiring complelted ------");
		((ClassPathXmlApplicationContext) context).close();
	}

}
