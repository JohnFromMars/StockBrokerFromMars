package com.stockbrokerfrommars.server.component;

import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.springframework.dao.DataAccessException;

import com.batchfrommars.component.BatchComponentII;
import com.batchfrommars.file.LogUtil;
import com.stockbrokerfrommars.server.bean.DatabaseOrder;
import com.stockbrokerfrommars.server.service.StockService;
import com.stockbrokerfrommars.server.util.db.DatabaseUtil;

/**
 * DatabaseComponent's job is updating stock price and insert transaction details
 * in database by receiving DatabaseOrder from DB_QUE
 * 
 * @author Yj
 *
 */
public class DatabaseComponent extends BatchComponentII {
	private static final int DB_QUE = 0;
	private Logger logger;
	private ResourceBundle resource;
	private StockService stockService;
	private int count = 0;

	@Override
	protected void onInit() {
		super.onInit();
		resource = ResourceBundle.getBundle("com.stockbrokerfrommars.server.config.serverapp");
		logger = LogUtil.setLogger("DatabaseComponent", resource.getString("dev.app.log"), Logger.getLogger("DatabaseComponent"));
		stockService = DatabaseUtil.getStockService();
	}

	@Override
	protected LinkedList<String> excuteProcess(LinkedList<String> inputList) {
		if (inputList.get(DB_QUE) != null) {
			DatabaseOrder order = new DatabaseOrder();
			order.parse(inputList.get(DB_QUE));
			executeOrder(order);
			count++;
		}
		return null;
	}

	@Override
	protected void onFinish() {
		super.onFinish();
		logger.info("---database report---");
		logger.info("database total comand reicved : " + count);
		logger.info("----------------------");

	}

	/**
	 * Execute the DatabaseOrder by different operate type
	 * 1. UPDATE_CURRENT_PRICE = update the stock price in database
	 * 2. INSERT_TX_DETAIL = insert a transaction detail into database;
	 * 
	 * @param order
	 */
	private void executeOrder(DatabaseOrder order) {
		switch (order.getOperateType()) {
		case DatabaseOrder.UPDATE_CURRENT_PRICE:
			System.out.println(order);
			updateCurrentPrice(order);
			break;

		case DatabaseOrder.INSERT_TX_DETAIL:
			insertTxDetail(order);
			break;

		default:
			break;
		}

	}

	/**
	 * Update stock price by using stockService
	 * 
	 * @param order
	 */
	private void updateCurrentPrice(DatabaseOrder order) {
		try {
			stockService.updatePrice(order.getStockId(), order.getCurrentPrice());
		} catch (DataAccessException e) {
			logger.warning(e.getClass() + " : " + e.getMessage());
		}
	}
	
	/**
	 * Insert a transaction detail into database by using TxDetailService
	 * 
	 * @param order
	 */
	private void insertTxDetail(DatabaseOrder order){
		System.out.println("insert tx detail!");
	}
}
